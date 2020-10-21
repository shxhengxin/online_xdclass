package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.domain.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VideoMapper {
    List<Video> listVideo();
}
