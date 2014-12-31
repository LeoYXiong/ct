package com.ibm.issac.toolkit.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.logging.Plog;
import com.ibm.issac.toolkit.validation.StringValidation;

public class ConsoleInput {

	/**
	 * �ռ��û�������ַ���
	 * 
	 * @param question
	 * @param �û�ֱ�Ӱ��س�������¸���Ĭ��ֵ
	 * @return
	 * @throws IOException
	 */
	public static String answerString(String question, String defaultVal) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Plog.p2(question);
		String answer = br.readLine();
		if (StringValidation.isStringReadable(answer))
			return answer;
		return defaultVal;

	}

	/**
	 * �ռ��û����������
	 * 
	 * @param question
	 * @return
	 * @throws IOException
	 */
	public static int answerInt(String question, int defaultVal) throws IOException {
		String ansStr = ConsoleInput.answerString(question, null);
		try {
			int i = Integer.valueOf(ansStr).intValue();
			return i;
		} catch (Exception e) {
			DevLog.warn("���ڸ�����Ļش��ܽ���Ϊint����ʹ��Ĭ��ֵ" + defaultVal + "�������ǣ�" + question);
			e.printStackTrace();
			return defaultVal;
		}
	}

	public static double answerDouble(String question, double dv) throws IOException {
		String ansStr = ConsoleInput.answerString(question, null);
		try {
			double dbl = Double.valueOf(ansStr).doubleValue();
			return dbl;
		} catch (Exception e) {
			DevLog.warn("���ڸ�����Ļش��ܽ���Ϊdouble����ʹ��Ĭ��ֵ" + dv + "�������ǣ�" + question);
			e.printStackTrace();
			return dv;
		}
	}

	public static float answerFloat(String question, float dv) throws IOException {
		try {
			String ansStr = ConsoleInput.answerString(question, null);
			return Float.valueOf(ansStr).floatValue();
		} catch (Exception e) {
			DevLog.warn("���ڸ�����Ļش��ܽ���Ϊfloat����ʹ��Ĭ��ֵ" + dv + "�������ǣ�" + question);
			e.printStackTrace();
			return dv;
		}
	}
}
