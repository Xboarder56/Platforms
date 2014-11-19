/**
 * MAIN.java
 * @author Garrett J. Beasley
 * 11/01/2014
 * Main class for lunching Butterfly and Net
 */

/** Imports for the project */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.io.IODialog;
import acm.program.GraphicsProgram;

public class MAIN extends GraphicsProgram
{
	
	/**Constant Window Size*/
	public static final int WINDOW_X = 500, WINDOW_Y = 1000, BALL_SIZE=45;
	private static final int SCORE_HEIGHT = 25, SCORE_WIDTH = 80;
	public double startSpeed = 1;
	public double gravity = startSpeed;
	public double platformSpeed = startSpeed;
	public static final int PLATFORM_SIZE_X = 200, PLATFORM_SIZE_Y = 20;
	public static final int keyboardMove = 40;
	private Platform [] platform;
	public static final int NUM_PLATFORMS = 3;

	
	/**Creates objects for global access throughout the class*/
	private GLabel scoringLabel;
	public int score;
	private Ball ball;
	//private Platform platform;
	
	// tell whether to end game
	boolean continueGame = true;
	
	public void init()
	{
		/**Set the size of the applet*/
		setSize(WINDOW_X,WINDOW_Y);
		
		/**Set the color of the applet*/
		setBackground(Color.CYAN);
		
		/**Adds the key listern to watch for up and down keys being pressed*/
		addKeyListeners();
		
		/** add score label */
		scoringLabel = new GLabel("Score: 0", (WINDOW_X - SCORE_WIDTH) / 2, SCORE_HEIGHT);	
		scoringLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		add(scoringLabel);
	}
	
	
	
	public void run()
	{
		/**Stops the applet from running right away until its clicked and then after will animate the applet*/
		 waitForClick();
		 playGame();
	}
	
	public void buildPlatforms()
	{
		platform = new Platform [NUM_PLATFORMS];
		
		// initialize each element of the array
		for (int i = 0; i <  NUM_PLATFORMS; i++) 
		{
			platform[i] = new Platform (Color.BLUE, (int) (Math.random( )*PLATFORM_SIZE_X), (int) (Math.random( )*PLATFORM_SIZE_Y));
			platform[i].setLocation((int) (Math.random( )*(WINDOW_X/2-(PLATFORM_SIZE_X))), (int) (Math.random( )*(WINDOW_Y/2-(PLATFORM_SIZE_Y))));
			add(platform[i]);
		}
	}
	
	public void playGame()
	{
		
		buildPlatforms();
		
		ball = new Ball(Color.RED, BALL_SIZE);
		ball.setLocation((int) (Math.random( )*(WINDOW_X-(BALL_SIZE))), (int) (Math.random( )*(WINDOW_Y/2-(BALL_SIZE))));
		add(ball);
		
		while(continueGame)
		{
			
			ball.move(0,startSpeed);
			pause(20);
			applyGravity();
			
			if (ball.getY() - ball.getHeight() >= WINDOW_Y)
			{
				score--;
				reachedApplet();
			}
			
			if (ball.getY()  <= 0)
			{
				score++;
				reachedApplet();
			}
			
			if (ball.getX()  <= 0)
			{
				ball.move(keyboardMove, 0);
			}
			
			
			if (ball.getX() + ball.getWidth() > WINDOW_X)
			{
				ball.move(-keyboardMove, 0);
			}
			
			for (int i = 0; i < platform.length; i++) 
			{
					
				platform[i].move(0,-platformSpeed);
				
				if (platform[i].getY()  <= 0)
				{
					platform[i].setLocation((int) (Math.random( )*(WINDOW_X/2-(PLATFORM_SIZE_X))), WINDOW_Y);
				}
				
					
				if (platform[i].getBounds().intersects(ball.getBounds()))
				{
					removeGravity();
				}
			
			}
			
		}
	}
	
	
	
	
	/**keypressed method called from the top in the init*/
	public void keyPressed(KeyEvent event)
	{
		/**Checks to see if the key pressed = the down key if it does enter the if statement*/
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			ball.move(keyboardMove,0);
		}
		
		/**Checks to see if the key pressed = the down key if it does enter the if statement*/
		if (event.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			ball.move(-keyboardMove,0);
		}
	}
	
	
	/**playerScored method will be called when the butterfly intersects with the net.*/
	public void playerScored() 
	{
		/**display new score on screen*/
		scoringLabel.setLabel("Score: " + score);
	}
	
	public void gameOver() 
	{
		IODialog dialog = new IODialog();
		dialog.println("Game Over");
	}
	
	public void applyGravity()
	{
		gravity+=0.02;
		startSpeed=gravity;
	}
	
	public void removeGravity()
	{
		gravity=0;
		ball.move(0, -platformSpeed*2);
	}
	
	public void reachedApplet()
	{
		playerScored();
		ball.setLocation((int) (Math.random( )*(WINDOW_X-(BALL_SIZE))), (int) (Math.random( )*(WINDOW_Y/2-(BALL_SIZE))));
		gameOver();
		continueGame = false;
	}
	

}
