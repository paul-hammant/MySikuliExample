package my.sikuli.example;

import org.sikuli.script.*;

public class Utilities {

    protected void goToURL(Screen screen, String url) throws FindFailed {
        Pattern p = new Pattern("src/images/backButton.png");
        Match urlField = screen.wait(p.similar(0.7f), 5);
        urlField.setTargetOffset(550, 0);
        urlField.click();
        screen.type('a', Key.CMD);
        screen.type(Key.DELETE);
        screen.type(url);
        screen.type(Key.ENTER);
    }

}
