package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasePage {
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    protected SelenideElement accountButton;
    @FindBy(css = "[class*='AppHeader_header__logo']")
    protected SelenideElement logo;
}
