package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User findByPhone(@Param("phone") String phone);
    int save(User user);
}
