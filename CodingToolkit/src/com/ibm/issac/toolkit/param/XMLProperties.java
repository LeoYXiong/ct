package com.ibm.issac.toolkit.param;

import java.util.Properties;

/**
 * ����XML��ʽ��PROPERTIES���ã�����������������ļ�������Ҫ���������Կ����ṩ���ࡣ
 * 
 * @deprecated Not Compatible with IBM-JDK 1.4.2
 * @author issac
 * 
 */
public class XMLProperties {
	public Properties parse(String fileName) {
		final Properties p = new Properties();
		/*
		try {
			FileInputStream pInStream = new FileInputStream(new File(fileName));
			// p.loadFromXML(pInStream);
			p.list(System.out);
			return p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		return p;
	}
}
