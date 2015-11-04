package ca.mcgill.cs.comp303.capone.model;

import java.util.Date;

/**
 * This class maps to a single politician membership resource from the openparliament.ca API. 
 * See an example of the data format from this example: api.openparliament.ca/politicians/memberships/1534/
 * 
 * Memberships should be naturally sortable in decreasing chronological order.
 * 
 * Not all data must be captured by this class. The minimum set is represented by the methods.
 * 
 * The class should be immutable.
 * 
 * @see http://api.openparliament.ca/politicians/memberships/
 */
public class Membership
{
	public Party getParty()
	{
		return null; // TODO
	}

	public Riding getRiding()
	{
		return null; // TODO
	}

	public Date getStartDate()
	{
		return null; // TODO
	}

	/**
	 * 
	 * @return If this is a past membership, it returns the end date of that membership.
	 * If this is a current membership, return null;
	 */
	public Date getEndDate()
	{
		return null; // TODO
	}
	
}
