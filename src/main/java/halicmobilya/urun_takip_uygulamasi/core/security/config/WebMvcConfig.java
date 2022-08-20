package halicmobilya.urun_takip_uygulamasi.core.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path materialUploadDir = Paths.get("./images/materials");
        String materialUploadPath = materialUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/images/materials/**").addResourceLocations("file:/" + materialUploadPath + "/");
    }

}
