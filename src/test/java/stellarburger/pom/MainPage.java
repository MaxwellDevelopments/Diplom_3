package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public abstract class MainPage extends BasePage implements Page {

    @Override
    public abstract void load();

    @Override
    public abstract boolean isLoaded();

    @Step("Open main page")
    public abstract MainPage openPage();

    @FindBy(how = How.XPATH, using = "//span[text()='Булки']/..")
    public SelenideElement bunButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']/..")
    public SelenideElement sauceButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']/..")
    public SelenideElement fillingButton;

    @Step("Click on bun button on constructor panel")
    public void clickBunButton() {
        bunButton.click();
    }
    @Step("Click on sauce button on constructor panel")
    public void clickSauceButton() {
        sauceButton.click();
    }
    @Step("Click on filling button on constructor panel")
    public void clickFillingButton() {
        fillingButton.click();
    }

}
