package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class Service {
	
	public String kraj;
	public String apiKey = "6d55d72e7cb0fbf6cc03b120aa744042";
	
	Service(String kraj){
		this.kraj=kraj;
		
	}
	
	public JSONObject getWeather(String miasto) throws IOException, MalformedURLException, JSONException{
		
		String wApi = "http://api.openweathermap.org/data/2.5/weather?q=";
		
		URL wUrl = new URL(wApi+miasto+"&appid="+apiKey);
		HttpURLConnection wConnection = (HttpURLConnection)wUrl.openConnection();
		BufferedReader wBr = new BufferedReader(
				new InputStreamReader(wConnection.getInputStream()));
		String wInputLine;
		StringBuffer wResponse = new StringBuffer();
		while((wInputLine = wBr.readLine())!=null) {
			wResponse.append(wInputLine+"\n");
			}
		wBr.close();
		JSONObject wJsonr = new JSONObject(wResponse.toString());		
		
		return wJsonr;
		
	}
	
	public JSONObject getRateFor(String curr) throws IOException, JSONException, MalformedURLException {
		
		//String currencyAPI = "bec94fafe6b00a2ed4fa691bdfedee9d";
		String baseCurr ="?base="+curr;
		String API_URL = "https://api.fixer.io/latest";
		URL url = new URL(API_URL+baseCurr);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		
		while((inputLine = br.readLine())!=null) {
			response.append(inputLine);
			}
		br.close();
		JSONObject jsonr = new JSONObject(response.toString());
		return jsonr;
	}
	
	public Double getNBPRate() throws IOException, MalformedURLException, TransformerException, SAXException, ParserConfigurationException {
		
		URL url = new URL("http://www.nbp.pl/kursy/xml/a071z180411.xml");
		URLConnection urlConnection = url.openConnection();
		BufferedReader nBr = new BufferedReader(
				new InputStreamReader(urlConnection.getInputStream()));
		String inputline = null;
		StringBuffer nResponse = new StringBuffer();
		while((inputline = nBr.readLine())!=null) 
			nResponse.append(inputline+ "\n");
			
		//append(nResponse.toString());
		nBr.close();
		
		//
		/*
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(nResponse.toString())));
		NodeList errNodes = doc.getElementsByTagName("pozycja");
		if(errNodes.getLength()>0) {
			Element err = (Element) errNodes.item(0);
			kursyText.append((((Document) err).getElementsByTagName("nazwa_waluty").item(0).getTextContent()));
			kursyText.append((((Document) err).getElementsByTagName("kod_waluty").item(0).getTextContent()));
			kursyText.append((((Document) err).getElementsByTagName("kurs_sredni").item(0).getTextContent()));
	}
		else {
			
		}
		*/
		return 1.0;
}
}
	
	
	
	
	
	