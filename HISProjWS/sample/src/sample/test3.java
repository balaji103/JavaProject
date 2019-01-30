package sample;

public class test3 {
	static int a =2 ;

	public static void main(String[] args) {
		int a =3;
		test3.a=a+test3.a;
		a=test3.a+a;
		System.out.println(test3.a+a);

	}
	static {
		test3.a=a+20;
	}

}
