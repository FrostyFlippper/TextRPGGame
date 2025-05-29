package TextRPGGame.Util;

import TextRPGGame.Main;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    public static Path getAppDataPath() {
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            String appData = System.getenv("APPDATA");
            return Paths.get(appData, Main.APP_NAME);
        } else if (os.contains("mac")) {
            return Paths.get(userHome, "Library", "Application Support", Main.APP_NAME);
        } else {
            return Paths.get(userHome, "." + Main.APP_NAME.toLowerCase());
        }
    }
}
