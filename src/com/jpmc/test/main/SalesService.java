/**
 * Main class for message validation and processing
 */
package com.jpmc.test.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import com.jpmc.test.dto.SaleData;
import com.jpmc.test.exception.TestException;
import com.jpmc.test.processor.MessageProcessor;
import com.jpmc.test.reader.MessageReader;
import com.jpmc.test.util.MessageValidator;
import com.jpmc.test.util.TestConstant;

/**
 * @author RD
 *
 */

public class SalesService {

	/**
	 * @param args
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SAXException, IOException {

		MessageProcessor mp = null;
		MessageValidator mv = null;
		ArrayList<SaleData> saleData = new ArrayList<SaleData>();
		File schemaFile = new File(TestConstant.path);
		File dir = new File(TestConstant.filePath);
		int counter = 0;
		for (File file : dir.listFiles()) {
			mv = new MessageValidator();
			
			//Validates each message with XSD
			mv.validateMessage(schemaFile, file);

			mp = new MessageProcessor();

			MessageReader mr = new MessageReader();
			SaleData data = mr.xmlReader(file.getAbsolutePath());
			saleData.add(data);
			counter++;
			//Generating report after every 10th Message
			if (counter == 10) {
				System.out.println("****Publishing report for 10 messages****");
				mp.processMessages(saleData);
				counter = 0;

			}
            //Generating report after 50th Message
			//Stopping the program
			if (saleData.size() == 50) {
				System.out.println("****Messages exceeds sizelimit 50****");
				System.out.println("****Stopping the program****");
				mp.processMessages(saleData);
				System.out.println("****System stops now****");
				throw new TestException();

			}

			System.out.println("counter is - " + counter);

		}

	}
}
