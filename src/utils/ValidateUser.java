package utils;

@SuppressWarnings("all")
public class ValidateUser {

	public boolean isFullName(String fullname) {
		boolean result = false;
		if(fullname.matches("(.*)[!,@,#,$,%,^,&,*,(,),-,_,+,=,?,:,;,',\"](.*)")){
			result = true;
		}
		return result;
	}
}
