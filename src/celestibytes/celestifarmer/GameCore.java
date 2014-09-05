package celestibytes.celestifarmer;

import static org.lwjgl.opengl.GL11.*;

import java.util.Random;

import okkapel.ogljguisystem.Gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import celestibytes.celestifarmer.graphics.util.Textures;
import celestibytes.celestifarmer.rendering.RenderHelper;
import celestibytes.celestifarmer.world.Area;
import celestibytes.ctiengine.Game;
import celestibytes.ctiengine.GameInitHelper;
import celestibytes.ctiengine.input.MouseHelper;
import celestibytes.ctiengine.util.Colour;

public class GameCore extends Game {
	
	// world render offset
	private float worldOffsX;
	private float worldOffsY;
	
	private Area testArea = null;
	
//	private World
	
	@Override
	protected void preload() {
		Textures.loadTextures();
	}
	
	@Override
	protected void init() {
		RenderHelper.init();
		try {
			guim.openGui(new Gui(50, 50, 200, 200, false, new okkapel.ogljguisystem.util.Colour(1f, 0f, 0f, 1f)));
			guim.openGui(new Gui(50, 50, 100, 200, false, new okkapel.ogljguisystem.util.Colour(0f, 1f, 0f, 1f)));
			guim.openGui(new Gui(50, 50, 200, 100, false, new okkapel.ogljguisystem.util.Colour(0f, 0f, 1f, 1f)));
			
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
	
	@Override
	protected void gameLoop() {
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

	@Override
	protected void deInit() {
		Textures.deleteTextures();
	}

}
