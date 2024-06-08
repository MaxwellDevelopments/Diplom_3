package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import stellarburger.tests.utils.Constants;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class MainPageAuthorized extends MainPage {

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement makeOderButton;

    @Override
    @Step("Check that main page is loaded when authorized")
    public void load() {
        makeOderButton.shouldBe(visible);
    }

    @Override
    @Step("Wait for main page authorized to be loaded")
    public boolean isLoaded() {
        return makeOderButton.isDisplayed();
    }

    @Override
    public MainPageAuthorized openPage() {
        return open(Constants.LINK, MainPageAuthorized.class);
    }

    @Step("Click on \"Личный кабинет\" button")
    public AccountPage clickAccountButton() {
        accountButton.click();
        return page(AccountPage.class);
    }
}
