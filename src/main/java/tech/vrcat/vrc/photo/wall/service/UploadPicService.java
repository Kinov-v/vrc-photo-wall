package tech.vrcat.vrc.photo.wall.service;

import com.aliyun.oss.OSSClient;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.vrcat.vrc.photo.wall.config.AliyunConfig;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;

@Service
public class UploadPicService {
    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png", ".webp"};

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AliyunConfig aliyunConfig;

    public String upload(MultipartFile multipartFile) {
        // 1. 对上传的图片进行校验: 这里简单校验后缀名
        // 另外可通过ImageIO读取图片的长宽来判断是否是图片,校验图片的大小等。
        // TODO 图片校验
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;  // 只要与允许上传格式其中一个匹配就可以
            }
        }
        // 格式错误, 返回与前端约定的error
        if (!isLegal) {
            return "上传失败!";
        }

        // 2. 准备上传API的参数
        String fileName = multipartFile.getOriginalFilename();
        String filePath = this.getFilePath(fileName);

        // 3. 上传至阿里OSS
        try {
            ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(multipartFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            // 上传失败
            return "上传失败!";
        }

        // 上传成功
//        picUploadResult.setStatus("done");
        // 文件名(即直接访问的完整路径)
        return aliyunConfig.getUrlPrefix() + filePath;
        // uid
//        picUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
//        return picUploadResult;
    }

    /**
     * 上传的目录
     * 目录: 根据年月日归档
     * 文件名: 时间戳 + 随机数
     *
     * @param fileName
     * @return
     */
    private String getFilePath(String fileName) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return "images/" + String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day) + "/" +
                System.currentTimeMillis() + RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(fileName, ".");
    }
}

