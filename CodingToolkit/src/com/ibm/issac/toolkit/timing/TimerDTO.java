package com.ibm.issac.toolkit.timing;

import java.util.Date;

import com.ibm.issac.toolkit.util.DateUtil;

/**
 * ������ʼʱ��
 * 
 * @author issac
 * 
 */
public class TimerDTO {
	private Date startDate;
	private Date stopDate;

	public TimerDTO() {
	}

	public TimerDTO(boolean startAutomatically) {
		if (startAutomatically) {
			this.startTimer();
		}
	}

	public void startTimer() {
		this.startDate = new Date();
	}

	public void stopTimer() {
		this.stopDate = new Date();
	}

	/**
	 * ��stopTimer��õ�����Ϊ��λ��ʱ���
	 * 
	 * @return
	 */
	public long getInterval() {
		return (stopDate.getTime() - startDate.getTime());
	}

	/**
	 * ��ֹͣ��ʱ�����浱ǰ�Ѿ�������ʱ��
	 * 
	 * @return
	 */
	public String reportCurrentInterval() {
		final StringBuffer sb = new StringBuffer();
		sb.append("TimerDTO{");
		sb.append("startDate : ").append(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss.SSS", this.startDate)).append(',');
		sb.append("elapsedTime : ").append(this.getCurrentInterval() / 1000.0).append("s");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * ���浱ǰ�Ѿ�������ʱ�䣬Ȼ�����¿�ʼ��ʱ
	 * @return
	 */
	public String reportAndReset(String title){
		final StringBuffer sb = new StringBuffer();
		sb.append("TIMER{");
		sb.append(title).append(':');
		//sb.append("startDate : ").append(DateUtil.formatDate("yyyy-MM-dd HH:mm:ss.SSS", this.startDate)).append(',');
		sb.append(this.getCurrentInterval() / 1000.0).append("s");
		sb.append("}");
		this.startTimer();
		return sb.toString();
	}

	/**
	 * ��ֹͣ��ʱ���������������ʱ��
	 * @return
	 */
	public long getCurrentInterval() {
		return (new Date().getTime() - startDate.getTime());
	}
}
