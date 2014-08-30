package celestibytes.celestifarmer.graphics.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import celestibytes.celestifarmer.input.util.MouseHelper;
import celestibytes.jgutil.GameInitHelper;

public class GuiManager {
	/**Note: active guis are normally displayed guis or minimized.*/
	public List<Gui> activeGuis;
	private int draggedGuiId = -1;
	
	
	public GuiManager() {
		activeGuis = new ArrayList<Gui>(0);
	}
	
	public void renderGuis() {
		for(int i=activeGuis.size()-1;i>-1;i--) {
			GuiRenderer.render(activeGuis.get(i));
		}
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
