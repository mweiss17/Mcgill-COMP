package ca.mcgill.cs.comp303.capone.loaders.op;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import ca.mcgill.cs.comp303.capone.loaders.ParliamentLoader;
import ca.mcgill.cs.comp303.capone.loaders.ParliamentLoaderException;
import ca.mcgill.cs.comp303.capone.loaders.op.stubs.JMP;
import ca.mcgill.cs.comp303.capone.model.Parliament;

import com.google.gson.Gson;

/**
 * A builder that can build the model from serialized 
 * JSON objects stored at specific locations on disk. Objects of this class should 
 * store the root of the data tree internally (e.g., C:\workspace...\data). The 
 * last branches of the path map directly to the OpenParliament API.
 * 
 * We packaged the data under Capone-M1/data. You can (should) use this as the root path.
 */
public class OpenParliamentFileLoader implements ParliamentLoader
{
	// Some formatters you might find useful.
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// You should keep this root to make sure we can run your project from our environment.
	private static final String DATA_FILE_SUBDIR = "." + File.separator + "data" + File.separator;
	private static final String QUERY_CONTEXT = new File(DATA_FILE_SUBDIR).getAbsolutePath() + File.separator;

	// Relative paths to JSON files	
	private static final String CONTEXT_POLITICIANS = "politicians" + File.separator;
	private static final String CONTEXT_SPEECHES = "debates" + File.separator;
	private static final String CONTEXT_RSS = "rss" + File.separator;

	private static final String JSON_SUFFIX = ".json";
	
	/* 
	 * The relative location indicates the subpath leading to a
	 * specific politician. For the complete list, see:
	 * http://api.openparliament.ca/politicians/
	 * an example of input for pRelativeLocation is: gord-brown
	 * @see ca.mcgill.cs.comp303.capone.loaders.ParliamentLoader#loadMP(java.lang.String, ca.mcgill.cs.comp303.capone.model.Parliament)
	 */
	@Override
	public String loadMP(String pRelativeLocation, Parliament pParliament)
	{
		// This method produces a complete path pointing to the
		// JSON file for this MP.
		String jsonUri = getMPJsonUri(pRelativeLocation);
		
		// JSON file can get loaded easily into stub objects
		// using the Google GSON library. See the ...op.stubs 
		// package. Do not change any stub.
		JMP jmp = getGson(jsonUri, JMP.class);
		
		// Load all the information available about the MP from the MP's
		// page on OpenParliament.ca.
		
		return null; // The primary key of the MP just loaded.
	}


	/* 
     * For milestone 1, only load the speeches into the MP object.
     * However, load the entire speech data for each speech entry 
     * in the RSS feed. This will require you to fetch complementary
     * data from the JSON files under /debates.
     * Only a subset of the speeches are available in the local folder
     * (we did not include speeches in Committees, from example). 
     * If you don't find a certain speech mentioned in the RSS feed,
     * just ignore it.
	 * @see ca.mcgill.cs.comp303.capone.loaders.ParliamentLoader#loadRecentEvents(java.lang.String, ca.mcgill.cs.comp303.capone.model.Parliament)
	 */
	@Override
	public void loadRecentEvents(String pMPKey, Parliament pParliament)
	{
		// Remember: use the getRSSFeedURI method in an MP to get access to their feed.
		// new RssSaxParser().parse(xmlStream); // API Call to use the SAX Parser
	}
	
	// Below are some potentially useful utility methods. You may need to 
	// experiment with them a bit to make sure you understand how they work.
	
	/*
	 * Takes as input the speech link in the RSS item and returns 
	 * the partial path leading to the corresponding speech file in
	 * the debate directory tree.
	 */
	@SuppressWarnings("unused")
	private String getSpeechContext(String pRssSpokeInTheHouseLink) 
	{
		String httpLink = pRssSpokeInTheHouseLink;
		String speechContextHttp = CONTEXT_SPEECHES.replace(File.separator, "/");
		int start = httpLink.indexOf(speechContextHttp)+speechContextHttp.length();
		String context = httpLink.substring(start, httpLink.length()-1).replace("/", File.separator);
		return context;
	}	
	
	/**
	 * Uses the google-gson library to convert JSON to Java objects (prefixed 
	 * those objects with J, in package ...loaders.op.stubs directory).
	 * 
	 * @param pJsonUri Filename of the JSON data
	 * @param ptype A Class object from the package ...loaders.op.stubs
	 * @return A GSON stub object, specified by the parameter "type" 
	 * CSOFF:
	 */
	public <T> T getGson(String pJsonUri, Class<T> ptype)
	{
		InputStreamReader json = new InputStreamReader(getInputStream(pJsonUri));
		T gson = new Gson().fromJson(json, ptype);
		return gson;
	}
	// CSON:
	
	/**
	 * @param pMPRelativeLocation Relative location that identifies the MP, returned by getMPRelativeLocations
	 * @return Filename of the JSON data  on the given MP
	 */
	public String getMPJsonUri(String pMPRelativeLocation)
	{
		return QUERY_CONTEXT + CONTEXT_POLITICIANS + pMPRelativeLocation + JSON_SUFFIX;
	}

	/**
	 * @param pSpeechContext The speech context obtained from the link from the 
	 * 		   RSS feed "Spoke in the house" event, e.g., debates/2013/6/17/thomas-mulcair-4/
	 * @return  Filename of the JSON data on transcript of the given MP's speeches
	 */
	public String getSpeechJsonUri(String pSpeechContext)
	{
		return QUERY_CONTEXT + CONTEXT_SPEECHES + pSpeechContext + JSON_SUFFIX;
	}

	/**
	 *  @param pMPRelativeLocation Relative location that identifies the MP, returned by getMPRelativeLocations
	 * @return Filename of the RSS feed for the specified MP.
	 */
	public String getRssUri(String pMPRelativeLocation)
	{
		return QUERY_CONTEXT + CONTEXT_RSS + pMPRelativeLocation + ".xml";
	}

	/*
	 * Return a FileInputStream for the given path
	 */
	private InputStream getInputStream(String pFilePath)
	{
		try
		{
			File file = new File(pFilePath);
			return new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			throw new ParliamentLoaderException(e);
		}
	}
	
	/**
	 * @return the relative locations of all the MPs.
	 */
	public Iterator<String> getMPRelativeLocations() 
	{
		List<String> result = new ArrayList<String>();
		String dir = QUERY_CONTEXT + CONTEXT_POLITICIANS; 
		Collection<File> files = FileUtils.listFiles(new File(dir), new WildcardFileFilter("*.json"), null);
		
		for ( File file : files )
		{
			String f = file.getName();
			result.add(f.substring(0, f.length()-".json".length()));
		}
		return result.iterator();
	}
}
