package agibank.tech.test.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import agibank.tech.test.parser.IFileParser;

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
            try (BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(!line.isEmpty())
                        lines.add(line);
                }
            }

            if(lines.size() > 0){
                fileParser.readFile(lines);
                Path p = Paths.get(fileName);
                String file = p.getFileName().toString();
                fileParser.writeFile(file);
            }
        } catch (Exception e) {
            return;
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