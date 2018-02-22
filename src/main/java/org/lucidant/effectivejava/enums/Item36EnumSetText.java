/**
 *
 */
package org.lucidant.effectivejava.enums;

import java.util.Set;

/**
 * Where Joshua explains that this old complicated Bitwise operations are defunct.
 * <p>
 * This was efficient but you had to know the ultimate potential size of your list of values.
 * The alternative is to use EnumSet which he says are backed by bit vectors.
 *
 * @author chrisfaulkner
 *
 */
public class Item36EnumSetText
{
	public enum Style { BOLD, ITALIC, UNDERLINE };

	/**
	 * Still pass the interface although most clients will likely pass EnumSet.
	 * See test case.
	 * @param styles
	 */
	public void applyStyles(final Set<Style> styles)
	{
		System.out.println("Value is " + styles);
	}

}
