package gui;

public class GUIToControlUpdate extends UpdateAbstract
{
	// Static Action Types
	public static enum Action 
	{
		GET_HIGH_SCORE, CHECK_ENCOUNTER, DISPLAY_WIN, DISPLAY_LOSE, GET_TRIVIA, MOVE, PURCHASE_ARROW, 
		PURCHASE_SECRET, SHOOT_ARROW, ENCOUNTER_BAT, ENCOUNTER_PIT, ENCOUNTER_WUMPUS
	}
	
	// --- Instance Data --- //
	
	public boolean messageProcessed = false;
	public String userMessage = "";
	public Action action;
	
	GUIToControlUpdate(boolean processed, String userM, Action a)
	{
		messageProcessed = processed;
		userMessage = userM;
		action = a;
	}
	
	public void finishProcessing()
	{
		messageProcessed = true;
	}
	
	// --- Getters --- //
	
	public boolean isMessageProcessed()
	{
		return messageProcessed;
	}
	public String getUserMessage()
	{
		return userMessage;
	}
	public Action getAction()
	{
		return action;
	}
}
