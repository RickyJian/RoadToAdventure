package tw.org.roadtoadventure.form;

import tw.org.roadtoadventure.bean.PersonalBean;

public class UpdatePersonalJourneyForm extends PersonalBean {

	private String overviewPolylineArrayStr ;
	private String locationArrayStr ;

	public String getOverviewPolylineArrayStr() {
		return overviewPolylineArrayStr;
	}
	public void setOverviewPolylineArrayStr(String overviewPolylineArrayStr) {
		this.overviewPolylineArrayStr = overviewPolylineArrayStr;
	}
	public String getLocationArrayStr() {
		return locationArrayStr;
	}
	public void setLocationArrayStr(String locationArrayStr) {
		this.locationArrayStr = locationArrayStr;
	}
	@Override
	public String getPersonalJourneyContent() {
		// TODO Auto-generated method stub
		return super.getPersonalJourneyContent();
	}
	@Override
	public void setPersonalJourneyContent(String personalJourneyContent) {
		// TODO Auto-generated method stub
		super.setPersonalJourneyContent(personalJourneyContent);
	}

}
