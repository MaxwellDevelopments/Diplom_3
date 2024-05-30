package stellarburger.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import stellarburger.business.User;
import stellarburger.pom.AccountPage;
import stellarburger.pom.LoginPage;
import stellarburger.pom.MainPageAuthorized;
import stellarburger.pom.MainPageUnauthorized;
import stellarburger.tests.utils.UtilMethods;

import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import static org.hamcrest.CoreMatchers.is;

@Epic("Tests transitions between pages using buttons")
class TransitionsTests {

    private static String refreshToken;
    private static String accessToken;

    @BeforeAll
    static void register() {
        User user = UtilMethods.getFakeUser();
        MainPageUnauthorized mainPage = new MainPageUnauthorized().openPage();
        LoginPage loginPage = mainPage.register(user);
        loginPage.load();
        MainPageAuthorized mainPageAuthorized = loginPage.login(user);
        mainPageAuthorized.load();

        refreshToken = executeJavaScript("return localStorage.getItem('refreshToken');");
        accessToken = executeJavaScript("return localStorage.getItem('accessToken');");
    }

    @BeforeEach
    void setTokens() {
        executeJavaScript("localStorage.clear();");
        executeJavaScript(String.format("localStorage.setItem('%s', '%s')", "refreshToken", refreshToken));
        executeJavaScript(String.format("localStorage.setItem('%s', '%s')", "accessToken", accessToken));
    }

    @Test
    @DisplayName("Account button on the main page when authorized leads to account page")
    @Description("Open main page and click on account button")
    void shouldGoToAccount() {
        MainPageAuthorized mainPage = new MainPageAuthorized().openPage();
        AccountPage accountPage = mainPage.clickAccountButton();

        accountPage.load();
        MatcherAssert.assertThat(accountPage.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Constructor button on the account page leads to main page")
    @Description("Open main page, go to account then click on constructor button")
    void shouldGoToConstructorFromAccountThroughConstructorButton() {
        MainPageAuthorized mainPage = new MainPageAuthorized().openPage();
        AccountPage accountPage = mainPage.clickAccountButton();

        accountPage.load();
        accountPage.clickConstructorButton();

        mainPage.load();
        MatcherAssert.assertThat(mainPage.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Logo leads to the main page")
    @Description("Check that logo leads to the main page")
    void shouldGoToConstructorFromAccountThroughLogo() {
        MainPageAuthorized mainPage = new MainPageAuthorized().openPage();
        AccountPage accountPage = mainPage.clickAccountButton();

        accountPage.load();
        accountPage.clickOnLogo();

        mainPage.load();
        MatcherAssert.assertThat(mainPage.isLoaded(), is(true));
    }

    @Test
    @DisplayName("Log out from account when authorized")
    @Description("Open main page, go to the account and click log out")
    void shouldLogout() {
        MainPageAuthorized mainPage = new MainPageAuthorized().openPage();
        mainPage.load();
        AccountPage accountPage = mainPage.clickAccountButton();

        accountPage.load();
        LoginPage loginPage = accountPage.clickQuitButton();

        loginPage.load();
        MatcherAssert.assertThat(loginPage.isLoaded(), is(true));
    }

    @AfterAll
    static void clearLocalStorageAfterAll() {
        executeJavaScript("localStorage.clear();");
    }

}
