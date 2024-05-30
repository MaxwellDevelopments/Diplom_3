package stellarburger.tests.utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import stellarburger.business.User;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class UtilMethods {

    private static final String ACCESS_TOKEN = "accessToken";

    private UtilMethods() {

    }

    private static final Faker faker = new Faker();

    private static String getQuestions(int amount) {
        return "?".repeat(Math.max(0, amount));
    }

    public static User getFakeUser(int nameLength, int emailLength, int passwordLength) {
        String email = faker.letterify(getQuestions(emailLength)
                                                + "@test.com"
        );
        String password = faker.bothify(getQuestions(passwordLength));
        String name = faker.letterify(getQuestions(nameLength));

        return User.with()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }

    public static User getFakeUser() {

        String email = faker.letterify("?????@?????.com");

        String password = faker.bothify("??????");
        String name = faker.name().name();

        return User.with()
                .name(name)
                .password(password)
                .email(email)
                .build();
    }

    public static String getAccessTokenFromLocalStorage() {
        return executeJavaScript("return localStorage.getItem('accessToken');");
    }

    public static String getRefreshTokenFromLocalStorage() {
        return executeJavaScript("return localStorage.getItem('refreshToken');");
    }

    public static void setRefreshTokenInLocalStorage(String refreshToken) {
         executeJavaScript(String.format("localStorage.setItem('%s', '%s')", "refreshToken", refreshToken));
    }

    public static void setAccessTokenInLocalStorage(String accessToken) {
        executeJavaScript(String.format("localStorage.setItem('%s', '%s')", "accessToken", accessToken));
    }

    public static void clearLocalStorage() {
        executeJavaScript("localStorage.clear();");
    }

    @Step("Delete user")
    public static Response deleteUser(User user) {
        Response response = loginUserApi(user);
        String accessToken = getDataFromResponse(response);
        return deleteUserApi(accessToken);
    }

    private static Response loginUserApi(User user) {
        return given()
                    .filter(new AllureRestAssured())
                    .header("Content-type", "application/json")
                    .and()
                    .body(user)
                    .when()
                    .post(Constants.LOGIN_USER_ENDPOINT);
    }

    private static Response deleteUserApi(String accessToken) {
        return given()
                    .filter(new AllureRestAssured())
                    .header("Authorization", accessToken)
                    .when()
                    .delete(Constants.DELETE_USER_ENDPOINT);
    }

    private static <T> T getDataFromResponse(Response response) {
        return response.jsonPath().get(ACCESS_TOKEN);
    }

}
