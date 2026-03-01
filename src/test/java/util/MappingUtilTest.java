package util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MappingUtilTest {
    @Autowired
    private MappingUtil util;

    @Test
    void testGetCountryCurrency_found() {
        assertEquals("BRL", util.getCountryCurrency("Brazil"));
        assertEquals("USD", util.getCountryCurrency("UnitedStates"));
    }

    @Test
    void testGetCountryCurrency_notFound() {
        assertEquals("NOT_FOUND", util.getCountryCurrency("France"));
        assertEquals("NOT_FOUND", util.getCountryCurrency(""));
        assertEquals("NOT_FOUND", util.getCountryCurrency(null));
    }
}
