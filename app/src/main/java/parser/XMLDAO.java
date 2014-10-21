package parser;

import android.util.Log;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLDAO {
	private static String TAG = "XMLDAO";


	String[][] matx ;
	int maxR=0;
	int maxC =0;
	
	public XMLDAO(int row, int col){
		// defensive by alloting extra row and column
		maxR = row;
		maxC = col;
		matx = new String[row][col];
	}
	
	public void add( EntryDO entry){
		if(entry.getRow()>=0 && entry.getRow()<maxR && entry.getCol()>=0 && entry.getRow()<maxC && entry.getContent()!=null)
		matx[entry.getRow()][entry.getCol()] = entry.getContent();
	}

    public List<NotificationRow> getNotificationRows(){
        List<NotificationRow> rows = new ArrayList<NotificationRow>();
        for(int id=0;id<maxR;id++){
            NotificationRow row = new NotificationRow();
            for(int j=0;j<maxC;j++){
                if(this.matx[id][j] != null)
                row.addInfo(this.matx[id][j]);
            }
            if(!row.isEmpty()) {
                rows.add(row);
            }
        }

        return rows;
    }
	
	public void print(){
		for(int id=0;id<maxR;id++){
			for(int j=0;j<maxC;j++){
				Log.i(TAG, this.matx[id][j] + " " + id + j);
			}
		//	System.out.println();
		}
	}
	
	String temp = "";
	 public void readXML(String XML) {
		 
		    try {
		 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
		 
			DefaultHandler handler = new DefaultHandler() {
		 
			boolean bfname = false;
			boolean blname = false;
			boolean bnname = false;
			boolean bsalary = false;
		 
			public void startElement(String uri, String localName,String qName, 
		                Attributes attributes) throws SAXException {
		 
				//System.out.println("Start Element :" + qName);
		 
				if (qName.equalsIgnoreCase("title")) {
					bfname = true;
				}
		 
				if (qName.equalsIgnoreCase("content")) {
					blname = true;
				}
		 
				
		 
			}
		 
			public void endElement(String uri, String localName,
				String qName) throws SAXException {
		 
				//System.out.println("End Element :" + qName);
		 
			}
		 
			public void characters(char ch[], int start, int length) throws SAXException {
		 
				if (bfname) {
					String te = new String(ch, start, length);
				//	System.out.println("Title ***************: " + te);
					temp = te;
					bfname = false;
				}
		 
				if (blname) {
					String t2 = new String(ch, start, length);
				//	System.out.println("Content **************** : " + t2);
					EntryDO entry = new EntryDO();
					entry.setValue(temp, t2);
				//	System.out.println(entry.getRow());
				//	System.out.println(entry.getCol());
					matx[entry.getRow()][entry.getCol()] = entry.getContent();
					//reset
					temp = "";
					blname = false;
				}
		 
		 
			}
		 
		     };
		 

				DocumentBuilderFactory factory1 = DocumentBuilderFactory.newInstance();
			    InputSource is = new InputSource(new StringReader(XML));
			    saxParser.parse(is, handler);
		       //saxParser.parse("/Users/narendrabidari/Documents/workspace/FinalSemester/Assignment2Par/src/com/1.xml", handler);
		 
		     } catch (Exception e) {
		       e.printStackTrace();
		     }
		 
		   }

}
