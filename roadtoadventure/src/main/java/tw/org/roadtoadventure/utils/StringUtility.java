package tw.org.roadtoadventure.utils;

public class StringUtility {

	public static boolean stringCompare (String str1 , String str2){
		char [] str1Char = str1.toCharArray();
		char [] str2Char = str2.toCharArray();
		int strLength = str1Char.length;
		if(str1Char.length==str2Char.length){
			for(int i = 0 ; i < strLength;i++){
				if(str1Char[i]!=str2Char[i]){
					return false;
				}else{
					if(i==(strLength-1)){
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean stringCompare (String str1Arr[] , String str2Arr[]){
		if(str1Arr.length==str2Arr.length){
			int arrLength = str1Arr.length;
			for(int i = 0 ; i < arrLength ; i++){
				char [] str1Char = str1Arr[i].toCharArray();
				char [] str2Char = str2Arr[i].toCharArray();
				int strLength = str1Char.length;
				if(str1Char.length==str2Char.length){
					for(int j = 0 ; j < strLength;j++){
						if(str1Char[j]!=str2Char[j]){
							return false;
						}
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}
}
