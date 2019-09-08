package hcloud.files.qiniu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"hcloud.files.qiniu.modules.dao"})
@SpringBootApplication
public class HcloudFilesQiniuApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcloudFilesQiniuApplication.class, args);
	}

}
