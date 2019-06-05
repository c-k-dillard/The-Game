package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class PGDriver {
    public static Connection database;
    private static String url = "jdbc:postgresql://localhost:5432/cdillard";

    private PGDriver() {
    }

    public static void init() {
        database = null;

        try {
            Class.forName("org.postgresql.Driver");
            database = DriverManager.getConnection(url, "cdillard", "asdf");

            System.out.println("Opened database successfully");

            Statement stmt = null;
            stmt = database.createStatement();

            // Ensures tables are not created
            String sql = "DROP TABLE IF EXISTS entries";
            stmt.executeUpdate(sql);
            System.out.println("Table *entries* dropped");

            sql = "DROP TABLE IF EXISTS options";
            stmt.executeUpdate(sql);
            System.out.println("Table *options* dropped");

            // Creates table of all the inputted selections made by each person
            sql = "CREATE TABLE entries" +
                    "(lobbies TEXT NOT NULL, " +
                    " users TEXT NOT NULL, " +
                    " selections TEXT NOT NULL, " +
                    " vote_count INT NOT NULL)";

            stmt.executeUpdate(sql);

            System.out.println("Table *entries* created successfully");

            // Creates table of all possible options in relation to lobby name
            sql = "CREATE TABLE options" +
                    "(selections TEXT NOT NULL, " +
                    " lobbies TEXT NOT NULL)";

            stmt.executeUpdate(sql);
            System.out.println("Table *options* created successfully");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
