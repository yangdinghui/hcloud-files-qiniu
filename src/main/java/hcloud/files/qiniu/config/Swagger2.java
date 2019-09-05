package hcloud.files.qiniu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/1
 *
 * @author 杨丁辉
 */
@ConditionalOnClass({Docket.class})
@Configuration
@EnableSwagger2
@Profile({"dev"})
public class Swagger2 {
    @Value("${swagger2.basePackage}")
    private String basePackage;
    @Value("${swagger2.enable}")
    private boolean enable;
    @Value("${swagger2.title}")
    private String title;
    @Value("${swagger2.desc}")
    private String desc;
    @Value("${swagger2.termsOfServiceUrl}")
    private String termsOfServiceUrl;
    @Value("${swagger2.version}")
    private String version;

    @Bean
    @ConditionalOnMissingBean(name = {"docketInstance"})
    public Docket docketInstance() { return (new Docket(DocumentationType.SWAGGER_2))
            .apiInfo(apiInfo())
            .enable(this.enable)
            .select()
            .apis(RequestHandlerSelectors.basePackage(this.basePackage))

            .paths(PathSelectors.any())
            .build(); }



    public ApiInfo apiInfo() { return (new ApiInfoBuilder())
            .title(this.title)
            .description(this.desc)
            .termsOfServiceUrl(this.termsOfServiceUrl)
            .version(this.version)
            .build(); }




    public String getBasePackage() { return this.basePackage; }



    public void setBasePackage(String basePackage) { this.basePackage = basePackage; }



    public boolean isEnable() { return this.enable; }



    public void setEnable(boolean enable) { this.enable = enable; }



    public String getTitle() { return this.title; }



    public void setTitle(String title) { this.title = title; }



    public String getDesc() { return this.desc; }



    public void setDesc(String desc) { this.desc = desc; }



    public String getTermsOfServiceUrl() { return this.termsOfServiceUrl; }



    public void setTermsOfServiceUrl(String termsOfServiceUrl) { this.termsOfServiceUrl = termsOfServiceUrl; }



    public String getVersion() { return this.version; }



    public void setVersion(String version) { this.version = version; }
}
