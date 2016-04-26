/**
 * 
 */
package com.kishore.shapes;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kishore.shapes.factory.ShapeFactory;

/**
 * @author rakishor
 *
 */
public class ShapeClientTest {

	@Test
	public void shapeFactory() {
		Shape circle = ShapeFactory.getShape("2");
		assertEquals("The shape expected is circle which is not correct , its instance of "+circle , true, circle instanceof Circle);
	}
	
	@Test
	public void shapeFactoryForCircle() {
		Shape circle = ShapeFactory.getShape("1");
		assertEquals("The shape expected is circle which is not correct , its instance of "+circle , true, circle instanceof Circle);
	}
	
	
	
	@Test
	public void shapeFactoryForCircleDraw() {
		Shape circle = ShapeFactory.getShape("1");
		assertEquals("The shape expected is circle which is not correct , its instance of "+circle , true, circle instanceof Circle);
		circle.draw();
		assertEquals("The shape Circle drawn successuflly "+circle , true, true);
	}
	
	@Test
	public void shapeFactoryForRectangleDraw() {
		Shape rectangle = ShapeFactory.getShape("2");
		assertEquals("The shape expected is rectangle which is not correct , its instance of "+rectangle , true, rectangle instanceof Rectangle);
		rectangle.draw();
		assertEquals("The shape rectangle drawn successuflly "+rectangle , true, true);
	}
	

}
