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
        
        verifyDataDirectory();
        verifyReadDirectory();
        verifyWriteDirectory();

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

	private void verifyWriteDirectory() {
		File writeDirectory = new File(writePath);
        if (!writeDirectory.exists()) {
        	writeDirectory.mkdir();
        }
	}

	private void verifyReadDirectory() {
		File readDirectory = new File(readPath);
        if (!readDirectory.exists()) {
        	readDirectory.mkdir();
        }
	}

	private void verifyDataDirectory() {
		File dataDirectory = new File(path);
        if (!dataDirectory.exists()) {
        	System.out.println("Creating 'data' folder");
        	dataDirectory.mkdir();
        }
	}
}