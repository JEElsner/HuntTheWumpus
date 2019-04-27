/* An Enum representing the type of an Update for the GUI or Controller
 */

package gui;

public enum UpdateType
{
	// === Enum Values === //
	
	
	// Updates that could either be for the GUI or Control
	DEBUG, // You know, debug
	
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
	NEW_GAME,
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to receive High Score
	 * Objects: Player
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI to display high scores
	 * Objects: Array of high scores
	 */
	GET_HIGH_SCORE,
	
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
	GET_TRIVIA,
	
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
	CHECK_ENCOUNTER,

	/* To GUI: Warnings that there are pits near the room the player is in
	 * Object: int number of pits in surrounding rooms
	 */
	PIT_WARNING,
	
	/* To GUI: Warnings that there are bats near the room the player is in
	 * Object: int number of bats in surrounding rooms
	 */
	BAT_WARNING,
	
	/* To GUI: Warnings that the wumpus is near the player
	 * Object: None
	 */
	WUMPUS_WARNING,
	
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
	DISPLAY_WIN,
	
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
	DISPLAY_LOSE,
	
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
	ENCOUNTER_BAT,
	
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
	ENCOUNTER_PIT,
	
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
	ENCOUNTER_WUMPUS,
	
	// Updates that are likely for Control only
	/* To Control:
	 * -------------
	 * Purpose: Notify of Movement and Direction
	 * Objects: Map
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the gui it is the player's turn to move
	 * Objects: N/A
	 */
	MOVE, // The user requested the player move, the data should be the MovementDirection enum
	
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
	PURCHASE_ARROW, // The user requested an arrow is purchased
	
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
	PURCHASE_SECRET, // The user requested a secret is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at shooting an arrow
	 * Objects: Map, Player(if successful for high scores, and win)
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI the user has shot an arrow
	 * Objects: int number of arrows the user has
	 */
	SHOOT_ARROW, // The user requested an arrow is shot, the data should be the MovementDirection enum
	
	/* To GUI: Notify the GUI the arrow did not hit anything
	 * Objects: N/A?
	 */
	ARROW_MISS
	
	;
}