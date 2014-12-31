package com.ibm.issac.toolkit.app;

import java.util.HashMap;
import java.util.Map;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.logging.Plog;
import com.ibm.issac.toolkit.util.DateUtil;

public abstract class AbstractIREApp {
	protected Map paramMap = new HashMap();

	public AbstractIREApp() {
		String appName = this.buildAppName();
		DevLog.init(appName);
		// ��ӡ������Ϣ
		Plog.p0("App started for class " + this.getClass().getName() + " at " + DateUtil.getDatetimeNow());

	}

	private String buildAppName() {
		String className = this.getClass().getName();
		if (className.indexOf('.') < 0) {
			return className;
		}
		String appName = className.substring(className.lastIndexOf('.') + 1, className.length());
		DevLog.debug("appName set to >" + appName + "<");
		return appName;
	}

	protected void setParamMap(Map paramMap) {
		this.paramMap.putAll(paramMap);
		DevLog.info("PARAM LIST: " + paramMap.toString());
	}

	/**
	 * �Ӳ����л��STRING
	 * 
	 * @param keyName
	 * @return
	 */
	protected String pstr(String keyName) {
		return (String) this.paramMap.get(keyName);
	}

	/**
	 * �����޸��û�����
	 * 
	 * @deprecated �����׻���������setParamData
	 * @param keyName
	 * @param keyValue
	 * @return
	 */
	protected void changeParamData(String keyName, Object keyValue) {
		this.paramMap.put(keyName, keyValue);
	}

	/**
	 * �����޸��û�����
	 * 
	 * @param keyName
	 * @param keyValue
	 * @return
	 */
	protected void setParamData(String keyName, Object keyValue) {
		this.paramMap.put(keyName, keyValue);
	}

	/**
	 * �ڲ������ṩһ����Ϊmode�ģ�����Ϊ�ַ�����<br/>
	 * ���mode���ַ���ֵ������˸�����ֵ������true������Ϊfalse
	 * 
	 * @param modeName
	 * @return
	 */
	protected boolean hasMode(String modeName) {
		if (pstr("mode") == null)
			return false;
		return pstr("mode").indexOf(modeName) >= 0;
	}

	protected int pint(String keyName) {
		try {
			Integer intVal = (Integer) this.paramMap.get(keyName);
			return intVal.intValue();
		} catch (ClassCastException e) {
			// ����ṩ��String���ͣ�����ת��Ϊint
			return (Integer.valueOf((String) this.paramMap.get(keyName))).intValue();
		} catch (NullPointerException e) {
			throw new RuntimeException("Failed locating integer key " + keyName);
		}
	}

	protected boolean pboolean(String keyName) {
		Boolean booleanVal = (Boolean) this.paramMap.get(keyName);
		try {
			return booleanVal.booleanValue();
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed locating boolean key " + keyName);
		}
	}

	protected void addDefaultValueForNullParam(Map paramMap, String keyName, Object userVal, Object defaultVal) {
		if (userVal == null)
			paramMap.put(keyName, defaultVal);
		else
			paramMap.put(keyName, userVal);
	}

	protected double pdouble(String keyName) {
		Double db = (Double) this.paramMap.get(keyName);
		try {
			return db.doubleValue();
		} catch (NullPointerException e) {
			throw new RuntimeException("Failed locating double key " + keyName);
		}
	}
}
