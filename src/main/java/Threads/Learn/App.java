package Threads.Learn;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String xString ="";
        xString ="nono";
        System.out.println(xString);
        if("no".equals(xString)) {
        	System.out.println("++=");
        }
        
        
        Test01 test01 = new Test01();
        double M =15;
        System.out.println(M*100);
        Map<Integer, String> Map1 = new HashMap<Integer, String>();
        Map1 =name(Map1);
        if(Map1 ==null) {
        	System.out.println("--------");
        }
        String sd="包时100分钟";
        int x =sd.indexOf("时");
        int y =sd.indexOf("分");
        System.out.println(sd.substring(x+1, y));
        
    }
    
    private static Map<Integer, String> name(Map<Integer, String> map) {
    	return null;
		
	}
    
}
