package net.xdclass.online_xdclass.controller;

import net.xdclass.online_xdclass.domain.Video;
import net.xdclass.online_xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName : VideoController  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 13:12  //时间
 */
@RestController
@RequestMapping("video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @GetMapping("list")
    public List<Video> listVideo(){
        List<Video> listVideo = videoService.listVideo();
        return listVideo;
    }
}
