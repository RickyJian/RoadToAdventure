package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.vo.GroupJourney;

public interface GroupJourneyDAO extends BaseDAO<GroupJourney> {
	
	public List<GroupJourney> readAllWithJoin();
	
	public List<GroupJourney> readByParameter(GroupBean groupBean);

}
