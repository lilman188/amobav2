package amobav;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Adatbázis-kezelő az amőba játékhoz.
 */
public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:highscores.db";
    private Connection connection;

    public DatabaseManager() {
        try {
            // Kapcsolat létrehozása
            connection = DriverManager.getConnection(DB_URL);
            // Tábla létrehozása, ha nem létezik
            String sql = "CREATE TABLE IF NOT EXISTS highscores (" +
                    "symbol TEXT PRIMARY KEY," +
                    "wins INTEGER" +
                    ")";
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ment egy győzelmet a játékoshoz.
     *
     * @param symbol játékos szimbóluma (pl. X vagy O)
     */
    public void saveWinner(String symbol) {
        try {
            // Ellenőrizzük, hogy létezik-e a rekord
            String query = "SELECT wins FROM highscores WHERE symbol = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setString(1, symbol);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Frissítés
                    int currentWins = rs.getInt("wins");
                    String update = "UPDATE highscores SET wins = ? WHERE symbol = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(update)) {
                        updateStmt.setInt(1, currentWins + 1);
                        updateStmt.setString(2, symbol);
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Új rekord beszúrása
                    String insert = "INSERT INTO highscores(symbol, wins) VALUES (?, ?)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insert)) {
                        insertStmt.setString(1, symbol);
                        insertStmt.setInt(2, 1);
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kiírja a high score táblázatot a konzolra.
     */
    public void printHighScores() {
        System.out.println("\nHigh Scores:");
        System.out.println("-----------------");
        System.out.printf("%-6s | %s%n", "Player", "Wins");
        System.out.println("-----------------");
        try {
            String query = "SELECT symbol, wins FROM highscores ORDER BY wins DESC";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String symbol = rs.getString("symbol");
                    int wins = rs.getInt("wins");
                    System.out.printf("%-6s | %d%n", symbol, wins);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------");
    }

    /**
     * Kapcsolat lezárása az adatbázissal.
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
