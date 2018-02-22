/**
 *
 */
package org.lucidant.effectivejava.enums;

/**
 * @author chrisfaulkner
 *
 */
public enum PayrollDayNoStrategy
{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,

	SATURDAY, SUNDAY;

	private static final int MINS_PER_SHIFT = 8 * 60;

	/**
	 * Implementation is OK but won't work  if new enum entries are added.
	 * See the version which forces the choice of strategy.
	 *
	 * @param minutesWorked
	 * @param payRate
	 * @return
	 */
	int pay(final int minutesWorked, final int payRate)
	{
		final int basePay = minutesWorked * payRate;
		int overtimePay;

		switch (this)
		{
			case SATURDAY:
			case SUNDAY: // Weekend
				overtimePay = basePay / 2;
				break;

			default: // Weekday
				overtimePay = minutesWorked <= MINS_PER_SHIFT ?
						0 : (minutesWorked - MINS_PER_SHIFT) * payRate / 2;
		}

		return basePay + overtimePay;

	}
}
