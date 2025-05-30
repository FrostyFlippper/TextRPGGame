package TextRPGGame;

import TextRPGGame.Util.Logger.Logger;

public class Main {
    public static final String APP_NAME = "TextRPGGame";

    public static void main(String[] args){
        Logger.initialise();
        Database.initialise();
    }
}
