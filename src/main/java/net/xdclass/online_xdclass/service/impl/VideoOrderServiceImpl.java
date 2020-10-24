package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.exception.XDException;
import net.xdclass.online_xdclass.mapper.EpisodeMapper;
import net.xdclass.online_xdclass.mapper.PlayRecordMapper;
import net.xdclass.online_xdclass.mapper.VideoMapper;
import net.xdclass.online_xdclass.mapper.VideoOrderMapper;
import net.xdclass.online_xdclass.model.entity.Episode;
import net.xdclass.online_xdclass.model.entity.PlayRecord;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName : VideoOrderServiceImpl  //类名
 * @Description :   //描述
 * @Author : shenhengxin  //作者
 * @Date: 2020-10-24 10:13  //时间
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {
    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    /***
     * @author shenhengxin
     * @description 下单操作
     * @Date 10:14 2020/10/24
     * @Param [userId, videoId]
     * @return int
     */
    @Override
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        if(videoOrder != null) return 0;
        //获取视频信息
        Video video = videoMapper.findById(videoId);
        if(video == null) return 0;
        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);
        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());
        int rows = videoOrderMapper.saveOrder(newVideoOrder);
        if(rows == 1) {//生成播放记录
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null) {
                throw new XDException(-1,"视频没有集信息,请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }
        return newVideoOrder.getId();
    }
}
