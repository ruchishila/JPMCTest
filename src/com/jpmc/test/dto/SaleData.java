package com.jpmc.test.dto;

public class SaleData {
	
	/*public SaleData(String itemName, String itemRate, int noItem, String adjustmentType, int adjustmentAmount) {
		this.itemName = itemName;
		this.itemRate = itemRate;
		this.noItem = noItem;
		this.adjustmentType = adjustmentType;
		this.adjustmentAmount = adjustmentAmount;
	}*/
	private String itemName;
	private String itemRate;
	private int noItem;
	private String adjustmentType;
	private int adjustmentAmount;
	
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemRate() {
		return itemRate;
	}
	public void setItemRate(String itemRate) {
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
	

}
