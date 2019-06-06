package db;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public final class PGDriver {
    public static Connection database;

    // SQL commands to run on initialization
    public static final String drop = "DROP TABLE IF EXISTS entries";
    public static final String create =
            "CREATE TABLE  entries" +
                    "(lobbies TEXT NOT NULL, " +
                    " users TEXT NOT NULL, " +
                    " selections TEXT NOT NULL, " +
                    " vote_count INT NOT NULL)";

    private static final String url = "jdbc:postgresql://localhost:5432/";

    private PGDriver() {
    }

    /**
     * Returns a connection with much of the initialization process for a proper database connection completed.
     *
     * @param dbName   The name of the database.
     * @param username The username to login.
     * @param pass     The password for the user.
     * @return         Returns the connection.
     */
    public static Connection establishConnection(String dbName, String username, String pass) {
            try {
                Class.forName("org.postgresql.Driver");
                return DriverManager.getConnection(url + dbName, username, pass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * Executes a SQL statement in a database.
     *
     * @param db         The database being edited.
     * @param sql        The statement.
     * @param descriptor A descriptor of the statement for verbosity (optional).
     */
    public static void executeStmt(Connection db, String sql, String descriptor) {
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Executed sql statement : " + descriptor);
        } catch (Exception e) {
            exceptionHandle(e);
        }
    }

    public static void executeStmt(Connection db, String sql) {
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Executed sql statement.");
        } catch (Exception e) {
            exceptionHandle(e);
        }
    }

    /**
     * Returns a closed connection database.
     *
     * @param db The database that is being closed.
     */
    public static Connection close(Connection db) {
        try {
            db.close();
            db = null;
        } catch (Exception e) {
            exceptionHandle(e);
        }

        return db;
    }

    /**
     * The same exception handling was being used so this method is used in place of it.
     *
     * @param e The exception.
     */
    public static void exceptionHandle(Exception e) {
        e.printStackTrace();
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
    }
}
