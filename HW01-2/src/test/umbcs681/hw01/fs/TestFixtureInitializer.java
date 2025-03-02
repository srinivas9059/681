package umbcs681.hw01.fs;

import java.time.LocalDateTime;

public class TestFixtureInitializer {
    public static FileSystem createFS() {
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 13, 17, 0);
        FileSystem fileSystem = FileSystem.getFileSystem();

        Directory umbcs680 = new Directory(null, "umbcs680", 0, localDateTime.plusMinutes(1));
        fileSystem.appendRootDir(umbcs680);

        Directory src = new Directory(umbcs680, "src", 0, localDateTime.plusMinutes(3));

        Directory main = new Directory(src, "main", 0, localDateTime.plusMinutes(5));
        File a = new File(main, "A.java", 50, localDateTime.plusMinutes(15));
        File b = new File(main, "B.java", 50, localDateTime.plusMinutes(17));

        Directory test = new Directory(src, "test", 0, localDateTime.plusMinutes(7));
        File aTest = new File(test, "ATest.java", 50, localDateTime.plusMinutes(19));
        File bTest = new File(test, "BTest.java", 50, localDateTime.plusMinutes(21));

        File build = new File(umbcs680, "build.xml", 25, localDateTime.plusMinutes(9));
        File ivy = new File(umbcs680, "ivy.xml", 25, localDateTime.plusMinutes(11));
        File readme = new File(umbcs680, "readme.md", 100, localDateTime.plusMinutes(13));

        Link rm = new Link(test, "rm.md", 0, localDateTime.plusMinutes(23), readme);

        return fileSystem;
    }
}
