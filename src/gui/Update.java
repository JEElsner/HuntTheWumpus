/* 2019-04-19
 * 
 * Object that represents an update either from the GUI to the Controller or from the Controller to
 * the GUI, which must be processed on the respective thread (GUI: EDT, Controller: Worker)
 */

package gui;

public class Update
{		
	// --- Instance Data --- //
	
	private boolean updateProcessed = false; // Has the update been fully consumed by the recipient?
	private Object data; // Associated data with the update
	private UpdateType type; // The type of update, determining what the recipient needs to do with it
	private boolean forControl; //Tells who the update if for
	
	public Update(UpdateType type, boolean forControl, Object data)
	{
		this.type = type;
		this.data = data;
		this.forControl = forControl;
	}
	
	public Update(UpdateType type, boolean forControl)
	{
		this(type, forControl, null);
	}
	
	// Indicate that the update has been fully consumed by the recipient
	public void finishProcessing()
	{
		updateProcessed = true;
	}
	
	// --- Getters --- //
	
	public boolean isUpdateProcessed()
	{
		return updateProcessed;
	}
	public Object getData()
	{
		return data;
	}
	public UpdateType getType()
	{
		return type;
	}
	
	public boolean isForControl()
	{
		return forControl;
	}
	
	public boolean isForGUI()
	{
		return !(forControl);
	}
}
