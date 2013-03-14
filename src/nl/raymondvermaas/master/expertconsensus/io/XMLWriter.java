package nl.raymondvermaas.master.expertconsensus.io;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import nl.raymondvermaas.master.expertconsensus.Ranking;

public class XMLWriter {
	
	private Ranking ranking;
	private String outputfile;
	private int startCrit;
	
	public XMLWriter(Ranking ranking, String outputfile, int startCrit) {
		super();
		this.ranking = ranking;
		this.outputfile = outputfile;
		this.startCrit = startCrit;
	}
	
	public void write(){

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(outputfile);
			DecimalFormat decim = new DecimalFormat("0.00000000");
			
			NodeList preferences = doc.getElementsByTagName("preference");
			for(int i=0; i<preferences.getLength(); i++) {
				Node preference = preferences.item(i);
				NodeList prefNodes = preference.getChildNodes();
				int ref = Integer.parseInt(prefNodes.item(1).getAttributes().getNamedItem("ref").getNodeValue());
				int rank = ranking.find(ref-(startCrit-1))+1;
				preference.getChildNodes().item(3).getAttributes().getNamedItem("rank").setNodeValue(""+rank);
			}			
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(outputfile));
			transformer.transform(source, result);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
