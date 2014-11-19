/** 
* Ball.java
 * @author Garrett J. Beasley
 * 11/16/2014
 * Ball class for making Ball GCompound
 */

 /** Imports for the project */
 import java.awt.Color;
 import acm.graphics.GCompound;
 import acm.graphics.*;

public class Ball extends GCompound
{
	/**Init of variables*/
	private int startX, startY, ballDiam; 
	private Color ballColor;
	public static final int locationX =0, locationY = 0;
	
	public Ball(Color color, int ballSize)
	{
		/**helper classes changes from input to another variable accessible by the program*/
		startX=locationX;
		startY=locationY;
		ballColor = color;
		ballDiam = ballSize;
		
		/**Calls the ball method createBall*/
		createBall();
	}
	
	/**createball Method where it makes the ball for the gcompound*/
	public void createBall()
	{
		/**Creates all the govals used to make the ball based off the set location startX/startY/ballDiam*/
		GOval ball = new GOval(startX,startY, ballDiam, ballDiam);
		
		/**Sets the color to the color that is passed into the program*/
		ball.setFillColor(ballColor);
		
		/**Sets the objects to filled true*/
		ball.setFilled(true);
		
		/**Adds the objects to the gcompound*/
		add(ball);
	}
}
