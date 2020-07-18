package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

public class HttpUtil {
	
	TextUtil tu = TextUtil.getInstance();

	/**
	 * Http Get요청 보내기
	 * 
	 * @param urlStr
	 * @return
	 */
	public boolean requestGET(String urlStr) {
		
		if (tu.isNullOrEmpty(urlStr)) {
			return false;
		}
		
		BufferedReader br =null;
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.addRequestProperty("User-Agent", "");
			con.setRequestMethod("GET");
			con.setDoOutput(false);
			
			con.connect();
			int responseCode = con.getResponseCode();
			StringBuilder sb = new StringBuilder();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(
						new InputStreamReader(con.getInputStream(),"utf-8"));
				String line;
				
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				
				System.out.println(""+sb.toString());
				
			} else {
				System.err.println(con.getResponseCode());
				System.err.println(con.getResponseMessage());
				return false;
			}
			
			return true;
		} catch (Exception e) { e.printStackTrace(); return false;
		} finally {
		    if (br != null) {
		    	try {
					br.close();
					br = null;
					
				} catch (Exception e) { e.printStackTrace(); }
		    }
        }
		
		
	}
	
	/**
	 * Http Post요청 보내기 (폼데이터와 함께)
	 * 
	 * @param urlStr
	 * @param contents
	 * @return
	 */
	public boolean requestPOST(String urlStr, Map<String,String> contents) {
		
		if (tu.isNullOrEmpty(urlStr)) {
			return false;
		}
		
		BufferedReader br =null;
		
		try {
			URL url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.addRequestProperty("User-Agent", "");
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			
			StringJoiner sj = new StringJoiner("&");
			for (Map.Entry<String, String> entry : contents.entrySet()) {
				sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
						+ URLEncoder.encode(entry.getValue(), "UTF-8"));
			}
			
			byte[] formData = sj.toString().getBytes(StandardCharsets.UTF_8);
			int dataLen = formData.length;
			
			con.setFixedLengthStreamingMode(dataLen);
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			
			con.connect();
			OutputStream os = con.getOutputStream();
			os.write(formData);
			
			int responseCode = con.getResponseCode();
			StringBuilder sb = new StringBuilder();
			
			if(responseCode == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(
						new InputStreamReader(con.getInputStream(),"utf-8"));
				String line;
				
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				
				System.out.println(""+sb.toString());
				
			} else {
				System.err.println(con.getResponseCode());
				System.err.println(con.getResponseMessage());
				return false;
			}
			
			return true;
		} catch (Exception e) { e.printStackTrace(); return false;
		} finally {
		    if (br != null) {
		    	try {
					br.close();
					br = null;
					
				} catch (Exception e) { e.printStackTrace(); }
		    }
		}
		
	}
	
}
