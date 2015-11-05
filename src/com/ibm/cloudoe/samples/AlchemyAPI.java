package com.ibm.cloudoe.samples;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.Reader;

import java.nio.charset.Charset;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class AlchemyAPI {
	
	// If running locally complete the variables below with the information in VCAP_SERVICES
	private static String baseURL = "https://gateway-a.watsonplatform.net/calls/";
	//private static String baseURL = "http://access.alchemyapi.com/calls/";
	private static String key = "ae37d050634ab5068cbd687327de9f33d11319ea";
	 
	public JSONObject URLGetAuthor(String searchKey,String url)
			throws IOException, SAXException,JSONException,
			ParserConfigurationException, XPathExpressionException
		{
			System.out.println("Search Key is " + searchKey);
			return URLGetAuthor(searchKey,url, new AlchemyApi_params());
		}
	
	public JSONObject URLGetAuthor(String searchKey,String url, AlchemyApi_params params)
			throws IOException, SAXException, ParserConfigurationException,JSONException,
			XPathExpressionException
		{
			params.setUrl(url);
			//Change the first parameter for different API calls
			//"URLGetAuthors"
			return GET(searchKey, "url", params);
		}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
}

private JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
}
	
	private JSONObject GET(String callName, String callPrefix, AlchemyApi_params params)
			throws IOException, SAXException,JSONException,
			ParserConfigurationException, XPathExpressionException
		{
			System.out.println("callName is " + callName);
			StringBuilder uri = new StringBuilder();
			uri.append(baseURL).append(callPrefix).append('/').append(callName)
			.append('?').append("apikey=").append(key);
			uri.append(params.getParameterString());
			//URL url = new URL(uri.toString());
			System.out.println(uri.toString());
			JSONObject json = readJsonFromUrl(uri.toString());
			
			return json;
//			HttpURLConnection handle = (HttpURLConnection) url.openConnection();
//			System.out.println("handle is:" + handle);
//			handle.setDoOutput(true);
//			//System.out.println("in 1");
//			return doRequest(handle, params.getOutputMode());
		}

	
	private String doRequest(HttpURLConnection handle, String outputMode)
			throws IOException
			{
				try{
				    int status = handle.getResponseCode();
				    System.out.println("handle message is: "+ handle.getInputStream());
				    switch (status) {
			            case 200:
			            case 201:
			                BufferedReader br = new BufferedReader(new InputStreamReader(handle.getInputStream()));
			                //System.out.println("br dats is :" + br);
			                StringBuilder sb = new StringBuilder();			                
			                String line;
			                while ((line = br.readLine()) != null) {
			                    sb.append(line+"\n");
			                    //System.out.println("sb dats is :" + sb);
			                }
			                br.close();
			        
			                System.out.println("sb:" + sb);
			                return sb.toString();
			                //return sb.toString();
			              
			        }
					}
				catch(IOException ex){
						System.out.println("IO Exception ");
					}
				return "";
			}
}
