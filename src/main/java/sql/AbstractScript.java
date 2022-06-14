package sql;

import utils.Resources;
import utils.ScriptRunner;

import java.time.Duration;
import java.time.LocalDateTime;

import static file.BatchConfig.*;

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
            System.out.println(Thread.currentThread().getName() + " : run " + DIRECTORY_PATH + fileName + index + ".sql：begin...");
            LocalDateTime begin = LocalDateTime.now();
            System.out.println("[runScript] " + "semaphore:" + semaphore.availablePermits());
            semaphore.acquire();
            ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
            scriptRunner.setSendFullScript(true);
            //scriptRunner.setLogWriter(null);
            scriptRunner.runScript(Resources.getFileAsReader(DIRECTORY_PATH + fileName + index + ".sql"));
            scriptRunner.closeConnection();
            LocalDateTime end = LocalDateTime.now();
            System.out.println(Thread.currentThread().getName() + " : run " + DIRECTORY_PATH + fileName + index + ".sql：successful. takes " + Duration.between(begin, end).toMillis() + "ms.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
