/**
 * Main class for message validation and processing
 */
package com.jpmc.test.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

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
	 * @takes argument config.properties file path as input
	 * @throws SAXException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SAXException, IOException {
		
		InputStream inputStream = null;
		Properties prop = new Properties();
		String propFileName = args[0];
		MessageProcessor mp = null;
		MessageValidator mv = null;
		//keeps count of incoming messages
		int counter = 0;
		
		System.out.println("propety name "+args[0]);
		
		try {
			inputStream = new FileInputStream(propFileName); 
			if (inputStream != null) {
				prop.load(inputStream);
			} 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		

		ArrayList<SaleData> saleData = new ArrayList<SaleData>();
		File schemaFile = new File(prop.getProperty(TestConstant.path));
		File dir = new File(prop.getProperty(TestConstant.filePath));
		
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
			if (counter==10) {
				System.out.println("****Publishing report after every 10th message****");
				mp.processMessages(saleData);
				
				/*if(saleData.size()==50)
{
					System.out.println("****Publishing report for 50 sales****");
					mp.processMessages(saleData);
				
				try
				{
				System.out.println("****System stops now****");
				throw new TestException("Forced stop");
				}catch(TestException exp)
				{
					System.out.println("****50 sales are done****");
				}
}*/
				counter = 0;

			}
            //Generating report after 50th Message
			//Stopping the program
			/*if (saleData.size() >= 50) {
				SaleData sd = saleData.get(saleData.size()-1);
				saleData.remove((saleData.size()-1));
								
				mp.processMessages(saleData);
				saleData.add(sd);
				System.out.println("last sale data "+sd.getItemName());
				try
				{
				System.out.println("****System stops now****");
				throw new TestException("Forced stop");
				}catch(TestException exp)
				{
					System.out.println("****50 sales are done****");
				}
				

			}*/

			if(saleData.size()==50)
			{
				System.out.println("****Publishing report for 50 sales****");
				mp.processMessages(saleData);
				//saleData.add(sd);
				//System.out.println("last sale data "+sd.getItemName());
				try
				{
				System.out.println("****System stops now****");
				throw new TestException("Forced stop");
				}catch(TestException exp)
				{
					System.out.println("****50 sales are done****");
				}
				break;
			}
		}

	}
}
