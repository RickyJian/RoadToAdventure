package tw.org.roadtoadventure.utils;

import java.util.HashSet;
import java.util.Set;

//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.beanutils.BeanUtilsBean;
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.commons.beanutils.converters.DateConverter;
//import org.apache.commons.beanutils.converters.IntegerConverter;
//import org.apache.commons.beanutils.converters.StringConverter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import org.springframework.beans .BeanUtils;

public class BeanUtility {
	//COPY時忽略null值
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	public static void copyProperties(Object orig,Object dest){
		BeanUtils.copyProperties(orig, dest,getNullPropertyNames(orig));

	}
	
}

