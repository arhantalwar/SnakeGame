package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.ID.ID;
import game.engine.Game;
import game.handler.Handler;

public class Head extends GameObjects {
	
	private Handler handler = new Handler();
	
	int oldBlock_X = 0;
	int oldBlock_Y = 60;
	int temp1_X, temp1_Y;
	int temp2_X, temp2_Y;
	int currentBlock_X, currentBlock_Y;
	int counter_x = 0;
	int counter_y = 0;
	int diff_X, diff_Y;
	int t_X, t_Y;
	private GameObjects apple;
	
	
	public Head(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			if(handler.gameObjects.get(i).getId() == ID.apple) {
				apple = handler.gameObjects.get(i);
			}
		}
		
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		currentBlock_X = handler.gameObjects.get(1).getX();
		currentBlock_Y = handler.gameObjects.get(1).getY();
		
		x = Game.clamp(x, 0, 780);
		y = Game.clamp(y, 0, 580);
		
		diff_X = apple.x - x;
		diff_Y = apple.y - y;
		
		t_X = diff_X / 20;
		t_Y = diff_Y / 20;
		
		counter_x++;
		
		if(counter_x == 5) {
			
			if( t_X > 0 ) {
				handler.gameObjects.get(1).setX(currentBlock_X + 20);
				
			}else if( t_X < 0 ) {
				handler.gameObjects.get(1).setX(currentBlock_X - 20);
				
			}
			
			counter_x = 0;
			
		}
		
		counter_y++;
		
		if(counter_y == 5) {
			
			if( t_Y > 0 ) {
				handler.gameObjects.get(1).setY(currentBlock_Y + 20);
				
			}else if( t_Y < 0 ) {
				handler.gameObjects.get(1).setY(currentBlock_Y - 20);
				
			}
			
			counter_y = 0;
			
		}
		
		
		// Relocating Snake
		
		if(handler.gameObjects.size() % 2 == 0) {
			updatePosEven();
		}
		
		if(handler.gameObjects.size() % 2 != 0) {
			updatePosOdd();
		}
		
	}

	private void updatePosOdd() {
		
		while(oldBlock_X != currentBlock_X || oldBlock_Y != currentBlock_Y ) {
			
			temp1_X = handler.gameObjects.get(2).getX();
			temp1_Y = handler.gameObjects.get(2).getY();
			
			handler.gameObjects.get(2).setX(oldBlock_X);
			handler.gameObjects.get(2).setY(oldBlock_Y);
			
			temp2_X = handler.gameObjects.get(3).getX();
			temp2_Y = handler.gameObjects.get(3).getY();
			
			handler.gameObjects.get(3).setX(temp1_X);
			handler.gameObjects.get(3).setY(temp1_Y);
			
			
			
			for(int i = 4; i < handler.gameObjects.size() - 1; i+=2) {
				
				temp1_X = handler.gameObjects.get(i).getX();
				temp1_Y = handler.gameObjects.get(i).getY();
				
				handler.gameObjects.get(i).setX(temp2_X);
				handler.gameObjects.get(i).setY(temp2_Y);
				
				temp2_X = handler.gameObjects.get(i + 1).getX();
				temp2_Y = handler.gameObjects.get(i + 1).getY();
				
				handler.gameObjects.get(i + 1).setX(temp1_X);
				handler.gameObjects.get(i + 1).setY(temp1_Y);
				
			}
			
			handler.gameObjects.getLast().setX(temp2_X);
			handler.gameObjects.getLast().setY(temp2_Y);
			
			oldBlock_X = currentBlock_X;
			oldBlock_Y = currentBlock_Y;
			
		}
		
	}

	private void updatePosEven() {
			
			while(oldBlock_X != currentBlock_X || oldBlock_Y != currentBlock_Y ) {
				
				temp1_X = handler.gameObjects.get(2).getX();
				temp1_Y = handler.gameObjects.get(2).getY();
				
				handler.gameObjects.get(2).setX(oldBlock_X);
				handler.gameObjects.get(2).setY(oldBlock_Y);
				
				temp2_X = handler.gameObjects.get(3).getX();
				temp2_Y = handler.gameObjects.get(3).getY();
				
				handler.gameObjects.get(3).setX(temp1_X);
				handler.gameObjects.get(3).setY(temp1_Y);
				
				
				
				for(int i = 4; i < handler.gameObjects.size(); i+=2) {
					
					temp1_X = handler.gameObjects.get(i).getX();
					temp1_Y = handler.gameObjects.get(i).getY();
					
					handler.gameObjects.get(i).setX(temp2_X);
					handler.gameObjects.get(i).setY(temp2_Y);
					
					temp2_X = handler.gameObjects.get(i + 1).getX();
					temp2_Y = handler.gameObjects.get(i + 1).getY();
					
					handler.gameObjects.get(i + 1).setX(temp1_X);
					handler.gameObjects.get(i + 1).setY(temp1_Y);
					
				}
				
				oldBlock_X = currentBlock_X;
				oldBlock_Y = currentBlock_Y;
				
			}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(20, 99, 86));
		g.fillRect(x, y, 20, 20);
		
		/* Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.ORANGE);
		g2d.draw(getBound()); */
		
		
	}

	@Override
	public Rectangle getBound() {
		return new Rectangle(x, y, 20, 20);
	}
	
	
	
}
