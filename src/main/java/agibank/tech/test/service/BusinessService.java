package agibank.tech.test.service;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import agibank.tech.test.parser.Parser;

@Component
public class BusinessService {

	private String pathRootProperty = "user.home";

	private String path;
	private String readPath;
    private String writePath;
    
    public BusinessService(String dataPath, String inPath, String outPath) {
    	this.path = dataPath;
    	this.readPath = inPath;
    	this.writePath = outPath;
    }

    public void check() throws Exception {
        String homeDir = System.getProperty(pathRootProperty);

        path = homeDir.concat(path);
        readPath = homeDir.concat(readPath);
        writePath =  homeDir.concat(writePath);
        
        verifyDirectory(path);
        verifyDirectory(readPath);
        verifyDirectory(writePath);

        System.out.println("Start create listener");
        ListenerService listener = new ListenerService();

        Parser parser = new Parser(writePath);
        FileService readerService = new FileService(parser);
        listener.register(readerService);
        System.out.println("Listener registred");

        try {
        	listener.run(readPath);
        } catch (InterruptedException e) {
            System.out.println("Error running the listener");
        }
    }
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	private void verifyDirectory(String path) {
		File directory = new File(path);
        if (!directory.exists()) {
        	directory.mkdir();
        }
	}
}