package celestibytes.celestifarmer;

import java.nio.ByteBuffer;

import celestibytes.ctie.core.Game;
import okkapel.kkplglutil.rendering.RenderBufferGenerator;

public class CelestiFarmer {
	
	public static Game theGame = null;
	
	public static void main(String[] args) {
		System.out.println("Starting up the game...");
		theGame = new GameCore();
		theGame.start();
	}
	
}
