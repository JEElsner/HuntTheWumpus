/* Jonathan Elsner
 * 2019-05-11 (Completion Date)
 * 
 */

package wumpus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.BiConsumer;

public class CaveGen
{
	// A Lambda Expression used for debug purposes
	public static BiConsumer<Integer, MovementDirection> onDoorAdd;
	
	public static final int NUM_OF_ROOMS = 30;
	
	// The probability of how often a room will have that many doors.
	// the number in each index is the probability out of the whole sum that a room will have that index number of doors.
	// So if the total of the array is 100, and there is a 10 at index 1, there is a 10% chance each room will have 1 door.
	public static final int[] DOOR_PROBS = {0, 10, 60, 30};
	
	public static int[][] generateNewCave()
	{
		return generateNewCave((long) Math.random() * Long.MAX_VALUE);
	}
	
	public static int[][] generateNewCave(long seed)
	{
		MovementDirection[][] cave;
		
		// Generate new caves until one has all rooms accessible
		// This usually happens on the first try, so it shouldn't take too long
		do
			cave = makeCave(seed);
		while(!areAllRoomsConnected(cave));

		// The notation I use to keep track of the tunnels between cave rooms is different than the notation used in the Cave object,
		// so convert it to that notation
		return convertNotation(cave);
	}
	 
	/* The notation I use to keep track of the tunnels between cave rooms is different than the notation used in the Cave object,
	 * so convert it to that notation.
	 * 
	 * Convert the notation of the cave tunnels from MovementDirections to just the adjacent rooms.
	 */
	private static int[][] convertNotation(MovementDirection[][] cave)
	{
		// Create an array for the new representation
		int[][] roomNotation = new int[cave.length][6];
		
		for(int room = 0; room < cave.length; room++)
		{
			// For each possible location of a door in a room, check to see if a door is there, if it is, add that to the array of
			// accessible rooms, otherwise, put a zero to indicate there is no tunnel in that direction
			for(MovementDirection dir : MovementDirection.values())
			{
				for(MovementDirection door : cave[room])
					if(door == dir)
					{
						// Add the door in the current room
						roomNotation[room][dir.ordinal()] = Map.getNearbyRoom(room + 1, dir);
						
						// Add the door in the adjacent room too
						roomNotation[Map.getNearbyRoom(room + 1, dir) - 1][MovementDirection.getOpposite(dir).ordinal()] = room + 1;
					}
					else
						roomNotation[room][dir.ordinal()] = 0;
			}
		}
		
		// Return the new notation
		return roomNotation;
	}
	
	/* Method to check if all of the rooms in a cave are connected to each other and accessible
	 */
	public static boolean areAllRoomsConnected(MovementDirection[][] cave)
	{
		// Call a helper method to recursively move through the rooms.
		boolean[] reached = roomsConnectedHelper(cave, 0, null);
		
		// If any one of the rooms hasn't been reached, the boolean at that room's number's index of the array will be false
		// If it hasn't been reached, return false
		for(boolean b : reached)
			if(!b)
				return false;
		
		// ... otherwise return true, indicating all rooms can be reached
		return true;
	}
	
	/* Helper method to determine whether all rooms are connected
	 */
	private static boolean[] roomsConnectedHelper(MovementDirection[][] cave, int room, boolean[] reached)
	{
		// If a null boolean array was passed, create one
		if(reached == null)
			reached = new boolean[cave.length];
		
		// Indicate that the room analyzed is reachable
		reached[room] = true;
		
		// Go through each door, and continue recursively searching for rooms
		for(MovementDirection door : cave[room])
		{
			if(door == null)
				continue;
			
			// Check if the adjacent room has already been found, if so, skip it
			if(reached[Map.getNearbyRoom(room + 1, door) - 1])
				continue;
			
			// Continue the search in the adjacent room. When it finishes, replace the old array
			reached = roomsConnectedHelper(cave, Map.getNearbyRoom(room + 1, door) - 1, reached);
		}
		
		return reached;
	}

