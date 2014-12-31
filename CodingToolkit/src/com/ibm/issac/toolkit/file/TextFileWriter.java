package com.ibm.issac.toolkit.file;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.ibm.issac.toolkit.DevLog;

/**
 * д�ı��ļ����йز���
 * 
 * @author issac
 * 
 */
public class TextFileWriter {
	
	/**
	 * д���ļ����Ḳ�ǵ�ԭ���ļ���
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void writeTextFile(String fileName, String content) throws IOException {
		DevLog.debug("Writing file: "+fileName);
		final PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName)), true);
		pw.println(content);
		pw.close();
	}
	
	/**
	 * д���ļ�ĩβ������ļ��������򴴽����ļ�
	 * @param fileName
	 * @param content
	 * @throws IOException
	 */
	public static void appendToFile(String fileName, String content) throws IOException{
		DevLog.debug("Appending file: "+fileName);
		FileWriter writer = new FileWriter(fileName, true);
		writer.write(content);
		writer.close();
	}
}