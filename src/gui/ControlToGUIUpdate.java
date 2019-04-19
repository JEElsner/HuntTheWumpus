package gui;

import gui.GUIToControlUpdate.Action;

public class ControlToGUIUpdate extends UpdateAbstract
{
	public String userMessage = "";
	public Action action;
	
	public String getUserMessage()
	{
		return userMessage;
	}
	public Action getAction()
	{
		return action;
	}
}

