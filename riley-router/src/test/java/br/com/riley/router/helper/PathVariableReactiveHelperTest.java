package br.com.riley.router.helper;

import br.com.riley.router.reactive.PathVariableReactive;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PathVariableReactiveHelperTest {

    private PathVariableReactive pathVariableReactive = new PathVariableReactive();

    @Test
    public void dadoUmaRotaComUmaVariavel_deveRetornaAMesma() {
        String url = "/users/{user_id}";
        pathVariableReactive.getParams(url).subscribe( params -> {
            assertEquals(params.get(0), "{user_id}");
        });
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

}
