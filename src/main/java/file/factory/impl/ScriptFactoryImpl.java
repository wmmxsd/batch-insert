package file.factory.impl;

import file.AbstractSqlFile;
import file.SqlFile;
import file.factory.ScriptFactory;
import sql.Script;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ScriptFactoryImpl implements ScriptFactory<SqlFile, Script> {
    @Override
    public Script build(SqlFile sqlFile) {
        String className = sqlFile.getClass().getSimpleName();
        Class<Script> clzss;
        try {
            //clzss = (Class<Script>) Class.forName("sql.impl.icssas." + className + "Script");
            clzss = (Class<Script>) Class.forName("sql.impl." + className + "Script");
            Constructor<Script> constructor = clzss.getConstructor(Integer.class);
            return constructor.newInstance(((AbstractSqlFile) sqlFile).getIndex());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
