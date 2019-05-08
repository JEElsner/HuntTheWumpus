package wumpus;

public enum MovementDirection
{
	UP,	// 0
	UP_RIGHT,	// 1
	DOWN_RIGHT,	// etc.
	DOWN,
	DOWN_LEFT,
	UP_LEFT;
	
	public static MovementDirection getOpposite(MovementDirection d)
	{
		return values()[(d.ordinal() + 3) % 6];
	}
}
