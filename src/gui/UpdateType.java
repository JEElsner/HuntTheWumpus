/* An Enum representing the type of an Update for the GUI or Controller
 */

package gui;

import java.util.ArrayList;

import wumpus.MovementDirection;

public enum UpdateType
{
	// === Enum Values === //
	
	
	// Updates that could either be for the GUI or Control
	DEBUG(Object.class, Object.class), // You know, debug
	
	/* To Control:
	 * -------------
	 * Purpose: Notify start of new game
	 * Objects: none
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: IDK yet
	 * Objects:
	 */
	NEW_GAME(Void.class, Void.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to receive High Score
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to display high scores
	 * Objects: Array of high scores
	 */
	GET_HIGH_SCORE(Void.class, ArrayList.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to receive Triva Q's based on action performed
	 * Objects: Trivia
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify GUI to display trivia screen
	 * Objects: String trivia question
	 */
	GET_TRIVIA(String.class, String.class),
	
	// Updates that are likely for GUI only
	/* To Control:
	 * -------------
	 * Purpose: Notify to check adjacent rooms for encounters
	 * Objects: Map
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: IDK
	 * Objects:
	 * 
	 * This may not be necessary
	 */
	CHECK_ENCOUNTER(Void.class, Void.class),

	/* To GUI: Warnings that there are pits near the room the player is in
	 * Object: int number of pits in surrounding rooms
	 */
	PIT_WARNING(Void.class, Integer.class),
	
	/* To GUI: Warnings that there are bats near the room the player is in
	 * Object: int number of bats in surrounding rooms
	 */
	BAT_WARNING(Void.class, Integer.class),
	
	/* To GUI: Warnings that the wumpus is near the player
	 * Object: None
	 */
	WUMPUS_WARNING(Void.class, Void.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to display the win screen
	 * Objects: int Score of the player
	 */
	DISPLAY_WIN(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to display the lose screen
	 * Objects: int Score of the player
	 */
	DISPLAY_LOSE(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to tell user they've encountered a bat
	 * Objects: String trivia question
	 */
	ENCOUNTER_BAT(Void.class, String.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to tell user they've encountered a pit
	 * Objects: String Trivia question
	 */
	ENCOUNTER_PIT(Void.class, String.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to tell user they've encountered the Wumpus
	 * Objects: String trivia question
	 */
	ENCOUNTER_WUMPUS(Void.class, String.class),
	
	// Updates that are likely for Control only
	/* To Control:
	 * -------------
	 * Purpose: Notify of Movement and Direction
	 * Objects: MovementDirection
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the gui it is the player's turn to move
	 * Objects: N/A
	 */
	MOVE(MovementDirection.class, Void.class), // The user requested the player move, the data should be the MovementDirection enum
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an arrow
	 * Objects: Trivia, Player
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of how many arrows the player has
	 * Objects: int number of arrows
	 */
	PURCHASE_ARROW(Void.class, Void.class), // The user requested an arrow is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an secret
	 * Objects: Trivia, Player
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI the user has purchased a secret
	 * Objects: String secret
	 */
	PURCHASE_SECRET(Void.class, String.class), // The user requested a secret is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at shooting an arrow
	 * Objects: Map, Player(if successful for high scores, and win)
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI the user has shot an arrow
	 * Objects:
	 */
	SHOOT_ARROW(Void.class, Void.class), // The user requested an arrow is shot, the data should be the MovementDirection enum
	
	/* To GUI: Notify the GUI the arrow did not hit anything
	 * Objects: int number of arrows left
	 */
	ARROW_MISS(Void.class, Integer.class)
	
	;
	
	// --- Value Data --- //
	
	public final Class<?> TO_CONTROL_DATA_TYPE;
	public final Class<?> TO_GUI_DATA_TYPE;
	
	private UpdateType(Class<?> toControl, Class<?> toGUI)
	{
		TO_CONTROL_DATA_TYPE = toControl;
		TO_GUI_DATA_TYPE = toGUI;
	}
	
	public Class<?> getDataType(boolean isToControl)
	{
		if(isToControl)
			return TO_CONTROL_DATA_TYPE;
		else
			return TO_GUI_DATA_TYPE;
	}
}