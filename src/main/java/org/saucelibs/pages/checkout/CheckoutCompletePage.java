package org.saucelibs.pages.checkout;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saucelibs.pages.BasePage;

public class CheckoutCompletePage extends BasePage {

    @FindBy(xpath = "//span[@class='title' and contains(text(),'Checkout: Complete!')]")
    private WebElement title;

    /**
     * Метод получения заголовка
     *
     * @return WebElement - возвращает объект заголовка
     */
    public WebElement getTitle() {
        return title;
    }
}
