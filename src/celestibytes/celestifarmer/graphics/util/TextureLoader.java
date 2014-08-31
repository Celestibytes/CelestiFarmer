package celestibytes.celestifarmer.graphics.util;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class TextureLoader {
	
	public static int loadTexture(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File(filename));
			
			// array for image pixel data
			int[] pixels = new int[img.getWidth()*img.getHeight()*4];
			
			// write pixel data to the array
			img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
			
			// byte buffer for pixel data
			ByteBuffer pixs = BufferUtils.createByteBuffer(img.getWidth()*img.getHeight()*4);
			
			// put pixel data into the buffer
			for(int y=0;y<img.getHeight();y++) {
				for(int x=0;x<img.getWidth();x++) {
					int pixl = pixels[y * img.getWidth() + x];
					pixs.put((byte)((pixl >> 16) & 255)); // Red
					pixs.put((byte)((pixl >> 8) & 255));  // Green
					pixs.put((byte)((pixl) & 255));       // Blue
					pixs.put((byte)((pixl >> 24) & 255)); // Alpha
				}
			}
			
			// required
			pixs.flip();
			int texId = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texId);
			System.out.println(texId);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, img.getWidth(), img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixs);
			
			// apparently I forgot these
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			
			return texId;
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.err.println("Texture loading failed!");
		return -1;
	}
}
