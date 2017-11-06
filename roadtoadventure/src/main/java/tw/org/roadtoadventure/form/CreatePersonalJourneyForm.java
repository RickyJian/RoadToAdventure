package tw.org.roadtoadventure.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tw.org.roadtoadventure.bean.PersonalBean;

public class CreatePersonalJourneyForm extends PersonalBean {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	
	private String beginDay ;
	private String endDay ;
	private String beginTime ;
	private String endTime ;
	

	public String getBeginDay() {
		return beginDay;
	}

	public void setBeginDay(String beginDay) {
		this.beginDay = beginDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public Date getBeginDate() {
		// TODO Auto-generated method stub
		try {
			return sdf.parse(getBeginDay()+" "+getBeginTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setBeginDate(Date beginDate) {
		// TODO Auto-generated method stub
		try {
			super.setBeginDate(sdf.parse(getBeginDay()+" "+getBeginTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Date getEndDate() {
		// TODO Auto-generated method stub
		try {
			return sdf.parse(getEndDay()+" "+getEndTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setEndDate(Date endDate) {
		// TODO Auto-generated method stub
		try {
			super.setEndDate(sdf.parse(getEndDay()+" "+getEndTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getPersonalJourneyName() {
		// TODO Auto-generated method stub
		return super.getPersonalJourneyName();
	}

	@Override
	public void setPersonalJourneyName(String personalJourneyName) {
		// TODO Auto-generated method stub
		super.setPersonalJourneyName(personalJourneyName);
	}

}
