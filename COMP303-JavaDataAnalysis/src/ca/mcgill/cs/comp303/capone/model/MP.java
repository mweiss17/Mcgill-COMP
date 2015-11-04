package ca.mcgill.cs.comp303.capone.model;


/**
 * This class represents a Member of Parliament, the representative of the 
 * voters to the Parliament of Canada, the federal legislative branch of Canada. 
 * [Reference: Wikipedia (http://en.wikipedia.org/wiki/Member_of_Parliament)]
 * 
 * This class directly maps to a single politician resource from the openparliament.ca API.
 * For an example, check out the data format of an MP: api.openparliament.ca/politicians/tom-lukiwski/
 * 
 * The minimum set of data to capture in this class is indicated through the getter methods.
 * 
 * This class should not be immutable. It will be a dynamic entity in the final application.
 */
public class MP
{
	/**
	 * @return A primary key (unique identifier) for this object. We will use an MP's email
	 * as primary key.
	 */
	public String getPrimaryKey()
	{
		return null; // TODO
	}
	
	/**
	 * @return The family name(s) of the MP
	 */
	public String getFamilyName()
	{
		return null; // TODO
	}
	
	/**
	 * @return The given name(s) of the MP
	 */
	public String getGivenName()
	{
		return null; // TODO
	}
	
	/**
	 * @return The given and family name(s), separated by a white space.
	 */
	public String getName()
	{
		return null; // TODO
	}
	
	/**
	 * @return The email address of the MP. This is used as the primary key.
	 */
	public String getEmail()
	{
		return null; // TODO
	}
	
	/**
	 * @return The phone number of the MP
	 */
	public String getPhoneNumber()
	{
		return null; // TODO
	}
	
	/**
	 * @return This MP's official RSS feed URL.
	 * Note that this is not found directly in the MP's JSON data. You
	 * have to find it somewhere else. Hint: look at the image field 
	 * in the JSON data, and the corresponding RSS URL from the website. 
	 */
	public String getRSSFeedURI()
	{
		return null; // TODO
	}
	
	// PROVIDE: Functionality to add memberships AND make membership information 
	// available to client objects.
	
	/**
	 * @return The total number of memberships for this MP, including the current one.
	 */
	public int getNumberOfMemberships()
	{
		return Integer.MAX_VALUE; // TODO Probably not the correct value.
	}
	
	/**
	 * @return The current MP membership  
	 */
	public Membership getCurrentMembership() 
	{
       return null; // TODO
	}
	
	// PROVIDE: Functionality to add speeches AND make speeches information 
	// available to client objects.
	

	/* 
	 * Two MPs objects are equals if they represent the same
	 * physical MP.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pMP)
	{
		return false; // TODO
	}

	@Override
	public int hashCode()
	{
		return Integer.MAX_VALUE; // TODO 
	}

}
