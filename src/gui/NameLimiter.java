package gui;

import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NameLimiter extends PlainDocument
{
	private int limit;
	
	public NameLimiter(int lim)
	{
		this.limit = lim;
	}
	
	public void insertString(int offset, String str, AttributeSet set) throws BadLocationException
	{
		{
			if(getLength() + str.length() > limit)
			{
				str = str.substring(0, limit - getLength());
			}
			
			super.insertString(offset, str, (javax.swing.text.AttributeSet) set);
		}
	}
}
