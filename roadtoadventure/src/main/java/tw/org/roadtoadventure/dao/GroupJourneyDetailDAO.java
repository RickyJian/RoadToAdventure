package tw.org.roadtoadventure.dao;

import java.util.List;

import tw.org.roadtoadventure.bean.GroupBean;
import tw.org.roadtoadventure.vo.GroupJourneyDetail;

public interface GroupJourneyDetailDAO extends BaseDAO<GroupJourneyDetail> {

	public List<GroupJourneyDetail> readByParamter(GroupBean groupBean);
	
}
