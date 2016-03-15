package org.utilities;

import net.webservicex.*;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WeatherService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.out.println(args[0]);
			System.out.println("Please provide city and country");
		} else {
			String city = args[0];
			String country = args[1];
			System.out.println("city: " + city + " country: " + country);
			GlobalWeather globalWeather = new GlobalWeather();
			GlobalWeatherSoap globalWeatherSoap = globalWeather.getGlobalWeatherSoap();
			String cityWeather = globalWeatherSoap.getWeather(city, country);
			System.out.println(cityWeather);

			try {

				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				DefaultHandler handler = new DefaultHandler() {
				boolean printData = false;
					
					public void startElement(String uri, String localName, String qName, Attributes attributes)
							throws SAXException {
						System.out.print(qName + " is: ");
						
						if (qName.length() > 0) {
							printData = true;
						}

					}

					public void endElement(String uri, String localName, String qName) throws SAXException {

					}

					public void characters(char[] ch, int start, int length) {
						String xmlData = new String(ch, start, length);

						if (printData) {
							//System.out.print("start of line:");
							//System.out.println(xmlData + start + " len: " + length);
							System.out.println(xmlData);
							printData = false;
						}

					}

				};

				try {
					saxParser.parse(new InputSource(new StringReader(cityWeather)), handler);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
