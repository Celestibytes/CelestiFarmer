package celestibytes.celestifarmer.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import celestibytes.celestifarmer.world.WorldSpecialRenderObj;

public class WorldSpecialRenderer {
	
	private List<WorldSpecialRenderObj> renderCache;
	
	/** Vertex Buffer Object */
	public void renderAsVBO(WorldSpecialRenderObj o) {
		int rid = o.getRenderObjId();
		if(rid == -1) {
			rid = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, rid);
			glBufferData(GL_ARRAY_BUFFER, (FloatBuffer)null, GL_DYNAMIC_DRAW); // TODO: data!
		}
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	/** Vertex Array */
	public void renderAsVA(WorldSpecialRenderObj os) {
		
	}
}
