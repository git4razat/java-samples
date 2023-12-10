package rider;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FileUtils {
	
	public void dumpLineIntoFile(File aInOutputFile, String aInLine)
    {
		try (BufferedWriter writer = Files.newBufferedWriter(
				aInOutputFile.toPath(), (!aInOutputFile.exists()) ? StandardOpenOption.CREATE : StandardOpenOption.APPEND)
			) {
            writer.write(aInLine + "\n");
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
	
	public void deleteFiles(String dirPath) {
		try (Stream<Path> paths = Files.walk(Paths.get(dirPath)).filter(Files::isRegularFile)) {
			paths
	        .forEach(file -> {
	        	try {
					Files.delete(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        });
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
