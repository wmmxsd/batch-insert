package sql;

import utils.Resources;
import utils.ScriptRunner;

import static file.BatchConfig.DIRECTORY_PATH;
import static file.BatchConfig.dataSource;

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
            scriptRunner.setSendFullScript(false);
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
