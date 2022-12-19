package org.saucelibs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.saucelibs.pages.CartPage;
import org.saucelibs.pages.LoginPage;
import org.saucelibs.pages.ProductPage;
import org.saucelibs.pages.checkout.CheckoutStepOnePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutStepOneTests extends BaseTest{

    private final ProductPage productPage = app.getProductPage();
    private final LoginPage loginPage = app.getLoginPage();
    private final CartPage cartPage = app.getCartPage();
    private final CheckoutStepOnePage checkoutStepOnePage = app.getCheckoutStepOnePage();

    @BeforeEach
    public void setup(){
        openPage(loginUrl);
        loginPage.waitUntilElementToBeVisible(loginPage.getLoginButton());

        login(loginPage, "standard_user", "secret_sauce");
        productPage.waitUntilElementToBeVisible(productPage.getTitle());

        int itemIndex1 = 5;

        productPage.addToCartByIndex(itemIndex1)
                .goToCart();
        cartPage.waitUntilElementToBeVisible(cartPage.getTitle());

        cartPage.checkout();
        checkoutStepOnePage.waitUntilElementToBeVisible(checkoutStepOnePage.getTitle());
    }

    @Test
    public void emptyFirstNameFail(){
        fillFieldsAndClick("", "Ivanov", "111111");
        checkoutStepOnePage.waitUntilElementToBeVisible(checkoutStepOnePage.getError());
        boolean actual = checkoutStepOnePage.isRequiredFirstNameError();
        assertTrue(actual, "При пустом имени должна появляться ошибка " +
                "об обязательном поле FirstName");
    }

    @Test
    public void emptyLastNameFail(){
        fillFieldsAndClick("Ivan", "", "123456");
        checkoutStepOnePage.waitUntilElementToBeVisible(checkoutStepOnePage.getError());
        boolean actual = checkoutStepOnePage.isRequiredLastNameError();
        assertTrue(actual, "При пустой фамилии должна появляться ошибка " +
                "об обязательном поле LastName");
    }

    @Test
    public void emptyPostalCodeFail(){
        fillFieldsAndClick("Ivan", "Ivanov", "");
        checkoutStepOnePage.waitUntilElementToBeVisible(checkoutStepOnePage.getError());
        boolean actual = checkoutStepOnePage.isRequiredPostalCodeError();
        assertTrue(actual, "При пустом индексе должна появляться ошибка " +
                "об обязательном поле PostalCode");
    }

    @AfterEach
    public void tearDown(){
        checkoutStepOnePage.reset();
    }

    public void fillFieldsAndClick(String firstName, String lastName, String postalCode){
        checkoutStepOnePage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillPostalCode(postalCode)
                .clickContinueButton();
    }
}
