package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import net.xdclass.online_xdclass.mapper.VideoMapper;
import net.xdclass.online_xdclass.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : VideoServiceImpl  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-21 13:08  //时间
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;
    @Override
    public List<Video> listVideo() {
        List<Video> videoList = videoMapper.listVideo();
        return videoList;
    }

    @Override
    public List<VideoBanner> listVideoBanner() {
        List<VideoBanner> videoBanners = videoMapper.listVideoBanner();
        return videoBanners;
    }

    @Override
    public Video findDetailById(int videoId) {
        Video video = videoMapper.findDetailById(videoId);
        return video;
    }

}
