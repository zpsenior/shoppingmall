package shoppingmall.alipay;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class StreamUtil {

	public static Map<String, byte[]> unzip(InputStream is)throws Exception{
		 Map<String, byte[]> map = new HashMap<String, byte[]>();
		 
		 ZipArchiveInputStream zis = new ZipArchiveInputStream(is, "gbk");
		 ZipArchiveEntry entry = null;
		 
		 byte[] buff = new byte[1024];
		 
		 while((entry = zis.getNextZipEntry()) != null){
			 String fileName = entry.getName();
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 int cnt = 0;
			 while((cnt=zis.read(buff)) > 0){
				 bos.write(buff, 0, cnt);
			 }
			 map.put(fileName, bos.toByteArray());
		 }
		 zis.close();
		 
		 return map;
	}

	public static byte[] read2Buff(InputStream is)throws Exception{
		int cnt = 0;
		byte[] buff = new byte[1024];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while((cnt=is.read(buff)) > 0){
			bos.write(buff, 0, cnt);
		}
		return bos.toByteArray();
	}
	
	public static void read2File(InputStream is, String fileName)throws Exception{
		int cnt = 0;
		byte[] buff = new byte[1024];
		FileOutputStream fos = new FileOutputStream(fileName);
		while((cnt=is.read(buff)) > 0){
			fos.write(buff, 0, cnt);
		}
		fos.flush();
		fos.close();
	}
	
	public static List<String> read2Lines(InputStream is)throws Exception{
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(is, "gbk"));
		List<String> lst = new ArrayList<String>();
		while(true){
			String line = reader.readLine();
			if(line == null){
				break;
			}
			line = line.trim();
			System.out.println(line);
			lst.add(line);
		}
		return lst;
	}
}
