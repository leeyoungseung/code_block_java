package utils.model;

import java.util.List;

public class JsonValBeanDat {
	public String message;
	public Boolean res;
	public List<JsonValUserDat> data;
	
	@Override
	public String toString() {
		return "JsonValBeanDat [message=" + message + ", res=" + res + ", data=" + data + "]";
	}
	
}