package sql;

import file.SqlFile;
import utils.Resources;
import utils.ScriptRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.stream.IntStream;

import static file.BatchConfig.*;
import static file.BatchConfig.semaphore;

public abstract class AbstractScript implements Script {
    private final Integer index;

    public AbstractScript(Integer index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void runScript(String fileName) {
        try {
            /*System.out.println("[runScript] " + "semaphore:" + semaphore.availablePermits());
            semaphore.acquire();*/
            ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.setSendFullScript(true);
            scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getFileAsReader(DIRECTORY_PATH + fileName + index + ".sql"));
            scriptRunner.closeConnection();
            System.out.println(Thread.currentThread().getName() + " : run " + DIRECTORY_PATH + fileName + index + ".sql：end");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //semaphore.release();
        }
    }
}
