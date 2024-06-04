package stellarburger.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import stellarburger.pom.MainPage.IngredientType;
import stellarburger.pom.MainPageUnauthorized;

import static org.hamcrest.CoreMatchers.is;

@Epic("Tests transitions on burger constructor panel")
class ConstructorPanelTests extends BaseTest {
    
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Transitions in the constructor burger panel between ingredient types")
    @Description("Tests that the tabs will change when clicked according to the button")
    void shouldChangeIngredientsInConstructorTest(IngredientType ingredientType) {
        MainPageUnauthorized mainPageUnauthorized = new MainPageUnauthorized().openPage();
        mainPageUnauthorized.load();

        mainPageUnauthorized.clickOnSpecificIngredientButton(ingredientType);
        boolean actual = mainPageUnauthorized.isIngredientSelected(ingredientType);

        MatcherAssert.assertThat(actual, is(true));
    }

}


