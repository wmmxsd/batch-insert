package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.stream.IntStream;

import static file.BatchConfig.*;

public abstract class AbstractSqlFile implements SqlFile{
    @Override
    public void writeToFile(String fileName) {
        for (int index = 0; index < CIRCLE; index++) {
            Path path = null;
            String sqlFileName = null;
            try {
                synchronized (AbstractSqlFile.class) {
                    if (Files.notExists(Paths.get(DIRECTORY_PATH))) {
                        Files.createDirectory(Paths.get(DIRECTORY_PATH));
                    }
                }
                sqlFileName = DIRECTORY_PATH + fileName + index + ".sql";
                Files.deleteIfExists(Paths.get(sqlFileName));
                path = Files.createFile(Paths.get(sqlFileName));

            } catch (IOException e) {
                e.printStackTrace();
            }
            Path finalPath = path;
            IntStream.range(0, SQL_COUNTS_IN_FILE).forEach(count -> {
                try {
                    Files.write(finalPath, Collections.singletonList(generateSql()), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(Thread.currentThread().getName() + " : " + SQL_COUNTS_IN_FILE + "行sql写入" + sqlFileName + "：end");
        }
    }
}
