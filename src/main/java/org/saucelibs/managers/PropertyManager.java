package org.saucelibs.managers;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyManager {
    private static final String PROPERTIES_FILE = "/" + System.getProperty("properties", "application.properties");
    private static final Properties PROPERTIES = getPropertiesInstance();
    private static PropertyManager INSTANCE = null;

    private PropertyManager() {
        loadCustomProperties();
    }

    /**
     * Метод ленивой инициализации PropertyManager
     *
     * @return PropertyManager - возвращает PropertyManager
     */
    public static PropertyManager getPropertyManager() {
        if (INSTANCE == null) {
            INSTANCE = new PropertyManager();
        }
        return INSTANCE;
    }

    /**
     * Метод заменяет значения, содержащиеся в ключах переменной {@link #PROPERTIES}
     * Заменяет на те значения, что передал пользователь через maven '-D{name.key}={value.key}'
     * Замена будет происходить только в том случае если пользователь передаст совпадающий key из application.properties
     *
     * @see PropertyManager#PropertyManager()
     */
    private void loadCustomProperties() {
        PROPERTIES.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        PROPERTIES.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }

    /**
     * Метод возвращает значение типа String записанное в ключе в переменной {@link #PROPERTIES},
     * если нет переменной вернет null
     *
     * @param key - ключ, значения которого хотите получить
     * @return String - строка со значением ключа
     */
    public String getPropertyString(String key) {
        return PROPERTIES.getProperty(key);
    }


    /**
     * Метод возвращает либо значение типа String, записанное в ключе в переменной {@link #PROPERTIES},
     * либо defaultValue, при отсутствие ключа в переменной {@link #PROPERTIES}
     *
     * @param key          - ключ, значения которого хотите получить
     * @param defaultValue - значение по умолчанию которое хотите получить если отсутствует ключ в {@link #PROPERTIES}
     * @return String - возвращает системное св-во либо переданное default значение
     */
    public String getPropertyString(String key, String defaultValue) {
        return PROPERTIES.getProperty(key, defaultValue);
    }


    /**
     * Метод возвращает значения типа Integer записанное в ключе в переменной {@link #PROPERTIES},
     * если нет переменной вернет null
     *
     * @param key - ключ, значения которого хотите получить
     * @return Integer - объект Integer со значением ключа
     */
    public Integer getPropertyInt(String key) {
        return Integer.parseInt(PROPERTIES.getProperty(key));
    }

    /**
     * Метод возвращает либо значение типа Integer, записанное в ключе в переменной {@link #PROPERTIES},
     * либо defaultValue при отсутствие ключа в переменной {@link #PROPERTIES}
     *
     * @param key          - ключ, значения которого хотите получить
     * @param defaultValue - значение по умолчанию которое хотите получить если отсутствует ключ в {@link #PROPERTIES}
     * @return Integer - возвращает системное св-во либо переданное default значение
     */
    public Integer getPropertyInt(String key, Integer defaultValue) {
        String value = PROPERTIES.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }


    /**
     * Вспомогательный метод, возвращает свойства из файла application.properties
     *
     * @return свойства из файла application.properties
     */
    @SneakyThrows(IOException.class)
    private static Properties getPropertiesInstance() {
        Properties instance = new Properties();
        try (
                InputStream resourceStream = PropertyManager.class.getResourceAsStream(PROPERTIES_FILE);
                InputStreamReader inputStream = new InputStreamReader(resourceStream, StandardCharsets.UTF_8)
        ) {
            instance.load(inputStream);
        }
        return instance;
    }

}
