package tech.vrcat.vrc.photo.wall.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tech.vrcat.vrc.photo.wall.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> select();

    @Insert("insert into category values(null,#{name})")
    void insert(Category category);

    @Delete("delete from category where id=#{id}")
    void deleteById(int id);
}
