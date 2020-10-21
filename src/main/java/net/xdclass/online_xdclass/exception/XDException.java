package net.xdclass.online_xdclass.exception;

import net.xdclass.online_xdclass.utils.JsonData;

/**
 * @ClassName : XDException  //类名
 * @Description : 自定义异常类  //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 15:20  //时间
 */
public class XDException extends RuntimeException{
    private Integer code;
    private String msg;
    public XDException(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
