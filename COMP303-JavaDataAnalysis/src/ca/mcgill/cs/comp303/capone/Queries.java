package ca.mcgill.cs.comp303.capone;

import java.util.Set;

/**
 * Compute the results of two queries on the data.
 */
public final class Queries
{
	private Queries(){}
	
	/**
	 * @return The email address of the MP who has served the longest 
	 * in parliament, in terms of total number of days in any membership.
	 * If there are ties, return all ties in any order.
	 */
	public static Set<String> q1LongestServingMPs()
	{
		return null; // TODO enhance this algorithm
	}
	
	/**
	 * @return The email address of the MP who has served in the largest
	 * number of different political parties. If there are ties, return 
	 * all ties in any order.
	 */
	public static Set<String> q2LargestNumberOfParties()
	{
		return null; // TODO enhance this algorithm
	}
	
	/**
	 * @return The number of speeches in which Thomas Mulcair
	 * uses the word "Conservative" (not the plural variant, 
	 * but case-insensitive).
	 */
	public static int q3NumberOfConservativeWordUsage()
	{
		return Integer.MAX_VALUE; // not the real answer
	}
}
