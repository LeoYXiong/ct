package com.ibm.issac.toolkit.license.pattern;

public abstract class LicensePattern {
	
	/**
	 * ��֤�������Ƿ�ӵ��LICENSE
	 * @param requestCode
	 * @return
	 */
	public abstract boolean validate(String requestCode);
}
