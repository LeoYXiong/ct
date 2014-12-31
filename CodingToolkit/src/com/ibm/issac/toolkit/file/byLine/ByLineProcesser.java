package com.ibm.issac.toolkit.file.byLine;

/**
 * ���line by line reader�����д���TEXT FILE
 * @author issac
 *
 */
public interface ByLineProcesser {

	/**
	 * ��������ı���ÿ��ֻҪ����һ������λ���ı����ɡ�
	 * @param text
	 */
	public void process(String text);
	
	
	/**
	 * ִ��һЩ�ı��Ѿ�ȫ�������Ҫ���еĲ���
	 * @param msg
	 */
	public Object afterProcessing(Object msg);

}
