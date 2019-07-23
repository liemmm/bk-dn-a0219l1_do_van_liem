package com.lmao.configs;

import com.lmao.controllers.MultipartFileInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final MultipartFileInterceptor multipartFileInterceptor;

    @Autowired
    public WebMvcConfig(MultipartFileInterceptor multipartFileInterceptor) {
        this.multipartFileInterceptor = multipartFileInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.multipartFileInterceptor)
                .addPathPatterns("/image/upload-file");
    }
}
