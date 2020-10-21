package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.model.entity.User;
import net.xdclass.online_xdclass.exception.XDException;
import net.xdclass.online_xdclass.mapper.UserMapper;
import net.xdclass.online_xdclass.service.UserService;
import net.xdclass.online_xdclass.utils.CommonUtils;
import net.xdclass.online_xdclass.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName : UserServiceImpl  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 18:24  //时间
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int save(Map<String, String> userInfo) {
        User user = parseToUser(userInfo);

        if(user == null) {
            throw new XDException(-1,"参数缺失");
        }
        User userMapperByPhone = findByPhone(userInfo.get("phone"));
        if (userMapperByPhone != null) {
            throw new XDException(-1,"手机号已存在");
        }
        return  userMapper.save(user);

    }

    private User parseToUser(Map<String, String> userInfo) {
        if(userInfo.containsKey("phone") && userInfo.containsKey("pwd") && userInfo.containsKey("name")) {
            User user = new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            user.setCreateTime(new Date());
            user.setPhone(userInfo.get("phone"));

            user.setPwd(CommonUtils.MD5(userInfo.get("pwd")));
            return user;
        }
        return null;
    }

    @Override
    public User findByPhone(String phone) {
        User user = userMapper.findByPhone(phone);
        return user;

    }

    @Override
    public String findByPhoneAndPwd(String phone, String pwd) {
        User user = userMapper.findByPhoneAndPwd(phone,CommonUtils.MD5(pwd));
        if(user == null) return null;
        String token = JWTUtils.geneJsonWebToken(user);
        return token;
    }

    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg() {
        int size = headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
}
