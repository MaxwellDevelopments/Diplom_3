package stellarburger.tests.utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import stellarburger.business.User;
import stellarburger.pom.MainPage;
import stellarburger.pom.Page;

import static com.codeborne.selenide.Selenide.open;

public class UtilMethods {

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

}
