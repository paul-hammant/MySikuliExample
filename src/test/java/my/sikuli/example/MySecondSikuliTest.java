package my.sikuli.example;

import org.junit.Test;
import org.sikuli.script.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.fail;

public class MySecondSikuliTest extends Utilities {

    @Test
    public void testSomething() throws FindFailed, IOException, InterruptedException {
        App.focus("firefox");
        Screen screen = new Screen();
        goToURL(screen, "https://en.wikipedia.org/wiki/United_States_one_hundred-dollar_bill#/media/File:Obverse_of_the_series_2009_$100_Federal_Reserve_Note.jpg");
        makeImageFrom("L", "G");
        screen.wait("src/images/LG.png", 40);
        Match m = screen.find("src/images/LG.png");
        assertThat("$100-note should have had a serialnumber starting with LG (with match score= 9/10)", m.getScore(), greaterThan(0.9));
        Location target = m.getTarget();
        System.out.println("X,Y for green 'LG' in serial number is " + target.getX() + "," + target.getY());
        makeImageFrom("L", "G", "L", "G", "L");
        try {
            screen.find("src/images/LGLGL.png");
            fail("should have barfed");
        } catch (FindFailed findFailed) {
        }
    }

    private void makeImageFrom(String... strings) throws IOException, InterruptedException {
        String pngs = "";
        String resultPng = "";
        for (String string : strings) {
            pngs = pngs + "src/images/" + string + ".png ";
            resultPng = resultPng + string;
        }
        // convert is from ImageMagick
        Process proc = Runtime.getRuntime().exec("convert " + pngs + " +append src/images/" + resultPng + ".png");
        proc.waitFor(20, TimeUnit.SECONDS);
    }
}
