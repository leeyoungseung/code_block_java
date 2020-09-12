package utils.model;

import java.util.ArrayList;
import java.util.List;

public class ExXmlModel {
	
	String mainId;
	String mainControlId;
	Integer mainNo;
	String openId;
	Integer totalFee;
	String createDate;
	
	List<Item> items;
	List<Integer> itemIds;
	List<String> itemNames;
	List<Integer> itemFees;
	List<Integer> itemCounts;
	
	List<Member> members;
	List<String> memberIds;
	List<String> memberNames;

	
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getMainControlId() {
		return mainControlId;
	}

	public void setMainControlId(String mainControlId) {
		this.mainControlId = mainControlId;
	}

	public Integer getMainNo() {
		return mainNo;
	}

	public void setMainNo(Integer mainNo) {
		this.mainNo = mainNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<Item> getItems() {
		if (items == null) items = new ArrayList<Item>();
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Member> getMembers() {
		if (members == null) members = new ArrayList<Member>();
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	public List<Integer> getItemIds() {
		if (itemIds == null) itemIds = new ArrayList<Integer>();
		return itemIds;
	}

	public void setItemIds(List<Integer> itemIds) {
		this.itemIds = itemIds;
	}

	public List<String> getItemNames() {
		if (itemNames == null) itemNames = new ArrayList<String>();
		return itemNames;
	}

	public void setItemNames(List<String> itemNames) {
		this.itemNames = itemNames;
	}

	public List<Integer> getItemFees() {
		if (itemFees == null) itemFees = new ArrayList<Integer>();
		return itemFees;
	}

	public void setItemFees(List<Integer> itemFee) {
		this.itemFees = itemFee;
	}

	public List<Integer> getItemCounts() {
		if (itemCounts == null) itemCounts = new ArrayList<Integer>();
		return itemCounts;
	}

	public void setItemCounts(List<Integer> itemCount) {
		this.itemCounts = itemCount;
	}

	public List<String> getMemberIds() {
		if (memberIds == null) memberIds = new ArrayList<String>();
		return memberIds;
	}

	public void setMemberIds(List<String> memberId) {
		this.memberIds = memberIds;
	}

	public List<String> getMemberNames() {
		if (memberNames == null) memberNames = new ArrayList<String>();
		return memberNames;
	}

	public void setMemberNames(List<String> memberName) {
		this.memberNames = memberName;
	}
	
	public void setItems() {
		if (this.items == null) this.items = new ArrayList<Item>();
		
		for (int su = 0; su < this.itemIds.size(); su++ ) {
			Item item = new Item();
			item.setItemId(itemIds.get(0));
			item.setItemName(itemNames.get(su));
			item.setItemFee(itemFees.get(su));
			item.setItemCount(itemCounts.get(su));
			this.items.add(item);
		}
		
	}
	
	public void setMembers() {
		if (this.members == null) this.members = new ArrayList<Member>();
		
		for (int su = 0; su < this.memberIds.size(); su++ ) {
			Member member = new Member();
			member.setMemberId(memberIds.get(su));
			member.setMemberName(memberNames.get(su));

			this.members.add(member);
		}

	}

	@Override
	public String toString() {
		return "ExXmlModel [mainId=" + mainId + ", mainControlId=" + mainControlId + ", mainNo=" + mainNo + ", openId="
				+ openId + ", totalFee=" + totalFee + ", createDate=" + createDate + ", items=" + items + ", itemIds="
				+ itemIds + ", itemNames=" + itemNames + ", itemFees=" + itemFees + ", itemCount=" + itemCounts
				+ ", members=" + members + ", memberIds=" + memberIds + ", memberName=" + memberNames + "]";
	}


}
