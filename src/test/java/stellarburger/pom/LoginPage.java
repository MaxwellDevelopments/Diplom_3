package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import stellarburger.business.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage implements Page {

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement loadElement;
    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailInput;

    @FindBy(how = How.NAME, using= "Пароль")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement restorePasswordButton;

    @Override
    @Step("Wait for login page to be loaded")
    public void load() {
        loadElement.shouldBe(visible);
    }
    @Step("Check that login page is loaded")
    public boolean isLoaded() { return loadElement.isDisplayed(); }

    @Step("Set email")
    public void setEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Set password")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Click on \"Зарегистрироваться\" button")
    public RegisterPage clickRegisterButton() {
        registerButton.click();
        return page(RegisterPage.class);
    }

    @Step("Click on \"Восстановить пароль\" button")
    public RestorePasswordPage clickRestorePasswordButton() {
        restorePasswordButton.click();
        return page(RestorePasswordPage.class);
    }

    @Step("Click on \"Войти\" button")
    public MainPageAuthorized clickLoginButton() {
        loginButton.click();
        return page(MainPageAuthorized.class);
    }

    @Step("Log in account")
    public MainPageAuthorized login(User user) {
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        return clickLoginButton();
    }

}
