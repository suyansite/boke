package com.databaker.hyzx.service;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ClientAuthInterceptorService implements ClientInterceptor {
    private final int devId;
    private final String devKey;

    public ClientAuthInterceptorService(int devId, String devKey) {
        this.devId = devId;
        this.devKey = devKey;
    }

    Logger log = LoggerFactory.getLogger(ClientAuthInterceptorService.class);

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

        ClientCall<ReqT, RespT> clientCall = next.newCall(method, callOptions);
        return new ForwardingClientCall<ReqT, RespT>() {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                Metadata.Key<String> token = Metadata.Key.of("x-api-key", Metadata.ASCII_STRING_MARSHALLER);
                long timestamp = System.currentTimeMillis();
                String signature = null;
                try {
                    signature = SignatureService.getSignature(devId, devKey, timestamp);
                } catch (NoSuchAlgorithmException e) {
                    log.error("process error: NoSuchAlgorithmException: " + e);
                } catch (InvalidKeyException e) {
                    log.error("process error: InvalidKeyException: " + e);
                }
                headers.put(token, devId + "," + timestamp + "," + signature);
                super.start(responseListener, headers);
            }

            @Override
            protected ClientCall<ReqT, RespT> delegate() {
                return clientCall;
            }
        };
    }
}