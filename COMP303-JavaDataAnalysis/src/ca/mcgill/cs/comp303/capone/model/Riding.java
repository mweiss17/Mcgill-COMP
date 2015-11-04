package ca.mcgill.cs.comp303.capone.model;

/**
 * This class represents an electoral district in Canada, a geographical constituency 
 * upon which a Member of Parliament to the Canadian House of Commons represents. 
 * [Reference: Wikipedia (http://en.wikipedia.org/wiki/Electoral_district_%28Canada%29)]
 * 
 * Immutable.
 * There should only even one instance of each unique Riding object
 * within a given JVM.
 */
public final class Riding
{
	/**
	 * The unique ID for this riding, as obtained from OpenParliament. E.g., 4700
	 */
	public int getId()
	{
		return Integer.MAX_VALUE; // Fix this.
	}

	/**
	 * The official name of the riding, e.g., "Regina\u2014Lumsden\u2014Lake Centre"
	 */
	public String getName()
	{
		return null; // TODO
	}

	/**
	 * The province code, e.g, SK
	 */
	public String getProvince()
	{
		return null; // TODO
	}
	
}
