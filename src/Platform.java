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

public class Platform extends GCompound
{
	/**Init of variables*/
	private int startX, startY, platformX, platformY; 
	private Color platformColor;
	public static final int locationX =0, locationY = 0;
	
	public Platform(Color color, int xDim, int yDim)
	{
		/**helper classes changes from input to another variable accessible by the program*/
		startX=locationX;
		startY=locationY;
		platformColor = color;
		platformX = xDim;
		platformY = yDim;
		
		/**Calls the butterfly method createButterfly*/
		createPlatform();
	}
	
	/**createball Method where it makes the ball for the gcompound*/
	public void createPlatform()
	{
		GRect platform = new GRect(startX,startY, platformX, platformY);
		platform.setFillColor(platformColor);
		platform.setFilled(true);
		add(platform);
	}
}
