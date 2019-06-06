package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class PGDriver {
    public static Connection database;
    private static String url = "jdbc:postgresql://localhost:5432/";

    private PGDriver() {}

    public static void init(Connection db, String dbName, String username, String pass) {
        db = null;

        try {
            Class.forName("org.postgresql.Driver");
            db = DriverManager.getConnection(url + dbName, username, pass);
            System.out.println("Opened database successfully");

            Statement stmt = null;
            stmt = db.createStatement();

            // Dropping for safety. Creates main table afterwards
            String sql = "DROP TABLE IF EXISTS entries";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE  entries" +
                    "(lobbies TEXT NOT NULL, " +
                    " users TEXT NOT NULL, " +
                    " selections TEXT NOT NULL, " +
                    " vote_count INT NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Table *entries* created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Closes connection to database parameter
     * @param db
     */
    public static void close(Connection db) {
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}