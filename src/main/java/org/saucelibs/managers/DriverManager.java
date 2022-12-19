package org.saucelibs.managers;

import org.apache.commons.exec.OS;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.saucelibs.config.Constants.TYPE_BROWSER;
import static org.saucelibs.config.Constants.WEBDRIVER_PATH_WINDOWS_CHROME;

public class DriverManager {

    /**
     * Переменна для хранения объекта веб-драйвера
     *
     * @see WebDriver
     */
    private WebDriver driver;


    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;


    /**
     * Менеджер properties
     *
     * @see PropertyManager#getPropertyManager()
     */
    private final PropertyManager props = PropertyManager.getPropertyManager();


    /**
     * Конструктор специально был объявлен как private (singleton паттерн)
     *
     * @see DriverManager#getDriverManager()
     */
    private DriverManager() {
    }

    /**
     * Метод ленивой инициализации DriverManager
     *
     * @return DriverManager - возвращает DriverManager
     */
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    /**
     * Метод ленивой инициализации драйвера
     *
     * @return WebDriver - возвращает драйвер
     */
    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }


    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Метод инициализирующий веб драйвер
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOs();
        } else {
            Assertions.fail("Данной OS не существует во фреймворке");
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     */
    private void initDriverWindowsOs() {
        initDriverAnyOs(WEBDRIVER_PATH_WINDOWS_CHROME);
    }

    /**
     * Метод инициализирующий веб драйвер под любую ОС
     *
     **/
    private void initDriverAnyOs(String chrome) {
        switch (props.getPropertyString(TYPE_BROWSER)) {
            case "chrome":
                String path = props.getPropertyString(chrome);
                System.setProperty("webdriver.chrome.driver", path );
                driver = new ChromeDriver();
                break;
            default:
                Assertions.fail("Типа браузера '" + props.getPropertyString(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }
}
