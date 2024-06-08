package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import stellarburger.business.User;
import stellarburger.tests.utils.Constants;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class MainPageUnauthorized extends MainPage {

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @Step("Click on \"Войти в аккаунт\" button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Override
    @Step("Wait for main page to be loaded")
    public void load() {
        loginButton.shouldBe(visible);
    }

    @Override
    @Step("Check that main page is loaded")
    public boolean isLoaded() {
        return loginButton.isDisplayed();
    }

    public MainPageAuthorized login(User user) {
        LoginPage loginPage = clickLoginButton();
        return loginPage.login(user);
    }

    public LoginPage register(User user) {
        LoginPage loginPage = clickLoginButton();
        return loginPage.clickRegisterButton().register(user);
    }

    @Override
    public MainPageUnauthorized openPage() {
        return open(Constants.LINK, MainPageUnauthorized.class);
    }

    @Step("Click on account button")
    public LoginPage clickAccountButton() {
        accountButton.click();
        return page(LoginPage.class);
    }
}
