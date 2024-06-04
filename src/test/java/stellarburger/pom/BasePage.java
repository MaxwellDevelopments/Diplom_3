package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    protected SelenideElement accountButton;
    @FindBy(css = "[class*='AppHeader_header__logo']")
    protected SelenideElement logo;

    protected void clickOnElement(SelenideElement selenideElement) {
        executeJavaScript("arguments[0].click()", selenideElement);
    }
}
