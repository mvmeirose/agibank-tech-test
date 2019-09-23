package agibank.tech.test.parser;

import java.util.List;

public interface IFileParser {
	    void readFile(List<String> lines);
	    void writeFile(String fileName);
}