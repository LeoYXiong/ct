package com.ibm.issac.toolkit.logging;

import java.io.IOException;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.file.TextFileWriter;
import com.ibm.issac.toolkit.util.StringUtil;

/**
 * �ռ����STRING������Ҫʱ���������STRING
 * BLog = buffer log
 * ������־������ʱ��ӡ��������־��ֻ�е�����FLUSH�����ȷ�ϵ�ǰ��־�Ѿ�����ӡ����
 * @author issac
 * 
 */
public class BLog {
	private static StringBuffer sb = new StringBuffer();
	private static String fileName = null;
	private static int AUTO_FLUSH_THRESHOLD = 100;
	private static int logCount = 0;

	public static void logln(String str) {
		sb.append(str).append('\n');
	}

	public static void log(String str) {
		sb.append(str);
		// �ж��Ƿ���ҪFLUSH
		logCount++;
		if (logCount >= BLog.AUTO_FLUSH_THRESHOLD) {
			BLog.flush();
		}
	}

	public static String getData() {
		return sb.toString();
	}

	private static void reset() {
		sb = null;
		sb = new StringBuffer();
	}

	public static void setLogFileName(String fileName) {
		BLog.fileName = fileName;
	}

	/**
	 * �ѻ��������д����̣���������
	 * 
	 * @throws IOException
	 */
	public static void flush() {
		if (StringUtil.isReadable(BLog.fileName)) {
			try {
				TextFileWriter.appendToFile(fileName, getData());
			} catch (IOException e) {
				DevLog.debug("StringLog: Failed writing to "+fileName+". Log data is not written, and will be discarded. Log data is :"+sb.toString());
				e.printStackTrace();
			}
			BLog.reset();
			return;
		}
		DevLog.warn("StringLog : Filename not set for StringLog. Log data is not written to a file. Log data is :" + sb.toString());
		BLog.reset();
	}
}
