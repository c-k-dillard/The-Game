package db;

import java.sql.Connection;
import java.sql.DriverManager;

public final class PGDriver {
    public static Connection database;
    private static String url = "jdbc:postgresql://localhost:5432/cdillard";

    private PGDriver() {}

    public static void init() {
        database = null;

        try {
            Class.forName("org.postgresql.Driver");
            database = DriverManager.getConnection(url, "cdillard", "asdf");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        System.out.println("Opened database successfully");
    }
}