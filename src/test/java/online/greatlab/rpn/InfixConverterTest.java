package online.greatlab.rpn;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author a.kotov
 * @since 25.06.2018
 */
public class InfixConverterTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void convertLogic() throws Exception {

        String[] value = {"max", "(", "2", ",", "VALUE", ")", "==", "4", "&&", "5", "==", "5", "&&", "ENABLE", "(", "EXTRUDER_1", ")"};

        List<String> convertedValue = InfixConverter.convert(value);

        List<String> expValue = new ArrayList<>();
        expValue.addAll(Arrays.asList("2", "VALUE", "max", "4", "==", "5", "5", "==", "&&", "EXTRUDER_1", "ENABLE", "&&"));

        assertEquals(expValue, convertedValue);
    }

    @Test
    public void convertMath() throws Exception {

        String[] value = {"2", "^", "3", "*", "(", "12", "/", "6", ")", "+", "18", "/", "3", "+", "5.0", "/", "2" };

        List<String> convertedValue = InfixConverter.convert(value);

        List<String> expValue = new ArrayList<>();
        expValue.addAll(Arrays.asList("2", "3", "^", "12", "6", "/", "*", "18", "3", "/", "+", "5.0", "2", "/", "+"));

        assertEquals(expValue, convertedValue);
    }

}