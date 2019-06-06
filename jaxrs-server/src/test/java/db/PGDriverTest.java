package db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class PGDriverTest {
    private Connection testData;

    @Before
    public void init() {
        testData = PGDriver.establishConnection(testData, "test", "test", "test");
    }

    @Test
    public void testConnection() {
        try {
            String lobbies, users, selections;
            lobbies = users = selections = null;
            int voteCount = -90;

            testData.setAutoCommit(false);

            // Test select statement
            Statement stmt = testData.createStatement();
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
            PGDriver.exceptionHandle(e);
        }
    }



    @Test
    public void closeTest() {
        testData = PGDriver.close(testData);
        Assert.assertNull(testData);
    }
}
