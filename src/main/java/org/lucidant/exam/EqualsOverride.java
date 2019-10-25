package org.lucidant.exam;

public class EqualsOverride
{
	private final int value;
	EqualsOverride(final int newValue) {
		this.value = newValue;
	}

	@Override
	public boolean equals(final Object obj){
		if (obj instanceof EqualsOverride) {
			return this.value%5 == ((EqualsOverride)obj).value%5;
		}
		else {
			return false;
		}
	}
}
