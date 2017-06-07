package program;

import org.apache.commons.math3.util.ArithmeticUtils;

public class Gcd {
	
	public boolean gcdTest(int multW, int offSetW, int multR, int offSetR)
	{
		int gcd = 0;
		if(offSetR - offSetW != 0)
		{
			gcd = ArithmeticUtils.gcd(multW, multR);
			if(gcd % (offSetR - offSetW) == 0)
			{
				return true;
			}
		}
		return false;
	}
}
