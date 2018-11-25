package br.com.riley.router;

import com.greghaskins.spectrum.Spectrum;
import io.reactivex.Observable;
import org.junit.runner.RunWith;

import java.util.List;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Spectrum.class)
public class ReactiveRouterTest {{

    describe("dado uma rota reativa", () -> {
        Route routeReact = Route.builder()
                .method(HttpConsts.METHOD_GET)
                .path("/index")
                .reactiveRouteAction(() -> Observable.just("hello world"))
                .build();

        List<Route> routes = asList(routeReact);

        it("deve retornar hello world", ()-> {
            routes.get(0).getReactiveRouteAction()
                    .execute()
                    .subscribe(res -> { assertEquals("hello world", res); });
        });
    });

}}
