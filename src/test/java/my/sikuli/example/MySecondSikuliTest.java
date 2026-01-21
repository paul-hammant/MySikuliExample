package my.sikuli.example;

import org.junit.jupiter.api.Test;
import org.sikuli.script.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MySecondSikuliTest extends Utilities {

    @Test
    public void testSomething() throws FindFailed, IOException, InterruptedException {
        ImagePath.add("src/images");
        String browserName = focusBrowser();
        Screen screen = new Screen();
        goToURL(screen,
                "https://en.wikipedia.org/wiki/United_States_one_hundred-dollar_bill#/media/File:Obverse_of_the_series_2009_$100_Federal_Reserve_Note.jpg",
                browserName);
        Match m = screen.wait("LG.png", 40);
        assertTrue(m.getScore() > 0.9,
                "$100-note should have had a serialnumber starting with LG (with match score= 9/10)");
        Location target = m.getTarget();
        System.out.println("X,Y for green 'LG' in serial number is " + target.getX() + "," + target.getY());
        try {
            screen.find("B.png");
            fail("should have barfed");
        } catch (FindFailed findFailed) {
        }
    }
}
