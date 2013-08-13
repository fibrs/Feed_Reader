package fibrs.financial.reader;

import java.util.List;
import java.util.Vector;

// import javax.xml.parsers.SAXParserFactory;

import fibrs.financial.reader.FeedItem;

public class Feed {

	private String feedTitle = "Title";
	private String feedDate = "Date";
	private int itemCount = 0;
	private List<FeedItem> feedList;
	
	// Constructor
	Feed() {
		feedList = new Vector(0);
	}
	
	// Method to add an item to the feed
	int addItem(FeedItem item){
		feedList.add(item);
		itemCount++;
		return itemCount;
	}
	
	FeedItem getItem(int location){
		return feedList.get(location);
	}
	
	List getAllItems(){
		return feedList;
	}
	
	int getItemCount(){
		return itemCount;
	}
	
	void setTitle(String title){
		feedTitle = title;
	}
	
	void setDate(String date){
		feedDate = date;
	}
	
	String getTitle(){
		return feedTitle;
	}
	
	String getDate(){
		return feedDate;
	}
	
}
