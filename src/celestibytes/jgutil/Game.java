package celestibytes.jgutil;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;

import celestibytes.celestifarmer.graphics.RenderHelper;
import celestibytes.celestifarmer.input.util.MouseHelper;
import celestibytes.celestifarmer.world.Area;
import static org.lwjgl.opengl.GL11.*;

public class Game {
	
	private static float delta;
	private static int stopScheluded = 100; // 100 is the ok value, any other value will quit the game and the value will be passed for return value
	
	private long fpsCheckLast = System.currentTimeMillis();
	private int frameCount = 0;
	
	private int[][] testWorld = new int[16][16];
	private Random rand = new Random(System.currentTimeMillis());
	
	// world render offset
	public static float worldOffsX = 0;
	public static float worldOffsY = 0;
	
	private Area testArea = null;
	
	public void start() {
		preload();
		init();
		
		while(keepRunning()) {
			if(Display.wasResized()) {
				GameInitHelper.onResize();
			}
			
			MouseHelper.update();
			
			worldOffsX += MouseHelper.dragMiddle.mouseDeltaX/500f;
			worldOffsY += MouseHelper.dragMiddle.mouseDeltaY/500f;
			
			gameLoop();
			
			int errCode = glGetError();
			if(errCode != GL_NO_ERROR) {
				System.out.println(GLU.gluErrorString(errCode));
			}
			
			Display.update();
			Display.sync(60);
			
			frameCount++;
			if((System.currentTimeMillis() - fpsCheckLast) > 1000) {
				System.out.println("FPS: " + frameCount++);
				frameCount = 0;
				fpsCheckLast = System.currentTimeMillis();
			}
		}
	}
	
	private boolean keepRunning() {
		return stopScheluded == 100 && !Display.isCloseRequested();
	}
	
	private void preload() {}
	
	private void init() {
		try {
			GameInitHelper.initGL("CelestiFarmer", 960, 720);
			RenderHelper.init();
			glClearColor(0f, .5f, .5f, 1f);
			glPointSize(10f);
			
			testArea = new Area();
			
		} catch(LWJGLException e) {
			e.printStackTrace();
			System.err.println("Initialization failed!!!");
			System.exit(-1);
		}
	}
	
	private void gameLoop() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
		
		glTranslatef(0f+worldOffsX,.5f+worldOffsY,-worldOffsY-.5f);
		glRotatef(-45, 1f,0f,0f);
		glRotatef(45, 0f,0f,1f);
		
		//renderWorld();
		RenderHelper.renderArea(testArea);
		
	}
	
	private void stop() {
		System.exit(stopScheluded);
	}
	
	/**@param retVal - if set to 100, return value will be -1*/
	public static void stopGame(int retVal) {
		stopScheluded = retVal != 100 ? retVal : -1;
	}
	
	private void randColor() {
		glColor3f(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
	}
}
