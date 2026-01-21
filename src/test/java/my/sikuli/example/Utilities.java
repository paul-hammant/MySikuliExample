package my.sikuli.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.sikuli.script.*;

public class Utilities {

    protected String focusBrowser() {
        List<String> browsers = List.of("firefox", "chrome", "msedge", "safari");
        App browserApp = null;
        String browserName = null;
        for (App app : App.getApps()) {
            String appName = app.getName().toLowerCase();
            browserName = browsers.stream().filter(browser -> appName.contains(browser)).findFirst().orElse(null);
            if (browserName != null) {
                browserApp = app;
                break;
            }
        }
        assertTrue(browserApp != null, "A supported browser should be running");
        browserApp.focus();
        return browserName;
    }

    protected void goToURL(Screen screen, String url, String browser) throws FindFailed {
        String os = System.getProperty("os.name").toLowerCase().contains("mac") ? "mac" : "windows";
        String imagePath = "backButton/" + os + "/" + browser + ".png";

        Pattern p = new Pattern(imagePath);
        Match urlField = screen.wait(p.similar(0.7f), 5);
        urlField.setTargetOffset(300, 0);
        urlField.click();
        selectAll(screen);
        screen.type(Key.DELETE);
        screen.type(url);
        screen.type(Key.ENTER);
    }

    protected void selectAll(Screen screen) {
        String os = System.getProperty("os.name").toLowerCase().contains("mac") ? "mac" : "windows";
        if (os.equals("mac")) {
            screen.type("a", KeyModifier.CMD);
        } else {
            screen.type("a", KeyModifier.CTRL);
        }
    }
}
