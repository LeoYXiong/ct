package com.ibm.issac.toolkit.file.byLine;

/**
 * �����н���ʱ�������н����������ݱ���Ϊ�ö��󣬸ö���ͬʱ�����һ���ı���װ������
 * @author issac
 *
 */
public abstract class LineData {

	/**
	 * ��һ���ı�������ȡ����ǰ������
	 * @param lineStr
	 * @return �Ƿ�����ɹ�
	 */
	public abstract boolean parse(String lineStr);
}
