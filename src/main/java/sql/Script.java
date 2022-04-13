package sql;

import utils.Resources;
import utils.ScriptRunner;

import java.util.concurrent.Callable;

import static file.BatchConfig.CIRCLE;
import static file.BatchConfig.DIRECTORY_PATH;
import static file.BatchConfig.dataSource;
import static file.BatchConfig.semaphore;


public interface Script extends Callable<Boolean> {
    void runScript(String fileName);
}
