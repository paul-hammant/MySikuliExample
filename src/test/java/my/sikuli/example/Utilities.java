package my.sikuli.example;

import org.sikuli.script.*;

public class Utilities {

    protected void goToURL(Screen screen, String url) throws FindFailed {
        Pattern p = new Pattern("src/images/backButton.png");
        Match urlField = screen.wait(p.similar(0.7f), 3);
        urlField.setTargetOffset(150, 0);
        urlField.click();
        screen.type(url);
        screen.type(Key.ENTER);
    }

}
