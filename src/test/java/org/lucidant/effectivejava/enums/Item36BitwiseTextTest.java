/**
 *
 */
package org.lucidant.effectivejava.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.lucidant.effectivejava.enums.Item36BitwiseText.STYLE_BOLD;
import static org.lucidant.effectivejava.enums.Item36BitwiseText.STYLE_ITALIC;

import org.junit.jupiter.api.Test;

/**
 * @author chrisfaulkner
 *
 */
class Item36BitwiseTextTest
{

	@Test
	void test()
	{
		final Item36BitwiseText text = new Item36BitwiseText();
		// Bold (1) OR italic (2) makes 3
		assertEquals(3, text.applyStyles(STYLE_BOLD | STYLE_ITALIC));
	}

}
