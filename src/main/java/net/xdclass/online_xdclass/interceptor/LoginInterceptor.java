package net.xdclass.online_xdclass.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import net.xdclass.online_xdclass.utils.JWTUtils;
import net.xdclass.online_xdclass.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @ClassName : LoginInterceptor  //类名
 * @Description :  登录拦截器 //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-23 20:25  //时间
 */
public class LoginInterceptor implements HandlerInterceptor {
    /***
     * @author shenhengxin
     * @description 进入到controller之前的方法
     * @Date 20:27 2020/10/23
     * @Param [request, response, handler]
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       try{
           String accesToken = request.getHeader("token");
           if(accesToken == null) {
               accesToken = request.getParameter("token");
           }
           if(!StringUtils.isNotBlank(accesToken)){
               sendJsonMessage(response, JsonData.buildError("登录过期，请重新登录"));
               return false;
           }

           Claims claims = JWTUtils.checkJWT(accesToken);
           if(claims == null) {
               sendJsonMessage(response, JsonData.buildError("登录过期，请重新登录"));
               return  false;
           }
           Integer id = (Integer) claims.get("id");
           String name = (String) claims.get("name");
           request.setAttribute("user_id",id);
           request.setAttribute("name",name);
           return true;
       }catch (Exception e) {
           sendJsonMessage(response, JsonData.buildError("登录过期，请重新登录"));
           return false;
       }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /***
     * @author shenhengxin
     * @description 响应Json数据给前端
     * @Date 20:54 2020/10/23
     * @Param [response, obj]
     * @return void
     */
    public static void sendJsonMessage(HttpServletResponse response,Object obj) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(objectMapper.writeValueAsString(obj));
            printWriter.close();
            response.flushBuffer();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
