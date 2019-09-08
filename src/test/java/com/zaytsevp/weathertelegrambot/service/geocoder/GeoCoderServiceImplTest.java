package com.zaytsevp.weathertelegrambot.service.geocoder;

import com.zaytsevp.weathertelegrambot.feign.geocoder.YandexGeocoderFeignClient;
import com.zaytsevp.weathertelegrambot.model.geocoder.FeatureMember;
import com.zaytsevp.weathertelegrambot.model.geocoder.LocalityWithCoordsProjection;
import com.zaytsevp.weathertelegrambot.model.geocoder.MainGeoResponse;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class GeoCoderServiceImplTest {

    private final YandexGeocoderFeignClient yandexGeocoderFeignClient = mock(YandexGeocoderFeignClient.class);

    private final String apiKey = "apiKey";

    private final GeoCoderService geoCoderService = new GeoCoderServiceImpl(yandexGeocoderFeignClient, apiKey);

    private final String cityName = "cityName";

    private final LocalityWithCoordsProjection localityWithCoordsProjection = mock(LocalityWithCoordsProjection.class, RETURNS_DEEP_STUBS);

    private final MainGeoResponse mainGeoResponse = mock(MainGeoResponse.class, RETURNS_DEEP_STUBS);

    private final FeatureMember featureMember = mock(FeatureMember.class, RETURNS_DEEP_STUBS);

    private final String latString = "1.1";

    private final double lat = 1.1;

    private final String lonString = "2.2";

    private final double lon = 2.2;

    private final String locality = "locality";

    private final String localityName = "name";

    @Test
    public void getGeoCoordsByCityName() {
        // Arrange
        when(featureMember.getGeoObject().getName()).thenReturn(localityName);

        when(featureMember.getGeoObject()
                          .getMetaDataProperty()
                          .getGeocoderMetaData()
                          .getKind()).thenReturn(locality);

        when(mainGeoResponse.getResponse()
                            .getGeoObjectCollection()
                            .getFeatureMember()).thenReturn(Lists.newArrayList(featureMember));

        when(featureMember.getGeoObject()
                          .getPoint()
                          .getPos()).thenReturn(lonString + " " + latString);

        when(yandexGeocoderFeignClient.getLocalityCoordsByName(anyString(), anyString(), anyInt(), anyString())).thenReturn(mainGeoResponse);

        // Actual
        LocalityWithCoordsProjection actualResult = geoCoderService.getGeoCoordsByCityName(cityName);

        // Assertion
        Assertions.assertThat(actualResult.getCoords()).isNotEmpty();
        Assertions.assertThat(actualResult.getCoords().size()).isEqualTo(2);
        Assertions.assertThat(actualResult.getCoords().containsKey("lat")).isTrue();
        Assertions.assertThat(actualResult.getCoords().containsKey("lon")).isTrue();
        Assertions.assertThat(actualResult.getCoords().get("lon")).isEqualTo(lon);
        Assertions.assertThat(actualResult.getCoords().get("lat")).isEqualTo(lat);
        Assertions.assertThat(actualResult.getLocalityName()).isEqualTo(localityName);

        verify(yandexGeocoderFeignClient).getLocalityCoordsByName(cityName, apiKey, 10, "locality");
    }
}