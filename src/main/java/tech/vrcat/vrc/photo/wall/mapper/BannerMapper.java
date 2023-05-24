package tech.vrcat.vrc.photo.wall.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.vrcat.vrc.photo.wall.entity.Banner;

import java.util.List;

@Mapper
public interface BannerMapper {
    @Insert("insert into banner values(null,#{url})")
    void insert(Banner banner);

    @Select("select id,url from banner")
    List<Banner> select();

    @Delete("delete from banner where id=#{id}")
    void deleteById(int id);

    @Select("select url from banner where id=#{id}")
    String selectUrlById(int id);
}
