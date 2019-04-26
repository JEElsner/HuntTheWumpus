/* An Enum representing the type of an Update for the GUI or Controller
 */

package gui;

public enum UpdateType
{
	// === Enum Values === //
	
	
	// Updates that could either be for the GUI or Control
	DEBUG, // You know, debug
	
	NEW_GAME,
	
	GET_HIGH_SCORE,
	GET_TRIVIA,
	
	// Updates that are likely for GUI only
	CHECK_ENCOUNTER,
	
	DISPLAY_WIN,
	DISPLAY_LOSE,
	
	ENCOUNTER_BAT,
	ENCOUNTER_PIT,
	ENCOUNTER_WUMPUS,
	
	// Updates that are likely for Control only
	MOVE, // The user requested the player move, the data should be the MovementDirection enum
	
	PURCHASE_ARROW, // The user requested an arrow is purchased
	PURCHASE_SECRET, // The user requested a secret is purchased
	
	SHOOT_ARROW, // The user requested an arrow is shot, the data should be the MovementDirection enum
	
	;
}