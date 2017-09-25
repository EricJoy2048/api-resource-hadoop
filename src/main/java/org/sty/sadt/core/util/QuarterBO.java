package org.sty.sadt.core.util;

import java.util.Calendar;
import java.util.Date;
/**
 * <li>功能描述：用于保存和计算季度信息
 * @author 高俊
 *
 */
public class QuarterBO {

	private int year;
	
	private int quarte;
	
	private Date beginDate;
	
	private Date endDate;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuarte() {
		return quarte;
	}

	public void setQuarte(int quarte) {
		this.quarte = quarte;
	}

	public Date getBeginDate() {
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.YEAR, year);
		int month = quarte*3-1;
		c1.set(Calendar.MONTH, month-1);
		c1.set(Calendar.DATE, 1);
		String str = DateFormatTool.dateToStringY(c1.getTime());
		c1.setTime(DateFormatTool.stringToDate(str));
		this.beginDate = c1.getTime();
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		this.beginDate = getBeginDate();
		Calendar c2 = Calendar.getInstance();
		c2.setTime(this.beginDate);
		c2.add(Calendar.MONTH, 3);
		this.endDate = c2.getTime();
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object obj) {
		QuarterBO bo = (QuarterBO)obj;
		if(bo == null){
			return false;
		}
		
		if(bo.getYear() == this.year && bo.getQuarte() == this.quarte){
			return true;
		}
		
		return false;
	}
	
	
	
	
}
