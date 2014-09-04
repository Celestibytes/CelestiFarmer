package celestibytes.celestifarmer.graphics.util;

import org.lwjgl.opengl.GL11;

import celestibytes.ctiengine.util.TextureLoader;

public class Textures {
	
	public static int TEXTURE_GUI_DECOR = -1;
	
	public static void loadTextures() {
		TEXTURE_GUI_DECOR = TextureLoader.loadTexture("res/textures/gui/gui_decoration.png");
	}
	
	public static void deleteTextures() {
		if(TEXTURE_GUI_DECOR != -1) {
			GL11.glDeleteTextures(TEXTURE_GUI_DECOR);
		}
	}
}
