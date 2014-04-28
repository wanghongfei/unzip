package cn.fh;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class App {
	public static void main(String[] args) throws IOException {
		if (args.length >= 1) {
			System.out.println("opening file:" + args[0]);
			unzip(args[0]);
		} else {
			System.out.println("Please specify the name of the file that you want to uncompress.");
		}
	}
	
	public static void unzip(String fileName) throws IOException {
		ZipInputStream zin = new ZipInputStream(new FileInputStream(fileName), Charset.forName("GB2312"));
		ZipEntry en = null;
		FileOutputStream fos = null;
		File file = null;
		
		while ((en = zin.getNextEntry()) != null) {
			if (true == en.isDirectory()) {
				System.out.println("mkdir " + en.getName());
				file = new File(en.getName());
				file.mkdir();
			} else {
				System.out.println("extracting file " + en.getName());
				fos = new FileOutputStream(en.getName());
				
				byte[] buf = new byte[2048];
				int len = 0;
				while ((len = zin.read(buf, 0, buf.length)) != -1) {
					fos.write(buf, 0, len);
				}
				fos.close();
			}
			
			zin.closeEntry();
		}
		
		zin.close();
	}
}
