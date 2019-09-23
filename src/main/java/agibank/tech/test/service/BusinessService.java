package agibank.tech.test.service;

import java.io.File;
import java.io.IOException;

import agibank.tech.test.parser.Parser;

public class BusinessService {

	private String pathRootProperty = "user.home";
	private String path = "\\data";
	private String readPath = path + "\\in";
    private String writePath = path + "\\out";

    public void check() throws Exception{
        String homeDir = System.getProperty(pathRootProperty);

        path = homeDir.concat(path);
        readPath = homeDir.concat(readPath);
        writePath =  homeDir.concat(writePath);
        
        File dataDirectory = new File(path);
        if (!dataDirectory.exists()) {
        	System.out.println("Creating 'data' folder");
        	dataDirectory.mkdir();
        }

        File readDirectory = new File(readPath);
        if (!readDirectory.exists()) {
        	readDirectory.mkdir();
        }

        File writeDirectory = new File(writePath);
        if (!writeDirectory.exists()) {
        	writeDirectory.mkdir();
        }

        System.out.println("Start create listener");
        ListenerService listener = new ListenerService();

        Parser parser = new Parser(writePath);
        FileService readerService = new FileService(parser);
        listener.register(readerService);
        System.out.println("Listener registred");

        try {
        	listener.run(readPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}