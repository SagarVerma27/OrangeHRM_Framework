package genericutilities;

public class JavaUtilities 
{
	
	public int randomNumber()
	{
		double randomNumber = Math.random()*1000;
		int random= (int)randomNumber;
		
		return random;
	}

}
