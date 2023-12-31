package com.asl.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;

public class FileHandling {
	
	private String getFileName(final Part part,String id) {
		String pc=part.getContentType();
		return id+"."+pc.substring(pc.indexOf('/')+1);
//		final String partHeader = part.getHeader("content-disposition");
//		for (String content : partHeader.split(";")) {
//			if (content.trim().startsWith("filename")) {
//				return email+content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
//			}
//		}
//		return null;
	}

	public String saveFile(String uploadPath, Part part,String id) throws Exception {
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		String fileName = getFileName(part,id);
		OutputStream out1 = new FileOutputStream(new File(uploadPath + File.separator + fileName));
		InputStream fileContent = part.getInputStream();
		int read = 0;
		final byte[] bytes = new byte[1024];
		while ((read = fileContent.read(bytes)) != -1) {
			out1.write(bytes, 0, read);
		}
		out1.flush();
		out1.close();
		fileContent.close();
		System.out.print("the file name is" +fileName);

		return fileName;
	}

}
