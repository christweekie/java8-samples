/**
 *
 */
package org.lucidant.effectivejava.enums;

import java.util.EnumSet;

import org.junit.jupiter.api.Test;
import org.lucidant.effectivejava.enums.Item36EnumSetText.Style;

/**
 * @author chrisfaulkner
 *
 */
class Item36EnumSetTextTest
{

	@Test
	void test()
	{
		new Item36EnumSetText().applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
	}

}
