package domain;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class PessoaTest {

    @Test
    public void test() {
        var resultadoEsperado = "clark.kent@dc.com;bruce.wayne@batcave.com;diana.prince@wonder.com";
        List<Pessoa> pessoas = List.of(
            new Pessoa(1L, "Clark Kent", "clark.kent@dc.com"),
            new Pessoa(2L, "Bruce Wayne", "bruce.wayne@batcave.com"),
            new Pessoa(3L, "Diana Prince", "diana.prince@wonder.com")
        );
        StringBuilder emails = new StringBuilder();
        for (Pessoa pessoa : pessoas) {
            emails.append(pessoa.getEmail()).append(";");
        }
        if (emails.length() > 0) {
            emails.setLength(emails.length() - 1); // Remove the last semicolon
        }
        System.out.println(emails.toString());
        assert resultadoEsperado.equals(emails.toString());
    }

    @Test
    public void test2() {

        var resultadoEsperado =
                "clark.kent@dc.com;bruce.wayne@batcave.com;diana.prince@wonder.com";

        List<Pessoa> pessoas = List.of(
                new Pessoa(1L, "Clark Kent", "clark.kent@dc.com"),
                new Pessoa(2L, "Bruce Wayne", "bruce.wayne@batcave.com"),
                new Pessoa(3L, "Diana Prince", "diana.prince@wonder.com")
        );
        String emails = pessoas.stream()
                .map(Pessoa::getEmail)
                .collect(Collectors.joining(";"));

        System.out.println(emails);

        assert resultadoEsperado.equals(emails);
    }
}
