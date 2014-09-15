package celestibytes.cetiengine.entity;

public abstract class EntityMobile extends EntityBase {
	float dx, dy, speed;
	
	public void positionUpdate() {
		this.x += dx;
		this.y += dy;
	}
	
}
