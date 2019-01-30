package sample;

public class test {
	
	static int a=90 ;
	static {
		int a = 20;
		test.a=test.a+a;
		
	}
        

	public static void main(String[] args) {
		// TODO Auto-generated method stub
         System.out.println(a);
         test.a = a+ test.a;
         System.out.println(test.a+a);
         
	}
 static {
	 int a = 50 ;
	 test.a = a+test.a;
	
 }

}
