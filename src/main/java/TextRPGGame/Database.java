package TextRPGGame;

import TextRPGGame.Util.FileUtil;
import TextRPGGame.Util.Logger.Logger;
import TextRPGGame.Util.Logger.LoggingLevel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    private static final Path APPDATA_PATH = FileUtil.getAppDataPath();
    private static final Path DATABASE_PATH = APPDATA_PATH.resolve("database.db");


    private static Connection SQLiteConnection;

    public static void initialise(){
        try {
            Files.createDirectories(APPDATA_PATH);
            Logger.log("Created appdata directory", LoggingLevel.INFO);
            if(Files.notExists(DATABASE_PATH)){
                Files.createFile(DATABASE_PATH);
                Logger.log("Created database file; didnt exist", LoggingLevel.INFO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connectToSQLite();
        Logger.log("Database initialised", LoggingLevel.INFO);
    }

    public static void connectToSQLite(){
        try {
            SQLiteConnection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH);
            Statement statement = SQLiteConnection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Players(username varchar primary key, level int, health int, wealth int)");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
