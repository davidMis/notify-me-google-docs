package com.davidmis.pushnewsfeed;

import android.util.Log;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import parser.NotificationRow;
import parser.Row;
import parser.XMLDAO;

/**
 * Created by David on 10/19/2014.
 */
public class MessageFetcher {
    private static String TAG = "MessageFetcher";

    public List<NewsfeedItem> getMessages() throws  IOException {
        List<NewsfeedItem> items = new ArrayList<NewsfeedItem>();

        XMLDAO xmldao = new XMLDAO(40,10);
        xmldao.readXML(getXMLString());
        List<NotificationRow> rows = xmldao.getNotificationRows();

        for(NotificationRow row : rows) {
            NewsfeedItem item = new NewsfeedItem();
            item.setTitle(row.getTitle());
            item.setMessage(row.getBody());
            items.add(item);
        }

        return items;
    }

    public String getXMLString() throws IOException {
        String URL = "https://spreadsheets.google.com/feeds/cells/1wz4HnMJLv74ikmAniR0fCemzUjQ4c0dZEAzZprs7Fi4/od6/public/basic";
        String response = new String(getBytes(URL));
        Log.i(TAG, response);
        return response;
    }

    private byte[] getBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();


        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }

            out.close();
            return out.toByteArray();

        } finally {
            connection.disconnect();
        }
    }
}

