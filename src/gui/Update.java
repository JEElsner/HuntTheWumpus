/* 2019-04-19
 * 
 * Object that represents an update either from the GUI to the Controller or from the Controller to
 * the GUI, which must be processed on the respective thread (GUI: EDT, Controller: Worker)
 * 
 * This object should be essentially immutable, we don't want any of the data changing after an update
 * has been published, we could make the fields final...
 */

package gui;

public class Update
{		
	// --- Instance Data --- //
	
	private boolean updateProcessed = false; // Has the update been fully consumed by the recipient?
	private Object data; // Associated data with the update
	private UpdateType type; // The type of update, determining what the recipient needs to do with it
	private boolean forControl; //Tells who the update if for
	
	/* Create a new Update
	 * 
	 * UpdateType type: the UpdateType enum of the update, specifying essentially what the update is
	 * 		notifying about
	 * boolean forControl: whether or not the update is meant to be sent to control (true),
	 * 		or the GUI (false)
	 * Object data: the associated information that needs to be transmitted with the update,
	 * 		must have the proper type associated with the UpdateType specified
	 */
	public Update(UpdateType type, boolean forControl, Object data)
	{
		try // It's kind of weird that we have to catch the exceptions that we throw, but that's the only way they show up
		{
			// Check whether the data passed is of the right type for the UpdateType
			if(data != null)
			{
				// If the data isn't null, it has a type, which should match the type for UpdateType
				
				if(!type.getDataType(forControl).isInstance(data)) // Check whether the types match
					throw new IllegalArgumentException("Invalid data for UpdateType " + type + ": " + data.getClass().getSimpleName());
			}else if(type.getDataType(forControl) != Void.class) // If the data is null, make sure that the UpdateType doesn't pass data
			{
				throw new IllegalArgumentException("Non-null data expected for UpdateType: " + type);
			}
		}catch(IllegalArgumentException ex)
		{
			ex.printStackTrace();
			return;
		}
		
		// Set the values for the fields
		this.type = type;
		this.data = data;
		this.forControl = forControl;
		
		System.out.println("\t" + toString());
	}
	
	// Another Constructor that takes no data
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
	
	/* Method to facilitate ease of understanding when reading code
	 * 
	 * Returns the opposite of isForControl, meaning if the update isn't meant to be sent to control
	 * it is inherently meant to be sent to the GUI
	 */
	public boolean isForGUI()
	{
		return !(forControl);
	}
	
	public String toString()
	{
		return type.toString() + " -> " + (forControl ? "CONTROL" : "GUI");
	}
}
