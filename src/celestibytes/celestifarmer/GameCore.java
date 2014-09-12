package celestibytes.celestifarmer;

import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;
import java.util.Random;

import okkapel.kkplglutil.rendering.GLHandler;
import okkapel.kkplglutil.rendering.GLRenderMethod;
import okkapel.kkplglutil.rendering.GLRenderObj;
import okkapel.kkplglutil.rendering.GLRenderObjPointer;
import okkapel.kkplglutil.rendering.RenderBufferGenerator;
import okkapel.kkplglutil.util.Colour;
import okkapel.ogljguisystem.Gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;

import celestibytes.celestifarmer.graphics.util.Textures;
import celestibytes.celestifarmer.rendering.RenderHelper;
import celestibytes.celestifarmer.world.Area;
import celestibytes.ctiengine.Game;
import celestibytes.ctiengine.GameInitHelper;
import celestibytes.ctiengine.input.MouseHelper;

public class GameCore extends Game {
	
	// world render offset
	private float worldOffsX;
	private float worldOffsY;
	
	private Area testArea = null;
	
	private FloatBuffer rbg_test;
	private GLRenderObj vbo_test;
	
	private GLRenderObjPointer testPtr;
	
//	private World
	
	@Override
	protected void preload() {
		Textures.loadTextures();
	}
	
	@Override
	protected void init() {
		RenderHelper.init();
		
		RenderBufferGenerator rbg = RenderBufferGenerator.INSTANCE;
		
		rbg.addVertexWColorWUV(0f, 0f, 0f, 1f, 0f, 0f, 0f, 0f);
		rbg.addVertexWColorWUV(100f, 0f, 0f, 0f, 1f, 0f, 0f, 0f);
		rbg.addVertexWColorWUV(100f, 100f, 0f, 0f, 0f, 1f, 0f, 0f);
		
		rbg_test = rbg.createBuffer();
		
		// Do in CTIEngine
		GLHandler.init();
		
		testPtr = GLHandler.createVBO(rbg_test, GL15.GL_DYNAMIC_DRAW, null, 3);
		
//		vbo_test = new GLRenderObj(GLRenderMethod.VERTEX_BUFFER_OBJECT, rbg_test, GL15.GL_DYNAMIC_DRAW);
		
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
		
		glLoadIdentity();
		
		GLHandler.renderRendPtr(testPtr);
		
//		GameInitHelper.guiProjection();
	}

	@Override
	protected void deInit() {
		Textures.deleteTextures();
		vbo_test.deleteVbo();
		GLHandler.deinit();
	}

}
