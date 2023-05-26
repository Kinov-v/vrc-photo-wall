package tech.vrcat.vrc.photo.wall.config;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于Ali产品的配置 （OSS）
 * @author: Orcas
 * @version:1.0.0
 * @date: 2019/4/28
 */
// @PropertySource("classpath:xxxx.properties") 默认路径下可不加
@Data
@Configuration
public class AliyunConfig {
    // 地域节点
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.bucketName}")
    private String bucketName;
    @Value("${aliyun.oss.urlPrefix}")
    private String urlPrefix;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
}
