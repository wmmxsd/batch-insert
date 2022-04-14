package sql;

import java.util.concurrent.Callable;


public interface Script extends Callable<Boolean> {
    void runScript(String fileName);
}
