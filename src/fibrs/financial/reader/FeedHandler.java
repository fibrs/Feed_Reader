package fibrs.financial.reader;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import android.util.Log;

public class FeedHandler extends DefaultHandler {

	Feed feed;
	FeedItem item;
	
	String lastElementName = "last";
	boolean bFoundChannel = false;
	final int FEED_TITLE = 1;
	final int FEED_LINK = 2;
	final int FEED_DESCRIP = 3;
	final int FEED_CAT = 4;
	final int FEED_DATE = 5;
	
	int depth = 0;
	int currentState = 0;
	
	// Constructor
	
	FeedHandler() {}
	
	Feed getFeed() {
		return feed;
	}
	
	public void startDoc() throws SAXException {
		feed = new Feed();
		item = new FeedItem();
	}
	
	public void endDoc() throws SAXException {}
	
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException
	{
		depth++;
		
		if (localName.equals("channel")) {
			currentState = 0;
			return;
		}
		
		if (localName.equals("image")) {
			feed.setTitle(item.getTitle());
			feed.setDate(item.getDate());
		}
		
		if (localName.equals("title")){
			currentState = FEED_TITLE;
			return;
		}
		
		if (localName.equals("link")) {
			currentState = FEED_LINK;
			return;
		}
		
		if (localName.equals("category")) {
			currentState = FEED_CAT;
			return;
		}
		
		if (localName.equals("pubDate")) {
			currentState = FEED_DATE;
			return;
		}
		
		currentState = 0;
	}
	
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException 
	{
		depth--;
		if(localName.equals("item")){
			feed.addItem(item);
			return;
		}
	}
	
	public void characters(char ch[], int start, int length){
		String theString = new String(ch,start,length);
		Log.i("FeedReader","characters[" + theString + "]");
		
		switch (currentState) {
		case FEED_TITLE:
			item.setTitle(theString);
			currentState = 0;
			break;
		case FEED_LINK:
			item.setLink(theString);
			currentState = 0;
			break;
		case FEED_DESCRIP:
			item.setDescrip(theString);
			currentState = 0;
			break;
		case FEED_CAT:
			item.setCat(theString);
			currentState = 0;
			break;
		case FEED_DATE:
			item.setDate(theString);
			currentState = 0;
			break;
		default:
			return;
		}
	}
}
