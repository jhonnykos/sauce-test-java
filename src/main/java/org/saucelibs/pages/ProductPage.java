package org.saucelibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    private final String addToCartButtonPrefix = "add-to-cart";
    private final String removeButtonPrefix = "remove";

    @FindBy(className = "title")
    private WebElement title;

    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "bm-burger-button")
    private WebElement menuButton;

    @FindBy(xpath = "//select[@data-test='product_sort_container']")
    private WebElement sortDropDown;

    @FindBy(xpath = "//button[@text()='Remove']")
    private List<WebElement> removeFromCartButtons;

    /**
     * Метод получения заголовка
     *
     * @return WebElement - возвращает title
     */
    public WebElement getTitle() {
        return title;
    }

    /**
     * Метод получния имени продукта по индексу
     *
     * @param index - индекс продукта в списке продуктов
     * @return String - возвращает имя продукта типа String
     */
    public String getItemNameByIndex(int index) {
        return getItemName(getProductByIndex(index));
    }

    /**
     * Метод получения имени продукта
     *
     * @param item - продукт типа WebElement
     * @return String - возвращает имя продукта типа String
     */
    public String getItemName(WebElement item) {
        WebElement itemName = findElement(item, By.className("inventory_item_name"));
        return itemName.getText();
    }

    /**
     * Метод получния кнопки добавления в корзину
     *
     * @param item - объект продукта
     * @return WebElement - возвращает кнопку добавить в корзину
     */
    public WebElement findAddToCartButton(WebElement item) {
        String selector = String.format("button[id^='%s']", addToCartButtonPrefix);
        return item.findElement(By.cssSelector(selector));
    }

    /**
     * Метод получния кнопки удаления из корзины
     *
     * @param item - объект продукта
     * @return WebElement - возвращает кнопку удалить из корзины
     */
    public WebElement findRemoveButton(WebElement item) {
        String selector = String.format("button[id^='%s']", removeButtonPrefix);
        return item.findElement(By.cssSelector(selector));
    }

    /**
     * Метод проверки кнопки удаления из корзины
     *
     * @param index - индекс продукта в списке
     * @return
     */
    public boolean checkRemoveButtonByIndex(int index) {
        String selector = String.format("button[id^='%s']", removeButtonPrefix);
        return checkIfElementPresent(products.get(index), By.cssSelector(selector));
    }

    /**
     * Метод добавления в корзину продукта по индексу
     *
     * @param index - индекс продукта в списке
     * @return ProductPage - возвращает ProductPage
     */
    public ProductPage addToCartByIndex(int index) {
        WebElement item = getProductByIndex(index);
        addToCart(item);
        return this;
    }

    /**
     * Метод добавления продукта в корзину
     *
     * @param item - объект продукта
     * @return ProductPage - возвращает ProductPage
     */
    private ProductPage addToCart(WebElement item) {
        clickElement(findAddToCartButton(item));
        return this;
    }

    /**
     * Метод удаления из корзины продукта по индексу
     *
     * @param index - индекс продукта в списке
     * @return ProductPage - возвращает ProductPage
     */
    private ProductPage removeFromCartByIndex(int index) {
        WebElement item = getProductByIndex(index);
        removeFromCart(item);
        return this;
    }

    /**
     * Метод удаления продукта из корзины
     *
     * @param item - объект продукта
     * @return ProductPage - возвращает ProductPage
     */
    private ProductPage removeFromCart(WebElement item) {
        clickElement(findRemoveButton(item));
        return this;
    }

    /**
     * Метод получения объекта продукта по индексу
     *
     * @param index - индекс продукта в списке
     * @return WebElement - возвращает объект продукта
     */
    public WebElement getProductByIndex(int index) {
        WebElement item = products.get(index);
        waitUntilElementToBeVisible(item);
        return item;
    }

    /**
     * Метод нажатия кнопки Корзина
     *
     * @return ProductPage - возвращает ProductPage
     */
    public ProductPage goToCart() {
        waitUntilElementToBeClickable(cartLink);
        clickElement(cartLink);
        return this;
    }
}
