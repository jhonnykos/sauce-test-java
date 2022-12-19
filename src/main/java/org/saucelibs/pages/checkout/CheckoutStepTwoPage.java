package org.saucelibs.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saucelibs.pages.BasePage;

public class CheckoutStepTwoPage extends BasePage {

    private final String xpathToItem = "//div[@class='inventory_item_name' and contains(text(),'%s')]";

    @FindBy(xpath = "//span[@class='title' and contains(text(),'Checkout: Overview')]")
    private WebElement title;

    @FindBy(id = "finish")
    private WebElement finishButton;

    /**
     * Метод получения заголовка
     *
     * @return WebElement - возвращает объект заголовка
     */
    public WebElement getTitle() {
        return title;
    }

    /**
     * Метод подтверждения заказа
     *
     * @return CheckoutStepTwoPage - возвращает CheckoutStepTwoPage
     */
    public CheckoutStepTwoPage finish() {
        waitUntilElementToBeClickable(finishButton);
        clickElement(finishButton);
        return this;
    }

    /**
     * Метод проверки продукта в заказе
     *
     * @param itemName - имя продукта
     * @return
     */
    public boolean checkItem(String itemName) {
        return checkIfElementPresent(By.xpath(String.format(xpathToItem, itemName)));
    }
}
