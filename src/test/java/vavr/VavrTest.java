package vavr;

import io.vavr.API;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.vavr.API.$;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VavrTest {
    @Test
    public void matchTest() {
        Object s = "42";
        String of = Match(s).of(
                API.Case($(instanceOf(String.class)), "String"),
                API.Case($(instanceOf(Integer.class)), "Integer"));
        System.out.println(of);
    }

    @Test
    public void regex() {
        var filename = "RATES_MUREX_765488_FoR_Tplus1_SYD.result_schema.rdb";
        var pattern = Stream.of("FoR_Tplus1_SYD").map(s -> String.format("(.*)_%s(\\..*)",s)).collect(Collectors.joining("|"));
        var regex = String.format("(.*)%s(\\..*)", pattern);
        var matches = filename.matches(regex);
        assertTrue(matches);
    }
}
