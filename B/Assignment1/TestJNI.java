public class TestJNI
{
	static
	{
		System.loadLibrary("cal");
	}
	private native int add(int n1,int n2);
	
	public static void main(String args[])
	{
		System.out.println("Addition is "+ new TestJNI().add(10,20));
	}
	
}

