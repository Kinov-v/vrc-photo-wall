package tech.vrcat.vrc.photo.wall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.vrcat.vrc.photo.wall.entity.User;

@Mapper
public interface UserMapper {
    @Select("select username,password,nick from user where username=#{username}")
    User selectByUsername(String username);

}
