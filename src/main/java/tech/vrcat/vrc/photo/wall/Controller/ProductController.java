package tech.vrcat.vrc.photo.wall.Controller;

import cn.hutool.core.lang.UUID;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.vrcat.vrc.photo.wall.entity.Product;
import tech.vrcat.vrc.photo.wall.mapper.ProductMapper;
import tech.vrcat.vrc.photo.wall.service.UploadPicService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductMapper mapper;

    @Value("${dirPath}")    //此注解会自动找到application.properties配置文件中的值
    private String dirPath;

    @Autowired
    UploadPicService uploadPicService;

    @RequestMapping("/product/insert")
    public void insert(Product product, MultipartFile picFile) throws IOException {
//        System.out.println("product = " + product + ", picFile = " + picFile);
//        //得到唯一文件名
//        String fileName = picFile.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        fileName = UUID.randomUUID() + suffix;
//        //日期格式化对象
//        SimpleDateFormat f = new SimpleDateFormat("/yyyy/MM/dd/");
//        //new Date()得到的是当前系统时间  /2022/02/28/
//        String datePath = f.format(new Date());
//        File dirFile = new File(dirPath + datePath);
//        if (!dirFile.exists()) {
//            dirFile.mkdirs();//创建文件夹
//        }
//        //得到完整的保存文件的路径
//        String filePath = dirPath + datePath + fileName;
//        //保存文件  异常抛出
//        picFile.transferTo(new File(filePath));
        //把图片的路径添加到作品对象里面
        //,,,,url,created,categoryId
        String name = uploadPicService.upload(picFile);
        product.setUrl(name);
        product.setCreated(new Date());//加不加都行 报错就加
        mapper.insert(product);
    }

    @RequestMapping("/product/select")
    public List<Product> select() {
        return mapper.selectForAdmin();
    }

    @RequestMapping("/product/delete")
    public void delete(int id) {
        //通过id查询图片url
        String url = mapper.selectUrlById(id);
        //得到完整的文件路径
        String filePath = dirPath + url;
        new File(filePath).delete();//删除文件
        mapper.deleteById(id);
    }

    @RequestMapping("/product/selectForIndex")
    public List<Product> selectForIndex() {
        return mapper.selectForIndex();
    }

    @RequestMapping("/product/selectView")
    public List<Product> selectView() {
        return mapper.selectView();
    }

    @RequestMapping("/product/selectLike")
    public List<Product> selectLike() {
        return mapper.selectLike();
    }

    @RequestMapping("/product/selectByCid")
    public List<Product> selectByCid(int cid) {
        return mapper.selectByCid(cid);
    }

    @RequestMapping("/product/selectByWd")
    public List<Product> selectByWd(String wd) {
        return mapper.selectByWd(wd);
    }

    @RequestMapping("/product/selectById")
    public Product selectById(int id, HttpSession session) {
        String info = (String) session.getAttribute("view" + id);
        if (info == null) {
            //浏览量+1
            mapper.updateViewCountById(id);
            session.setAttribute("view" + id, "viewed");
        }
        return mapper.selectById(id);
    }

    @RequestMapping("/product/likeById")
    public int likeById(int id, HttpSession session) {
        //获取曾经点赞成功时保存的数据
        String info = (String) session.getAttribute("like" + id);
        if (info == null) {
            //处理数据库中数据
            mapper.updateLikeCountById(id);
            session.setAttribute("like" + id, "liked");
            return 1;//点赞成功!
        }
        return 0;//已经点过赞了
    }
}
