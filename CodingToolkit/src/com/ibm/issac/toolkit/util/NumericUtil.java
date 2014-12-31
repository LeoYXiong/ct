package com.ibm.issac.toolkit.util;

public class NumericUtil {

	private final static char[] HEX = "0123456789abcdef".toCharArray();

	/**
	 * �� int ��������ת�ɶ����Ƶ��ַ��������� int ����λ��ʱ��ǰ����0���Դ���λ��
	 * 
	 * @param num
	 * @return
	 */
	public static String toFullBinaryString(int num) {
		char[] chs = new char[Integer.SIZE];
		for (int i = 0; i < Integer.SIZE; i++) {
			chs[Integer.SIZE - 1 - i] = (char) (((num >> i) & 1) + '0');
		}
		return new String(chs);
	}

	/**
	 * �� int ��������ת��ʮ�����Ƶ��ַ��������� int ����λ��ʱ��ǰ����0���Դ���λ��
	 * 
	 * @param num
	 * @return
	 */
	public static String toFullHexString(int num) {
		char[] chs = new char[Integer.SIZE / 4]; // integer��SIZE��32
		for (int i = 0; i < chs.length; i++) {
			chs[chs.length - 1 - i] = HEX[(num >> (i * 4)) & 0xf];
		}
		return new String(chs);
	}

	/**
	 * MQ HEXÿ������ֻ��2λ
	 * 
	 * @param num
	 *            ע�⣺������0-255��ʾ��BYTE��JAVAĬ��BYTE��ΧΪ-127~128
	 * @return
	 */
	public static String toMQHexString(int num) {
		char[] chs = new char[Integer.SIZE / 16]; // integer��SIZE��32
		for (int i = 0; i < chs.length; i++) {
			chs[chs.length - 1 - i] = HEX[(num >> (i * 4)) & 0xf];
		}
		return new String(chs);
	}

	/**
	 * ��-127-128��BYTE����ת��Ϊ0-255��ʾ����������
	 * 
	 * @param b
	 * @return
	 */
	public static int toIntByte(byte b) {
		return -1; //����û�ҵ��򵥷�������EncodingUtil����ByteArrayInputStream��������⡣
	}

	/**
	 * �� long ��������ת�ɶ����Ƶ��ַ��������� long ����λ��ʱ��ǰ����0���Դ���λ��
	 * 
	 * @param num
	 * @return
	 */
	public static String toFullBinaryString(long num) {
		char[] chs = new char[Long.SIZE];
		for (int i = 0; i < Long.SIZE; i++) {
			chs[Long.SIZE - 1 - i] = (char) (((num >> i) & 1) + '0');
		}
		return new String(chs);
	}

	/**
	 * �� long ��������ת��ʮ�����Ƶ��ַ��������� long ����λ��ʱ��ǰ����0���Դ���λ��
	 * 
	 * @param num
	 * @return
	 */
	public static String toFullHexString(long num) {
		char[] chs = new char[Long.SIZE / 4];
		for (int i = 0; i < chs.length; i++) {
			chs[chs.length - 1 - i] = HEX[(int) ((num >> (i * 4)) & 0xf)];
		}
		return new String(chs);
	}
}
