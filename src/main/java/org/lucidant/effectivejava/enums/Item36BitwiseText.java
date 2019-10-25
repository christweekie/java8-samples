/**
 *
 */
package org.lucidant.effectivejava.enums;

/**
 * Where Joshua explains that this old complicated Bitwise operations are defunct.
 * <p>
 * This was efficient but you had to know the ultimate potential size of your list of values.
 * The alternative is to use EnumSet which he says are backed by bit vectors.
 *
 * @author chrisfaulkner
 *
 */
public class Item36BitwiseText
{
	public static final int STYLE_BOLD          = 1 << 0;  // 1

	public static final int STYLE_ITALIC        = 1 << 1;  // 2

	public static final int STYLE_UNDERLINE     = 1 << 2;  // 4

	public static final int STYLE_STRIKETHROUGH = 1 << 3;  // 8

	// Parameter is bitwise OR of zero or more STYLE_ constants

//	public int applyStyles(final int styles)
//	{
//		System.out.println("Value is " + styles);
//		int x = 5;
//		x = x >>1;
//	x.value();
//	x = !x;
//	x = ~x;
//	return styles;
//	}
}
