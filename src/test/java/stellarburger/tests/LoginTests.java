package stellarburger.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import stellarburger.business.User;
import stellarburger.pom.*;
import stellarburger.tests.utils.UtilMethods;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.hamcrest.CoreMatchers.is;

@Epic("Login functionality tests")
class LoginTests extends BaseTest {
    static User user;

    @BeforeAll
    static void register() {
        user = UtilMethods.getFakeUser();
        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();
        mainPage.register(user);
    }

    @BeforeEach
    void clearLocalStorageBefore() {
        executeJavaScript("localStorage.clear();");
    }

    @Test
    @DisplayName("Tests for login functionality through login button on the main page")
    @Description("Tests that button open login page, then log in")
    void shouldLoginThroughLoginButton() {
        MainPageUnauthorized mainPageUnauthorized = new MainPageUnauthorized().openPage();

        MainPageAuthorized mainPageAuthorized = mainPageUnauthorized.login(user);

        mainPageAuthorized.load();
        MatcherAssert.assertThat(mainPageAuthorized.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Tests for login functionality through account button on the main page")
    @Description("Tests that button open login page, then log in")
    void shouldOpenLoginPageThroughAccountButton() {
        MainPageUnauthorized mainPageUnauthorized = new MainPageUnauthorized().openPage();
        LoginPage loginPage = mainPageUnauthorized.clickAccountButton();

        loginPage.load();
        MatcherAssert.assertThat(loginPage.isLoaded(), is(true));

        MainPageAuthorized mainPageAuthorized = loginPage.login(user);
        mainPageAuthorized.load();
        MatcherAssert.assertThat(mainPageAuthorized.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Login page opens when click on the login button on the register page")
    @Description("Tests that button open login page, then log in")
    void shouldOpenLoginPageFromRegisterPage() {
        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();
        LoginPage loginPage = mainPage.clickLoginButton();

        RegisterPage registerPage = loginPage.clickRegisterButton();
        registerPage.load();
        MatcherAssert.assertThat(registerPage.isLoaded(), is(true));

        registerPage.clickOnLoginButton();
        loginPage.load();

        MainPageAuthorized mainPageAuthorized = loginPage.login(user);
        mainPageAuthorized.load();
        MatcherAssert.assertThat(mainPageAuthorized.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Login page opens when click on the login button on the restore password page")
    @Description("Tests that button open login page, then log in")
    void shouldOpenLoginPageFromRestorePasswordPage() {
        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();

        LoginPage loginPage = mainPage.clickAccountButton();
        RestorePasswordPage restorePasswordPage = loginPage.clickRestorePasswordButton();
        restorePasswordPage.load();

        restorePasswordPage.clickLoginButton();
        loginPage.load();
        MatcherAssert.assertThat(loginPage.isLoaded(), is(true));

        MainPageAuthorized mainPageAuthorized = loginPage.login(user);
        mainPageAuthorized.load();
        MatcherAssert.assertThat(mainPageAuthorized.isLoaded(), is(true));

    }

    @AfterAll
    static void clearLocalStorageAfterAll() {
        UtilMethods.clearLocalStorage();
    }

    @AfterAll
    static void deleteUser() {
        UtilMethods.deleteUser(user);
    }

}
