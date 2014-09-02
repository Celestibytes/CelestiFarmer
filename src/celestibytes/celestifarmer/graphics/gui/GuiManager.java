package celestibytes.celestifarmer.graphics.gui;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import celestibytes.celestifarmer.graphics.util.TextureLoader;
import celestibytes.celestifarmer.input.util.MouseHelper;
import celestibytes.jgutil.GameInitHelper;

public class GuiManager {
	
	public static int TEXTURE_GUI_CLOSE = -1;
	public static int TEXTURE_GUI_MAXIMIZE = -1;
	public static int TEXTURE_GUI_MINIMIZE = -1;
	public static int TEXTURE_GUI_SIZE_DEFAULT = -1;
	
	public static int TEXTURE_GUI_DECOR = -1;
	
	public static final int dragBarHeight = 25;
	
	public static FloatBuffer guiDecorDta = null;
	
	public static final float[] guiDecorInterl = new float[] {
		// U   V   R   G   B   X     Y     Z
		// Close button
		0.75f, 1f, 1f, 1f, 1f, 0.0f, 0.0f, 0.0f, 
		0.75f, 0f, 1f, 1f, 1f, 0.0f, dragBarHeight, 0.0f, 
		1f, 0f, 1f, 1f, 1f, dragBarHeight, dragBarHeight, 0.0f, 
		
		1f, 0f, 1f, 1f, 1f, dragBarHeight, dragBarHeight, 0.0f,
		1f, 1f, 1f, 1f, 1f, dragBarHeight, 0.0f, 0.0f, 
		0.75f, 1f, 1f, 1f, 1f, 0.0f, 0.0f, 0.0f,
		
		// Minimize
		0.5f, 1f, 1f, 1f, 1f, dragBarHeight, 0.0f, 0.0f,
		0.5f, 0f, 1f, 1f, 1f, dragBarHeight, dragBarHeight, 0.0f,
		.75f, 0f, 1f, 1f, 1f, 2*dragBarHeight, dragBarHeight, 0.0f,
		
		.75f, 0f, 1f, 1f, 1f, 2*dragBarHeight, dragBarHeight, 0.0f,
		.75f, 1f, 1f, 1f, 1f, 2*dragBarHeight, 0.0f, 0.0f,
		0.5f, 1f, 1f, 1f, 1f, dragBarHeight, 0.0f, 0.0f,
		
		// Maximize
		0.25f, 1f, 1f, 1f, 1f, 2*dragBarHeight, 0.0f, 0.0f,
		0.25f, 0f, 1f, 1f, 1f, 2*dragBarHeight, dragBarHeight, 0.0f,
		.5f, 0f, 1f, 1f, 1f, 3*dragBarHeight, dragBarHeight, 0.0f,
		
		.5f, 0f, 1f, 1f, 1f, 3*dragBarHeight, dragBarHeight, 0.0f,
		.5f, 1f, 1f, 1f, 1f, 3*dragBarHeight, 0.0f, 0.0f,
		0.25f, 1f, 1f, 1f, 1f, 2*dragBarHeight, 0.0f, 0.0f,
		
		// Default size
		0f, 1f, 1f, 1f, 1f, 3*dragBarHeight, 0.0f, 0.0f,
		0f, 0f, 1f, 1f, 1f, 3*dragBarHeight, dragBarHeight, 0.0f,
		.25f, 0f, 1f, 1f, 1f, 4*dragBarHeight, dragBarHeight, 0.0f,
		
		.25f, 0f, 1f, 1f, 1f, 4*dragBarHeight, dragBarHeight, 0.0f,
		.25f, 1f, 1f, 1f, 1f, 4*dragBarHeight, 0.0f, 0.0f,
		0f, 1f, 1f, 1f, 1f, 3*dragBarHeight, 0.0f, 0.0f
	};
	
	/**Note: active guis are normally displayed guis or minimized.*/
	public List<Gui> activeGuis;
	private int draggedGuiId = -1;
	
	public static void init() {
		TEXTURE_GUI_CLOSE = TextureLoader.loadTexture("res/textures/gui/icon_gui_close.png");
		TEXTURE_GUI_MINIMIZE = TextureLoader.loadTexture("res/textures/gui/icon_gui_minimize.png");
		TEXTURE_GUI_DECOR = TextureLoader.loadTexture("res/textures/gui/gui_decoration.png");

		guiDecorDta = BufferUtils.createFloatBuffer(guiDecorInterl.length);
		guiDecorDta.put(guiDecorInterl);
		guiDecorDta.flip();
	}
	
	public GuiManager() {
		activeGuis = new ArrayList<Gui>(0);
	}
	
