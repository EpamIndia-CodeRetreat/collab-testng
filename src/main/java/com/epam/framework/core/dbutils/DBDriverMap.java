package com.epam.framework.core.dbutils;

import java.util.HashMap;
import java.util.Map;

public class DBDriverMap {
    private static Map<String, String> driverMap = new HashMap<>();

    private static void DBMap() {
        driverMap.put("oracle", "oracle.jdbc.OracleDriver");
        driverMap.put("mysql", "com.mysql.jdbc.Driver");
        driverMap.put("sqlserver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        driverMap.put("postgresql", "org.postgresql.Driver");
        driverMap.put("sqlite", "org.sqlite.JDBC");
    }

    public static String get(String driverType) {
        return driverMap.get(driverType);
    }
}
