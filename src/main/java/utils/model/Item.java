package utils.model;

public class Item {

	Integer itemId;
	String itemName;
	Integer itemFee;
	Integer itemCount;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getItemFee() {
		return itemFee;
	}
	public void setItemFee(Integer itemFee) {
		this.itemFee = itemFee;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemFee=" + itemFee + ", itemCount=" + itemCount
				+ "]";
	}
	
	
}
