package celestibytes.celestifarmer.graphics.gui;

import celestibytes.celestifarmer.input.util.MouseHelper;
import celestibytes.celestifarmer.util.Colour;

public class Gui implements IGui {
	
	private static final int dragBarHeight = 20;
	private int x, y, width, height;
	private Colour bgColor = new Colour(.3f, .3f, .3f, .7f);
	private boolean draggedByMouse = false;
	private boolean minimized = false;
	private boolean maximized = false;
	private boolean resizable = false;
	
	public Gui(int x, int y, int width, int height, boolean resizable, Colour bgColor) {
		this(x, y, width, height, resizable);
		this.bgColor = bgColor;
	}
	
	public Gui(int x, int y, int width, int height, boolean resizable) {
		this(x, y, width, height);
		this.resizable = resizable;
	}
	
	public Gui(int x, int y, int width, int height) {
		this.x = x; this.y = y; this.width = width; this.height = height;
	}
	
	public Gui setResizable(boolean v) {
		this.resizable = v;
		return this;
	}
	
	public void dragUpdate() {
		if(this.draggedByMouse || true) {
			this.x += MouseHelper.dragLeft.mouseDeltaX;
			this.y -= MouseHelper.dragLeft.mouseDeltaY;
		}
	}
	
	public void render() {
		
	}
	
	public void addX(int v) {
		this.x += v;
	}
	
	public void addY(int v) {
		this.y += v;
	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Colour getBGColor() {
		return this.bgColor;
	}
	
	public boolean isBeingDraggedByMouse() {
		return this.draggedByMouse;
	}
	
	public void setBeingDragged(boolean dragged) {
		this.draggedByMouse = dragged;
	}
	
	public boolean pointInsideDragBar(int x, int y) {
		return this.x <= x && x < this.x + this.width && this.y <= y && y < this.y + dragBarHeight;
	}
	
	public boolean pointInsideGui(int x, int y) {
		return this.x <= x && x < this.x + this.width && this.y <= y && y < this.y + this.height;
	}
	
	public void setMinimized(boolean v) {
		this.minimized = v;
	}
	
	public void setMaximized(boolean v) {
		this.maximized = v;
	}
	
	public boolean isMinimized() {
		return this.minimized;
	}
	
	public boolean isMaximized() {
		return this.maximized;
	}

}
