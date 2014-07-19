package exercise;

import exercise.bean.Color;
import exercise.bean.FinishingType;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class PaintShopTest {

    @Test
    public void shouldPassTestCase1() throws Exception {
        //Given
        final String content = "5\n" +
                "1 M 3 G 5 G\n" +
                "2 G 3 M 4 G\n" +
                "5 M";
        final PaintShop parser = new PaintShop(content);
        //When
        final String response = parser.processColors();
        //Then
        assertEquals("G G G G M", response);
    }

    @Test
    public void shouldPassTestCase2() throws Exception {
        //Given
        final String content = "5\n" +
                "2 M\n" +
                "5 G\n" +
                "1 G\n" +
                "5 G 1 G 4 M\n" +
                "3 G\n" +
                "5 G\n" +
                "3 G 5 G 1 G\n" +
                "3 G\n" +
                "2 M\n" +
                "5 G 1 G\n" +
                "2 M\n" +
                "5 G\n" +
                "4 M\n" +
                "5 G 4 M";
        final PaintShop parser = new PaintShop(content);
        //When
        final String response = parser.processColors();
        //Then
        assertEquals("G M G M G", response);
    }

    @Test
    public void shouldPassTestCase3() throws Exception {
        //Given
        final String content = "2\n" +
                "1 G 2 M\n" +
                "1 M";
        final PaintShop parser = new PaintShop(content);
        //When
        final String response = parser.processColors();
        //Then
        assertEquals("M M", response);
    }

    @Test
    public void shouldGenerateNoSolutionMsg() throws Exception {
        //Given
        final String content = "1\n" +
                "1 G\n" +
                "1 M";
        final PaintShop parser = new PaintShop(content);
        //When
        final String response = parser.processColors();
        //Then
        assertEquals("No solution exists", response);
    }

    @Test
    public void shouldParseStringLines() throws Exception {
        //Given
        final String content = "2\n" +
                "1 G 2 M\n" +
                "1 M";
        final PaintShop parser = new PaintShop(content);
        //When and Then
        assertEquals(2, parser.getContent().size());
        assertEquals(2, parser.getAmountOfColors());
    }

    @Test
    public void shouldParseColors() throws Exception {
        //Given
        final String line = "1 G 2 M";
        final PaintShop parser = new PaintShop();
        //When
        final Map<Integer, Color> parsed = parser.parseColors(line);
        //Then
        assertEquals(2, parsed.size());
        assertEquals(FinishingType.G, parsed.get(1).getFinishingType());
        assertEquals(FinishingType.M, parsed.get(2).getFinishingType());
    }

    @Test
    public void shouldParseSingleColor(){
        //Given
        final String line = "1 G";
        final PaintShop parser = new PaintShop();
        //When
        final Map<Integer,Color> colors = parser.parseColors(line);
        //Then
        assertEquals(1, colors.size());
        assertEquals(FinishingType.G, colors.get(1).getFinishingType());
    }
}
