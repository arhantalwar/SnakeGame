package game.handler;

import java.awt.Graphics;
import java.util.LinkedList;

import game.objects.GameObjects;

public class Handler {
	
	public LinkedList<GameObjects> gameObjects = new LinkedList<GameObjects>();
	public LinkedList<GameObjects> path = new LinkedList<GameObjects>();
	
	public void tick() {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObjects temp =  gameObjects.get(i);
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObjects temp =  gameObjects.get(i);
			temp.render(g);
		}
	}
	
	public void addObject(GameObjects obj) {
		gameObjects.add(obj);
	}
	
	public void removeObject(GameObjects obj) {
		gameObjects.remove(obj);
	}
	
	public void addPath(GameObjects obj) {
		path.add(obj);
	}
	
	public void removePath(GameObjects obj) {
		path.remove(obj);
	}
	
}
