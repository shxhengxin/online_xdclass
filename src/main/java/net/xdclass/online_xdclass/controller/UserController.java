package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.service.UserService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName : UserController  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 18:08  //时间
 */
@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {
   @Autowired
    private UserService userService;

    @PostMapping("register")
    public JsonData register(@RequestBody Map<String,String> userInfo) {
        int rows = userService.save(userInfo);
        return  rows == 1?JsonData.buildSuccess():JsonData.buildError("注册失败");
    }
}
