package org.saucelibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.saucelibs.managers.DriverManager;
import org.saucelibs.managers.PageManager;
import org.saucelibs.managers.PropertyManager;

import java.time.Duration;
import java.util.List;

import static org.saucelibs.config.Constants.*;

public abstract class BasePage {

    /**
     * Менеджер драйвера
     *
     * @see DriverManager#getDriverManager()
     */
    protected final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Менеджер страниц
     *
     * @see PageManager#getPageManager()
     */
    protected final PageManager pageManager = PageManager.getPageManager();

    /**
     * Менеджер properties
     *
     * @see PropertyManager#getPropertyManager()
     */
    protected final PropertyManager props = PropertyManager.getPropertyManager();

    protected final JavascriptExecutor js = (JavascriptExecutor) driverManager.getDriver();

    /**
     * Настройка явных ожиданий
     */
    private final WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(props.getPropertyInt(EXPLICIT_WAIT)),
            Duration.ofSeconds(props.getPropertyInt(EXPLICIT_SLEEP)));

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    /**
     * Явное ожидание состояния clickable элемента
     *
     * @param element - веб-элемент который требует проверки clickable
     * @return WebElement - возвращаем тот же веб элемент что был передан в функцию
     */
    public WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание видимого элемента
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    public WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Метод скролла до элемента
     *
     * @param element - элемент, до которого нужен скролл
     * @return WebElement - возвращает WebElement
     */
    protected WebElement scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

    /**
     * Метод скролла до элемента с отступом
     *
     * @param element - элемент, до которого нужен скролл
     * @param x - отступ по оси X
     * @param y - отступ по оси Y
     * @return WebElement - возвращает WebElement
     */
    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    /**
     * Метод заполнения поля
     *
     * @param element - объект поля
     * @param data    - данные для заполнения
     */
    public void fillFields(WebElement element, String data) {
        clickElement(element);
        clearElement(element);
        element.sendKeys(data);
    }

    /**
     * Метод проверки элемента на странице
     *
     * @param by - путь к элементу
     * @return
     */
    public boolean checkIfElementPresent(By by) {
        try {
            findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Метод проверки дочернего элемента на странице
     *
     * @param parent - родительский элемент
     * @param by - путь к элементу
     * @return
     */
    public boolean checkIfElementPresent(WebElement parent, By by) {
        try {
            findElement(parent, by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Метод нажатия на элемент
     *
     * @param element - элемент, на который нужно кликнуть
     */
    public void clickElement(WebElement element) {
        scrollWithOffset(element, 0, -500);
        element.click();
    }

    /**
     * Метод очистки поля
     *
     * @param element - объект поля
     */
    public void clearElement(WebElement element) {
        element.clear();
    }

    /**
     * Метод поиска элемента
     *
     * @param by - путь до элемента
     * @return
     */
    public WebElement findElement(By by) {
        return driverManager.getDriver().findElement(by);
    }

    /**
     * Метод поиска дочернего элемента
     *
     * @param parent - родительский элемент
     * @param by - путь до элемента
     * @return
     */
    public WebElement findElement(WebElement parent, By by) {
        return parent.findElement(by);
    }

    /**
     * Метод поиска списка элементов
     *
     * @param by - путь до элементов
     * @return List<WebElement> - возвращает список WebElement
     */
    public List<WebElement> findElements(By by) {
        return driverManager.getDriver().findElements(by);
    }
}
