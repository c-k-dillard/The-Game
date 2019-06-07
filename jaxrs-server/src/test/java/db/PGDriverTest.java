package db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class PGDriverTest {
    private Connection testData;

    @Before
    public void init() throws SQLException {
        testData = PGDriver.establishConnection("test", "test", "test");
        PGDriver.executeStmt(testData, PGDriver.dropEntries, "Drop entries");
        PGDriver.executeStmt(testData, PGDriver.createEntries, "Create entries");
        PGDriver.executeStmt(testData, PGDriver.dropSelections);
        PGDriver.executeStmt(testData, PGDriver.createSelections);

        // Inserts fake data
        Statement stmt = testData.createStatement();
        String sql = "INSERT INTO entries (lobbies, users, selections, vote_count) " +
                "VALUES ('foo', 'chase', 'tetris', 9);";
        testData.setAutoCommit(false);
        stmt.executeUpdate(sql);

        // Selections fake data
        sql = "INSERT INTO selections (lobby_name, options)" +
                "VALUES ('foo', 'bar');";
        stmt.executeUpdate(sql);
        stmt.close();
        testData.commit();
    }

    @Test
    public void testEntries() throws SQLException {

        String lobbies, users, selections;
        lobbies = users = selections = null;
        int voteCount = -90;

        testData.setAutoCommit(false);

        // Test select statement
        Statement stmt = testData.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM entries WHERE users = 'chase'");

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
    }

    @Test
    public void testSelections() throws SQLException {
        String lobby, option;
        lobby = option = null;

        testData.setAutoCommit(false);
        Statement stmt = testData.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM selections");

        while (rs.next()) {
            lobby = rs.getString("lobby_name");
            option = rs.getString("options");
        }

        Assert.assertEquals("foo", lobby);
        Assert.assertEquals("bar", option);
    }

    @Test
    public void closeTest() {
        testData = PGDriver.close(testData);
        Assert.assertNull(testData);
    }
}
