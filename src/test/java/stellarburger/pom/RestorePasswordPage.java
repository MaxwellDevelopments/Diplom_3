package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RestorePasswordPage implements Page {
    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private SelenideElement loadElement;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginButton;

    @Override
    public void load() {
        loadElement.shouldBe(visible);
    }

    @Override
    public boolean isLoaded() {
        return loadElement.isDisplayed();
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
