package tech.vrcat.vrc.photo.wall.mapper;

import org.apache.ibatis.annotations.*;
import tech.vrcat.vrc.photo.wall.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product values(null,#{title},#{intro},#{author},#{url},0,0,#{created},#{categoryId})")
    void insert(Product product);

    @Select("select id,title,author,url,view_count,like_count,created from product")
    @Result(column = "view_count", property = "viewCount")
    @Result(column = "like_count", property = "likeCount")
    List<Product> selectForAdmin();

    @Select("select url from product where id=#{id}")
    String selectUrlById(int id);

    @Delete("delete from product where id=#{id}")
    void deleteById(int id);

    @Select("select id,title,url,view_count,like_count from product")
    @Result(column = "view_count", property = "viewCount")
    @Result(column = "like_count", property = "likeCount")
    List<Product> selectForIndex();

    @Select("select id,title,author,url from product order by view_count desc limit 0,4")
    List<Product> selectView();

    @Select("select id,title,author,url from product order by like_count desc limit 0,4")
    List<Product> selectLike();

    @Select("select id,title,url,view_count,like_count from product where category_id=#{cid}")
    @Result(column = "view_count", property = "viewCount")
    @Result(column = "like_count", property = "likeCount")
    List<Product> selectByCid(int cid);

    @Select("select id,title,url,view_count,like_count from product where title like concat('%',#{wd},'%')")
    @Result(column = "view_count", property = "viewCount")
    @Result(column = "like_count", property = "likeCount")
    List<Product> selectByWd(String wd);

    @Select("select * from product where id=#{id}")
    @Result(column = "view_count", property = "viewCount")
    @Result(column = "like_count", property = "likeCount")
    Product selectById(int id);

    @Update("update product set like_count=like_count+1 where id=#{id}")
    void updateLikeCountById(int id);

    @Update("update product set view_count=view_count+1 where id=#{id}")
    void updateViewCountById(int id);
}
