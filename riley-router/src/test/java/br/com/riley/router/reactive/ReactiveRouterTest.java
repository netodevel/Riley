package br.com.riley.router.reactive;

import br.com.riley.router.Router;
import br.com.riley.router.RouterException;
import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class ReactiveRouterTest {{

    describe("dado uma rota reativa", () -> {
        Router.get("/index", ()-> Observable.just("hello world"));

        it("deve retornar hello world", ()-> {
            Router router = Router.getInstance();
            router.routes.get(0).getReactiveRouteAction()
                    .execute()
                    .subscribe(res -> { assertEquals("hello world", res); });
        });
    });

    describe("dado uma url", () -> {
        String url = "/users";
        context("com duas rotas registradas", () -> {
            Router.get("/index", ()-> Observable.just("hello world"));
            Router.get("/users", ()-> Observable.just("hello users"));

            it("deve executar a rota '/users'", () -> {
                Router router = Router.getInstance();

                router.executeRequest(url).subscribe(res -> {
                    assertEquals("hello users", res);
                });
            });
        });

        context("nao encontrada", ()-> {
            it("deve retornar RouterException", () -> {
                Router router = new Router();
                router.routes = asList();

                router.executeRequest(url).subscribe(res -> {
                    Assert.assertEquals(new RouterException(), res);
                });
            });
        });
    });

}}
