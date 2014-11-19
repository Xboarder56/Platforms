/** 
* Butterfly.java
 * @author Garrett J. Beasley
 * 11/01/2014
 * Butterfly class for making Butterfly GCompound
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
		
		/**Calls the butterfly method createButterfly*/
		createBall();
	}
	
	/**createball Method where it makes the ball for the gcompound*/
	public void createBall()
	{
		GOval ball = new GOval(startX,startY, ballDiam, ballDiam);
		ball.setFillColor(ballColor);
		ball.setFilled(true);
		add(ball);
	}
}
