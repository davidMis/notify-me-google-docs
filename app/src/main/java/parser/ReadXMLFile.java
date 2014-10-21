package parser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class ReadXMLFile {
 
   public static void main(String argv[]) {
 
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
			System.out.println("Title ***************: " + new String(ch, start, length));
			bfname = false;
		}
 
		if (blname) {
			System.out.println("Content **************** : " + new String(ch, start, length));
			blname = false;
		}
 
 
	}
 
     };
 
       saxParser.parse("/Users/narendrabidari/Documents/workspace/FinalSemester/Assignment2Par/src/com/1.xml", handler);
 
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}