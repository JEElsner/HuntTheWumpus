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
	 * Purpose:
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
	 * Purpose:
	 * Objects:
	 */
	GET_HIGH_SCORE,
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to receive Triva Q's based on action performed
	 * Objects: Trivia
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
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
	 * Purpose:
	 * Objects:
	 */
	CHECK_ENCOUNTER,
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	DISPLAY_WIN,
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	DISPLAY_LOSE,
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	ENCOUNTER_BAT,
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	ENCOUNTER_PIT,
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
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
	 * Purpose:
	 * Objects:
	 */
	MOVE, // The user requested the player move, the data should be the MovementDirection enum
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an arrow
	 * Objects: Trivia, Player
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	PURCHASE_ARROW, // The user requested an arrow is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an secret
	 * Objects: Trivia, Player
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	PURCHASE_SECRET, // The user requested a secret is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at shooting an arrow
	 * Objects: Map, Player(if successful for high scores, and win)
	 * 
	 * To GUI:
	 * ------------
	 * Purpose:
	 * Objects:
	 */
	SHOOT_ARROW, // The user requested an arrow is shot, the data should be the MovementDirection enum
	
	;
}