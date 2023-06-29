package com.kiljaeden.salarysys.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kil'jaeden
 * @Email: pure3306@163.com
 * @Date: 2023/6/28 19:01
 * @Description:
 */
@Data
public class R<T> {

    private Integer code; //状态码

    private String message; //返回状态信息（成功 失败）

    private T data; //返回数据

    private Map<String, Object> map;

    public R() {
    }

    //成功的方法，有data数据
    public static <T> R<T> ok(T data) {
        R<T> R = new R<>();
        if (data != null) {
            R.setData(data);
        }
        R.setCode(200);
        R.setMessage("成功");
        return R;
    }

    public static <T> R<T> ok() {
        R<T> R = new R<>();
        R.setCode(200);
        R.setMessage("成功");
        return R;
    }

    public static <T> R<T> ok(String msg) {
        R<T> R = new R<>();
        R.setMessage(msg);
        R.setCode(200);
        R.setMessage("成功");
        return R;
    }


    //失败的方法,有data数据
    public static <T> R<T> error(T data) {
        R<T> R = new R<>();
        if (data != null) {
            R.setData(data);
        }
        R.setCode(400);
        R.setMessage("失败");
        return R;
    }

    /*无参失败*/
    public static <T> R<T> error() {
        R<T> R = new R<>();
        R.setCode(400);
        R.setMessage("失败");
        return R;
    }

    /*消息失败*/
    public static <T> R<T> error(String message) {
        R<T> R = new R<>();
        R.setMessage(message);
        R.setCode(400);
        return R;
    }

    public R<T> data(T data){
        this.setData(data);
        return this;
    }

    public R<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    public R<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
