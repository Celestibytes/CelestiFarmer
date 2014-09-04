package celestibytes.celestifarmer;

import celestibytes.ctiengine.Game;
import celestibytes.ctiengine.logging.ILogger;

public class CelestiFarmer {
	
	public static Game theGame = null;
	
	public static void main(String[] args) {
		System.out.println("Starting up the game...");
		theGame = new GameCore();
		ILogger engineLogger = new Out();
		engineLogger.setLoggerPrefix("CTIEngine");
		theGame.start(engineLogger, 60, 960, 720, Version.getTitle());
	}
	
}
