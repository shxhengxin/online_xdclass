package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.domain.Video;
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
}
