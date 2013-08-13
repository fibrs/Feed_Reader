package fibrs.financial.reader;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import java.util.ArrayList;
import java.net.URL;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import android.content.Intent;
import fibrs.financial.reader.ShowDescription;

public class FeedReaderActivity extends Activity implements OnItemClickListener {
    /** Called when the activity is first created. */
   // @Override
    
    private Feed feed = null;
    
    public final String FEEDURL = "http://feeds.finance.yahoo.com/rss/2.0/category-commodities?region=US&lang=en-US";
    
    public void onCreate(Bundle frozen) {
        super.onCreate(frozen);
        setContentView(R.layout.main);
        
        feed = getFeed(FEEDURL);
  
    	if (feed == null) {
    		//Toast.makeText(this, "The feed is null" , Toast.LENGTH_SHORT).show();
    	}
        
        UpdateDisplay();
    }
    
    private Feed getFeed(String urlForFeed){
    	try {
    		//Toast.makeText(this, "getting feed" , Toast.LENGTH_SHORT).show();
    		
    		URL url = new URL(urlForFeed);
    	
    		SAXParserFactory factory = SAXParserFactory.newInstance();
    	
    		SAXParser parser = factory.newSAXParser();
    	
    		XMLReader xmlReader = parser.getXMLReader();
    	
    		FeedHandler theFeedHandler = new FeedHandler();
    	
    		xmlReader.setContentHandler(theFeedHandler);
    	
    		InputSource is = new InputSource(url.openStream());
    	
    		xmlReader.parse(is);
    	
    		return theFeedHandler.getFeed();
    	}
    	
    	catch (Exception ee){
    		Toast.makeText(this, "exception" , Toast.LENGTH_SHORT).show();
    		return null;
    	}
   
    }
    
    private void UpdateDisplay(){
    	TextView feedtitle = (TextView) findViewById(R.id.feedtitle);
    	TextView feedpubdate = (TextView) findViewById(R.id.feedpubdate);
    	ListView itemlist = (ListView) findViewById(R.id.itemlist);
    	
    	if (feed == null) {
    		feedtitle.setText("Feed Unavailable");
    		//Toast.makeText(this, "The feed is null" , Toast.LENGTH_SHORT).show();
    		return;
    	}
    	
    	feedtitle.setText(feed.getTitle());
    	feedpubdate.setText(feed.getDate());
    	
    	ArrayAdapter<FeedItem> adapter = new ArrayAdapter<FeedItem>(this,android.R.layout.simple_list_item_1,feed.getAllItems());
    	
    	itemlist.setAdapter(adapter);
    	
    	itemlist.setSelection(0);
    	
    	itemlist.setOnItemClickListener(this);
    }
    
    public void onItemClick(AdapterView parent, View v, int position, long id) {
    	Log.i("tag","item clicked! [" + feed.getItem(position).getTitle() + "]");
    	
    	Intent itemIntent = new Intent(this,ShowDescription.class);
    	
    	Bundle b = new Bundle();
    	b.putString("title", feed.getItem(position).getTitle());
    	b.putString("description", feed.getItem(position).getDescrip());
    	b.putString("link", feed.getItem(position).getLink());
    	b.putString("pubdate", feed.getItem(position).getDate());
    	
    	itemIntent.putExtra("android.intent.extra.INTENT", b);
    	
    	// startSubActivity(itemIntent,0);
    	startActivity(itemIntent);
    }
}