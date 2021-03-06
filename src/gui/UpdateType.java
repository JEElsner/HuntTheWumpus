/* An Enum representing the type of an Update for the GUI or Controller
 */

package gui;

import java.util.ArrayList;

import wumpus.MovementDirection;

public enum UpdateType
{
	// === Enum Values === //
	
	// REVIEW, some of the information below needs to be corrected, go over with Logan
	
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
	NEW_GAME(Integer.class, Void.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify Control of player name
	 * Objects: none
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: 
	 * Objects:
	 */
	PLAYER_NAME(String.class, Void.class),
	
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
	GET_HIGH_SCORE(Void.class, new ArrayList<String>().getClass()),
	
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
	GET_SCORE_STATS(Integer.class, int[].class),
	
	/* To Control: N/A
	 * 
	 * To GUI: Sends the statistics about the last trivia question, how many trivia questions have been
	 * answered, how many need to be answered to pass the trivia, and how many more questions can be asked
	 * before the user fails
	 * 
	 * Objects: An array of int values where index:
	 * 		0: 1 if the answer to the last question was correct, 0 otherwise
	 * 		1: the number of questions the user has guessed correctly
	 * 		2: the number of questions the user has been asked
	 * 		3: the number of questions remaining (before the user fails)
	 */
	TRIVIA_STATS(Void.class, int[].class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to receive Triva Q's based on action performed
	 * 			Actually, this may not be necessary to be sent to Control
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify GUI to display trivia screen
	 * Objects: String trivia question
	 */
	GET_TRIVIA_QUESTION(String.class, String.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify to give Control the user answer and check if correct
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Giving the GUI a boolean for is correct
	 * Objects: Boolean if is correct
	 */
	GIVE_ANSWER(String.class, boolean.class),
	
	/* To Control: N/A
	 * 
	 * To GUI:
	 * Tell the GUI if the user has succeed or failed at the entire round of questions for the hazard.
	 * (e.g. has the user answered 2/3 questions correct to escape a pit)
	 */
	TRIVIA_SUCCESS(Void.class, Boolean.class),
	
	// Updates that are likely for GUI only

	/* To GUI: Warnings that there are pits near the room the player is in
	 * Object: int number of pits in surrounding rooms **If 0 there are not pits
	 */
	PIT_WARNING(Void.class, Integer.class),
	
	/* To GUI: Warnings that there are bats near the room the player is in
	 * Object: int number of bats in surrounding rooms **If 0 there are not bats
	 */
	BAT_WARNING(Void.class, Integer.class),
	
	/* To GUI: Warnings that the wumpus is near the player
	 * Object: int number, 1 if wumpus is nearby and 0 if not
	 */
	WUMPUS_WARNING(Void.class, Integer.class),
	
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
	ENCOUNTER_BAT(Void.class, Void.class),
	
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
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of Movement and Direction
	 * Objects: MovementDirection
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Tells the GUI the player has moved rooms
	 * Objects: int index of new room
	 */
	MOVE(MovementDirection.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: N/A
	 * Objects: N/A
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the gui it is the player's turn to move
	 * Objects: Directions the player can move to get out of the new room
	 * 			the 0th index will contain the direction from which the player came
	 */
	NEW_DOORS(Void.class, MovementDirection[].class), // The user requested the player move, the data should be the MovementDirection enum
	
	// Updates that are likely for Control only
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an arrow
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of how many arrows the player has
	 * Objects: String trivia question to begin asking questions to get an arrow
	 */
	PURCHASE_ARROW(Void.class, String.class), // The user requested an arrow is purchased
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at purchasing an secret
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI the user has purchased a secret
	 * Objects: String trivia question to begin asking questions to get a secret
	 */
	PURCHASE_SECRET(Void.class, String.class), // The user requested a secret is purchased
	
	/* To Control: N/A
	 * 
	 * To GUI: The new secret the user gets
	 * 
	 * REVIEW It seems like secrets are separate from trivia. trivia is specifically an answer to a question,
	 * while a secret can be that or a few other bits of information. so...
	 */
	GET_SECRET(Void.class, String.class),
	
	/* To Control: N/A
	 * 
	 * To GUI: The new trivia answer the user gets
	 * 
	 * REVIEW It seems like secrets are separate from trivia. trivia is specifically an answer to a question,
	 * while a secret can be that or a few other bits of information. so...
	 */
	GET_TRIVIA_ANSWER(Void.class, String.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of attempt at shooting an arrow
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI the user has shot an arrow
	 * Objects: integer number of arrows left
	 */
	SHOOT_ARROW(MovementDirection.class, Integer.class), // The user requested an arrow is shot, the data should be the MovementDirection enum
	
	/* To GUI: Notify the GUI the arrow did not hit anything
	 * Objects: int number of arrows left
	 */
	ARROW_MISS(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of need for coins
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of current coin total
	 * Objects:
	 */
	GET_COINS(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of need for coins
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of max coins
	 * Objects:
	 */
	MAX_COINS_REACHED(Void.class, Void.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of need for current player score
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of current player score
	 * Objects:
	 */
	GET_PLAYER_SCORE(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of need for number of turns
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of number of turns
	 * Objects:
	 */
	GET_NUM_OF_TURNS(Void.class, Integer.class),
	
	/* To Control:
	 * -------------
	 * Purpose: Notify of need for arrows
	 * Objects:
	 * 
	 * To GUI:
	 * ------------
	 * Purpose: Notify the GUI of arrows
	 * Objects:
	 */
	GET_ARROWS(Void.class, Integer.class),
	
	/* To Control: Notify that the GUI is closing and the program should exit
	 * Object: N/A
	 * 
	 * To GUI: N/A
	 */
	WINDOW_CLOSING(Void.class, Void.class),
	
	; // <---- Hey there's a semi-colon here! Yay!
	
	// --- Instance Data --- //
	
	// The type of data that would be transmitted to Control for this update
	public final Class<?> TO_CONTROL_DATA_TYPE;
	
	// The type of data that would be transmitted to GUI (& the EDT) for this update
	public final Class<?> TO_GUI_DATA_TYPE;
	
	// Constructor
	private UpdateType(Class<?> toControl, Class<?> toGUI)
	{
		TO_CONTROL_DATA_TYPE = toControl;
		TO_GUI_DATA_TYPE = toGUI;
	}
	
	/* Returns the type of data being transmitted for the UpdateType and direction,
	 * based on the boolean parameter of whether the data is going to the control object or not.
	 * 
	 * This is essentially a quality of life function, because the fields being accessed are already
	 * public, this just makes it even easier to use in Update.java, because there is already a boolean
	 * there called forControl which can be passed to this function to get the proper data type.
	 */
	public Class<?> getDataType(boolean isToControl)
	{
		if(isToControl)
			return TO_CONTROL_DATA_TYPE;
		else
			return TO_GUI_DATA_TYPE;
	}
}