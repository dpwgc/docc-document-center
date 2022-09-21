package com.dpwgc.document.center.sdk.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "统一返回模板-DTO")
public class ResultDTO<T> {

    @ApiModelProperty(value = "响应代码")
    protected Integer code;

    @ApiModelProperty(value = "响应信息")
    protected String msg;

    @ApiModelProperty(value = "响应数据")
    protected T data;

    public Integer getCode() {
        return this.code;
    }

    public ResultDTO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public ResultDTO<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultDTO() {
    }

    public T getData() {
        return this.data;
    }

    public ResultDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultDTO<T> getSuccessResult(T v) {
        ResultDTO<T> result = new ResultDTO();
        result.setCode(Code.SUCCESS);
        result.setMsg("success");
        result.setData(v);
        return result;
    }

    public static <T> ResultDTO<T> getFailureResult(String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setCode(Code.ERROR);
        result.setMsg(msg);
        return result;
    }
}
