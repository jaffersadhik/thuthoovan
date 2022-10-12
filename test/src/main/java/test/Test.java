package test;

public class Test {

	public static void main(String[] args) {
		
		System.out.println("test");
		
		String test="smsc:default:test";
		
		String t[]=test.split(":");
		
		for(int i=0;i<t.length;i++) {
			
			System.out.println(t[i]);

		}

	}

}
