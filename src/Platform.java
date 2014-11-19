/** 
* Platform.java
 * @author Garrett J. Beasley
 * 11/16/2014
 * Platform class for making Platform GCompound
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
		
		/**Calls the platform method createPlatform*/
		createPlatform();
	}
	
	/**createPlatform Method where it makes the platform for the gcompound*/
	public void createPlatform()
	{
		/**Creates all the GRect used to make the ball based off the set location startX/startY/platformX/platformY*/
		GRect platform = new GRect(startX,startY, platformX, platformY);
		
		/**Sets the color to the color that is passed into the program*/
		platform.setFillColor(platformColor);
		
		/**Sets the objects to filled true*/
		platform.setFilled(true);
		
		/**Adds the objects to the gcompound*/
		add(platform);
	}
}
