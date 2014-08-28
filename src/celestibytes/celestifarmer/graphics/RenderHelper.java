package celestibytes.celestifarmer.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import celestibytes.celestifarmer.world.Area;

public class RenderHelper {
	
	public static FloatBuffer areaVertexArray = null;
	public static final int areaVertexArrayStride = 3*4;
	/**Not the vertex count*/
	public static final int areaVertexArraySize = 6*32*32;
	
	public static void init() {
		if(areaVertexArray == null) {
			areaVertexArray = BufferUtils.createFloatBuffer(3*6*32*32);
			float ps = 0.1f;
			for(int y=0;y<32;y++) {
				for(int x=0;x<32;x++) {
					areaVertexArray.put(x*ps).put(y*ps).put(0f);
					areaVertexArray.put(x*ps).put((y+1)*ps).put(0f);
					areaVertexArray.put((x+1)*ps).put((y+1)*ps).put(0f);
					
					areaVertexArray.put((x+1)*ps).put((y+1)*ps).put(0f);
					areaVertexArray.put((x+1)*ps).put(y*ps).put(0f);
					areaVertexArray.put(x*ps).put(y*ps).put(0f);
				}
			}
			areaVertexArray.flip();
		}
	}
	
	public void renderWorld() {
		
	}
	
	public static void renderArea(Area a) {
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		
		GL11.glVertexPointer(3, areaVertexArrayStride, areaVertexArray);
		GL11.glColorPointer(3, true, 3, a.getBGData());
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, areaVertexArraySize);
		
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}
}
