package webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class SeleniumHubTest {

    private static final DesiredCapabilities CHROME = DesiredCapabilities.chrome();
    private static final DesiredCapabilities FIREFOX = DesiredCapabilities.firefox();
    private static final String SELENIUM_TITLE = "Selenium Grid Hub v.3.1.0";

    private String seleniumSite = "http://localhost:4444";

    @Test
    public void titleExistsChrome() throws Exception {
        titleExists(CHROME);
    }

    @Test
    public void titleExistsFirefox() throws Exception {
        titleExists(FIREFOX);
    }

    private void titleExists(DesiredCapabilities capabilities) throws MalformedURLException {
        WebDriver driver = createDriver(capabilities);
        driver.get(seleniumSite);
        assertEquals(SELENIUM_TITLE, driver.getTitle());
        driver.quit();
    }

    private WebDriver createDriver(DesiredCapabilities capability) throws MalformedURLException {
        seleniumSite = System.getProperty("selenium_host", seleniumSite);
        WebDriver driver = new RemoteWebDriver(new URL(seleniumSite + "/wd/hub"), capability);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}
