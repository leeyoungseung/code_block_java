package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileIOUtil {

	TextUtil tu = TextUtil.getInstance();
	public final int PERMISSION_E = 1;
	public final int PERMISSION_W = 2;
	public final int PERMISSION_R = 4;
	public final int PERMISSION_WE = 3;
	public final int PERMISSION_RE = 5;
	public final int PERMISSION_RWE = 7;
	
	public FileIOUtil() { }
	
	/**
	 * 파일이 있으면 true, 없으면 false 리턴
	 * 
	 * @param filePath
	 * @return
	 */
	public boolean existFile(String filePath) {
		if (tu.isNullOrEmpty(filePath)) {
			return false;
		}
		
		File f = new File(filePath);
		return f.isFile();
	}
	
	/**
	 * 디렉토리가 있으면 true, 없으면 false 리턴
	 * 
	 * @param dirPath
	 * @return
	 */
	public boolean existDir(String dirPath) {
		if (tu.isNullOrEmpty(dirPath)) {
			return false;
		}
		File d = new File(dirPath);
		return d.isDirectory();
	}
	
	/**
	 * 파일의 권한을 숫자로 리턴. 실행권한 : 1, 쓰기권한 : 2, 읽기 권한 : 4
	 * 
	 * @param path
	 * @return
	 */
	public Integer checkPermission(String path) {
		if (tu.isNullOrEmpty(path)) {
			return null;
		}
		File f = new File(path);
		
		if (!existFile(path)) {
			return null;
		}
		
		int permission = 0;
		permission += (f.canExecute()) ? PERMISSION_E : 0;
		permission += (f.canWrite()) ? PERMISSION_W : 0;
		permission += (f.canRead()) ? PERMISSION_R : 0;
		
		return (Integer) permission;
	}
		

	/**
	 * (권한설정 메소드 1번) 파일의 권한을 설정한다. 
	 * 모든권한 : 7, 읽기권한 : 4, 쓰기권한 : 2, 실행권한 : 1
	 * 읽기 + 쓰기 : 6, 읽기 + 실행 : 5, 쓰기+실행 : 3
	 * 
	 * @param target
	 * @param permission
	 * @return
	 */
	public boolean setFilePermission(String target, Integer permission) {
		if (tu.isNullOrEmpty(target) || permission == null) {
			return false;
		}
		
		if (!existFile(target)) {
			return false;
		}
		
		return setFilePermission(new File(target), permission);
	}
	
	
	/**
	 * (권한설정 메소드 2번) 파일의 권한을 설정한다. 
	 * 모든권한 : 7, 읽기권한 : 4, 쓰기권한 : 2, 실행권한 : 1
	 * 읽기 + 쓰기 : 6, 읽기 + 실행 : 5, 쓰기+실행 : 3
	 * 
	 * @param target
	 * @param permission
	 * @return
	 */
	public boolean setFilePermission(File target, Integer permission) {
		if (target == null || permission == null) {
			return false;
		}
		
		if (!existFile(target.getAbsolutePath())) {
			return false;
		}
		
		boolean res = false;
		
		switch (permission) {
		case PERMISSION_E:
			res = setPermission(target,false,false,true);
			break;
		case PERMISSION_W:
			res = setPermission(target,false,true,false);
			break;
		case PERMISSION_R:
			res = setPermission(target,true,false,false);
			break;
		case PERMISSION_WE:
			res = setPermission(target,false,true,true);
			break;
		case PERMISSION_RE:
			res = setPermission(target,true,false,true);
			break;
		case PERMISSION_RWE:
			res = setPermission(target,true,true,true);
			break;
		default:
			res = setPermission(target,false,false,false);
			break;
		}
		
		return res;
	}
	
	/**
	 * 파일 권한 설정 서브 메소드
	 * 
	 * @param target
	 * @param read
	 * @param write
	 * @param exe
	 * @return
	 */
	private boolean setPermission(File target , boolean read, boolean write, boolean exe) {
		try {
			target.setReadable(read);
			target.setWritable(write);
			target.setExecutable(exe);
			return true;
		} catch (Exception e1) { e1.printStackTrace(); return false; }
	}
	
	/**
	 * 파일을 생성
	 * 
	 * @param path
	 * @return
	 */
	public File createFile(String path) {
		if (tu.isNullOrEmpty(path)) {
			return null;
		}
		
		File f = new File(path);
		
		if (!existFile(path)) {
		    try {
			    f.createNewFile();
			
		    } catch (IOException e) { e.printStackTrace(); }
		
		}
		return f;
	}
	
	/**
	 * 디렉토리 생성
	 * 
	 * @param path
	 * @return
	 */
	public boolean createDir(String path) {
		boolean res = false;
		if (tu.isNullOrEmpty(path)) {
			return res;
		}
		
		File f = new File(path);
		
		
		if (!f.exists()) {
			try {
				
				f.mkdir();
				res = true;
				
			} catch (Exception e) { e.printStackTrace(); return res; }
		}
		
		return res;
		
	}
	
	/**
	 * 파일 또는 디렉토리 삭제
	 * @param path
	 * @return
	 */
	public boolean delete(String path) {
		if (tu.isNullOrEmpty(path)) {
			return false;
		}
		
		File f = new File(path);
		
		try {
			
			return f.delete();
		} catch (Exception e) { e.printStackTrace(); return false;}
		
	}
	
	/**
	 * 파일을 줄단위로 읽어오기
	 * 
	 * @param file
	 * @return
	 */
	public List<String> readFilePerLine(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		String line = "";
		
		try {
			
			br = new BufferedReader(new FileReader(file));
			while((line = br.readLine()) != null) {
				list.add(line);
			}
			
		} catch (IOException ioe) { ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException ioe) { ioe.printStackTrace(); }
			}
		}
		
		return list;
	}
	
	/**
	 * CSV파일을 읽어오기
	 * 
	 * @param path
	 * @return
	 */
	public List<String[]> readCsvFile(String path) {
		if (tu.isNullOrEmpty(path)) {
			return null;
		}
		
		File f = new File(path);
		
		List<String> tmpList = readFilePerLine(f);
		List<String[]> resList = new ArrayList<String[]>();
		
		for (String var : tmpList) {
			resList.add(tu.makeArrayFromStr(var));
		}
		
		return resList;
	}
	
	/**
	 * 파일에 줄단위로 데이터를 입력한다. 
	 * 
	 * @param file
	 * @param data
	 * @return
	 */
	public boolean writeFilePerLine(File file , List<String> data) {
		
		BufferedWriter wr = null;
		
		try {
			wr = new BufferedWriter(new FileWriter(file));
			for (String var : data) {
				wr.append(var);
				wr.append("\n");
			}
			wr.flush();
			
		} catch (IOException ioe) {ioe.printStackTrace(); return false;
		} finally {
			if (wr != null) {
				try {
					wr.close();
					wr = null;
				} catch (IOException ioe) { ioe.printStackTrace(); }
			}
		}
		
		return true;
	}
	
	/**
	 * CSV파일에 데이터를 줄단위로 입력한다. 
	 * 
	 * @param path
	 * @param data
	 * @return
	 */
	public boolean writeCsvFile(String path, List<String[]> data) {
		if (tu.isNullOrEmpty(path) || data == null || data.size() == 0) {
			return false;
		}
		
		File f = new File(path);
		List<String> tmpList = new ArrayList<String>();
		
		for (String[] var : data) {
			tmpList.add(tu.convertCsvFormatRecode(var));
		}
		
		return writeFilePerLine(f,tmpList);
	}
	
}
