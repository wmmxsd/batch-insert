import file.SqlFile;
import file.factory.SqlFileFactory;
import file.factory.impl.ScriptFactoryImpl;
import file.factory.impl.SqlFileFactoryImpl;
import sql.Script;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static file.BatchConfig.*;
import static utils.FileUtil.deletFile;

public class App {
    public static void main(String[] args)  {
        System.out.println("批量导入审计：begin");
        System.out.println("**********************************************************************************************************************");
        if (args.length == 1) {
            SQL_COUNTS_IN_FILE = Integer.parseInt(args[0]);
        }
        if (args.length == 2) {
            SQL_COUNTS_IN_FILE = Integer.parseInt(args[0]);
            CIRCLE = Integer.parseInt(args[1]);
        }
        SqlFileFactory sqlFileFactory = new SqlFileFactoryImpl();
        ExecutorService executorService = buildThreadPool();
        List<Future<Boolean>> futureList = new ArrayList<>();
        List<SqlFile> sqlFileList = sqlFileFactory.buildAll();
        sqlFileList.forEach(sqlFile -> futureList.add(executorService.submit(sqlFile)));
        for (Future<Boolean> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("**********************************************************************************************************************");
        futureList.clear();
        for (SqlFile sqlFile : sqlFileList) {
            Script script = new ScriptFactoryImpl().build(sqlFile);
            if (script == null) {
                System.err.println(sqlFile.getClass().toString() + ": have not a script object!");
                continue;
            }
            futureList.add(executorService.submit(script));
        }
        for (Future<Boolean> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println("批量导入审计：end");
                break;
            }
        }

        deletFile(Paths.get(DIRECTORY_PATH).toString());
        System.out.println("删除" + DIRECTORY_PATH + "：end");
    }


    public static ExecutorService buildThreadPool() {
        System.out.println("线程池创建-end");
        AtomicInteger index = new AtomicInteger(0);
        return new ThreadPoolExecutor(10, 30, 100,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "import-sql-" + index.getAndIncrement()), new ThreadPoolExecutor.AbortPolicy());
    }
}
