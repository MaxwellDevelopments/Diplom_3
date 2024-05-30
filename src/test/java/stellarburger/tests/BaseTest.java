package stellarburger.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import stellarburger.tests.utils.Constants;

class BaseTest {

    private enum Browser {
        FIREFOX,
        CHROME
    }

    @BeforeAll
    static void setURI() {
        RestAssured.baseURI = Constants.LINK;
    }

    @BeforeAll
    static void initBrowser() {
        Browser browser = Browser.valueOf(System.getProperty("Browser"));
        switch (browser) {
            case FIREFOX:
                initFirefox();
                break;
            case CHROME:
                initChrome();
                break;
        }

    }

    private static void initChrome() {
        WebDriverManager.chromedriver().setup();
        var driver = new ChromeDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    private static void initFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var driver = new FirefoxDriver();
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterAll
    static void closeWindow() {
        Selenide.closeWindow();
    }

}
