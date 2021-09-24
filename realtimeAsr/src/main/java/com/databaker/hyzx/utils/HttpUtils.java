package com.databaker.hyzx.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

import static com.databaker.hyzx.utils.SslUtils.ignoreSsl;

/**
 * @author
 *
 */
public class HttpUtils {

   // private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    private static Logger logger = LoggerFactory.getLogger(com.databaker.hyzx.utils.HttpUtils.class);
    /**
     * 发送HttpGet请求
     * @param url
     * @return
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httpget);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if(httpClient!=null){
                try {
                    // logger.error("httpClient关闭http链接");
                    httpClient.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpGet带参请求
     * @param url
     * @param header
     * @return
     */
    public static String sendGet(String url, Map<String, String> header) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = null;

        //设置头部
        for(Map.Entry entry:header.entrySet()){
//            System.out.println(entry.getKey()+ "###########" + entry.getValue());
            httpGet.setHeader(entry.getKey().toString(),entry.getValue().toString());
        }
//        System.out.println(jsonObject.toString());


//        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            // 创建一个http客户端
            httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httpGet);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {

            if(httpClient!=null){
                try {
                    // logger.error("httpClient关闭http链接");
                    httpClient.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
            try {
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     * @param url
     * @param paramsMap
     * @return
     */
    public static String sendPost(String url, Map<String,String> paramsMap) {
        // 用于接收返回的结果
        String resultData ="";
        CloseableHttpResponse  response = null;
        CloseableHttpClient httpClient = null;
        try {
            HttpPost post = new HttpPost(url);
            List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
            // 迭代Map-->取出key,value放到BasicNameValuePair对象中-->添加到list中
            for (String key : paramsMap.keySet()) {
                pairList.add(new BasicNameValuePair(key, paramsMap.get(key)));
            }
            UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(pairList, "utf-8");
            post.setEntity(uefe);
            // 创建一个http客户端
            httpClient = HttpClientBuilder.create().build();
            // 发送post请求
            response = httpClient.execute(post);
            // 状态码为：200
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                // 返回数据：
                resultData = EntityUtils.toString(response.getEntity(),"UTF-8");
            }else{
                logger.error("状态码{}",response.getStatusLine().getStatusCode());
                throw new RuntimeException("接口连接失败！");
            }
        } catch (Exception e) {
            logger.error("{}",e);
            throw new RuntimeException("接口连接失败！");
        }finally {
            if(httpClient!=null){
                try {
                   // logger.error("httpClient关闭http链接");
                    httpClient.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
            if(response!=null){
                try {
                   // logger.error("response关闭http链接");
                    response.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }

        }
        return resultData;
    }



    /**
     * 发送不带参数的HttpPost请求
     * @param url
     * @return
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }finally {
            if(httpClient!=null){
                try {
                    // logger.error("httpClient关闭http链接");
                    httpClient.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }
            if(response!=null){
                try {
                    // logger.error("response关闭http链接");
                    response.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }

            }

        }
        return result;
    }



    /**
     * 发送设置header  HttpPost请求
     * @param url
     * @return
     */
    public static String sendPostHeader(String url,Map<String,String> headers) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        String result = null;
        try {
            if (!CollectionUtils.isEmpty(headers)){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httppost.setHeader(entry.getKey(), entry.getValue());
                    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                }
            }
            httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }


    public static Object send(String url,String filePath,String creater_id,String creater_name,String hyjl_id,String hy_name,String fileName){
        RestTemplate restTemplate = new RestTemplate();
        //filePath，即文件路径
        //FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        // 此files对应请求接口方法参数变量，需一致，否则对方接口接不到参数
         param.add("filePath", filePath);
         param.add("creater_id", creater_id);
         param.add("creater_name", creater_name);
         param.add("hyjl_id", hyjl_id);
         param.add("hy_name", hy_name);
         param.add("fileName", fileName);

        // 根据restTemplate来发送请求
        Object object = restTemplate.postForObject(url, param, String.class);
        return object;
    }

    /**

     * 发送带json格式数据！get請求方式

     *

     * @param url

     * @param jsonParams {\"from\":\"20150101\","to":"20150111"}

     * @return

     * @throws ServiceException

     */


    public static String requestService(String url ,Map<String,Object> reqmap) {

        JSONObject obj = new JSONObject(reqmap);
        String r = null;
        try {
            r = URLEncoder.encode(obj.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // String reqUrl = "http://192.168.17.35:8888/getParkInfo?jsonString=" + r;
        String reqUrl = url+r;
        System.out.println("请求参数:" + reqUrl);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String res = null;
        HttpGet httpGet = new HttpGet(reqUrl);
        System.out.println("executing request" + httpGet.getRequestLine());
        try {
            client = HttpClients.createDefault();
            response = client.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200) {

                String result = EntityUtils.toString(response.getEntity());

                System.out.println("executing result---连接正常" + result);
                return result;
            } else {
                System.out.println("executing result---服务器连接异常");
            }


        } catch (Exception e) {
            System.out.println("Exception================" + e.toString());
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }


    /**

     * 发送post请求

     * @param url 请求地址

     * @param json json格式字符串

     * @param contentType 这里用 "application/json"

     * @return

     */

    public static String post(String url, String json, String contentType) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        SSLContext sslcontext = null ;
        //采用绕过验证的方式处理https请求
        try {
            sslcontext = createIgnoreVerifySSL();
        }catch (Exception e){
            e.printStackTrace();
        }
        //设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        CloseableHttpClient client = getIgnoeSSLClient();

       // client = (CloseableHttpClient) wrapClient(client);

        HttpPost post = new HttpPost(url);
        //指定报文头Content-type、User-Agent
       /* post.setHeader("Content-type", "application/x-www-form-urlencoded");

        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
*/
        HttpResponse res = null;
        try {
            StringEntity s = new StringEntity(json, "utf-8");

            if (StringUtils.isBlank(contentType)) {
                s.setContentType("application/json");

            }

            s.setContentType(contentType);

            post.setEntity(s);

            res = client.execute(post);

            HttpEntity entity = res.getEntity();

            String str = EntityUtils.toString(entity, "utf-8");

            return str;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static CloseableHttpClient getIgnoeSSLClient() {
        try {
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }

            }).build();

            //创建httpClient
            CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).
                    setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            return client;
        }catch (Exception e){
            e.printStackTrace();
        }
           return null;

    }


    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }


    private static org.apache.http.client.HttpClient wrapClient(HttpClient base) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLSv1");

            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,

                                               String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs,

                                               String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;

                }

            };

            ctx.init(null, new TrustManager[]{tm}, null);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(ctx, new String[]{"TLSv1"}, null,

                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            return httpclient;

        } catch (Exception ex) {
            return null;

        }

    }


}
