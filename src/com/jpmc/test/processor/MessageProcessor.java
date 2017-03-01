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

	Map<String, Integer> productName = new HashMap<String, Integer>();

	
	/**
	 * This method process all the messages and calculates total value of the sales
	 * @param saleData
	 */
	public void processMessages(ArrayList<SaleData> saleData) {

		Iterator<SaleData> iter = saleData.iterator();
		while (iter.hasNext()) {
			SaleData sd = iter.next();
			int prodrate = Integer.parseInt(sd.getItemRate());
			String prodname = sd.getItemName();

			if (productName.containsKey(prodname)) {
				if (!(sd.getAdjustmentType() == null)) {
					if (sd.getAdjustmentType() == TestConstant.add) {
						productName.put(prodname,
								(productName.get(prodname) + (prodrate + sd.getAdjustmentAmount()) * sd.getNoItem()));
					}
					if (sd.getAdjustmentType() == TestConstant.subtract) {
						productName.put(prodname,
								(productName.get(prodname) + (prodrate - sd.getAdjustmentAmount()) * sd.getNoItem()));
					}
					if (sd.getAdjustmentType() == TestConstant.multiply) {
						productName.put(prodname,
								(productName.get(prodname) + (prodrate * sd.getAdjustmentAmount()) * sd.getNoItem()));
					}
				} else {
					productName.put(prodname, productName.get(prodname) + (prodrate * sd.getNoItem()));
				}

			} else {
				if (!(sd.getAdjustmentType() == null)) {
					if (sd.getAdjustmentType().equals(TestConstant.add)) {
						System.out.println("Adding");
						productName.put(prodname, (sd.getAdjustmentAmount() + prodrate) * sd.getNoItem());

					}
					if (sd.getAdjustmentType().equals(TestConstant.subtract)) {
						System.out.println("Subtracting");
						productName.put(prodname, (prodrate - sd.getAdjustmentAmount()) * sd.getNoItem());

					}
					if (sd.getAdjustmentType().equals(TestConstant.multiply)) {
						System.out.println("Multiplying");
						productName.put(prodname, (prodrate * sd.getAdjustmentAmount()) * sd.getNoItem());

					}
				} else {
					productName.put(prodname, (prodrate * sd.getNoItem()));
				}

			}

		}

		for (Map.Entry<String, Integer> entry : productName.entrySet()) {
			System.out.print("Total Cost of " + entry.getKey() + " = " + entry.getValue() + " ");
			System.out.println();
		}
		System.out.println("We sold " + productName.size() + " items.");
	}

}
