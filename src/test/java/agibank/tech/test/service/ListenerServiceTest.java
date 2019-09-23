package agibank.tech.test.service;

import static org.junit.Assert.assertTrue;

import java.nio.file.NoSuchFileException;

import org.junit.Test;

public class ListenerServiceTest {

    @Test
    public void shouldRegisterListener() throws Exception {
    	ListenerService listener = new ListenerService();
        FileService fileService = new FileService("test.dat");

        listener.register(fileService);

        assertTrue(listener.getListeners().size() == 1);
    }

    @Test
    public void shouldRegisterException() {
        String message = "File cannot be null";
        ListenerService listener = new ListenerService();

        try {
        	listener.register(null);
        } catch (Exception e) {
            assertTrue(message, e.getMessage().equals(message));
        }
    }

    @Test
    public void shouldRunException() {
        String message = "Path cannot be null";
        ListenerService listener = new ListenerService();

        try {
        	listener.run(null);
        } catch (Exception e) {
            assertTrue(e.getMessage().equals(message));
        }
    }

    @Test(expected = NoSuchFileException.class)
    public void shouldRunFileException() throws Exception {
    	ListenerService listener = new ListenerService();
    	listener.run("C:\\test");
    }
}