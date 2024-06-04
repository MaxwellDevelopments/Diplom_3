package stellarburger.pom;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


public abstract class MainPage extends BasePage implements Page {

    public enum IngredientType {
        BUN("Булки"),
        FILLING("Начинки"),
        SAUCE("Соусы");

        private final String name;

        IngredientType(String value) {
            this.name = value;
        }

        public String toString() {
            return this.name;
        }
    }

    @Override
    public abstract void load();

    @Override
    public abstract boolean isLoaded();

    @Step("Open main page")
    public abstract MainPage openPage();

    @Step("Click on ingredient on constructor panel")
    public void clickOnSpecificIngredientButton(IngredientType ingredientType) {
        String xPath = getIngredientXpath(ingredientType);
        clickOnElement($(byXpath(xPath)));
    }

    @Step("Check that ingredient type selected")
    public boolean isIngredientSelected(IngredientType ingredientType) {
        String xPath = getIngredientXpath(ingredientType);
        SelenideElement selectedIngredientType = $(byXpath(xPath));
        selectedIngredientType.shouldHave(attributeMatching("class", ".*current.*"));
        String cssClass = selectedIngredientType.getAttribute("class");
        if (cssClass == null) {
            return false;
        }
        else {
            return cssClass.contains("current");
        }
    }

    private String getIngredientXpath(IngredientType ingredientType) {
        return String.format("//span[text()='%s']/..", ingredientType);
    }

}
