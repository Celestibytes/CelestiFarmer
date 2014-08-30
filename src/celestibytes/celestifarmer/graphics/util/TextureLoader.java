package celestibytes.celestifarmer.graphics.util;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class TextureLoader {
	
	public static int loadTexture(String filename) {
		try {
			
			BufferedImage img = ImageIO.read(new File(filename));
			int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
			ByteBuffer pixs = BufferUtils.createByteBuffer(img.getWidth()*img.getHeight()*4);
			for(int i=0;i<pixels.length;i++) {
//				pixs.put((byte)((pixels[i] >> 16) & 255)); // Red
//				pixs.put((byte)((pixels[i] >> 8) & 255));  // Green
//				pixs.put((byte)((pixels[i]) & 255));       // Blue
//				pixs.put((byte)((pixels[i] >> 24) & 255)); // Alpha
				pixs.put((byte)255);pixs.put((byte)0);pixs.put((byte)0);pixs.put((byte)255);
			}
			pixs.flip();
			int texId = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, img.getWidth(), img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_BYTE, pixs);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			return texId;
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.err.println("Texture loading failed!");
		return -1;
	}
}
