package db;

import org.junit.Assert;
import org.junit.Before;
import db.PGDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class PGDriverTest {
    private Connection testData;

    @Before
    public void init() {
        PGDriver.init(testData, "test", "test", "test");

        try {
            // Establish connection to database
            Class.forName("org.postgresql.Driver");
            testData = null;
            testData = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "test", "test");

            // Fake data for test database inserted
            Statement stmt = null;
            stmt = testData.createStatement();

            String sql = "INSERT INTO entries (lobbies, users, selections, vote_count)" +
                    "VALUES('foo', 'chase', 'tetris', 9);";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @org.junit.Test
    public void testConnection() throws Exception {
        try {
            String lobbies, users, selections;
            lobbies = users = selections = null;
            int voteCount = -90;

            testData.setAutoCommit(false);

            // Test select statement
            Statement stmt = null;
            stmt = testData.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM entries");

            while (rs.next()) {
                lobbies = rs.getString("lobbies");
                users = rs.getString("users");
                selections = rs.getString("selections");
                voteCount = rs.getInt("vote_count");
            }

            // Close all streams
            rs.close();
            stmt.close();
            testData.close();

            // Test for proper results
            Assert.assertEquals("foo", lobbies);
            Assert.assertEquals("chase", users);
            Assert.assertEquals("tetris", selections);
            Assert.assertEquals(9, voteCount);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @org.junit.Test
    public void closeTest() {
        try {
            testData.close();
            testData = null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        Assert.assertEquals(null, testData);
    }
}
