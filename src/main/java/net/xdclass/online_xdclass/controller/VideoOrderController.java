package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.model.request.VideoOrderRequest;
import net.xdclass.online_xdclass.service.VideoOrderService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName : VideoOrderController  //类名
 * @Description : 视频下单控制器  //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-23 22:27  //时间
 */
@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;
    /***
     * @author shenhengxin
     * @description 下单接口
     * @Date 22:32 2020/10/23
     * @Param []
     * @return net.xdclass.online_xdclass.utils.JsonData
     */
    @PostMapping("saveOrder")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        int videoOrderId = videoOrderService.save(userId, videoOrderRequest.getVideoId());
        return videoOrderId == 0 ?JsonData.buildError("下单失败"):JsonData.buildSuccess(videoOrderId);
    }
    /***
     * @author shenhengxin
     * @description 订单列表
     * @Date 20:21 2020/10/24
     * @Param [request]
     * @return net.xdclass.online_xdclass.utils.JsonData
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(videoOrderList);
    }
}
