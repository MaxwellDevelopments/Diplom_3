package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import stellarburger.business.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage implements Page {

    @FindBy(how = How.XPATH, using = "//*[text()='Регистрация']")
    private SelenideElement loadElement;
    @FindBy(how = How.XPATH, using = "//*[text()='Имя']/../input")
    private SelenideElement nameInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Email']/../input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/../input")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement loginButton;

    @Override
    @Step("Wait for register page to be loaded")
    public void load() {
        loadElement.shouldBe(visible);
    }
    @Step("Check that register page is loaded")
    @Override
    public boolean isLoaded() {
        return loadElement.isDisplayed();
    }

    @Step("Set email value")
    public void setEmail(String email) {
        this.emailInput.setValue(email);
    }

    @Step("Set name value")
    public void setName(String name) {
        this.nameInput.setValue(name);
    }

    @Step("Set password value")
    public void setPassword(String password) {
        this.passwordInput.setValue(password);
    }

    public SelenideElement getRegisterButton() {
        return registerButton;
    }

    @Step("Register user")
    public LoginPage register(User user) {
        emailInput.setValue(user.getEmail());
        nameInput.setValue(user.getName());
        passwordInput.setValue(user.getPassword());
        registerButton.click();
        return page(LoginPage.class);
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }
}
