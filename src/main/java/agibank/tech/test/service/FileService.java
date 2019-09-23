package agibank.tech.test.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import agibank.tech.test.parser.IFileParser;

@Component
public class FileService {

    private IFileParser fileParser;
    private BufferedWriter bufferWriter;

    private String extensionAllowed = ".dat";

    public FileService(IFileParser fileParser) {
        this.fileParser = fileParser;
    }

    public FileService(String fileName) throws IOException {
    	bufferWriter = new BufferedWriter(new FileWriter(fileName));
    }

    public void readLine(String fileName) {
        try {
            validateExtension(fileName);

            List<String> lines  = new ArrayList<>();
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            	lines = stream.collect(Collectors.toList());
    		}

            if(lines.size() > 0){
                fileParser.readFile(lines);
                Path p = Paths.get(fileName);
                String file = p.getFileName().toString();
                fileParser.writeFile(file);
            }
        } catch (Exception e) {
            System.out.println("Error reading lines of file");
        }
    }

    public FileService writeLine(String line) throws IOException {
    	bufferWriter.write(line);
    	bufferWriter.write("\r\n");
        return this;
    }

    public void close() throws IOException {
    	bufferWriter.close();
    }

    public void validateExtension(String fileName) throws Exception {
        if(fileName.endsWith(extensionAllowed))
        	return;
        else
            throw new Exception("Invalid extension");
    }
}