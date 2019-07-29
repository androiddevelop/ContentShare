package me.codeboy.clipboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态文件配置
 * Created by yuedong.li on 2019-07-19
 */
@Configuration
public class StaticImageConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = new java.io.File(".").getAbsolutePath() + "/images/";
        registry.addResourceHandler("/images/**").addResourceLocations(
                "file:" + path);
    }
}
