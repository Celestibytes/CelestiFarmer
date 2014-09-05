package celestibytes.celestifarmer.world;

import celestibytes.celestifarmer.graphics.util.GLRenderMethod;

/** Anything that isn't a 'simple' field plot*/
public class WorldSpecialRenderObj {
	private GLRenderMethod renderMethod;
	private int renderObjId = -1;
	
	public WorldSpecialRenderObj() {
		
	}
	
	public int getRenderObjId() {
		return this.renderObjId;
	}
}
