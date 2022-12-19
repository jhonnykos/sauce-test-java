package org.saucelibs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.saucelibs.managers.DriverManager;
import org.saucelibs.managers.InitialManager;
import org.saucelibs.managers.PageManager;
import org.saucelibs.managers.PropertyManager;
import org.saucelibs.pages.LoginPage;

import static org.saucelibs.config.Constants.BASE_URL;
import static org.saucelibs.config.Constants.LOGIN_ENDPOINT;

public abstract class BaseTest {

    /**
     * Менеджер WebDriver
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    /**
     * Менеджер страниц
     */
    protected PageManager app = PageManager.getPageManager();

    /**
     * Адрес логина
     */
    protected final String loginUrl = getPropertyString(BASE_URL) + getPropertyString(LOGIN_ENDPOINT);

    /**
     * Метод получения текущего адреса страницы
     *
     * @return String - возвращает адрес типа String
     */
    protected String getCurrentUrl() {
        return driverManager.getDriver().getCurrentUrl();
    }

    /**
     * Метод получения свойства
     *
     * @param property - ключ свойства
     * @return String - возвращает значение свойства типа String
     */
    protected String getPropertyString(String property) {
        return PropertyManager.getPropertyManager().getPropertyString(property);
    }

    /**
     * Метод получения свойства типа Integer
     *
     * @param property - ключ свойства
     * @return Integer - возвращает значение свойства типа Integer
     */
    protected Integer getPropertyInt(String property) {
        return PropertyManager.getPropertyManager().getPropertyInt(property);
    }

    /**
     * Метод открытия страницы
     *
     * @param url - адрес страницы
     */
    protected void openPage(String url) {
        driverManager.getDriver().get(url);
    }


    /**
     * Вспомогательный метод входа в приложение
     *
     * @param userName - логин
     * @param password - пароль
     * @return LoginPage - возвращает LoginPage
     */
    protected LoginPage login(LoginPage loginPage, String userName, String password){
        return loginPage
                .fillLogin(userName)
                .fillPassword(password)
                .clickLoginButton();
    }

    @BeforeAll
    public static void beforeAll() {
        InitialManager.initFramework();
    }

    @AfterAll
    public static void afterAll() {
        InitialManager.quitFramework();
    }
}
