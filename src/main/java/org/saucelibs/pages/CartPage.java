package org.saucelibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    private final String xpathToItem = "//div[@class='inventory_item_name' and contains(text(),'%s')]";

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    /**
     * Метод получения заголовка
     *
     * @return WebElement - возвращает объект заголовка
     */
    public WebElement getTitle() {
        return title;
    }

    /**
     * Метод перехода на страницу ввода данных
     *
     * @return CartPage - возвращает CartPage
     */
    public CartPage checkout() {
        waitUntilElementToBeClickable(checkoutButton);
        clickElement(checkoutButton);
        return this;
    }

    /**
     * Метод проверки продукта в Корзине
     *
     * @param itemName - имя продукта
     * @return
     */
    public boolean checkItem(String itemName) {
        return checkIfElementPresent(By.xpath(String.format(xpathToItem, itemName)));
    }
}
