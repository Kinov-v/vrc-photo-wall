package tech.vrcat.vrc.photo.wall.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.vrcat.vrc.photo.wall.entity.Category;
import tech.vrcat.vrc.photo.wall.mapper.CategoryMapper;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryMapper mapper;

    @RequestMapping("/category/select")
    public List<Category> select(HttpSession session) {
        return mapper.select();
    }

    @RequestMapping("/category/insert")
    public void insert(Category category) {
        mapper.insert(category);
    }

    @RequestMapping("/category/delete")
    public void delete(int id) {
        mapper.deleteById(id);
    }
}
