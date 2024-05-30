package stellarburger.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import stellarburger.pom.MainPageUnauthorized;

import static com.codeborne.selenide.Condition.attributeMatching;
import static org.hamcrest.CoreMatchers.containsString;


import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

@Epic("Tests transitions on burger constructor panel")
class ConstructorPanelTests {

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
    
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Transitions in the constructor burger panel between ingredient types")
    @Description("Tests that the tabs will change when clicked according to the button")
    void shouldChangeIngredientsInConstructorTest(IngredientType ingredientType) {
        String xPath = String.format("//span[text()='%s']/..", ingredientType);
        MainPageUnauthorized mainPageUnauthorized = new MainPageUnauthorized().openPage();
        mainPageUnauthorized.load();

        switch (ingredientType) {
            // У булочки особый случай. На кнопки нельзя тыкать, если они уже выбраны
            // иначе вылетает исключение.
            // поэтому в начале приходится тыкнуть на какую-то другую кнопку.
            case BUN:
                mainPageUnauthorized.clickSauceButton();
                mainPageUnauthorized.load();
                mainPageUnauthorized.clickBunButton();
                break;
            case FILLING:
                mainPageUnauthorized.clickFillingButton();
                break;
            case SAUCE:
                mainPageUnauthorized.clickSauceButton();
                break;
        }

        $(byXpath(xPath)).shouldHave(attributeMatching("class", ".*current.*"));
        String cssClass = $(byXpath(xPath)).getAttribute("class");
        MatcherAssert.assertThat(cssClass, containsString("current"));

    }


}


