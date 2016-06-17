package utils;

@SuppressWarnings("all")
public class ValidateCat {

	public boolean isName(String name) {
		boolean result = false;
		if(name.matches("(.*)[!,@,#,$,%,^,&,*,(,),0-9](.*)")){
			result = true;
		}
		return result;
	}
}
