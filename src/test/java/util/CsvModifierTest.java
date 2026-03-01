package util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CsvModifierTest {

    // -----------------------------------------------------------------------
    // parseKeyValuePairs
    // -----------------------------------------------------------------------

    @Test
    void parseKeyValuePairs_withSingleQuotes() {
        Map<String, String> result = CsvModifier.parseKeyValuePairs("batchName='Load_Data1',country='Brazil'");
        assertEquals("Load_Data1", result.get("batchName"));
        assertEquals("Brazil",     result.get("country"));
    }

    @Test
    void parseKeyValuePairs_withoutSingleQuotes() {
        Map<String, String> result = CsvModifier.parseKeyValuePairs("label=Changed,quote=100");
        assertEquals("Changed", result.get("label"));
        assertEquals("100",     result.get("quote"));
    }

    @Test
    void parseKeyValuePairs_mixedQuotes() {
        Map<String, String> result = CsvModifier.parseKeyValuePairs("label=Changed,quote='100'");
        assertEquals("Changed", result.get("label"));
        assertEquals("100",     result.get("quote"));
    }

    @Test
    void parseKeyValuePairs_singleEntry() {
        Map<String, String> result = CsvModifier.parseKeyValuePairs("country='Mexico'");
        assertEquals(1, result.size());
        assertEquals("Mexico", result.get("country"));
    }

    @Test
    void parseKeyValuePairs_invalidPairThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> CsvModifier.parseKeyValuePairs("noEqualsSign"));
    }

    // -----------------------------------------------------------------------
    // splitCsvLine
    // -----------------------------------------------------------------------

    @Test
    void splitCsvLine_simpleLine() {
        String[] fields = CsvModifier.splitCsvLine("1,Load_Data1,Brazil,20,Original,SOUTH_AMERICA");
        assertArrayEquals(new String[]{"1", "Load_Data1", "Brazil", "20", "Original", "SOUTH_AMERICA"}, fields);
    }

    @Test
    void splitCsvLine_quotedFieldWithComma() {
        String[] fields = CsvModifier.splitCsvLine("1,\"Hello, World\",Brazil");
        assertArrayEquals(new String[]{"1", "Hello, World", "Brazil"}, fields);
    }

    @Test
    void splitCsvLine_header() {
        String[] fields = CsvModifier.splitCsvLine("key,batchName,country,quote,label,region");
        assertArrayEquals(new String[]{"key", "batchName", "country", "quote", "label", "region"}, fields);
    }

    // -----------------------------------------------------------------------
    // joinCsvLine
    // -----------------------------------------------------------------------

    @Test
    void joinCsvLine_simpleFields() {
        assertEquals("1,Load_Data1,Brazil,100,Changed,SOUTH_AMERICA",
                CsvModifier.joinCsvLine(new String[]{"1", "Load_Data1", "Brazil", "100", "Changed", "SOUTH_AMERICA"}));
    }

    @Test
    void joinCsvLine_fieldWithCommaIsWrapped() {
        String result = CsvModifier.joinCsvLine(new String[]{"1", "Hello, World", "Brazil"});
        assertEquals("1,\"Hello, World\",Brazil", result);
    }

    @Test
    void joinCsvLine_nullFieldBecomesEmpty() {
        assertEquals("1,,Brazil", CsvModifier.joinCsvLine(new String[]{"1", null, "Brazil"}));
    }

    // -----------------------------------------------------------------------
    // main ? end-to-end via temp file
    // -----------------------------------------------------------------------

    private static final String HEADER = "key,batchName,country,quote,label,region";
    private static final String ROW1   = "1,Load_Data1,Brazil,20,Original,SOUTH_AMERICA";
    private static final String ROW2   = "2,Load_Data2,Mexico,1,Original,CENTER_AMERICA";
    private static final String ROW3   = "3,Load_Data1,Brazil,100,Persisted,SOUTH_AMERICA";

    private Path writeCsv(Path dir, String... lines) throws IOException {
        Path file = dir.resolve("test.csv");
        Files.write(file, List.of(lines));
        return file;
    }

    @Test
    void main_updatesMatchingRows(@TempDir Path tmp) throws IOException {
        Path csv = writeCsv(tmp, HEADER, ROW1, ROW2, ROW3);

        CsvModifier.main(new String[]{
                csv.toString(),
                "batchName='Load_Data1',country='Brazil'",
                "label=Changed,quote='100'"
        });

        List<String> result = Files.readAllLines(csv);
        assertEquals(4, result.size());
        assertEquals(HEADER, result.get(0));
        assertEquals("1,Load_Data1,Brazil,100,Changed,SOUTH_AMERICA", result.get(1));
        assertEquals(ROW2,   result.get(2));   // unchanged
        assertEquals("3,Load_Data1,Brazil,100,Changed,SOUTH_AMERICA", result.get(3));
    }

    @Test
    void main_noMatchLeavesFileUnchanged(@TempDir Path tmp) throws IOException {
        Path csv = writeCsv(tmp, HEADER, ROW1, ROW2, ROW3);

        CsvModifier.main(new String[]{
                csv.toString(),
                "country='Argentina'",
                "label=Changed"
        });

        List<String> result = Files.readAllLines(csv);
        assertEquals(4, result.size());
        assertEquals(ROW1, result.get(1));
        assertEquals(ROW2, result.get(2));
        assertEquals(ROW3, result.get(3));
    }

    @Test
    void main_singleCriteriaUpdatesCorrectRows(@TempDir Path tmp) throws IOException {
        Path csv = writeCsv(tmp, HEADER, ROW1, ROW2, ROW3);

        CsvModifier.main(new String[]{
                csv.toString(),
                "country='Mexico'",
                "label=Updated"
        });

        List<String> result = Files.readAllLines(csv);
        assertEquals("2,Load_Data2,Mexico,1,Updated,CENTER_AMERICA", result.get(2));
        // rows 1 and 3 must be untouched
        assertEquals(ROW1, result.get(1));
        assertEquals(ROW3, result.get(3));
    }

    @Test
    void main_emptyCsvPrintsNothing(@TempDir Path tmp) throws IOException {
        Path csv = tmp.resolve("empty.csv");
        Files.createFile(csv);
        // Should not throw
        CsvModifier.main(new String[]{csv.toString(), "country='Brazil'", "label=Changed"});
        assertEquals(0, Files.size(csv));
    }

    @Test
    void main_headerOnlyLeavesFileUnchanged(@TempDir Path tmp) throws IOException {
        Path csv = writeCsv(tmp, HEADER);

        CsvModifier.main(new String[]{
                csv.toString(),
                "country='Brazil'",
                "label=Changed"
        });

        List<String> result = Files.readAllLines(csv);
        assertEquals(1, result.size());
        assertEquals(HEADER, result.get(0));
    }
}

