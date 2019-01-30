package sample;

public class Test4 {
	static {
		Test4.a=Test4.a+200;
	}
	static int a;
	public static void main (String[] args)
	{
		System.out.println(a);
		
	}

static 
{
	System.out.println(a);
	a=Test4.a+200;
}
}
