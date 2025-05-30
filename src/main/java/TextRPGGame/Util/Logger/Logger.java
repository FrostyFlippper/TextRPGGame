package TextRPGGame.Util.Logger;

import TextRPGGame.Util.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.time.LocalTime;

public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    private static final Path APPDATA_PATH = FileUtil.getAppDataPath();
    private static final Path LOGGING_PATH = APPDATA_PATH.resolve("Logs");

    private static final Calendar c = Calendar.getInstance();

    public static void initialise(){
        try {
            Files.createDirectories(LOGGING_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void log(String logText, LoggingLevel loggingLevel){
        switch (loggingLevel){
            case INFO:
                System.out.println(ANSI_BLUE + ANSI_BOLD + "[INFO] " + ANSI_RESET + logText);
                break;
            case WARN:
                System.out.println(ANSI_YELLOW + ANSI_BOLD + "[WARNING] " + ANSI_RESET + logText);
                break;
            case ERROR:
                System.out.println(ANSI_RED + ANSI_BOLD + "[ERROR] " + ANSI_RESET + logText);
                break;
        }
        try {
            String data = (getPaddedTime() + " |-" + loggingLevel.name() + " - " + logText + "\n");
            Files.writeString(Path.of(LOGGING_PATH + "/" + "[code] " + c.get(Calendar.DAY_OF_MONTH) + "." + (c.get(Calendar.MONTH) + 1) + "." + c.get(Calendar.YEAR) + ".log"), data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getPaddedTime(){
        StringBuilder localTime = new StringBuilder(LocalTime.now().toString());
        while(localTime.length() < 18){
            localTime.append("0");
        }
        return localTime.toString();
    }
}