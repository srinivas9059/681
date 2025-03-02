package umbcs681.hw01.fs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import umbcs681.hw01.util.FileCrawlingVisitor;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSystemTest {
    private static FileCrawlingVisitor fileCrawlingVisitor;

    @BeforeAll
    public static void setUp() {
        FileSystem fileSystem = TestFixtureInitializer.createFS();

        Directory umbcs680 = fileSystem.getRootDirs().getFirst();

        fileCrawlingVisitor = new FileCrawlingVisitor();
        umbcs680.accept(fileCrawlingVisitor);
    }

    @Test
    public void testFindJavaFilesAfterDate() {
        // Create a cutoff date/time
        LocalDateTime cutoffDateTime = LocalDateTime.of(2024, 11, 13, 17, 15);

        // Find the number of files that were created after the cutoff date and have .java extensions
        long javaFilesAfterCutoff = fileCrawlingVisitor.files()
                .filter(file -> file.getCreationTime().isAfter(cutoffDateTime))
                .filter(file -> file.getName().endsWith(".java"))
                .count();

        // Should be 3 .java files created after 17:15: B.java, ATest.java, BTest.java
        assertEquals(3, javaFilesAfterCutoff);

        // Print the names of these files for verification
        System.out.println("Java files created after " + cutoffDateTime + ":");
        fileCrawlingVisitor.files()
                .filter(file -> file.getCreationTime().isAfter(cutoffDateTime))
                .filter(file -> file.getName().endsWith(".java"))
                .forEach(file -> System.out.println(file.getName() + " - Created at: " + file.getCreationTime()));
    }
}
