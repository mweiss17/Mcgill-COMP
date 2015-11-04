package ca.mcgill.cs.comp303.capone.model;

import java.util.Date;

/**
 * This class maps to a single speech (that is part of a house aDebate)
 * resource from the openparliament.ca API.
 * 
 * Here is an example of the data format: http://api.openparliament.ca/debates/2013/6/18/tom-lukiwski-1/
 * 
 * Immutable.
 * 
 */
public class Speech
{
	/**
	 * @return The main label for this speech. e.g., "Routine Proceedings"
	 */
	public String getHeader1()
	{
		return null; // TODO
	}

	/**
	 * @return The secondary label for this speech. e.g., "Government Response to Petitions"
	 */
	public String getHeader2()
	{
		return null; // TODO
	}

	/**
	 * @return The content of the speech.
	 */
	public String getContent()
	{
		return null; // TODO
	}

	/**
	 * @return The time at which the speech was given.
	 */
	public Date getTime()
	{
		return null; // TODO
	}
}
