package zad1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.lang.model.element.Element;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Main {
	
	
	public static void main(String[] args) throws Exception{
		
		JFrame prePanel = new JFrame();
		prePanel.setLayout(new GridLayout(2,3,2,2));
		JTextField fieldWaluta = new JTextField("Skrot waluty");
	    JTextField fieldMiasto = new JTextField("Nazwa miasta");
	    JTextField fieldKraj = new JTextField("Nazwa kraju");
	    JButton przycisk = new JButton("GO");
		JTextField miastoField = new JTextField("Warsaw",20);
		JTextField krajField = new JTextField("Poland",20);
		JTextField walutaField = new JTextField("USD",10);
		prePanel.setSize(new Dimension(300, 100));
		prePanel.add(fieldMiasto);
		prePanel.add(fieldKraj);
		prePanel.add(fieldWaluta);
		prePanel.add(przycisk);
		prePanel.add(miastoField);
		prePanel.add(krajField);
		prePanel.add(walutaField);
		fieldMiasto.setEditable(false);
		fieldKraj.setEditable(false);
		fieldWaluta.setEditable(false);
		prePanel.setVisible(true);
		
		prePanel.setVisible(true);
		przycisk.addMouseListener(new MouseAdapter() {
			 

			public void mouseClicked(MouseEvent e) {
				Service s = new Service(krajField.getText());
				
				try {
					JSONObject weatherJSON = s.getWeather(miastoField.getText());
					Double rate2 = s.getNBPRate();
					JSONObject rate1 = s.getRateFor(walutaField.getText());
					MyFrame frame = new MyFrame(weatherJSON, rate1, miastoField.getText(), krajField.getText(), walutaField.getText());
					prePanel.setVisible(false);
				} catch (IOException | JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParserConfigurationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		
		
	}
		});

}
}