package vavr;

import io.vavr.API;
import org.junit.jupiter.api.Test;

import static io.vavr.API.$;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

public class VavrTest {
    @Test
    public void matchTest() {
        Object s = "42";
        String of = Match(s).of(
                API.Case($(instanceOf(String.class)), "String"),
                API.Case($(instanceOf(Integer.class)), "Integer"));
        System.out.println(of);
    }
}