	public static float[] getGuiDecor(float x, float y, float width) {
		float[] ret = new float[6*8];
		System.arraycopy(guiDecorInterl, 0, ret, 0, ret.length);
		ret[5] = x+width-dragBarHeight; ret[6] = y;
		ret[13] = x+width-dragBarHeight; ret[14] = y+dragBarHeight;
		ret[21] = x+width; ret[22] = y+dragBarHeight;
		
		ret[29] = x+width; ret[30] = y+dragBarHeight;
		ret[37] = x+width; ret[38] = y;
		ret[45] = x+width-dragBarHeight; ret[46] = y;
		return ret;
	}
	
	public void interlTest() {
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, TEXTURE_GUI_DECOR);
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		glInterleavedArrays(GL_T2F_V3F, 4*5, guiDecorDta); // GL_T2F_C3F_V3F?
		
		glDrawArrays(GL_TRIANGLES, 0, 24);
		
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		glBindTexture(GL_TEXTURE_2D, 0);
		glDisable(GL_TEXTURE_2D);
	}
	
	private float trsX, trsY; // Current translation
	public void renderGuis() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Gui iter = null;
		
		for(int i=activeGuis.size()-1;i>-1;i--) {
			iter = activeGuis.get(i);
//			GuiRenderer.render(iter);
			iter.renderInter();
			
			
			trsX += iter.getX()-trsX; trsY += iter.getY()-trsY;
		}
		
		glDisable(GL_BLEND);
	}
	
	private boolean leftWasDown = false;
	private boolean middleWasDown = false;
	private boolean rightWasDown = false;
	
	public void mouseUpdate() {
		// LEFT
		if(leftWasDown) {
			if(!MouseHelper.dragLeft.isButtonDown()) {
				mouseUp(0);
				leftWasDown = false;
			}
		} else {
			if(MouseHelper.dragLeft.isButtonDown()) {
				mouseDown(0);
				leftWasDown = true;
			}
		}
		
		// MIDDLE
		if(middleWasDown) {
			if(!MouseHelper.dragMiddle.isButtonDown()) {
				mouseUp(2);
				middleWasDown = false;
			}
		} else {
			if(MouseHelper.dragMiddle.isButtonDown()) {
				mouseDown(2);
				middleWasDown = true;
			}
		}
		
		// RIGHT
		if(rightWasDown) {
			if(!MouseHelper.dragRight.isButtonDown()) {
				mouseUp(1);
				rightWasDown = false;
			}
		} else {
			if(MouseHelper.dragRight.isButtonDown()) {
				mouseDown(1);
				rightWasDown = true;
			}
		}
		if(MouseHelper.dragLeft.mouseDeltaX != 0 || MouseHelper.dragLeft.mouseDeltaY != 0) {
			mouseDrag(0);
		}
		if(MouseHelper.dragMiddle.mouseDeltaX != 0 || MouseHelper.dragMiddle.mouseDeltaY != 0) {
			mouseDrag(2);
		}
		if(MouseHelper.dragRight.mouseDeltaX != 0 || MouseHelper.dragRight.mouseDeltaY != 0) {
			mouseDrag(1);
		}
	}
	
	/**0 = left, 1 = right, 2 = middle*/
	private void mouseDrag(int button) {
		if(button == 0 && draggedGuiId != -1) {
			this.activeGuis.get(draggedGuiId).dragUpdate();
		}
	}
	
	/**0 = left, 1 = right, 2 = middle*/
	private void mouseUp(int button) {
		if(button == 0) {
			if(draggedGuiId != -1) {
				this.activeGuis.get(this.draggedGuiId).renderUpdate();
			}
			this.draggedGuiId = -1;
		}
	}
	
	/**0 = left, 1 = right, 2 = middle*/
	private void mouseDown(int button) {
		if(button == 0) {
			for(int i=0; i < this.activeGuis.size(); i++) {
				if(this.activeGuis.get(i).pointInsideGui(Mouse.getX(), GameInitHelper.getWindowHeight()-Mouse.getY())) {
					if(i != 0) {
						Gui buffer = this.activeGuis.get(i);
						this.activeGuis.add(0, buffer);
						this.activeGuis.remove(i+1);
					}
					if(this.activeGuis.get(0).pointInsideDragBar(Mouse.getX(), GameInitHelper.getWindowHeight()-Mouse.getY())) {
						this.draggedGuiId = 0;
					}
					break;
				}
			}
		}
	}
	
	public void openGui(Gui gui) {
		gui.setupRenderData();
		this.draggedGuiId++;
		this.activeGuis.add(0, gui);
	}
	
	public void closeGui(int id) {
		if(id > -1 && id < this.activeGuis.size()) {
			this.activeGuis.remove(id);
			if(this.draggedGuiId > id) {
				this.draggedGuiId--;
			}
		}
	}
}
