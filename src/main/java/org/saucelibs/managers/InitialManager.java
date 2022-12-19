package org.saucelibs.managers;

import java.time.Duration;

import static org.saucelibs.config.Constants.*;

public class InitialManager {

    /**
     * Менеджер properties
     *
     * @see PropertyManager#getPropertyManager()
     */
    private static final PropertyManager props = PropertyManager.getPropertyManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private static final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Инициализация фреймворка и запуск браузера со страницей приложения
     *
     */
    public static void initFramework() {
        driverManager.getDriver().manage().window().maximize();
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(props.getPropertyInt(IMPLICIT_WAIT)));
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(props.getPropertyInt(PAGE_LOAD_TIMEOUT)));
    }

    /**
     * Завершение работы фреймворка - отключение драйвера и закрытие сессии с браузером
     *
     * @see DriverManager#quitDriver()
     */
    public static void quitFramework() {
        driverManager.quitDriver();
    }
}
