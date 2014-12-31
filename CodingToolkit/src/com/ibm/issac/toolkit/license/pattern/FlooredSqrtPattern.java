package com.ibm.issac.toolkit.license.pattern;

import java.util.Date;

import com.ibm.issac.toolkit.DatetimeUtility;
import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.logging.ColorLog;

/**
 * ��֤�벻�����ű��������������Ӷ�������������Ѷ�
 * 
 * @author issac
 * 
 */
public class FlooredSqrtPattern extends LicensePattern {

	public boolean validate(String requestCode) {
		try {
			String currentDate = DatetimeUtility.formatDate("MMdd", new Date());
			final int dateInt = Integer.valueOf(currentDate).intValue();
			final int requestInt = Integer.valueOf(requestCode).intValue();
			if (this.isRequestCodeValid(dateInt, requestInt)) {
				ColorLog.debug("The ire number is valid.");
				return true;
			}
		} catch (Exception e) {
			ColorLog.warn("An error occurred while validating the IRE NUMBER.");
		}
		return false;
	}

	/**
	 * ����������Ƿ���Ч
	 * 
	 * @param dateInt
	 * @param requestInt
	 * @return
	 */
	private boolean isRequestCodeValid(int dateInt, int requestInt) {
		double flooredDate = Math.floor(Math.sqrt(dateInt) * 100);
		if (flooredDate == requestInt)
			return true;
		return false;
	}

	public static void main(String[] args) {
		String currentDate = DatetimeUtility.formatDate("MMdd", new Date());
		final int dateInt = Integer.valueOf(currentDate).intValue();
		DevLog.debug(Math.floor(Math.sqrt(dateInt) * 100) + "");
	}
}
