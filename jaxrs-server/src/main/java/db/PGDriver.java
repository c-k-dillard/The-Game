package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class PGDriver {
    public static Connection database;
    private static String url = "jdbc:postgresql://localhost:5432/cdillard";

    private PGDriver() {}

    public static void init() {
        database = null;

        try {
            Class.forName("org.postgresql.Driver");
            database = DriverManager.getConnection(url, "cdillard", "asdf");

            System.out.println("Opened database successfully");

            Statement stmt = null;
            String sql = "CREATE TABLE IF NOT EXISTS entries" +
                    "(lobbies TEXT NOT NULL, " +
                    " users TEXT NOT NULL, " +
                    " selections TEXT NOT NULL, " +
                    " vote_count INT NOT NULL)";

            stmt = database.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Table *entries* created successfully");

            sql = "CREATE TABLE IF NOT EXISTS options" +
                    "(selections TEXT NOT NULL, " +
                    " lobbies TEXT NOT NULL)";

            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
}