package com.ibm.issac.toolkit.format;

public class TbF {

	/**
	 * ������ʾ����ʱ����������룬�÷������������Ʊ����
	 * 
	 * @param s
	 *            ԭʼ�ַ���
	 * @param l
	 *            ���s���Ȳ���l�����㳤��ʱ���ְ����������㡣�򲹳�ո�lΪֹ
	 * @return
	 */
	public static String td(String s, int l) {
		int currentL = s.getBytes().length;
		int gap = l - currentL;
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i <= gap; i++) {
			sb.append(' ');
		}
		sb.append('\t');
		return sb.toString();
	}
}
