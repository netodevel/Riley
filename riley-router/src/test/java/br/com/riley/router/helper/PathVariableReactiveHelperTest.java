package br.com.riley.router.helper;

import br.com.riley.router.reactive.PathVariableReactive;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.*;

public class PathVariableReactiveHelperTest {

    private PathVariableReactive pathVariableReactive = new PathVariableReactive();

    @Test
    public void dadoUmaRotaComUmaVariavel_deveRetornaAMesma() {
        String url = "/users/{user_id}";

        List<String> paramsExpected = asList("{user_id}");
        pathVariableReactive.getParams(url).test().assertSubscribed()
                .assertValue(paramsExpected).assertComplete().assertNoErrors();
    }

    @Test
    public void dadoUmaRotaSemVariavel_deveRetornaUmaListaVazio() {
        String url = "/users";
        pathVariableReactive.getParams(url).subscribe( params -> {
            assertEquals(params.size(), 0);
        });
    }

    @Test
    public void dadoUmaRotaSemParametro_deveRetornarFalse() {
        String url = "/users";
        pathVariableReactive.isParameter(url).subscribe( r -> {
            assertFalse(r);
        });
    }

    @Test
    public void dadoUmaRotaComParametro_deveRetornarTrue() {
        String url = "{user_id}";
        pathVariableReactive.isParameter(url).subscribe( r -> {
            assertTrue(r);
        });
    }

    @Test
    public void dadoUmaUrl_deveRetornarOsParametros() {
        String url = "/users/{user_id}";
        String contextPath = "/users/1";

        pathVariableReactive.param(url, contextPath).subscribe( value -> {
            System.out.println(value.get("{user_id}"));
        });
    }

}



