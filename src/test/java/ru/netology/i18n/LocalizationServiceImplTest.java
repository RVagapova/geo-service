package ru.netology.i18n;

import org.testng.annotations.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {
    @Test
    void locateTest() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        assertEquals(localizationService.locale(Country.RUSSIA), "Добро пожаловать");
        assertEquals(localizationService.locale(Country.GERMANY), "Welcome");

    }
}
