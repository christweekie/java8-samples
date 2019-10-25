/**
 *
 */
package org.lucidant.effectivejava.enums;

import org.junit.jupiter.api.Test;

/**
 * @author chrisfaulkner
 *
 */
public class TestForExam
{

	@Test
	public void test()
	{
		final int [][] array2D = {{ 0,1,2}, {3,4,5,6}};
		System.out.println(array2D[0].length);
		System.out.println(array2D[1].getClass().isArray());
		System.out.println(array2D[0][1]);
	}

}
