package celestibytes.jgutil;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import celestibytes.celestifarmer.Out;

public class GameInitHelper {
	
	private static boolean init = false;
	private static int w_width, w_height;
	
	public static int getWindowWidth() {
		return w_width;
	}
	
	public static int getWindowHeight() {
		return w_height;
	}
	
	public static void initGL(String title, int width, int height) throws LWJGLException{
		if(init) {
			return;
		}
		
		w_width = width;
		w_height = height;
		
		Display.setTitle(title);
		Display.setDisplayMode(new DisplayMode(width, height));
//		Display.setResizable(true);
		Display.create();
		long tsts = System.currentTimeMillis();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glViewport(0, 0, width, height);
		//GLU.gluPerspective(90, width/height, 2.0f, 2.0f);
		GL11.glOrtho(0, width, height, 0, 2.0d, -2.0d);
		
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	
	public static void destGL() {
		Display.destroy();
	}
	
	/*
	public static void worldProjection() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glViewport(0, 0, w_width, w_height);
		GL11.glOrtho(w_width, 0, 0, w_height, 2.0d, -2.0d);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static void guiProjection() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glViewport(0, 0, w_width, w_height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}*/
	
	//Display.wasResized()
		public static void onResize() {
//			if(Display.getHeight() > Display.getWidth()) {
//				try {
//					Display.setDisplayMode(new DisplayMode(Display.getHeight(),Display.getHeight()));
//				} catch(LWJGLException e) {
//					e.printStackTrace();
//				}
//			}
//			
////			int width = Display.getWidth();
////			int height = Display.getHeight();
//			int dimA = Math.max(Display.getWidth(), Display.getHeight());
//			int dimB = Math.min(Display.getWidth(), Display.getHeight());
//			
//			GL11.glMatrixMode(GL11.GL_PROJECTION);
//			//GL11.glViewport(width/2-height/2, 0, height, height);
//			GL11.glViewport(dimA/2-dimB/2, 0, dimB, dimB);
//			//GLU.gluPerspective(90, width/height, 2.0f, 2.0f);
//			GLU.gluPerspective(90, dimA/dimB, 2.0f, 2.0f);
//			GL11.glMatrixMode(GL11.GL_MODELVIEW);
//			GL11.glLoadIdentity();
		}
}
