package tw.org.roadtoadventure.form;

import tw.org.roadtoadventure.bean.GroupBean;

public class CreateGroupForm extends GroupBean {

	
	@Override
	public String getGroupDescription() {
		// TODO Auto-generated method stub
		return super.getGroupDescription();
	}

	@Override
	public void setGroupDescription(String groupDescription) {
		// TODO Auto-generated method stub
		super.setGroupDescription(groupDescription.equals("")?null:groupDescription);
	}

	@Override
	public String getGroupName() {
		// TODO Auto-generated method stub
		return super.getGroupName();
	}

	@Override
	public void setGroupName(String groupName) {
		// TODO Auto-generated method stub
		super.setGroupName(groupName);
	}

	@Override
	public String getGroupPicture() {
		// TODO Auto-generated method stub
		return super.getGroupPicture();
	}

	@Override
	public void setGroupPicture(String groupPicture) {
		// TODO Auto-generated method stub
		super.setGroupPicture(groupPicture.trim());
	}

}
