package com.databaker.hyzx.utils;


import com.databaker.hyzx.constant.HttpConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


  /*
   *
   * http返回的结构体
   * */
  public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
      put("code", HttpConstant.SUCCESS_CODE);
    }

    public static R error(String msg) {
      return error(HttpConstant.FAIL_CODE, msg);
    }

    public static R error() {
      return error(HttpConstant.FAIL_CODE, "系统错误");
    }


    public static R error(int code, String msg) {
      R r = new R();
      r.put("code", code);
      r.put("msg", msg);
      return r;
    }

    public static R ok(String msg) {
      R r = new R();
      r.put("msg", msg);
      return r;
    }

    public static R ok(Map<String, Object> map) {
      R r = new R();
      r.putAll(map);
      return r;
    }

    public static R ok() {
      return new R();
    }

    @Override
    public R put(String key, Object value) {
      super.put(key, value);
      return this;
    }
  }