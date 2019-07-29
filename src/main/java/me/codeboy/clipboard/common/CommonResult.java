package me.codeboy.clipboard.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用请求
 * Created by yuedong.li on 2019/5/16
 */
@Data
public class CommonResult<T> implements Serializable {
    /**
     * 是否请求成功
     */
    private String status = "SUCCESS";
    /**
     * 是否请求成功
     */
    private boolean success = true;
    /**
     * 请求失败的时候的信息
     */
    private String message;

    private int code = 0;

    private long version;
    /**
     * 请求成功时的数据
     */
    private T data;

    public CommonResult() {
    }

    private CommonResult(T data) {
        this.data = data;
    }

    public CommonResult(boolean success, T data, String message, long version) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.version = version;
        if (!success) {
            this.status = "FAILED";
        }
    }

    private CommonResult(boolean success, String message) {
        this.success = success;
        if (!success) {
            this.status = "FAILED";
        }
        this.message = message;
    }

    private CommonResult(T data, long version) {
        this.data = data;
        this.version = version;
    }

    public static <T> CommonResult success(T data) {
        return new CommonResult<>(data);
    }

    public static <T> CommonResult success(T data, long version) {
        return new CommonResult<>(data, version);
    }

    public static CommonResult failed(String message) {
        return new CommonResult<>(false, message);
    }

    public static <T> CommonResult failed(T data, String message) {
        return new CommonResult<>(false, data, message, 0);
    }

}
