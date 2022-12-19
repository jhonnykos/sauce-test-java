package org.saucelibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private final String wrongUserOrPasswordErrorMsg = "Epic sadface: Username and password do not match any user in this service";
    private final String requiredUserErrorMsg = "Epic sadface: Username is required";
    private final String requiredPasswordErrorMsg = "Epic sadface: Password is required";
    private final String lockedUserErrorMsg = "Epic sadface: Sorry, this user has been locked out.";

    private final By error = By.xpath("//h3[@data-test='error']");

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getError() {
        return findElement(error);
    }

    /**
     * Метод заполнения поля логин
     *
     * @param login
     * @return LoginPage - возвращает LoginPage
     */
    public LoginPage fillLogin(String login) {
        waitUntilElementToBeVisible(userNameInput);
        fillFields(userNameInput, login);
        return this;
    }

    /**
     * Метод заполнения поля пароль
     *
     * @param password
     * @return LoginPage - возвращает LoginPage
     */
    public LoginPage fillPassword(String password) {
        waitUntilElementToBeVisible(passwordInput);
        fillFields(passwordInput, password);
        return this;
    }

    /**
     * Метод нажатия кнопки Login
     *
     * @return LoginPage - возвращает LoginPage
     */
    public LoginPage clickLoginButton() {
        waitUntilElementToBeClickable(loginButton);
        clickElement(loginButton);
        return this;
    }

    /**
     * Проверка сообщения о неправильном логине или пароле
     *
     * @return
     */
    public boolean isWrongUserOrPasswordError() {
        WebElement element = findElement(error);
        return element.getText().equals(wrongUserOrPasswordErrorMsg);
    }

    /**
     * Проверка сообщения об обязательном логине
     *
     * @return
     */
    public boolean isRequiredUserError() {
        WebElement element = findElement(error);
        return element.getText().equals(requiredUserErrorMsg);
    }

    /**
     * Проверка сообщения об обязательном пароле
     *
     * @return
     */
    public boolean isRequiredPasswordError() {
        WebElement element = findElement(error);
        return element.getText().equals(requiredPasswordErrorMsg);
    }

    /**
     * Проверка сообщения о заблокированном пользователе
     *
     * @return
     */
    public boolean isLockedUserError() {
        WebElement element = findElement(error);
        return element.getText().equals(lockedUserErrorMsg);
    }
}
