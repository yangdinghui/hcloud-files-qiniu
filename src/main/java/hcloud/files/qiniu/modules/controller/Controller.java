package hcloud.files.qiniu.modules.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2019/9/2
 *
 * @author 杨丁辉
 */
@RestController
@RequestMapping(value = "/",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class Controller {

    @GetMapping(value = "/upload")
    public String upload(){

        return "files";
    }

}
