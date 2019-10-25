package org.lucidant.effectivejava.enums;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lucidant.exam.PositionManager;

class Test1ForExam
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void test()
	{
		final int [][] array2D = {{ 0,1,2}, {3,4,5,6}};

		final PositionManager positionManager = new PositionManager()
											.initialize()
											.andMoveRight(-3)
											.andMoveUp(9)
											.andPrintPosition();
	}

}
