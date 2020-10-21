package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.service.VideoService;
import net.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : VideoController  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 13:12  //时间
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    /***
     * @author shenhengxin
     * @description  视频列表
     * @Date 14:18 2020/10/21
     * @Param []
     * @return net.xdclass.online_xdclass.utils.JsonData
     */
    @GetMapping("list")
    public JsonData listVideo(){
        List<Video> listVideo = videoService.listVideo();
        return JsonData.buildSuccess(listVideo);
    }

    @GetMapping("findDetailById")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true) int videoId) {
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }

    /***
     * @author shenhengxin
     * @description 首页轮播图
     * @Date 14:18 2020/10/21
     * @Param []
     * @return net.xdclass.online_xdclass.utils.JsonData
     */
    @GetMapping("banner")
    public JsonData indexBanner() {
        List<VideoBanner> bannerList = videoService.listVideoBanner();

        return  JsonData.buildSuccess(bannerList);
    }
}
