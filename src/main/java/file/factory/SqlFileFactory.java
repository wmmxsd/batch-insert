package file.factory;

import file.SqlFile;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SqlFileFactory {
    List<SqlFile> buildAll() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
