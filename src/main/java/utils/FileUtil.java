package utils;

import java.io.File;
import java.nio.file.Paths;

public class FileUtil {
    public static void deletFile(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File singleFile : files) {
            if (singleFile.isFile()) {
                singleFile.delete();
                continue;
            }
            deletFile(singleFile.getPath());
        }
        file.delete();
    }

    public static void main(String[] args) {
        deletFile(Paths.get("E:\\test").toString());
    }
}
