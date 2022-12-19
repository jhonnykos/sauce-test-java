package org.saucelibs.managers;

import org.saucelibs.pages.CartPage;
import org.saucelibs.pages.LoginPage;
import org.saucelibs.pages.ProductPage;
import org.saucelibs.pages.checkout.CheckoutCompletePage;
import org.saucelibs.pages.checkout.CheckoutStepOnePage;
import org.saucelibs.pages.checkout.CheckoutStepTwoPage;

public class PageManager {
    private static PageManager pageManager;

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutStepOnePage checkoutStepOnePage;
    private CheckoutStepTwoPage checkoutStepTwoPage;
    private CheckoutCompletePage checkoutCompletePage;

    private PageManager() {
    }

    /**
     * Метод ленивой инициализации PageManager
     *
     * @return PageManager - возвращает PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Метод получение страницы Логина
     *
     * @return LoginPage - возвращает LoginPage
     */
    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    /**
     * Метод получение страницы Продуктов
     *
     * @return ProductPage - возвращает ProductPage
     */
    public ProductPage getProductPage() {
        if (productPage == null) {
            productPage = new ProductPage();
        }
        return productPage;
    }

    /**
     * Метод получение страницы Корзины
     *
     * @return CartPage - возвращает CartPage
     */
    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;
    }

    /**
     * Метод получение страницы Ввода данных
     *
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage getCheckoutStepOnePage() {
        if (checkoutStepOnePage == null) {
            checkoutStepOnePage = new CheckoutStepOnePage();
        }
        return checkoutStepOnePage;
    }

    /**
     * Метод получение страницы Подтверждения
     *
     * @return CheckoutStepTwoPage - возвращает CheckoutStepTwoPage
     */
    public CheckoutStepTwoPage getCheckoutStepTwoPage() {
        if (checkoutStepTwoPage == null) {
            checkoutStepTwoPage = new CheckoutStepTwoPage();
        }
        return checkoutStepTwoPage;
    }

    /**
     * Метод получение Подтвержденного заказа
     *
     * @return CheckoutCompletePage - возвращает CheckoutCompletePage
     */
    public CheckoutCompletePage getCheckoutCompletePage() {
        if (checkoutCompletePage == null) {
            checkoutCompletePage = new CheckoutCompletePage();
        }
        return checkoutCompletePage;
    }
}
