package file.factory;

import file.SqlFile;
import sql.Script;

public interface ScriptFactory<E extends SqlFile, F extends Script> {
    F build(E script);
}
