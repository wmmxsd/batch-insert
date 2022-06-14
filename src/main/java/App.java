import file.SqlFile;
import file.factory.SqlFileFactory;
import file.factory.impl.ScriptFactoryImpl;
import file.factory.impl.SqlFileFactoryImpl;
import sql.Script;
import utils.DuridConfig;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static file.BatchConfig.*;
import static utils.FileUtil.deletFile;

public class App {
    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Instant begin = Instant.now();
        System.out.println("args :" + Arrays.toString(args));
        if (args.length == 1) {
            SQL_COUNTS_IN_FILE = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            SQL_COUNTS_IN_FILE = Integer.parseInt(args[0]);
            CIRCLE = Integer.parseInt(args[1]);
        }

        if (Files.notExists(Paths.get(DIRECTORY_PATH))) {
            Files.createDirectory(Paths.get(DIRECTORY_PATH));
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

        executorService.shutdown();
        while (true) {
            if (executorService.isTerminated()) {
                System.out.println(Thread.currentThread().getName() + " : 批量将sql写入到文件 successful");
                break;
            }
        }
        futureList.clear();
        ExecutorService executorService1 = buildThreadPool1();
        for (SqlFile sqlFile : sqlFileList) {
            Script script = new ScriptFactoryImpl().build(sqlFile);
            if (script == null) {
                System.err.println(sqlFile.getClass().toString() + ": have not a script object!");
                continue;
            }
            futureList.add(executorService1.submit(script));
        }
        for (Future<Boolean> future : futureList) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService1.shutdown();
        while (true) {
            if (executorService1.isTerminated()) {
                System.out.println(Thread.currentThread().getName() + " : 批量将文件中的sql写入到数据库 successful");
                break;
            }
        }
        deletFile(Paths.get(DIRECTORY_PATH).toString());
        System.out.println("删除" + DIRECTORY_PATH + "：end");

        Instant end = Instant.now();
        ;
        System.out.println("批量导入审计：successful。耗时："  + Duration.between(begin, end).toMillis() + "毫秒");
    }


    public static ExecutorService buildThreadPool() {
        System.out.println("创建批量将sql写入到文件的线程池-end");
        AtomicInteger index = new AtomicInteger(0);
        return new ThreadPoolExecutor(100, 100, 100,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "sql-to-file-" + index.getAndIncrement()), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public static ExecutorService buildThreadPool1() {
        System.out.println("创建批量将文件中的sql写入到数据库的线程池-end");
        AtomicInteger index = new AtomicInteger(0);
        return new ThreadPoolExecutor(100, 100, 100,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "file-to-db-" + index.getAndIncrement()), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
