package agibank.tech.test.service;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ListenerService {
	
	private List<FileService> fileServices;

    public ListenerService() {
    	fileServices = new ArrayList<>();
    }

    public void register(FileService service) throws Exception {
    	if(service == null)
    		throw new Exception("File cannot be null");

        fileServices.add(service);
    }

    public List<FileService> getListeners() {
        return fileServices;
    }

    public void run(String watchPath) throws Exception {
    	if(watchPath == null)
    		throw new Exception("Path cannot be null");

        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(watchPath);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		System.out.println("Service running..");

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                for (FileService listener : fileServices) {
                    new Thread(() -> {
                        listener.readLine(watchPath.concat("\\").concat(event.context().toString()));
                    }).start();

                }
            }
            key.reset();
        }

    }
}
