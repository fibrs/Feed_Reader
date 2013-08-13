package fibrs.financial.reader;

public class FeedItem {
	private String itemTitle = "Title";
	private String itemDescrip = "Description";
	private String itemLink = "Link";
	private String itemCat = "Category";
	private String itemDate = "Publication Date";
	
	// Constructor
	FeedItem () {
	}
	
	void setTitle(String title) {
		itemTitle = title;
	}
	
	void setDescrip(String descrip){
		itemDescrip = descrip;
	}
	
	void setLink(String link) {
		itemLink = link;
	}
	
	void setCat(String category){
		itemCat = category;
	}
	
	void setDate(String pubdate){
		itemDate = pubdate;
	}
	
	String getTitle() {
		return itemTitle;
	}
	
	String getDescrip() {
		return itemDescrip;
	}
	
	String getLink() {
		return itemLink;
	}
	
	String getCat() {
		return itemCat;
	}
	
	String getDate() {
		return itemDate;
	}
	
	public String toString() {
		if(itemTitle.length() > 42) {
			return itemTitle.substring(0, 42) + "...";
		}
		return itemTitle;
	}
}
