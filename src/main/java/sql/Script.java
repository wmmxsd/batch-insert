package sql;

import utils.Resources;
import utils.ScriptRunner;

import java.util.concurrent.Callable;

import static file.BatchConfig.CIRCLE;
import static file.BatchConfig.DIRECTORY_PATH;
import static file.BatchConfig.dataSource;
import static file.BatchConfig.semaphore;


public interface Script extends Callable<Boolean> {
    default void runScript(String fileName) {
        for (int index = 0; index < CIRCLE; index++) {
            try {
                System.out.println("[runScript] " + "semaphore:" + semaphore.availablePermits());
                semaphore.acquire();
                ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
                scriptRunner.setSendFullScript(true);
                scriptRunner.setLogWriter(null);
                scriptRunner.runScript(Resources.getFileAsReader(DIRECTORY_PATH + fileName + index + ".sql"));
                scriptRunner.closeConnection();
                System.out.println(Thread.currentThread().getName() + " : run " + DIRECTORY_PATH + fileName + index + ".sql：end");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    };
}
