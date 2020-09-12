package utils.model;

public class Member {

	String memberId;
	String memberName;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + "]";
	}
	
}
