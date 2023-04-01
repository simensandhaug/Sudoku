package sudoku.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * A helper class for reading and writing to files. You can use this for your
 * project.
 */
public class FileHelper {

    /**
     * Reads all lines from the file at the given path, or from the resource with
     * the given path. A resource is a file that is contained within the jar file,
     * and is located in the /src/main/resources folder.
     *
     * @param path     The path to the file or resource. If the file is a resource,
     *                 you do not need to include the /src/main/resources part of
     *                 the path
     * @param resource Whether the file is a resource or not
     * @return A list of all lines in the file
     * @throws IOException If the file could not be read
     */
    public static List<String> readLines(String path, boolean resource) throws IOException {
        if (!resource) {
            return Files.readAllLines(Path.of(path));
        }

        try (InputStream resourceAsStream = FileHelper.class.getResourceAsStream(path)) {
            System.out.println(resourceAsStream);
            if (resourceAsStream == null) {
                throw new IOException("Resource not found");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
                return reader.lines().toList();
            }
        }
    }

    /**
     * Write the given lines to the file at the given path. Will overwrite any
     * existing
     * file.
     *
     * @param path  The path
     * @param lines The lines to write
     * @throws IOException If the file could not be written to
     */
    public static void writeLines(String path, List<String> lines) throws IOException {
        Files.write(Path.of(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
    }
}
