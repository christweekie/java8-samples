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
class PayrollDayStrategyTest
{

	@Test
	void test()
	{
		// To the client it's the same but the enum internally needs to choose a pay calculation strategy
		// instead of
		assertEquals(60, PayrollDayStrategy.FRIDAY.pay(6, 10));

		// Time and a half for Saturday
		assertEquals(90, PayrollDayStrategy.SATURDAY.pay(6, 10));
	}

}
