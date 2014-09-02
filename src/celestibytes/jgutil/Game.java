package celestibytes.jgutil;

import java.io.FileInputStream;
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
import org.newdawn.slick.opengl.Texture;

import celestibytes.celestifarmer.Out;
import celestibytes.celestifarmer.Version;
import celestibytes.celestifarmer.graphics.RenderHelper;
import celestibytes.celestifarmer.graphics.gui.Gui;
import celestibytes.celestifarmer.graphics.gui.GuiManager;
import celestibytes.celestifarmer.graphics.gui.GuiRenderer;
import celestibytes.celestifarmer.graphics.util.TextureLoader;
import celestibytes.celestifarmer.input.util.MouseHelper;
import celestibytes.celestifarmer.util.Colour;
import celestibytes.celestifarmer.world.Area;
import static org.lwjgl.opengl.GL11.*;

public class Game {
	
	private static float delta;
	private static long lastCycle = System.currentTimeMillis();
	private static int stopScheluded = 100; // 100 is the ok value, any other value will quit the game and the value will be passed for return value
	
	private long fpsCheckLast = System.currentTimeMillis();
	private int frameCount = 0;
	
	private Random rand = new Random(System.currentTimeMillis());
	
	// world render offset
	public static float worldOffsX = 0;
	public static float worldOffsY = 0;
	
	private Area testArea = null;
	
	private GuiManager guim = null;
	
	//private Gui testGui = new Gui(10, 10, 200, 200);
	
	public void start() {
		preload();
		init();
		
		while(keepRunning()) {
			if(Display.wasResized()) {
				GameInitHelper.onResize();
			}
			
			calcDelta();
			
			MouseHelper.update();
			guim.mouseUpdate();
			
			worldOffsX += MouseHelper.dragMiddle.mouseDeltaX;
			worldOffsY += MouseHelper.dragMiddle.mouseDeltaY;
			
			
//			if(testGui.isBeingDraggedByMouse()) {
//				if(!MouseHelper.dragLeft.isButtonDown()) {
//					testGui.setBeingDragged(false);
//				}
//			} else {
//				if(MouseHelper.dragLeft.isButtonDown() && testGui.pointInsideDragBar(Mouse.getX(), GameInitHelper.getWindowHeight()-Mouse.getY())) {
//					testGui.setBeingDragged(true);
//				}
//			}
//			testGui.update();
			
			
			
			gameLoop();
			
			int errCode = glGetError();
			if(errCode != GL_NO_ERROR) {
				System.out.println(GLU.gluErrorString(errCode));
			}
			
			
			
			frameCount++;
			if((System.currentTimeMillis() - fpsCheckLast) > 1000) {
				System.out.println("FPS: " + frameCount + ", Logic time: " + (System.currentTimeMillis()-lastCycle) + "ms");
				frameCount = 0;
				fpsCheckLast = System.currentTimeMillis();
			}
			Display.update();
			Display.sync(60);
		}
		
		if(GuiManager.TEXTURE_GUI_CLOSE != -1) {
			GL11.glDeleteTextures(GuiManager.TEXTURE_GUI_CLOSE);
		}
		
		if(GuiManager.TEXTURE_GUI_MINIMIZE != -1) {
			GL11.glDeleteTextures(GuiManager.TEXTURE_GUI_MINIMIZE);
		}
		GameInitHelper.destGL();
	}
	
	private boolean keepRunning() {
		return stopScheluded == 100 && !Display.isCloseRequested();
	}
	
	private void preload() {}
	
	private void init() {
		try {
			GameInitHelper.initGL(Version.getTitle(), 960, 720);
			RenderHelper.init();
			GuiManager.init();
			guim = new GuiManager();
			guim.openGui(new Gui(50, 50, 200, 200, false, new Colour(1f, 0f, 0f, 1f)));
			guim.openGui(new Gui(50, 50, 100, 200, false, new Colour(0f, 1f, 0f, 1f)));
			guim.openGui(new Gui(50, 50, 200, 100, false, new Colour(0f, 0f, 1f, 1f)));
			
			glClearColor(0f, .5f, .5f, 1f);
			glPointSize(10f);
			glEnable(GL_TEXTURE_2D);
			
			testArea = new Area();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("Initialization failed!!!");
			System.exit(-1);
		}
	}
	
	private void calcDelta() {
		delta = (System.currentTimeMillis()-lastCycle)/1000f;
		lastCycle = System.currentTimeMillis();
	}
	
	public static float getDelta() {
		return delta;
	}
	
	private void gameLoop() {
		glClear(GL_COLOR_BUFFER_BIT);
		glLoadIdentity();
//		GameInitHelper.worldProjection();
		glTranslatef(worldOffsX,-worldOffsY,0);
//		glRotatef(-45, 1f,0f,0f);
//		glRotatef(45, 0f,0f,1f);
//		renderWorld();
		RenderHelper.renderArea(testArea);
		
		glLoadIdentity();
		
//		guim.renderDecor();
//		guim.interlTest();
		glColor3f(1f,1f,1f);
		guim.renderGuis();
//		GuiRenderer.render(testGui);
		
//		GameInitHelper.guiProjection();
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
