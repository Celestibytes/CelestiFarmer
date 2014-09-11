package celestibytes.celestifarmer.graphics.util;

import okkapel.kkplglutil.util.Colour;

public class ProgressBar {
	private float x, y, width, height, progress;
	private boolean horisontal;
	private Colour border, barcol;
	
	public ProgressBar(float x, float y, float width, float height, float progress, boolean horisontal, Colour border, Colour barcol) {
		this.x = x; this.y = y; this.width = width; this.height = height; this.progress = progress; this.horisontal = horisontal; this.border = border; this.barcol = barcol;
	}
	
	/**If you construct with this you'll need to specify x, y, width, height and progress at render time*/
	public ProgressBar(boolean horisontal, Colour border, Colour barcol) {
		this(0f,0f,0f,0f,0f,horisontal, border, barcol);
	}
	
	/**If you construct with this you'll need to specify x, y, width, height, progress and horizontal/vertical at render time*/
	public ProgressBar(Colour border, Colour barcol) {
		this(true, border, barcol);
	}
}
