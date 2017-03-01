package com.jpmc.test.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.jpmc.test.dto.SaleData;

public class MessageReader {
	/**
	 * This method parse the xml messages
	 * @param message
	 * @return
	 */
	public SaleData xmlReader(String message) {
		String itemName = null;
		String itemRate = null;
		int noItem = 0;
		String adjustmentType = null;
		int adjustmentAmount = 0;
		SaleData sd = null;

		boolean iname = false;
		boolean irate = false;
		boolean noi = false;
		boolean atype = false;
		boolean amount = false;

		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(message));
			sd = new SaleData();
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();
					if (qName.equalsIgnoreCase("sale")) {
						Iterator<Attribute> attributes = startElement.getAttributes();
						System.out.println("Start Element : sale");
					} else if (qName.equalsIgnoreCase("item-name")) {

						iname = true;

					} else if (qName.equalsIgnoreCase("item-rate")) {
						irate = true;
					} else if (qName.equalsIgnoreCase("number-of-item")) {
						noi = true;
					} else if (qName.equalsIgnoreCase("adjustment-type")) {
						atype = true;
					} else if (qName.equalsIgnoreCase("adjustment-amount")) {
						amount = true;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if (iname) {
						Characters characters = event.asCharacters();
						itemName = characters.getData();
						sd.setItemName(itemName);
						System.out.println("Item name: " + itemName);
						iname = false;
					}

					if (irate) {
						Characters characters = event.asCharacters();
						itemRate = characters.getData();
						sd.setItemRate(itemRate);
						System.out.println("Rate of the Item : " + itemRate);
						irate = false;
					}
					if (noi) {
						Characters characters = event.asCharacters();
						noItem = Integer.parseInt(characters.getData());
						sd.setNoItem(noItem);
						System.out.println("No of Item: " + noItem);
						noi = false;
					}
					if (atype) {
						Characters characters = event.asCharacters();
						adjustmentType = characters.getData();
						sd.setAdjustmentType(adjustmentType);
						System.out.println("Adjustment Type: " + adjustmentType);
						atype = false;
					}
					if (amount) {
						Characters characters = event.asCharacters();
						adjustmentAmount = Integer.parseInt(characters.getData());
						sd.setAdjustmentAmount(adjustmentAmount);
						System.out.println("Amount for adjustment: " + characters.getData());
						amount = false;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equalsIgnoreCase("sale")) {
						System.out.println("End Element : sale");
						System.out.println();
					}
					break;
				}

			}

			System.out.println("Item in object " + sd.getItemName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return sd;
	}
}
