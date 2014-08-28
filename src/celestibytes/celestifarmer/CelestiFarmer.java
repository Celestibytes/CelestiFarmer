package celestibytes.celestifarmer;

import celestibytes.jgutil.Game;

public class CelestiFarmer {
	
	public static Game theGame = null;
	
	public static void main(String[] args) {
		System.out.println("Starting up the game...");
		theGame = new Game();
		theGame.start();
	}
}
