package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AccountPage extends BasePage implements Page {

    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement loadElement;

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement quitButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private SelenideElement constructorButton;


    @Override
    public void load() {
        loadElement.shouldBe(visible);
    }

    @Override
    public boolean isLoaded() {
        return loadElement.isDisplayed();
    }

    public MainPageAuthorized clickConstructorButton() {
        constructorButton.click();
        return page(MainPageAuthorized.class);
    }

    public MainPageAuthorized clickOnLogo() {
        logo.click();
        return page(MainPageAuthorized.class);
    }

    public LoginPage clickQuitButton() {
        quitButton.click();
        return page(LoginPage.class);
    }
}
