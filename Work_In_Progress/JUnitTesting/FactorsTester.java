import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class FactorsTester {

	@Test
	void testPerfect1()
	{	
		// TEST 1: should throw the exception because the parameter value is less than 1
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
	}
	
	@Test
	void testPerfect2()
	{	
		// TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
		assertFalse(FactorsUtility.perfect(1));
	}
	
	@Test
	void testPerfect3()
	{	
		// TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
		assertTrue(FactorsUtility.perfect(6));
	}
	
	@Test
	void testPerfect4()
	{	
		// TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
		// I've coded this using assertEquals to show that there's often more than one way to write a test 
		boolean expected = false;
		assertEquals(expected, FactorsUtility.perfect(7));
	}
	
	@Test
	void testGetFactors1() {
		// TEST 1: should succeed because 2 is a valid value, with a factor of 1.
		ArrayList<Integer> factors = new ArrayList<>();
		factors.add(1);
		assertEquals(factors, FactorsUtility.getFactors(2));
	}
	
	@Test
	void testGetFactors2() {
		// TEST 2: Should should succeed because 1 has no factors, therefore should return an empty list.
		ArrayList<Integer> factors = new ArrayList<>();
		assertEquals(factors, FactorsUtility.getFactors(1));
	}
	
	@Test
	void testGetFactors3() {
		// TEST 3: Should succeed because 0 has no factors, therefore should return an empty list.
		ArrayList<Integer> factors = new ArrayList<>();
		assertEquals(factors, FactorsUtility.getFactors(0));
	}
	
	@Test
	void testGetFactors4() {
		// TEST 4: Should throw an IllegalArgumentException because the parameter is less than 1.
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
	}
	
	@Test
	void testGetFactors5() {
		// TEST 5: Should succeed because 12 is a valid parameter, with multiple factors.
		ArrayList<Integer> factors = new ArrayList<>();
		factors.add(1);
		factors.add(2);
		factors.add(3);
		factors.add(4);
		factors.add(6);

		assertEquals(factors, FactorsUtility.getFactors(12));
	}
	
	@Test
	void testFactor1() {
		// TEST 1: should succeed because 1 valid and is a factor of 1.
		assertTrue(FactorsUtility.factor(1, 1));
		
	}
	
	@Test
	void testFactor2() {
		// TEST 2: should throw an IllegalArgumentException exeption because a is less than 0.
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1, 5));
	}
	
	@Test
	void testFactor3() {
		// TEST 3: Should succeed because a and b are valid parameters just not a is not a factor of b.
		boolean expected = false;
		assertEquals(expected, FactorsUtility.factor(2, 3));
	}
	
	@Test
	void testFactor4() {
		// TEST 4: Should succeed for however, b is smaller than a so it's expected to return false.
		boolean expected = false;
		assertEquals(expected, FactorsUtility.factor(5, 2));
	}
	
	@Test
	void testFactor5() {
		// TEST 5: should throw the exception because the parameter values are less than 1.
		assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-5, -1));
	}

}
