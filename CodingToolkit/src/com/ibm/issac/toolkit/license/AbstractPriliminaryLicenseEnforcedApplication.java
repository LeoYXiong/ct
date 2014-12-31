package com.ibm.issac.toolkit.license;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ʹ�ü򵥵ķ�������ǿӦ�ð�ȫ�Ե�Ӧ�á�
 * 
 * @author issac
 * 
 */
public abstract class AbstractPriliminaryLicenseEnforcedApplication {

	protected String getProductName(){
		return "IRE Family, (c)Copyright Song Jie";
	}

	/**
	 * �������������ʼ����ҵ���߼�
	 * 
	 * @param args
	 */
	public abstract void proceed(String[] args);

	/**
	 * ����IRE ACCESSERϵ�е�Ӧ�ö�ֻ��Ҫһ�����������������ļ�������
	 * 
	 * @param args
	 * @return
	 */
	protected String getPropertyFileNameFromCommandLineArguments(String[] args) {
		try {
			return args[0];
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error occurred while trying to acquire configuration file name.");
		}
	}

	protected final void checkLicense() {
		// ���غ���֤LICENSE�ļ�
		final String requestCode = loadRequestCodeFromLicenseFile();
		boolean licenseValid = new LicenseValidation().isLicenseGranted("FlooredSqrtPattern", requestCode);
		if (!licenseValid) {
			throw new RuntimeException("License data is not valid. Operation aborted.");
		}
	}

	protected String loadRequestCodeFromLicenseFile() {
		final String licenseFileName = "ire_FR.lic";
		try {
			final FileReader reader = new FileReader(licenseFileName);
			final BufferedReader br = new BufferedReader(reader);
			String requestCode = br.readLine(); // ֻ��ȡ��һ��
			br.close();
			reader.close();
			return requestCode;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException(licenseFileName + " can not be found in the current directory. The license file is required to run this program.");
	}
}
