package umbcs681.hw01.fs;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import umbcs681.hw01.util.FileCrawlingVisitor;

import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void testFileExtensionStatistics() {
        // Group files based on their extensions and obtain summary statistics about file sizes
        Map<String, IntSummaryStatistics> statisticsByExtension = fileCrawlingVisitor.files()
                .collect(Collectors.groupingBy(
                        file -> {
                            String fileName = file.getName();
                            int lastDotIndex = fileName.lastIndexOf('.');
                            return fileName.substring(lastDotIndex + 1);
                        },
                        Collectors.summarizingInt(File::getSize)
                ));

        // Print statistics for each extension group
        System.out.println("File size statistics by extension:");
        statisticsByExtension.forEach((extension, stats) ->
                System.out.println(extension + " files: " +
                        "count=" + stats.getCount() +
                        ", total=" + stats.getSum() +
                        ", avg=" + stats.getAverage() +
                        ", min=" + stats.getMin() +
                        ", max=" + stats.getMax()));

        // Verify statistics for java files
        IntSummaryStatistics javaStats = statisticsByExtension.get("java");
        assertEquals(4, javaStats.getCount());    // 4 .java files
        assertEquals(200, javaStats.getSum());    // Total size is 200 (4 * 50)
        assertEquals(50.0, javaStats.getAverage()); // Average size is 50
        assertEquals(50, javaStats.getMin());      // Min size is 50
        assertEquals(50, javaStats.getMax());      // Max size is 50

        // Verify statistics for xml files
        IntSummaryStatistics xmlStats = statisticsByExtension.get("xml");
        assertEquals(2, xmlStats.getCount());     // 2 .xml files
        assertEquals(50, xmlStats.getSum());      // Total size is 50 (25 + 25)
        assertEquals(25.0, xmlStats.getAverage());  // Average size is 25

        // Verify statistics for md files
        IntSummaryStatistics mdStats = statisticsByExtension.get("md");
        assertEquals(1, mdStats.getCount());      // 1 .md file
        assertEquals(100, mdStats.getSum());      // Total size is 100
        assertEquals(100.0, mdStats.getAverage()); // Average size is 100
    }
}
