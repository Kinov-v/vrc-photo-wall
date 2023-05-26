package tech.vrcat.vrc.photo.wall.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.vrcat.vrc.photo.wall.entity.Banner;
import tech.vrcat.vrc.photo.wall.mapper.BannerMapper;
import tech.vrcat.vrc.photo.wall.service.UploadPicService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class BannerController {
    @Autowired
    BannerMapper mapper;

    @Autowired
    UploadPicService uploadPicService;

    @Value("${dirPath}")
    private String dirPath;

    @RequestMapping("/banner/select")
    public List<Banner> select() {
        return mapper.select();
    }

    @RequestMapping("/banner/delete")
    public void delete(int id) {
        //通过id查询图片的url
        String url = mapper.selectUrlById(id);
        //得到图片的完整路径
        String filePath = dirPath + url;
        //删除文件
        new File(filePath).delete();
        mapper.deleteById(id);
    }

    @RequestMapping("/banner/insert")
    public void insert(MultipartFile picFile) throws IOException {
//        String fileName = picFile.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf("."));
//        fileName = UUID.randomUUID() + suffix;
//        File dirFile = new File(dirPath);
//        if (!dirFile.exists()) {
//            dirFile.mkdirs();//创建文件夹
//        }
//        String filePath = dirPath + "/" + fileName;
//        //保存文件  异常抛出
//        picFile.transferTo(new File(filePath));
//        //把数据保存到banner表中
        String name = uploadPicService.upload(picFile);
        Banner b = new Banner();
        b.setUrl(name);
        mapper.insert(b);

    }
}
