package ch03;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Singh.Akash
 *
 */
public class ListFiles {
	public static void main(String[] args) throws IOException {
		Files.list(Paths.get(".")).forEach(System.out::println);
		// Files.list gave closeable stream to iterate over files in given path
		// list() expects a Path instance, that we get from Paths.get()

		System.out.println("-----------------------------------------------------------------------------");

		// to list only subdirectories in the given directory
		Files.list(Paths.get(".")).filter(Files::isDirectory).forEach(System.out::println);

		System.out.println("-----------------------------------------------------------------------------");
		Files.newDirectoryStream(
				Paths.get("D:\\learning\\workspace\\functional-programming\\functional-programming\\src\\ch03"),
				path -> path.toString().endsWith(".java")).forEach(System.out::println);
		// can use DirectoryStream if we are working with large directory

		new File(".").listFiles(File::isHidden);

		// Listing immediate subdirectories using flatmap
		Stream.of(new File(".").listFiles())
				.flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles()))
				.collect(Collectors.toList());
	}
}
