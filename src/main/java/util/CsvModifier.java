package util;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Usage:
 *   java CsvModifier <csvFilePath> "<col='val',...>" "<col='val',...>"
 *
 * Example (plain replacement):
 *   java CsvModifier c:\temp\config.csv "configName='Load_Data1',country='Brazil'" "label=Changed,quote='100'"
 *
 * Example (substring replacement):
 *   java CsvModifier c:\temp\config.csv "configName='Load_Data1',country='Brazil'" "label=Changed,quote='100',complement='replaceSubstring('value1','value2')'"
 *
 * Criteria and update values may optionally be wrapped in single quotes.
 * Use replaceSubstring('old','new') as an update value to replace a substring
 * within the existing cell content rather than overwriting the whole cell.
 */
public class CsvModifier {

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println("Usage: CsvModifier <csvFile> \"<criteria>\" \"<updates>\"");
            System.err.println("  criteria: col='val',col2='val2'  (quotes optional)");
            System.err.println("  updates : col='val',col2='val2'  (quotes optional)");
            System.exit(1);
        }

        Path csvPath = Paths.get(args[0]);
        Map<String, String> criteria = parseKeyValuePairs(args[1]);
        Map<String, String> updates  = parseKeyValuePairs(args[2]);

        List<String> lines = Files.readAllLines(csvPath);
        if (lines.isEmpty()) {
            System.out.println("CSV file is empty – nothing to do.");
            return;
        }

        // Parse header
        String headerLine = lines.get(0);
        String[] headers  = splitCsvLine(headerLine);

        // Build index: column name -> position
        Map<String, Integer> colIndex = new LinkedHashMap<>();
        for (int i = 0; i < headers.length; i++) {
            colIndex.put(headers[i].trim(), i);
        }

        // Validate criteria columns
        for (String col : criteria.keySet()) {
            if (!colIndex.containsKey(col)) {
                System.err.println("Criteria column not found in header: " + col);
                System.exit(1);
            }
        }

        // Validate update columns
        for (String col : updates.keySet()) {
            if (!colIndex.containsKey(col)) {
                System.err.println("Update column not found in header: " + col);
                System.exit(1);
            }
        }

        int updatedRows = 0;
        List<String> resultLines = new ArrayList<>();
        resultLines.add(headerLine);   // keep original header

        for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
            String line = lines.get(lineIdx);
            if (line.isBlank()) {
                resultLines.add(line);
                continue;
            }

            String[] fields = splitCsvLine(line);
            // Ensure array is large enough (in case last columns are empty)
            if (fields.length < headers.length) {
                fields = Arrays.copyOf(fields, headers.length);
            }

            if (matchesCriteria(fields, colIndex, criteria)) {
                for (Map.Entry<String, String> update : updates.entrySet()) {
                    int idx = colIndex.get(update.getKey());
                    fields[idx] = applyUpdateValue(fields[idx], update.getValue());
                }
                updatedRows++;
            }

            resultLines.add(joinCsvLine(fields));
        }

        Files.write(csvPath, resultLines);
        System.out.println("Done. Rows updated: " + updatedRows);
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    /**
     * Applies an update directive to the current field value.
     *
     * If the directive matches  replaceSubstring('oldVal','newVal')  the method
     * replaces every occurrence of oldVal with newVal inside the existing field.
     * Otherwise the directive is treated as a plain replacement value.
     */
    static String applyUpdateValue(String currentValue, String directive) {
        String trimmed = directive.trim();
        if (trimmed.toLowerCase().startsWith("replacesubstring(")) {
            String[] args = parseReplaceSubstringArgs(trimmed);
            String oldSub = args[0];
            String newSub = args[1];
            return currentValue == null ? null : currentValue.replace(oldSub, newSub);
        }
        // Plain replacement – strip surrounding single quotes if present
        if (trimmed.startsWith("'") && trimmed.endsWith("'") && trimmed.length() >= 2) {
            return trimmed.substring(1, trimmed.length() - 1);
        }
        return trimmed;
    }

    /**
     * Extracts the two single-quoted arguments from a replaceSubstring('a','b') directive.
     * Returns a two-element array: [oldSubstring, newSubstring].
     */
    static String[] parseReplaceSubstringArgs(String directive) {
        // Find content between the outer parentheses
        int open  = directive.indexOf('(');
        int close = directive.lastIndexOf(')');
        if (open < 0 || close < 0 || close <= open) {
            throw new IllegalArgumentException("Invalid replaceSubstring syntax: " + directive);
        }
        String inner = directive.substring(open + 1, close).trim(); // 'value1','value2'

        // Split on the comma that separates the two quoted arguments
        // Pattern: '<anything>','<anything>'
        String[] parts = inner.split("',\\s*'");
        if (parts.length != 2) {
            throw new IllegalArgumentException("replaceSubstring requires exactly 2 arguments: " + directive);
        }
        // Strip the remaining leading/trailing single quote from each part
        String first  = parts[0].startsWith("'") ? parts[0].substring(1) : parts[0];
        String second = parts[1].endsWith("'")   ? parts[1].substring(0, parts[1].length() - 1) : parts[1];
        return new String[]{first, second};
    }

    /**
     * Parses a string like: batchName='Load_Data1',country='Brazil'
     * Single quotes around values are optional and are stripped.
     */
    static Map<String, String> parseKeyValuePairs(String input) {
        Map<String, String> map = new LinkedHashMap<>();
        // Split on commas that are NOT inside single quotes
        String[] pairs = input.split(",(?=(?:[^']*'[^']*')*[^']*$)");
        for (String pair : pairs) {
            pair = pair.trim();
            int eq = pair.indexOf('=');
            if (eq < 0) {
                throw new IllegalArgumentException("Invalid key=value pair: " + pair);
            }
            String key   = pair.substring(0, eq).trim();
            String value = pair.substring(eq + 1).trim();
            // Strip surrounding single quotes from value
            if (value.startsWith("'") && value.endsWith("'") && value.length() >= 2) {
                value = value.substring(1, value.length() - 1);
            }
            map.put(key, value);
        }
        return map;
    }

    /**
     * Simple CSV line splitter (handles commas inside double-quoted fields).
     */
    static String[] splitCsvLine(String line) {
        List<String> tokens = new ArrayList<>();
        StringBuilder sb    = new StringBuilder();
        boolean inQuotes    = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                tokens.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        tokens.add(sb.toString());
        return tokens.toArray(new String[0]);
    }

    /**
     * Re-joins fields with commas. Wraps fields containing commas in double quotes.
     */
    static String joinCsvLine(String[] fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            if (i > 0) sb.append(',');
            String f = fields[i] == null ? "" : fields[i];
            if (f.contains(",") || f.contains("\"")) {
                sb.append('"').append(f.replace("\"", "\"\"")).append('"');
            } else {
                sb.append(f);
            }
        }
        return sb.toString();
    }

    private static boolean matchesCriteria(String[] fields,
                                           Map<String, Integer> colIndex,
                                           Map<String, String> criteria) {
        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            int idx = colIndex.get(entry.getKey());
            String actual = idx < fields.length ? fields[idx] : "";
            if (actual == null) actual = "";
            if (!actual.trim().equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }
}
