package agibank.tech.test.service;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileServiceTest {

    @Test(expected = FileNotFoundException.class)
    public void shouldReadNotFound() throws Exception {
    	FileService service = new FileService("");
    	service.validateExtension("test.dat");
    }

    @Test
    public void shouldReadSuccess() throws Exception {
    	FileService service = new FileService("test.dat");
    	service.validateExtension("test.dat");
    }

    @Test
    public void shouldReadException() throws IOException {
        String message = "Invalid extension";
        FileService service = new FileService("test.txt");

        try {
            service.readLine("test.txt");
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }
}