package com.ibm.issac.toolkit.timing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ibm.issac.toolkit.DevLog;
import com.ibm.issac.toolkit.param.SysProp;

/**
 * ������¼���ʱ��㣬������һ�����ɱ���ļ�ʱ��<br/>
 * ��timerÿ�μ�¼ʱ�����Զ����¿�ʼ��ʱ<br/>
 * ������ȷ������ർ��CPUռ�ù��ߣ���������˿����Ƿ����õĿ��أ���ѹ�����Ե��У���ʵ������CPU��Դ����ʱ��Ӧ�ر�ʹ������ࡣ
 * 
 * @author issac
 * 
 */
public class ResettingTimer extends TimerDTO {
	private List tL = new ArrayList();
	private String title="";

	public ResettingTimer(Object obj, boolean autoStart) {
		super(autoStart);
		//���ⷴ��������CPU���Ĺ���
		if (!SysProp.b_bool("issac.debugMode.timer", true)) {
			return;
		}
		this.title = obj.getClass().getSimpleName();
		DevLog.trace("[TIMER-" + title + "] Starting");
	}

	/**
	 * ���Ƴ�������һȦ����¼һ��ʱ��
	 */
	public void lapAndReset() {
		if (!SysProp.b_bool("issac.debugMode.timer", true)) {
			return;
		}
		long t = this.getCurrentInterval();
		tL.add(new Long(t));
		DevLog.trace("[TIMER-" + title + "] lap No. " + tL.size());
		this.startTimer();
	}

	public String reportLapTimes() {
		if (!SysProp.b_bool("issac.debugMode.timer", true)) {
			return "";
		}
		return this.reportLapTimes("");
	}

	/**
	 * ����һ��ĿǰΪֹ����ʱ����¼
	 * 
	 * @return
	 */
	public String reportLapTimes(String title) {
		if (!SysProp.b_bool("issac.debugMode.timer", true)) {
			return "";
		}
		// ��ȡ���淧ֵ
		final int threshold = SysProp.d_int("issac.timerThreshold", 1);
		// �����ַ���
		final StringBuffer sb = new StringBuffer(), sb1 = new StringBuffer(" = ");
		sb.append("[TIMER-").append(title).append("]");
		sb.append(title).append(':');
		// ������ʱ��
		long total = 0;
		final Iterator it = tL.iterator();
		while (it.hasNext()) {
			Long t = (Long) it.next();
			total += t.longValue();
			// ���������ֵ����ͳ����ϸʱ��������
			sb1.append(t.longValue() / 1000.0);
			if (it.hasNext()) {
				sb1.append(" + ");
			}
		}
		if (total > threshold) {
			sb.append(total / 1000.0).append('s');
			sb.append(sb1);
		}
		return sb.toString();
	}
}
