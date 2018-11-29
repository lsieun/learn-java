package lsieun.tutorial;

import lsieun.graphics.Color;
import lsieun.graphics.Ellipse;
import lsieun.graphics.Rectangle;

// Doc: http://horstmann.com/sjsu/graphics/
// File: http://horstmann.com/sjsu/graphics/graphics.zip
public class Demo01 {
    public static void main(String[] args) {
        Rectangle box = new Rectangle(5, 10, 60, 80);
        box.draw();

        Ellipse egg = new Ellipse(100, 100, 40, 60);
        egg.setColor(Color.YELLOW);
        egg.fill();
        //egg.draw();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        box.translate(20, 30); // Move by 20 pixels to the right, 30 pixels down
        egg.grow(5, 5); // Grow by 5 pixels on all four sides
    }
}
