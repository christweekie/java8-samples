/**
 *
 */
package org.lucidant.effectivejava.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author chrisfaulkner
 *
 */
class PayrollDayNoStrategyTest
{

	@Test
	void test()
	{
		assertEquals(60, PayrollDayNoStrategy.FRIDAY.pay(6, 10));

		// Time and a half for Saturday
		assertEquals(90, PayrollDayNoStrategy.SATURDAY.pay(6, 10));
	}

}
