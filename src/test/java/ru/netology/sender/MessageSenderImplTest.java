package ru.netology;

import org.mockito.Mockito;
import org.testng.annotations.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MessageSenderImplTest {
    @Test
    void messageSenderWithMockitoRus(){
        // отправляет только русский текст, если ip относится к российскому
        var geoService = Mockito.mock(GeoService.class);
        var localizationService = Mockito.mock(LocalizationService.class);
        //переменная для messageSenderImp
        Map<String, String> headersRus = new HashMap<>();
        headersRus.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Добро пожаловать");
        var messageSenderImpRus = new MessageSenderImpl(geoService,localizationService);

        //expected
        var expectedRus = "Добро пожаловать";

        //actual
        var actualRus = messageSenderImpRus.send(headersRus);

        assertEquals(expectedRus, actualRus);
    }
    @Test
    void messageSenderWithMockitoEn(){
        //отправляет только английский текст, если ip относится к американскому сегменту адресов
        var geoService = Mockito.mock(GeoService.class);
        var localizationService = Mockito.mock(LocalizationService.class);
        //переменная для messageSenderImp
        Map<String, String> headersEn = new HashMap<>();
        headersEn.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        Mockito.when(geoService.byIp(Mockito.any())).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(localizationService.locale(Mockito.any())).thenReturn("Welcome");
        var messageSenderImpEn = new MessageSenderImpl(geoService,localizationService);

        //expected
        var expectedRus = "Welcome";

        //actual
        var actualEn = messageSenderImpEn.send(headersEn);

        assertEquals(expectedRus, actualEn);
    }
}
