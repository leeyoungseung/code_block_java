package utils;

import org.jdom2.Element;

public interface XmlUtil {
	
	public Object parser(String target, Object obj);
	public void getObject(Element el, Object obj);
	public boolean setData(String elName, String val, Object obj);
	public String makeXml(Object obj);
	
}
