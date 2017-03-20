package webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

public class GoogleSearchTest {

    private static final DesiredCapabilities CHROME = DesiredCapabilities.chrome();
    private static final DesiredCapabilities FIREFOX = DesiredCapabilities.firefox();
    private static final String DEFAULT_HOST = "http://localhost:4444/wd/hub";
    private static final String GOOGLE_COM = "http://www.google.com";
    private static final String GOOGLE = "Google";

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
        driver.get(GOOGLE_COM);
        assertEquals(GOOGLE, driver.getTitle());
        driver.quit();
    }

    private WebDriver createDriver(DesiredCapabilities capability) throws MalformedURLException {
        String seleniumHost = System.getProperty("selenium_host",DEFAULT_HOST);
        WebDriver driver = new RemoteWebDriver(new URL(seleniumHost), capability);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }
}
