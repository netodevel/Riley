package br.com.riley.router;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Spectrum.class)
public class RegexHelperTest {{

    describe("dado uma rota", ()-> {
        String url = "/users/{user_id}";

        it("deve converter a rota para regex", ()-> {
            String regex = RegexHelper.generatorRegexFromUrl(url);
            assertEquals("/users/\\w*", regex);
        });
    });

    describe("dado uma rota", ()-> {
        String url = "/users/{user_id}";

        context("quando feita uma request", ()-> {
            String requestedUrl = "/users/1";
            it("deve fazer o match da rota", ()-> {
                String regex = RegexHelper.generatorRegexFromUrl(url);
                Boolean match = RegexHelper.matchUrl(regex, requestedUrl);
                assertTrue(match);
            });
        });
    });

    describe("dado uma rota com duas variaveis", ()-> {
        String url = "/users/{user_id}/friend/{friend_id}";

        context("quando feita uma request", ()-> {
            String requestedUrl = "/users/1/friend/1";

            it("deve fazer o match da rota", ()-> {
                String regex = RegexHelper.generatorRegexFromUrl(url);
                Boolean match = RegexHelper.matchUrl(regex, requestedUrl);
                assertTrue(match);
            });
        });
    });

    describe("dado uma rota sem variaveis", ()-> {
        String url = "/users";

        context("quando feita uma request", ()-> {
            String requestedUrl = "/users";

            it("deve fazer o match da rota", ()-> {
                String regex = RegexHelper.generatorRegexFromUrl(url);
                Boolean match = RegexHelper.matchUrl(regex, requestedUrl);
                assertTrue(match);
            });
        });
    });

    describe("dado uma rota raiz", ()-> {
        String url = "/";

        context("quando feita uma request", ()-> {
            String requestedUrl = "/";

            it("deve fazer o match da rota", ()-> {
                String regex = RegexHelper.generatorRegexFromUrl(url);
                Boolean match = RegexHelper.matchUrl(regex, requestedUrl);
                assertTrue(match);
            });
        });
    });

}}
