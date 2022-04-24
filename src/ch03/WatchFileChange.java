package ch03;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

/**
 * @author Singh.Akash
 *
 */
public class WatchFileChange {
	public static void main(String[] args) throws IOException, InterruptedException {
		// Watching a file change, can watch configuration files, system resources, etc
		final Path path = Paths.get(".");
		final WatchService watchService = path.getFileSystem().newWatchService();

		path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

		System.out.println("Below files have been modified:");
		final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
		if (watchKey != null) {
			watchKey.pollEvents().stream().forEach(event -> System.out.println(event.context()));
		}
	}
}
