package com.ibm.issac.toolkit.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.param.SysProp;

/**
 * ��ʾ�ַ�����
 * 
 * @author issac
 * 
 */
public class EncodingUtil {
	
	public static String r(Object obj){
		return EncodingUtil.r(obj, SysProp.b_str("issac.encoding.default", "UTF-8"));
	}
	
	/**
	 * ��Ҫ����STRING�ĸ����ַ�����
	 * 
	 * @param obj
	 *            Ϊ�˱��ڲ������룬���ﲻ��STRING��ֱ�ӷ�����
	 * @param strEncoding
	 * @return
	 */
	public static String r(Object obj, String strEncoding) {
		if (!SysProp.b_bool("issac.debugMode.encoding", true)) {
			return null;
		}
		if (obj == null) {
			return "EC{\"null input string\"}";
		}
		String str = null;
		if (!(obj instanceof String)) {
			return "EC{\"Non-string\"}";
		}
		str = (String) obj;
		try {
			// �����ֽ���
			final ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes(strEncoding));
			// �������ֽ���
			final StringBuffer sb = new StringBuffer("EC{");
			sb.append(strEncoding);
			sb.append(":\"");
			int k = -1;
			while ((k = bais.read()) != -1) {
				sb.append("[").append(k).append("] ");
			}
			sb.append("\"}");
			bais.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void printWithEncoding(String str, String stringEncoding, String printEncoding) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes(stringEncoding));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = bais.read()) != -1) {
			baos.write(i);
		}
		baos.flush();
		bais.close();
		baos.close();
		EncodingUtil.printWithEncoding(baos, printEncoding);
	}

	public static void printWithEncoding(ByteArrayOutputStream baos, String printEncoding) {
		DevLog.display("\n");
		DevLog.display("\n");
		DevLog.display("\n");
		DevLog.display("\n");
		DevLog.display("\n############# BEGIN PRINTING THE ENCODED IN " + printEncoding + " #############\n");
		final String parsedStr = EncodingUtil.parseWithEncoding(baos, printEncoding);
		DevLog.display("\n### THE COMPLETE STRING\n");
		DevLog.display(parsedStr + "\n");
		// ��ʾ��ENCODING��Ӧ���ַ�����������ģ�ÿ�����ֶ�Ӧһ��CHAR
		DevLog.display("\n### PARSED CHAR ARRAY\n");
		final char[] charArray = parsedStr.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			DevLog.display("[" + charArray[i] + "]\t");
		}
		EncodingUtil.printRuler(charArray.length);
		// ��ʾ��ENCODING��Ӧ��BYTE ARRAY����������ģ�ÿ�����ֶ�Ӧ���BYTE
		DevLog.display("\n### PARSED BYTE ARRAY (-127~128)\n");
		// final byte[] byteArray = parsedStr.getBytes();
		final byte[] byteArray = baos.toByteArray();
		for (int i = 0; i < byteArray.length; i++) {
			DevLog.display("[" + byteArray[i] + "]\t");
		}
		EncodingUtil.printRuler(byteArray.length);
		// ��ʾ0-255�����BYTE���Լ���Ӧ��HEX
		DevLog.display("\n### PARSED INTEGER ARRAY (0~255), AND HEX\n");
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		int k = -1;
		int count = 0;
		String hexStr = "";
		while ((k = bais.read()) != -1) {
			DevLog.display("[" + k + "]\t");
			hexStr += "[" + NumericUtil.toMQHexString(k) + "]\t";
			count++;
		}
		DevLog.display("\n" + hexStr);
		EncodingUtil.printRuler(count);
		try {
			bais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DevLog.display("\n### END PRINTING THE ENCODED IN " + printEncoding + " ###\n");
		DevLog.display("\n");
	}

	/**
	 * ��ʾ������ţ�������ߣ���������ַ�
	 */
	private static void printRuler(int length) {
		DevLog.display("\n");
		int j = length;
		for (int i = 1; i <= length; i++, j--) {
			DevLog.display(i + "," + j + "\t");
		}
		DevLog.display("\n");
	}

	public static String parseWithEncoding(ByteArrayOutputStream baos, String encoding) {
		try {
			return baos.toString(encoding);
		} catch (UnsupportedEncodingException e) {
			return "FailedToParseHex{Unable to parse the string as " + encoding + "}";
		}
	}

	/**
	 * ת��BYTE�������
	 * 
	 * @param byteArrayEncoding
	 *            ����������BYTE�����ʵ�ʱ���
	 * @param convertedEncoding
	 *            ϣ��ת���ɵ�Ŀ�����
	 * @param bArray
	 *            BYTE���飬��-127~128��ʾ�������0-255�ģ���(byte)238��������ת��
	 * @return
	 * @throws IOException
	 */
	public static String changeEncoding(String byteArrayEncoding, String convertedEncoding, byte[] bArray) throws IOException {
		DevLog.info("###### BEFORE CHANGING ENCODING #######");
		String sourceStr = new String(bArray, byteArrayEncoding);
		EncodingUtil.printWithEncoding(sourceStr, byteArrayEncoding, byteArrayEncoding);
		DevLog.info("###### AFTER CHANGING ENCODING ########");
		String convertedStr = new String(sourceStr.getBytes(convertedEncoding), convertedEncoding);
		EncodingUtil.printWithEncoding(convertedStr, convertedEncoding, convertedEncoding);
		return convertedStr;
	}
}
