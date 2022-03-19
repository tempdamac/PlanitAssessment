package utilities;

import java.security.SecureRandom;

public class RandomStringGenerator {

	private static SecureRandom rnd = new SecureRandom();
	private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String CD = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	
	public static String alphanumeric(int length){
		   StringBuilder sb = new StringBuilder(length);
		   for( int i = 0; i < length; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
	}
	
	public static String alphabet(int length){
		   StringBuilder sb = new StringBuilder(length);
		   for( int i = 0; i < length; i++ ) 
		      sb.append( CD.charAt( rnd.nextInt(CD.length()) ) );
		   return sb.toString();
	}
	
	public static String serialNumber="SRL-"+alphanumeric(15);

	
}