	/* Generate the actual cave
	 * 
	 * This function does not guarantee that all rooms are reachable and connected.
	 * 
	 * Essentially, first it places one door in every room. In doing so, it may force some rooms to have more than one door, in the worst
	 * case scenario where the room it is trying to place a door in is surrounded on all sides by rooms with doors, however, first preference
	 * is given to exactly one door per room. Second, it then randomly determines how many doors it wants in each room, then attempts to add
	 * them, if it can't get to that number, because all neigbors already have the maximum number of doors, then it stops trying in that room.
	 */
	public static MovementDirection[][] makeCave(long seed)
	{
		Random rand = new Random();
		rand.setSeed(seed);
		
		MovementDirection[][] cave = new MovementDirection[30][3];
		
		// Loop through each room, and place at least one door in each room
		for(int room = 0; room < cave.length; room++)
		{
			// If there's already at least one door in the room, skip it
			if(cave[room][0] != null)
				continue;
			
			// Establish a list of possible door directions in the room
			// This list will slowly be removed from as possible directions are tried eliminated
			ArrayList<MovementDirection> dirs = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.values()));
			do // Run this while there are no doors in the room
			{
				MovementDirection newDoor;
				
				if(dirs.size() > 0)
				{
					// If not all directions have been tried, pick one at random
					newDoor = dirs.get(rand.nextInt(dirs.size()));
				}else
				{
					// Otherwise, if none of the directions work conventionally, force a door to be placed in a direction
					// This happens when all nearby rooms have at least one door, so one must be forced to have two
					newDoor = MovementDirection.values()[rand.nextInt(MovementDirection.values().length)];
				}
				
				// Get the neighboring room for the room and door direction picked
				int neighbor = Map.getNearbyRoom(room + 1, newDoor) - 1;
				
				// Try to find an empty slot in the neighboring room to place the door
				for (int nDoor = 0; nDoor < cave[neighbor].length; nDoor++)
				{
					// If there is already a door in the neighboring room, and not all of the door directions have been tried yet, break, and try a different direction
					if (nDoor > 0 && dirs.size() > 0)
						break;

					// Find the first available index to put in a new door
					// We do this instead of just calling cave[neighbor][0], because the neighbor might already have at least one door
					// 		if all the possible door directions have already been looked over once (meaning all nearby rooms have at least one door)
					if (cave[neighbor][nDoor] == null)
					{
						cave[room][0] = newDoor; // Add the new door
						cave[neighbor][nDoor] = MovementDirection.getOpposite(newDoor); // Add the door to the neighboring room as well

						// Call a callback function that can be used for debug if necessary
						// For example, if we want to watch the generation of the cave, we can put code that updates the visual in onDoorAdd
						if (onDoorAdd != null)
							onDoorAdd.accept(room, newDoor);

						// Stop trying to put a door in the neighboring room
						break;
					}
				}
				
				dirs.remove(newDoor);
			}while(cave[room][0] == null);
		} // End for
		
		for(int room = 0; room < cave.length; room++)
		{
			// Sum the probabilities of the number of doors
			int totalProb = 0;
			for(int prob : DOOR_PROBS)
				totalProb += prob;
			
			// Using the total probabilities, figure out how many rooms there will be
			// This is the "probability" per se, not the actual number of rooms
			// Like rolling a die and doing something if the result is between 1 and 3
			int doorsProb = rand.nextInt(totalProb);
			
			// Determine how many doors that probability is
			// Should work for any probability and number of doors in a room
			/* This bit can be compared to an if structure that says:
			 * if doorsProb < probability of one door (e.g.)
			 * else if doorsProb < probability of two doors (e.g.)
			 * ...
			 * 
			 * it's a bit complicated to figure out, sorry
			 */
			int numDoors = 0; // The ideal number of doors for the room
			for(int min = 0; numDoors < DOOR_PROBS.length && doorsProb >= min; numDoors++, min += DOOR_PROBS[numDoors]);
			
			// --- The part that actually assigns doors to the room --- //
			
			int actualDoors = 0; // The number of doors actually placed in the room so far
			
			// Account for the doors placed in the first loop
			for(MovementDirection dir : cave[room])
				if(dir != null)
					actualDoors++;
			
			// Do most of the same stuff in the first loop. They probably could be combined into one loop. It was late at night when I wrote
			// this... I'm also lazy now, so they're staying separate
			// Good luck figuring this one out. It was hard to write, so it should be hard to read :)
			ArrayList<MovementDirection> dirs = new ArrayList<MovementDirection>(Arrays.asList(MovementDirection.values()));
			while(actualDoors < numDoors && dirs.size() > 0)
			{
				MovementDirection newDoor = dirs.get(rand.nextInt(dirs.size()));
				
				int neighbor = Map.getNearbyRoom(room + 1, newDoor) - 1;
				for(int nDoor = 1; nDoor < cave[neighbor].length; nDoor++)
				{
					if(cave[neighbor][nDoor] == null)
					{
						cave[room][actualDoors] = newDoor;
						cave[neighbor][nDoor] = MovementDirection.getOpposite(newDoor);
						
						if(onDoorAdd != null)
							onDoorAdd.accept(room, newDoor);
						
						actualDoors++;
						break;
					}
				}
				
				dirs.remove(newDoor);
			} // End while
		}
		
		return cave;
	} // End makeCave
}
