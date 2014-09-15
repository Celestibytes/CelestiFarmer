package celestibytes.cetiengine.util;

import celestibytes.cetiengine.util.CollisionHelper.CollisionShape;

public interface ICollidable {
	public int getX();
	public int getY();
	
	public CollisionShape getCollShape();
}
