package stellarburger.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import stellarburger.business.User;
import stellarburger.pom.LoginPage;
import stellarburger.pom.MainPageUnauthorized;
import stellarburger.pom.RegisterPage;
import stellarburger.tests.utils.UtilMethods;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.is;

@Epic("Tests for register functionality")
class RegistrationTests  {

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 10, 15})
    @DisplayName("Positive tests for user register")
    @Description("Tests that can register with the correct password length (greater than 5)")
    void shouldRegisterPasswordLengthGreaterThanFive(int passwordLength) {
        int emailLength = new Random().nextInt(6) + 5;
        int nameLength = new Random().nextInt(6) + 5;

        User user = UtilMethods.getFakeUser(nameLength, emailLength, passwordLength);

        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();
        mainPage.load();

        LoginPage loginPage = mainPage.register(user);
        loginPage.load();
        MatcherAssert.assertThat(loginPage.isLoaded(), is(true));
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 4, 1, 2})
    @DisplayName("Negative tests for user register")
    @Description("Tests that can't register with the bad password length (less than 6)," +
            "Also check error message")
    void shouldNotRegister(int passwordLength) {
        int emailLength = new Random().nextInt(6) + 5;
        int nameLength = new Random().nextInt(6) + 5;

        User user = UtilMethods.getFakeUser(nameLength, emailLength, passwordLength);

        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();
        mainPage.load();

        LoginPage loginPage = mainPage.clickAccountButton();
        loginPage.load();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.load();

        registerPage.setPassword(user.getPassword());
        registerPage.setName(user.getName());
        MatcherAssert.assertThat(
                $(new By.ByXPath("//*[text()='Некорректный пароль']")).isDisplayed(),
                is(true)
        );

        registerPage.setEmail(user.getEmail());
        registerPage.getRegisterButton().click();
        MatcherAssert.assertThat(page(LoginPage.class).isLoaded(), is(false));
    }

    @AfterAll
    static void clearLocalStorageAfterAll() {
        executeJavaScript("localStorage.clear();");
    }


}
