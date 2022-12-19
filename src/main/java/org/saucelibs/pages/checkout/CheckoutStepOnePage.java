package org.saucelibs.pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.saucelibs.pages.BasePage;

public class CheckoutStepOnePage extends BasePage {

    private final String requiredFirstName = "Error: First Name is required";
    private final String requiredLastName = "Error: Last Name is required";
    private final String requiredPostalCode = "Error: Postal Code is required";

    private final By error = By.xpath("//h3[@data-test='error']");
    private final By resetButtonId = By.id("reset_sidebar_link");

    @FindBy(xpath = "//span[@class='title' and contains(text(),'Checkout: Your Information')]")
    private WebElement title;

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    private WebElement menu;


    /**
     * Метод получения заголовка
     *
     * @return WebElement - возвращает объект заголовка
     */
    public WebElement getTitle() {
        return title;
    }

    /**
     * Метод получения ошибки
     *
     * @return WebElement - возвращает объект ошибки
     */
    public WebElement getError() {
        return findElement(error);
    }

    /**
     * Метод заполнения имени
     *
     * @param firstName - имя
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage fillFirstName(String firstName) {
        waitUntilElementToBeVisible(firstNameInput);
        fillFields(firstNameInput, firstName);
        return this;
    }

    /**
     * Метод заполнения фамилии
     *
     * @param secondName - фамилия
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage fillLastName(String secondName) {
        waitUntilElementToBeVisible(lastNameInput);
        fillFields(lastNameInput, secondName);
        return this;
    }

    /**
     * Метод заполнения индекса
     *
     * @param postalCode - индекс
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage fillPostalCode(String postalCode) {
        waitUntilElementToBeVisible(postalCodeInput);
        fillFields(postalCodeInput, postalCode);
        return this;
    }

    /**
     * Метод нажатия кнопки Continue
     *
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage clickContinueButton() {
        waitUntilElementToBeClickable(continueButton);
        clickElement(continueButton);
        return this;
    }

    /**
     * Метод нажатия кнопки меню
     *
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage clickMenu() {
        waitUntilElementToBeClickable(menu);
        clickElement(menu);
        return this;
    }

    /**
     * Метод нажатия кнопки сброса
     *
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage clickReset() {
        WebElement resetButton = findElement(resetButtonId);
        clickElement(resetButton);
        return this;
    }

    /**
     * Метод сброса состояния
     *
     * @return CheckoutStepOnePage - возвращает CheckoutStepOnePage
     */
    public CheckoutStepOnePage reset() {
        clickMenu();
        clickReset();
        return this;
    }

    /**
     * Проверка сообщения об обязательном имени
     *
     * @return
     */
    public boolean isRequiredFirstNameError() {
        WebElement element = findElement(error);
        return element.getText().equals(requiredFirstName);
    }

    /**
     * Проверка сообщения об обязательной фамилии
     *
     * @return
     */
    public boolean isRequiredLastNameError() {
        WebElement element = findElement(error);
        return element.getText().equals(requiredLastName);
    }

    /**
     * Проверка сообщения об обязательном индексе
     *
     * @return
     */
    public boolean isRequiredPostalCodeError() {
        WebElement element = findElement(error);
        return element.getText().equals(requiredPostalCode);
    }
}
