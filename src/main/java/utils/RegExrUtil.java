package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.TextUtil;

public class RegExrUtil {
	
	TextUtil tu = TextUtil.getInstance();

	private RegExrUtil() {}
	
	public static RegExrUtil getInstance() {
		return RegExrUtilHolder.INSTANCE;
	}
	
	private static class RegExrUtilHolder {
		private static final RegExrUtil INSTANCE = new RegExrUtil();
	}
	
	public final String P_NUM = "^[0-9]*$";          // 숫자
	public final String P_ENG = "^[a-zA-Z]*$";       // 영어
	public final String P_ENG_UPPER = "^[A-Z]*$";    // 영어 대문자
	public final String P_ENG_LOWER = "^[a-z]*$";    // 영어 소문자
	public final String P_KR = "^[가-힣]*$";          // 한글
	public final String P_N_ENG = "^[a-zA-Z0-9]*$";  // 숫자+영어
	public final String P_PASSWD_BASIC = "^[A-Za-z0-9]{6,12}$";         // 패스워드(영어 + 숫자) 6-12이내
	public final String P_PASSWD_ENHANCE = "^.*(?=^.{8,16}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";       // 패스워드(숫자, 문자, 특수문자 포함 8-16자리 이내)
	public final String P_EMAIL = "	^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	public final String P_CPHONE = "^\\d{3}-\\d{3,4}-\\d{4}$";
	public final String P_PHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	public final String P_IMG_FILE = "(png|gif|jpg|jpeg|ico|bmp|svg|tiff)(?-i)$";
	public final String P_DATA_FILE = "([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.doc|.docx|.pdf|.csv|.xlsx|.xls|.txt)$";
	public final String P_URL = "https?://(\\w*:\\w*@)?[-\\w.]+(:\\d+)?(/([\\w/_.]*(\\?\\S+)?)?)?";
	public final String COMMENT_HTML = "<!-{2,}.*?-{2,}>";
	public final String COMMENT_CODE = "\\/\\/.*";
	
	/**
	 * 지정 문자열(target)이 패턴(pattern)에 일치하는지 여부 확인 
	 * 
	 * @param pattern
	 * @param target
	 * @return
	 */
	public boolean isMatch(String pattern, String target) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target))
			return false;
		
		Pattern p = Pattern.compile(pattern);
		return p.matcher(target).matches();
	}
	
	/**
	 * 지정 문자열(target)에서 패턴(pattern)에 일치하는 문자가 있다면, 변경할 문자열(changeStr)로 모두 치환
	 * 
	 * @param pattern
	 * @param target
	 * @param changeStr
	 * @return
	 */
	public String replaceAllMatchStr(String pattern, String target, String changeStr) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target))
			return null;
		
		Pattern p = Pattern.compile(pattern);
		return p.matcher(target).replaceAll(changeStr);
	}
	
	/**
	 * 지정 문자열(target)에서 패턴(pattern)에 일치하는 가장 첫번째 단어만을 변경할 문자열(changeStr)로 치환.
	 * 
	 * @param pattern
	 * @param target
	 * @param changeStr
	 * @return
	 */
	public String replacePrefixMatchStr(String pattern, String target, String changeStr) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target) || tu.isNullOrEmpty(changeStr)) 
			return null;
		
		Pattern p = Pattern.compile(pattern);
		return p.matcher(target).replaceFirst(changeStr);
	}
	
	
	/**
	 * 지정 문자열(target)에서 패턴(pattern)에 일치하는 단어의 길이를 리턴.
	 * 
	 * @param pattern
	 * @param target
	 * @return
	 */
	public Integer getMatchStrLength(String pattern, String target) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target))
			return null;
		
        int matchStart = -1;
        int matchLength = -1;
            
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(target);
        
        if(m.find()) {
            matchStart = m.start();
            System.out.println("start : "+matchStart);
            System.out.println("end : "+ m.end());
            matchLength = m.end() - matchStart;
            System.out.println("matchLength : " + matchLength);
            
            return (Integer) matchLength;
        } else {
        	return null;
        }
	}
	
	/**
	 * 지정 문자열(target)에서 패턴(pattern)에 일치하는 단어가 몇개인지 일치수를 리턴
	 * @param pattern
	 * @param target
	 * @return
	 */
	public Integer getMatchCount(String pattern, String target) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target))
			return null;
		
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(target);
        //int res = (Integer) m.groupCount();
        List<String> list = new ArrayList<String>();
        int res = 0;
        while (m.find()) {
        	res++;
        }
        
        return (Integer) res;
	}
	
	/**
	 * 지정 문자열(target)에서 패턴(pattern)에 일치하는 단어의 리스트를 추출해서 리턴
	 * @param pattern
	 * @param target
	 * @return
	 */
	public List<String> getMatchStrList(String pattern, String target) {
		if (tu.isNullOrEmpty(pattern) || tu.isNullOrEmpty(target))
			return null;
		
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(target);
		List<String> list = new ArrayList<String>();
        
        while (m.find()) {
        	list.add(m.group());
        }
        
        return (0 < list.size()) ? list : null;
	}
	
}
