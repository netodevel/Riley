package br.com.riley.router.reactive;

import br.com.riley.router.RouteManager;
import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;

@RunWith(Spectrum.class)
public class ReactivePathVariableTest {{

    describe("dado uma rota com path variable", ()-> {
        RouteManager.get("/users/{user_id}", ()-> Observable.just("rota mapeada"));

        context("quando executar a request", ()-> {
            it("deve fazer match com a rota mapeada", ()-> {
                String requestedUrl = "/users/1";

                RouteManager routeManager = RouteManager.getInstance();

                routeManager.executeRequest(requestedUrl).subscribe(res -> {
                    Assert.assertEquals("rota mapeada", res);
                });
            });
        });
    });

}}
