package gui;

import gui.GUIToControlUpdate.Action;

public abstract class UpdateAbstract
{
	public String userMessage = "";
	public Action action;
	
	public abstract String getUserMessage();
	public abstract Action getAction();
}
