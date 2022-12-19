package org.saucelibs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.saucelibs.pages.CartPage;
import org.saucelibs.pages.LoginPage;
import org.saucelibs.pages.ProductPage;
import org.saucelibs.pages.checkout.CheckoutCompletePage;
import org.saucelibs.pages.checkout.CheckoutStepOnePage;
import org.saucelibs.pages.checkout.CheckoutStepTwoPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ProductPurchase extends BaseTest{

    private final ProductPage productPage = app.getProductPage();
    private final LoginPage loginPage = app.getLoginPage();
    private final CartPage cartPage = app.getCartPage();
    private final CheckoutStepOnePage checkoutStepOnePage = app.getCheckoutStepOnePage();
    private final CheckoutStepTwoPage checkoutStepTwoPage = app.getCheckoutStepTwoPage();
    private final CheckoutCompletePage checkoutCompletePage = app.getCheckoutCompletePage();

    @Test
    public void productPurchase(){
        openPage(loginUrl);
        loginPage.waitUntilElementToBeVisible(loginPage.getLoginButton());
        log.info("Текущая страница Login");

        login(loginPage,"standard_user", "secret_sauce");
        productPage.waitUntilElementToBeVisible(productPage.getTitle());
        log.info("Текущая страница Products");

        int itemIndex1 = 2;
        String itemName1 = productPage.getItemNameByIndex(itemIndex1);

        productPage.addToCartByIndex(itemIndex1)
                .goToCart();
        cartPage.waitUntilElementToBeVisible(cartPage.getTitle());
        log.info("Текущая страница Cart");
        assertTrue(cartPage.checkItem(itemName1), "Добавленные продукты есть в корзине");

        cartPage.checkout();
        checkoutStepOnePage.waitUntilElementToBeVisible(checkoutStepOnePage.getTitle());
        log.info("Текущая страница CheckOutStepOne");

        checkoutAndContinue("Ola", "Profitrola", "345177");
        checkoutStepTwoPage.waitUntilElementToBeVisible(checkoutStepTwoPage.getTitle());
        log.info("Текущая страница CheckOutStepTwo");
        assertTrue(checkoutStepTwoPage.checkItem(itemName1), "Добавленные продукты есть в заказе");

        checkoutStepTwoPage.finish();
        checkoutCompletePage.waitUntilElementToBeVisible(checkoutCompletePage.getTitle());
        log.info("Текущая страница CheckOutComplete");
    }

    private CheckoutStepOnePage checkoutAndContinue(String firstName, String lastName, String postalCode){
        return checkoutStepOnePage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillPostalCode(postalCode)
                .clickContinueButton();
    }
}
