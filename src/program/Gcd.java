package program;

import org.apache.commons.math3.util.ArithmeticUtils;

public class Gcd {
	
	// perform GCD testing for inter-dependency
	// meaning of mult offset are explained in Variable class
	public boolean gcdTest(int multW, int offSetW, int multR, int offSetR)
	{
		int gcd = 0;
		if(offSetR - offSetW != 0) // if the difference between offset of read and write is 0 (both are equal
		{
			gcd = ArithmeticUtils.gcd(multW, multR); // perform Greatest common divisor on mult of read and mult of write 
			if(gcd % (offSetR - offSetW) == 0) // if GCD divides difference between offset of read and write then dependencies exists
			{
				return true;
			}
		}
		return false;
	}
}
