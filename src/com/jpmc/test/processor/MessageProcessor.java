package com.jpmc.test.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jpmc.test.dto.SaleData;
import com.jpmc.test.util.TestConstant;

/**
 * @author RD
 *
 */
public class MessageProcessor {

	public SaleData sd = null;

	Map<String, Integer> productName = null;
	int adjustment = 0;

	/**
	 * This method process all the messages and calculates total value of the
	 * sales
	 * 
	 * @param saleData
	 */
	public void processMessages(ArrayList<SaleData> saleData) {
		
		productName = new HashMap<String, Integer>();

		Iterator<SaleData> iter = saleData.iterator();
		while (iter.hasNext()) {
			SaleData sd = iter.next();
			int prodrate = sd.getItemRate();
			String prodname = sd.getItemName();
			

			if (productName.containsKey(prodname)) {

				int totalProduct = productName.get(prodname) + sd.getNoItem();
				productName.put(prodname, totalProduct);

				if (!(sd.getAdjustmentType() == null)) {
					if ((sd.getAdjustmentType()).equals(TestConstant.add)) {
						adjustment = prodrate + (sd.getAdjustmentAmount());
						System.out.println("After adding the adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * totalProduct));
					}
					if ((sd.getAdjustmentType()).equals(TestConstant.subtract)) {
						adjustment = prodrate - (sd.getAdjustmentAmount());
						System.out.println("After subtracting adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * totalProduct));
					}
					if ((sd.getAdjustmentType()).equals(TestConstant.multiply)) {
						adjustment = prodrate * (sd.getAdjustmentAmount());
						System.out.println("After multiplying adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * totalProduct));
					}

				}

			} else {

				productName.put(prodname, sd.getNoItem());

				if (!(sd.getAdjustmentType() == null)) {
					if ((sd.getAdjustmentType()).equals(TestConstant.add)) {
						adjustment = prodrate + (sd.getAdjustmentAmount());
						System.out.println("After adding adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * sd.getNoItem()));
					}
					if ((sd.getAdjustmentType()).equals(TestConstant.subtract)) {
						adjustment = prodrate - (sd.getAdjustmentAmount());
						System.out.println("After subtracting adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * sd.getNoItem()));
					}
					if ((sd.getAdjustmentType()).equals(TestConstant.multiply)) {
						adjustment = prodrate * (sd.getAdjustmentAmount());
						System.out.println("After multiplying adjustment, the rate for " + prodname + " is " + adjustment);
						System.out.println("Total amount is " + (adjustment * sd.getNoItem()));
					}

				}

			}

			
		}
		
		for (Map.Entry<String, Integer> entry : productName.entrySet()) {
			System.out.print("Total number of " + entry.getKey() + " sold = " + entry.getValue() + " ");
			System.out.println();

		}
		System.out.println("We sold " + productName.size() + " items.");

	}
}
