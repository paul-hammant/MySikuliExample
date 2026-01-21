package my.sikuli.example;

import org.junit.jupiter.api.Test;
import org.sikuli.script.Button;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MySikuliTest extends Utilities {

    @Test
    public void testSomething() throws FindFailed {
        ImagePath.add("src/images");
        String browserName = focusBrowser();
        Screen screen = new Screen();
        goToURL(screen, "https://example.com", browserName);

        Match exampleWord = screen.wait("Exa.png", 5);
        exampleWord.doubleClick();
        assertNotNull(screen.wait("selectedExample.png", 5),
                "Example word should be selected");

        exampleWord.setTargetOffset(-70, 0);
        exampleWord.click();
        exampleWord.mouseDown(Button.LEFT);
        exampleWord.mouseMove(300, 0);
        exampleWord.mouseUp(Button.LEFT);
        assertNotNull(screen.wait("selectedExampleDomain.png", 5),
                "Example Domain words should be selected");

        selectAll(screen);
        assertNotNull(screen.wait("selectedAll.png", 5),
                "Pompei row should be zeroed out");
    }
}
