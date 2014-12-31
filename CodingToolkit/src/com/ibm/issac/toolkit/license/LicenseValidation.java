package com.ibm.issac.toolkit.license;

import com.ibm.issac.toolkit.license.pattern.BasicDateValidationPattern;
import com.ibm.issac.toolkit.license.pattern.FlooredSqrtPattern;

/**
 * IREϵ�еĹ��߲������κ��û�ʹ�á�����ṩ��һЩ��Ȩ������
 * @author issac
 *
 */
public final class LicenseValidation {

	/**
	 * ��֤��ǰ�û��Ƿ�ӵ��LICENSE��
	 * @param licensePattern ��ͬ��LICENSE��֤����
	 * @param requestCode �����룬����LICENSE��֤���͵Ĳ�ͬ������������Ҳ��������
	 * @return �û��Ƿ����LICENSE
	 */
	public boolean isLicenseGranted(String licensePattern, String requestCode){
		if("BasicDateValidation_LicenseSurfix".equals(licensePattern))
			return new BasicDateValidationPattern().validate(requestCode);
		if("FlooredSqrtPattern".equals(licensePattern))
			return new FlooredSqrtPattern().validate(requestCode);
		throw new RuntimeException("License Pattern "+licensePattern+" does not exist.");
	}
}
