package game.engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.ID.ID;
import game.handler.Handler;
import game.keyInput.KeyInput;
import game.objects.Apple;
import game.objects.Body;
import game.objects.Head;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	private boolean running = false;
	private Handler handler = new Handler();
	
	Thread thread = new Thread(this);
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "SNAKE", this);
		handler.addObject(new Apple(20, 60, ID.apple, handler));
		handler.addObject(new Head(0, 60, ID.head, handler));
		handler.addObject(new Body(0, 40, ID.body));
		handler.addObject(new Body(0, 20, ID.body));
	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running) {
			requestFocus();
			long  now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick();
				delta--;
			}
			
			if(running) {
				render();
			}
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			
			createBufferStrategy(3);
			return;
			
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
		
	}
	
	public static int clamp(int var, int min, int max) {
		
		if(var >= max) {
			return max;
		}else if(var <= min) {
			return min;
		}else {
			return var;
		}
		
	}
	
	
	
}
