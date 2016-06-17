package library;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@SuppressWarnings("all")
public class LibraryString {
	public String md5(String str){
		String result = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			result = new BigInteger(1,md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Error "+e.getMessage());
		}
		return result;
	}
//	public static void main(String[]args){
//		System.out.print("Nhập chuỗi mã hóa: ");
//		String str1 = new Scanner(System.in).nextLine();
//		System.out.print("Chuỗi được mã hóa: "+new LibraryString().md5(str1));
//	}
}
