package net.xdclass.online_xdclass.model.request;

/**
 * @ClassName : LoginRequest  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 21:22  //时间
 */
public class LoginRequest {
    private String phone;
    private String pwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
