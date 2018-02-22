/**
 *
 */
package org.lucidant.effectivejava.enums;

/**
 * Calls to calculate pay demand the passing of a strategy.
 * The strategy is a {@link PayType} which has its own
 * implementation.
 *
 * @author chrisfaulkner
 *
 */
public enum PayrollDayStrategy
{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,

	SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

	private final PayType payType;

	PayrollDayStrategy(final PayType payType) { this.payType = payType; }

	PayrollDayStrategy() { this(PayType.WEEKDAY); } // Default

	int pay(final int minutesWorked, final int payRate)
	{
		return payType.pay(minutesWorked, payRate);
	}

	// The strategy enum type
	private enum PayType
	{
		WEEKDAY
		{
			@Override
			int overtimePay(final int minsWorked, final int payRate)
			{
				return minsWorked <= MINS_PER_SHIFT ? 0 :
					(minsWorked - MINS_PER_SHIFT) * payRate / 2;
			}
		},

		WEEKEND
		{
			@Override
			int overtimePay(final int minsWorked, final int payRate)
			{
				return minsWorked * payRate / 2;
			}
		};

		abstract int overtimePay(int mins, int payRate);

		private static final int MINS_PER_SHIFT = 8 * 60;

		int pay(final int minsWorked, final int payRate)
		{
			final int basePay = minsWorked * payRate;
			return basePay + overtimePay(minsWorked, payRate);
		}
	}

}
