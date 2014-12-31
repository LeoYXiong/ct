package com.ibm.issac.toolkit.dto;

import org.apache.log4j.PropertyConfigurator;

import com.ibm.issac.toolkit.logging.ColorLog;
import com.ibm.issac.toolkit.validation.StringValidation;

/**
 * ���������ļ��������������ʱ����
 * 
 * ������������ʱ����ʹ��public���������ڱ�DTO�С�
 * 
 * @author issac
 * 
 */
public abstract class AbstractRuntimeRegistryDTO {
	/**
	 * ���������ļ�����������Ϣ��ȡ����
	 * 
	 * @param fileName
	 */
	public abstract void loadProperties(String fileName);

}
