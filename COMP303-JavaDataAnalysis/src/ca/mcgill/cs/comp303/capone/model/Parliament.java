package ca.mcgill.cs.comp303.capone.model;


/**
 * An object representing a graph of data about the Canadian parliament, including MPs, speeches, etc.
 * 
 * There should only ever be one instance of this class in any given JVM.
 * It is permissible to access this instance through a global variable.
 * 
 */
public final class Parliament
{
	private static final Parliament INSTANCE = new Parliament();
	
	private Parliament() {}

	/**
	 * Support for the singleton design pattern.
	 * 
	 * @return The model instance.
	 */
	public static Parliament getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 * Returns the MP with this key, or null if no information is available.
	 * @param pKey
	 * @return
	 */
	public MP getMP(String pKey)
	{
		return null;
	}
}
