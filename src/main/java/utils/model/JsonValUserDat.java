package utils.model;

public class JsonValUserDat {
	public String user_id;
	public String user_mail;
	public String user_passwd;
	public String user_name;
	public String user_gender;
	public String user_join_day;
	public String user_update_day;
	public Integer user_sort;
	public String user_country;
	
	
	@Override
	public String toString() {
		return "JsonValUserDat [user_id=" + user_id + ", user_mail=" + user_mail + ", user_passwd=" + user_passwd
				+ ", user_name=" + user_name + ", user_gender=" + user_gender + ", user_join_day=" + user_join_day
				+ ", user_update_day=" + user_update_day + ", user_sort=" + user_sort + ", user_country=" + user_country
				+ "]";
	}
	
}
