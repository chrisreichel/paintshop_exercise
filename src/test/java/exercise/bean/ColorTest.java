package exercise.bean;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ColorTest {

    @Test
    public void shouldParseMetadata(){
        //Given
        final String metadata = "1 G";
        //When
        final Color color = new Color(metadata);
        //Then
        assertEquals(1, color.getCode());
        assertEquals(FinishingType.G, color.getFinishingType());
    }

    @Test
    public void shouldParseMetadata2(){
        //Given
        final String metadata = "33 M";
        //When
        final Color color = new Color(metadata);
        //Then
        assertEquals(33, color.getCode());
        assertEquals(FinishingType.M, color.getFinishingType());
    }

}
