package game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.ID.ID;

public class Body extends GameObjects {

	public Body(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(163, 218, 141));
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
