package pl.noname.ooc.actors.play;

public interface WorldObject {
	public boolean collides();
	public boolean interacts();
	public void showInteraction();
}
