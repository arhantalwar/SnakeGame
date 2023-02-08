package game.keyInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.ID.ID;
import game.handler.Handler;
import game.objects.GameObjects;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			GameObjects temp = handler.gameObjects.get(i);
			
			if(temp.getId() == ID.head) {
				
				if(key == KeyEvent.VK_W) temp.setY(temp.getY() - 20);
				if(key == KeyEvent.VK_S) temp.setY(temp.getY() + 20);
				if(key == KeyEvent.VK_A) temp.setX(temp.getX() - 20);
				if(key == KeyEvent.VK_D) temp.setX(temp.getX() + 20);
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
	}
	
}
