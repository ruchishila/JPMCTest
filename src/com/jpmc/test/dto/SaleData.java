package com.jpmc.test.dto;

/**
 * @author RD 
 * DTO class to hold each sale details
 *
 */
public class SaleData {

	private String itemName;
	private int itemRate;
	private int noItem;
	private String adjustmentType;
	private int adjustmentAmount;
	private int totalValue;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemRate() {
		return itemRate;
	}

	public void setItemRate(int itemRate) {
		this.itemRate = itemRate;
	}

	public int getNoItem() {
		return noItem;
	}

	public void setNoItem(int noItem) {
		this.noItem = noItem;
	}

	public String getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(String adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public int getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void setAdjustmentAmount(int adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

}
