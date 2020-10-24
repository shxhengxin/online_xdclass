package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

public interface VideoOrderMapper {
    /***
     * @author shenhengxin
     * @description 查询用户是否购买此商品
     * @Date 10:18 2020/10/24
     * @Param [userId, videoId, state]
     * @return net.xdclass.online_xdclass.model.entity.VideoOrder
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") int userId,
                                              @Param("video_id") int videoId,
                                              @Param("state") int state);

    /***
     * @author shenhengxin
     * @description 下单
     * @Date 10:19 2020/10/24
     * @Param []
     * @return int
     */
    int saveOrder(VideoOrder videoOrder);
}
