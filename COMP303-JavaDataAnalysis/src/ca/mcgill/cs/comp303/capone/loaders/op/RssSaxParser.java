package ca.mcgill.cs.comp303.capone.loaders.op;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ca.mcgill.cs.comp303.capone.loaders.MPEvent;
import ca.mcgill.cs.comp303.capone.loaders.ParliamentLoaderException;

/**
 * This RSS feed parser uses the Java SAX library to parse the XML stream that contains the RSS feeds.
 * To use this class, simply call new RssSaxParser().parse(xmlStream) on an InputStream, then close the stream.
 */
public class RssSaxParser extends DefaultHandler
{
	private static final String ELEMENT_ITEM = "item";
	private static final String ELEMENT_TITLE = "title";
	private static final String ELEMENT_LINK = "link";
	private static final String ELEMENT_DESCRIPTION = "description";

	private static final String PREFIX_MEDIA = "Mentioned by";
	private static final String PREFIX_VOTE = "Voted";
	private static final String PREFIX_SPOKE = "Spoke";;
	private boolean aInHeader = true;
	private List<MPEvent> aEvents = new ArrayList<MPEvent>();
	private String aCurrentTag = "";
	private String aCurrentTitle = "";
	private String aCurrentLink = "";
	private String aCurrentDescription = "";

	/**
	 * Parse RSS feed stream and returns a list of RSS items represented as a List of MPEvent.
	 * 
	 * @param pInputStream The file name.
	 * @throws ParliamentLoaderException If there is any problem loading the rss file.
	 * @return A list of events. 
	 */
	public List<MPEvent> parse(InputStream pInputStream)
	{
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(pInputStream, this);
		}
		catch (ParserConfigurationException e)
		{
			throw new ParliamentLoaderException(e);
		}
		catch (SAXException e)
		{
			throw new ParliamentLoaderException(e);
		}
		catch (IOException e)
		{
			throw new ParliamentLoaderException(e);
		}
		return aEvents;
	}

	@Override
	public void startElement(String pURI, String pLocalName, String pQualifiedName, Attributes pAttributes)
			throws SAXException
	{
		// We have to make this test to distinguish whether the title, link, and
		// element are from the header
		// (which are not used), or from items (which we use).
		if (pQualifiedName.equalsIgnoreCase(ELEMENT_ITEM))
		{
			if (!aInHeader)
			{
				aEvents.add(new MPEvent(classifyEvent(aCurrentTitle), aCurrentTitle, aCurrentLink, aCurrentDescription));
				aCurrentTitle = "";
				aCurrentLink = "";
				aCurrentDescription = "";
			}
			aInHeader = false;
			return;
		}

		if (aInHeader)
		{
			return; // Don't do anything if we're visiting a node in the header.
		}

		// At this point we are visiting items.

		if (pQualifiedName.equalsIgnoreCase(ELEMENT_TITLE) || 
				pQualifiedName.equalsIgnoreCase(ELEMENT_LINK) || 
				pQualifiedName.equalsIgnoreCase(ELEMENT_DESCRIPTION))
		{
			aCurrentTag = pQualifiedName;
		}
	}

	@Override
	public void characters(char[] pCharacters, int pStart, int pLength) throws SAXException
	{
		// Because the SAX parser may call the characters methods several time
		// for the contents
		// of a node, the strings must be added to the existing data.
		if (aCurrentTag.equalsIgnoreCase(ELEMENT_TITLE))
		{
			aCurrentTitle = aCurrentTitle + createNewString(pCharacters, pStart, pLength);
		}
		else if (aCurrentTag.equalsIgnoreCase(ELEMENT_LINK))
		{
			aCurrentLink = aCurrentLink + createNewString(pCharacters, pStart, pLength);
		}
		else if (aCurrentTag.equalsIgnoreCase("description"))
		{
			aCurrentDescription = aCurrentDescription + createNewString(pCharacters, pStart, pLength);
		}
	}

	private String createNewString(char[] pCharacters, int pStart, int pLength)
	{
		String str = new String(pCharacters, pStart, pLength).trim();
		return str;
	}

	private static MPEvent.Descriptor classifyEvent(String pTitle)
	{
		if (pTitle.startsWith(PREFIX_MEDIA))
		{
			return MPEvent.Descriptor.MEDIA;
		}
		else if (pTitle.startsWith(PREFIX_SPOKE))
		{
			return MPEvent.Descriptor.SPEECH;
		}
		else if (pTitle.startsWith(PREFIX_VOTE))
		{
			return MPEvent.Descriptor.VOTE;
		}
		else
		{
			return MPEvent.Descriptor.UNCLASSIFIED;
		}
	}
}
