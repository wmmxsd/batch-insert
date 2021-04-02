package file.factory;

import file.SqlFile;

import java.util.List;

public interface SqlFileFactory {
    List<SqlFile> buildAll();
}
