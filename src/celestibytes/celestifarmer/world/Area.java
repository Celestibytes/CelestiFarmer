package celestibytes.celestifarmer.world;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import celestibytes.celestifarmer.graphics.util.Colors;

public class Area {
	public static final int areaPlots = 32*32;
	// 32*32 plot - size area
	private ByteBuffer groundColors;
	private FieldPlot[] plots;
	// Area coordinates
	private int ax, ay;
	
	public Area() {
		createGround();
		plots = new FieldPlot[areaPlots];
	}
	
	public void setPlot() {
		
	}
	
	public void createGround() {
		groundColors = BufferUtils.createByteBuffer(32*32*6*3);
		int color = 0;
		int counter = 0;
		for(int i=0;i<32*32*6;i++) {
			switch(color) {
			case 0:
				groundColors.put(Colors.DARK_GREEN);
				break;
			case 1:
				groundColors.put(Colors.YELLOW);
				break;
			case 2:
				groundColors.put(Colors.BLUE);
				break;
			default:
				groundColors.put(Colors.BROWN);
				break;
			}
			counter++;
			if(counter > 5) {
//				color++;
				if(color > 2) {
					color = 0;
				}
				counter = 0;
			}
			
		}
		groundColors.flip();
	}
	
	public ByteBuffer getBGData() {
		return this.groundColors;
	}
	
}