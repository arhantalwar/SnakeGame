package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.ID.ID;
import game.handler.Handler;

public class Apple extends GameObjects {
	
	private Handler handler = new Handler();
	private Random r = new Random();
	private int s_X, s_Y;
	private int score;
	
	
	public Apple(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	@Override
	public void tick() {
		
		collison(); // head and apple
		noCollison(); // body and apple should not overlap
		
	}

	private void noCollison() {
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			if(handler.gameObjects.get(i).getId() == ID.body) {
				GameObjects body = handler.gameObjects.get(i);
				
				if(getBound().intersects(body.getBound())) {
					
					s_X = (r.nextInt(391)/10) * 20;
					s_Y = (r.nextInt(291)/10) * 20;
					setX(s_X);
					setY(s_Y);
					
				}
			}
		}
		
	}

	private void collison() {
		
		for(int i = 0; i < handler.gameObjects.size(); i++) {
			if(handler.gameObjects.get(i).getId() == ID.head) {
				GameObjects head = handler.gameObjects.get(i);
				
				if(getBound().intersects(head.getBound())) {
					
					s_X = (r.nextInt(391)/10) * 20;
					s_Y = (r.nextInt(291)/10) * 20;
					
					// Apple relocate
					
					if((this.s_X != head.x && this.s_Y != head.x)) {
						setX(s_X);
						setY(s_Y);
					}else {
						do {
							s_X = (r.nextInt(391)) * 2;
							s_Y = (r.nextInt(291)) * 2;
						}while(this.s_X != head.x && this.s_Y != head.y);
						setX(s_X);
						setY(s_Y);
					}
					
					// Adding Body
					
					handler.gameObjects.add(new Body(0, 0, ID.body));
					// it adds at the end (checker) => handler.gameObjects.add(new Green(0, 0, ID.green));
					score++;
					
				}
				
			}
		}
		
		
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect(x, y, 20, 20);
		
		g.setColor(Color.WHITE);
		g.drawString("Score : " + score, 20, 580);
		
		
	}

	@Override
	public Rectangle getBound() {
		return new Rectangle(x, y, 20, 20);
	}
	
	
	
}
