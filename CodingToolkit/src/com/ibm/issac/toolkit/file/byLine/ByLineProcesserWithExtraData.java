package com.ibm.issac.toolkit.file.byLine;

/**
 * ���line by line reader�����д���TEXT FILE
 * @author issac
 *
 */
public interface ByLineProcesserWithExtraData extends ByLineProcesser {

	/**
	 * ���ڴ���ĸ�������
	 * @param obj
	 */
	public void setExtraData(Object obj);

}
