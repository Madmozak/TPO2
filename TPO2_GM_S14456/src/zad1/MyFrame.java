package zad1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

@SuppressWarnings("serial")
public class MyFrame extends JFrame{
	String miasto;
	String waluta;
	String kraj;
	JSONObject weatherJSON;
	JSONObject rate1;
	public MyFrame(JSONObject weatherJSON,JSONObject rate1 ,String miasto, String kraj, String waluta) throws JSONException {
		this.miasto =miasto;
		this.waluta= waluta;
		this.kraj = kraj;
		this.weatherJSON = weatherJSON;
		this.rate1 = rate1;
	JTextField miastoField = new JTextField("Warsaw",20);
	JTextField krajField = new JTextField("Poland",20);
	JTextField walutaField = new JTextField("USD",10);
	
	//String miasto = miastoField.getText();
	//String kraj = krajField.getText();
	//String waluta = walutaField.getText();
	
	
	JFrame frame = new JFrame("Uslugi Sieciowe");
	
	
    JTextField fieldWaluta = new JTextField("Skrot waluty");
    JTextField fieldMiasto = new JTextField("Nazwa miasta");
    JTextField fieldKraj = new JTextField("Nazwa kraju");
    JButton przycisk = new JButton("GO");
    JTextArea kursyText = new JTextArea();
	JTextArea pogodaText = new JTextArea();
	JTextArea walutaText = new JTextArea();
    
    
	JPanel mainPanel = new JPanel();
	JScrollPane jScroll = new JScrollPane(kursyText);
	JFXPanel fxPanel = new JFXPanel();
	JPanel choicePanel = new JPanel();
	/*
	przycisk.addMouseListener(new MouseAdapter() {
	 
		public void mouseClicked(MouseEvent e) {
	    		Frame.repaint();
	      }
	});
	
	
	*/
	mainPanel.setLayout(new BorderLayout());
	
	frame.add(kursyText, BorderLayout.EAST);
	frame.add(walutaText, BorderLayout.EAST);
	frame.add(fxPanel, BorderLayout.WEST);
	frame.add(pogodaText, BorderLayout.SOUTH);
	
	
	fxPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
	pogodaText.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
	walutaText.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
	kursyText.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1600, 800);
	kursyText.setEditable(false);
	
	//jScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    frame.add(kursyText);
    kursyText.add(jScroll);
    
    pogodaText.setEditable(false);
	kursyText.setBackground(Color.CYAN);
	pogodaText.setBackground(Color.cyan);
	pogodaText.setPreferredSize(new Dimension(100, 200));
	walutaText.setPreferredSize(new Dimension(300,300));
	kursyText.setPreferredSize(new Dimension(500, 200));
	choicePanel.setPreferredSize(new Dimension(100, 100));
	
	fxPanel.setSize(800, 800);
	//tekst pogody
	pogodaText.append("Coordinations :"+weatherJSON.getJSONObject("coord").toString()+"\n");
	pogodaText.append("Weather :"+weatherJSON.getJSONArray("weather").toString()+"\n");
	pogodaText.append("Base :"+weatherJSON.getString("base").toString()+"\n");
	pogodaText.append("Main info :"+weatherJSON.getJSONObject("main").toString()+"\n");
	
	pogodaText.append("Wind :"+weatherJSON.getJSONObject("wind").toString()+"\n");
	pogodaText.append("Clouds :"+weatherJSON.getJSONObject("clouds").toString()+"\n");
	pogodaText.append("System info :"+weatherJSON.getJSONObject("sys").toString()+"\n");
	pogodaText.append("City name :"+weatherJSON.getString("name")+"\n");
	
	//waluta tekst
	walutaText.append("date : "+rate1.getString(("date"))+"\n");
	walutaText.append("base : "+rate1.getString(("base"))+"\n");
	walutaText.append("Kurs zlotego wzgledem "+waluta+"\n");
	walutaText.append("PLN : "+rate1.getJSONObject("rates").getDouble("PLN"));
	
	
	Platform.runLater(new Runnable() {
		@Override
		public void run() {
			WebView webView = new WebView();
			fxPanel.setScene(new Scene(webView));
			webView.getEngine().load("https://en.wikipedia.org/wiki/"+miasto);
	
		}
	});
	frame.setVisible(true);
	}

}
