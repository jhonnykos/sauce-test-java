package org.saucelibs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.saucelibs.pages.LoginPage;
import org.saucelibs.pages.ProductPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class LoginTests extends BaseTest {

    private final LoginPage loginPage = app.getLoginPage();
    private final ProductPage productPage = app.getProductPage();

    private static Stream<Arguments> incorrectUserOrPasswordData() {
        return Stream.of(
                Arguments.of("standard_use", "secret_sauce"),
                Arguments.of("standard_user", "secret"),
                Arguments.of("standard_user", "secret_sauce "),
                Arguments.of("standard_user", " secret_sauce"),
                Arguments.of("user", "password")
        );
    }

    @BeforeEach
    public void setup(){
        openPage(loginUrl);
    }

    @Test
    public void loginAccepted() {
        login(loginPage,"standard_user", "secret_sauce");
        productPage.waitUntilElementToBeVisible(productPage.getTitle());
        log.info("Текущая страница Products");
    }

    @Test
    public void loginLockedUserFail() {
        login(loginPage,"locked_out_user", "secret_sauce");
        loginPage.waitUntilElementToBeVisible(loginPage.getError());
        boolean actual = loginPage.isLockedUserError();
        assertTrue(actual, "При вводе логина заблокированного пользователя должна появляться ошибка " +
                "о заблокированном пользователе");
    }

    @Test
    public void emptyUserNameFail(){
        login(loginPage,"", "secret_sauce");
        loginPage.waitUntilElementToBeVisible(loginPage.getError());
        boolean actual = loginPage.isRequiredUserError();
        assertTrue(actual, "При пустом логине должна появляться ошибка " +
                "об обязательном поле UserName");
    }

    @Test
    public void emptyPasswordFail(){
        login(loginPage,"standard_user", "");
        loginPage.waitUntilElementToBeVisible(loginPage.getError());
        boolean actual = loginPage.isRequiredPasswordError();
        assertTrue(actual, "При пустом пароле должна появляться ошибка " +
                "об обязательном поле Password");
    }

    @ParameterizedTest
    @MethodSource("incorrectUserOrPasswordData")
    public void incorrectUserNameOrPasswordFail(String userName, String password){
        login(loginPage, userName, password);
        loginPage.waitUntilElementToBeVisible(loginPage.getError());
        boolean actual = loginPage.isWrongUserOrPasswordError();
        assertTrue(actual, "При неправильно логине или пароле должна появляться ошибка " +
                "о неправильном логине или пароле");
    }
}
