/**
 * MAIN.java
 * @author Garrett J. Beasley
 * 11/17/2014
 * Main class for lunching Platform and the Ball
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
	public static final int keyboardMove = 40, NUM_PLATFORMS = 3;

	
	/**Creates objects for global access throughout the class*/
	private GLabel scoringLabel;
	public int score;
	private Ball ball;
	boolean continueGame = true;
	private Platform [] platform;
	
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
		/**builds new platforms based off the number of platforms passed in*/
		platform = new Platform [NUM_PLATFORMS];
		
		/**Loops the platforms for the amount of platforms passed in (3)*/
		for (int i = 0; i <  NUM_PLATFORMS; i++) 
		{
			/**Creates new platform random x and random y withn the set randoms passed in from the PLATFORM_SIZE_X/Y*/
			platform[i] = new Platform (Color.BLUE, (int) (Math.random( )*PLATFORM_SIZE_X), (int) (Math.random( )*PLATFORM_SIZE_Y));
			
			/**Sets random location for the platforms created withn the top half of the window to start*/
			platform[i].setLocation((int) (Math.random( )*(WINDOW_X/2-(PLATFORM_SIZE_X))), (int) (Math.random( )*(WINDOW_Y/2-(PLATFORM_SIZE_Y))));
			
			/**Adds the platforms to the project*/
			add(platform[i]);
		}
	}
	
	public void buildBall()
	{
		/**Creates new ball with set size passed in from the BALL_SIZE*/
		ball = new Ball(Color.RED, BALL_SIZE);
		
		/**Sets random location for the ball created withn the top half of the window to start*/
		ball.setLocation((int) (Math.random( )*(WINDOW_X-(BALL_SIZE))), (int) (Math.random( )*(WINDOW_Y/2-(BALL_SIZE))));
		
		/**Adds the platforms to the project*/
		add(ball);
	}
	
	public void playGame()
	{
		/**Calls the buildPlatforms method into the game*/
		buildPlatforms();
		
		/**Calls the buildBall method into the game*/
		buildBall();
		
		/**While loop for the game animation*/
		while(continueGame)
		{
			/**Moves the ball at the set speed*/
			ball.move(0,startSpeed);
			
			/**Slows the loop by 20 seconds*/
			pause(20);
			
			/**Apply the effects of gravity because they are always pulling on the ball*/
			applyGravity();
			
			/**Checks the ball to see if it has hit the bottom*/
			if (ball.getY() - ball.getHeight() >= WINDOW_Y)
			{
				/**-+1 each time the user hits the bottom of the applet*/
				score--;
				
				/**Call the reachedApplet command*/
				reachedApplet();
			}
			
			/**Checks the ball to see if it has hit the top*/
			if (ball.getY()  <= 0)
			{
				/**-+1 each time the user hits the bottom of the applet*/
				score++;
				
				/**Call the reachedApplet command*/
				reachedApplet();
			}
			
			/**Checks the ball to keep it in the applet*/
			if (ball.getX()  <= 0)
			{
				/**move back if the user tries to move the ball out*/
				ball.move(keyboardMove, 0);
			}
			
			/**Checks the ball to keep it in the applet*/
			if (ball.getX() + ball.getWidth() > WINDOW_X)
			{
				/**move back if the user tries to move the ball out*/
				ball.move(-keyboardMove, 0);
			}
			
			/**Loop inside the animation loop to detect how long the platform arry is and loop that many times to create all 3 platforms*/
			for (int i = 0; i < platform.length; i++) 
			{
				/**Moves i platform */	
				platform[i].move(0,-platformSpeed);
				
				/**IF i platform reaches the top move it to random x in the bottom of the window*/	
				if (platform[i].getY()  <= 0)
				{
					/**IF i platform reaches the top move it to random x in the bottom of the window*/
					platform[i].setLocation((int) (Math.random( )*(WINDOW_X/2-(PLATFORM_SIZE_X))), WINDOW_Y);
				}
				
				/**IF i platform intersects with the ball remove the gravity from the ball*/
				if (platform[i].getBounds().intersects(ball.getBounds()))
				{
					/**IF i platform intersects with the ball remove the gravity from the ball*/
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
			/**Move the ball to the right the specified amount*/
			ball.move(keyboardMove,0);
		}
		
		/**Checks to see if the key pressed = the down key if it does enter the if statement*/
		if (event.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			/**Move the ball to the left the specified amount*/
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
		/**Makes the pop-up window to display to the user*/
		IODialog dialog = new IODialog();
		
		/**Print the string Game over to the user*/
		dialog.println("Game Over");
		
		/**End the game, exit the while loop*/
		continueGame = false;
	}
	
	public void applyGravity()
	{
		/**increments the gravity by + 0.02 every time the loop is looped*/
		gravity+=0.02;
		
		/**sets the startSpeed to gravity instead of the regular predefined start speed*/
		startSpeed=gravity;
	}
	
	public void removeGravity()
	{
		/**Sets the gravity back to Zero because we are on the platform*/
		gravity=0;
		
		/**Move the ball up with the platform*/
		ball.move(0, -platformSpeed*2);
	}
	
	
	public void reachedApplet()
	{
		/**Updates the score board based of the method*/
		playerScored();
		
		/**sets the ball to random location in the window*/
		ball.setLocation((int) (Math.random( )*(WINDOW_X-(BALL_SIZE))), (int) (Math.random( )*(WINDOW_Y/2-(BALL_SIZE))));
		
		/**ends the game*/
		gameOver();
	}
	

}
