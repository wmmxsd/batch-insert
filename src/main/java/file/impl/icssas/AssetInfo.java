package file.impl.icssas;

import file.AbstractSqlFile;
import utils.UUIDUtil;

import java.util.Random;

public class AssetInfo extends AbstractSqlFile {

    private final Random random = new Random();

    public AssetInfo(Integer index) {
        super(index);
    }

    @Override
    public String generateSql() {
        return "INSERT INTO `icssas_asset_info`" +
                "(`assetName`, `ip`, `mac`, `assetNumber`, `assetLevel`, `assetTypeId`, `manufacturerId`, `assetModel`, `deptId`, `osId`, `assetStatus`, `location`, `madeInChina`, `accessTime`, `isIndustry`, `ipdecimal`)" +
                " VALUES " +
                "('wmm', '" + getIp() + "', '11-22-33-44-55-66', '00544', " + Math.floor(Math.random() * 5 + 1) + ", " + Math.floor(Math.random() * 30 + 1) + ", "  + Math.floor(Math.random() * 6 + 1) + ", NULL, " + Math.floor(Math.random() * 9 + 1) + ", " + Math.floor(Math.random() * 6 + 1) + ", "+ random.nextInt(3) + ", NULL, " + random.nextInt(2) + ", '2022-03-28 10:14:06', " + random.nextInt(2) + ", 3232266011);";
    }

    @Override
    public Boolean call() {
        writeToFile("icssas_asset_info_");
        return true;
    }

    private String getIp() {
        return 192 + "." + 168 + "." + random.nextInt(255) + "." + random.nextInt(255);
    }
}
