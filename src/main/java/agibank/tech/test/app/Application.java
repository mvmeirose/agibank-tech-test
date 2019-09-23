package agibank.tech.test.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import agibank.tech.test.service.BusinessService;

@SpringBootApplication
public class Application {

	@Value("${directory.path.data}")
	private String path;

	@Value("${directory.path.in}")
	private String readPath;

	@Value("${directory.path.out}")
    private String writePath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public void execute() throws Exception {
    	BusinessService service = new BusinessService(path, readPath, writePath);
		service.check();
    }
}