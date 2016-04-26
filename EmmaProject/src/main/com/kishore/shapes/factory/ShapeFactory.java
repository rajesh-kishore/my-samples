/**
 * 
 */
package com.kishore.shapes.factory;

import com.kishore.shapes.Circle;
import com.kishore.shapes.Rectangle;
import com.kishore.shapes.Shape;

/**
 * @author rakishor
 *
 */
public class ShapeFactory {
	
	public static Shape getShape(String code) {
		
		if (code.equals("1")) {
			return new Circle();
		}
		
		if (code.equals("2")) {
			return new Rectangle();
		}
		
		return null;
	}

}
