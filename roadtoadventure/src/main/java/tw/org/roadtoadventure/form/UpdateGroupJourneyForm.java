package tw.org.roadtoadventure.form;

import tw.org.roadtoadventure.bean.GroupBean;

public class UpdateGroupJourneyForm extends GroupBean {
	
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
	public String getGroupJourneyContent() {
		// TODO Auto-generated method stub
		return super.getGroupJourneyContent();
	}
	@Override
	public void setGroupJourneyContent(String groupJourneyContent) {
		// TODO Auto-generated method stub
		super.setGroupJourneyContent(groupJourneyContent);
	}
	
}
