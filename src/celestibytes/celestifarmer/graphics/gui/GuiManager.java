package celestibytes.celestifarmer.graphics.gui;

import java.util.ArrayList;
import java.util.List;

public class GuiManager {
	/**Note: active guis are normally displayed guis or minimized.*/
	public List<Gui> activeGuis;
	private int topMostGuiId = -1;
	
	public GuiManager() {
		activeGuis = new ArrayList<Gui>();
	}
	
	public void update(int mouseX, int mouseY) { // MouseStatus?
		for(int i=0;i<activeGuis.size();i++) {
			 // Update activeGuis.get(i);
		}
	}
	
	public void renderGuis() {
		for(int i=0;i<activeGuis.size();i++) {
			if(i != topMostGuiId) {
				// Render
			}
		}
	}
	
	public void openGui(Gui gui) {
		this.activeGuis.add(gui);
		// TODO: set as topmost gui!
	}
}
