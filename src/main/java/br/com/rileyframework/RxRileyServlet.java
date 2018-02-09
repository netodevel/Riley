/**
 * Copyright 2013 Jitendra Kotamraju.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.rileyframework;

import br.com.rileyframework.reactive.ObservableServlet;
import rx.Observable;
import rx.Observer;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.List;

/**
 *
 * @author Jitendra Kotamraju, NetoDevel
 */
@WebServlet(value="/", asyncSupported = true)
public class RxRileyServlet extends HttpServlet {

    private Riley riley;
    private List<Route> listRoutes;
    private HttpHelper httpHelper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        riley = new Riley();
        httpHelper = new HttpHelper();

        try {
            listRoutes = riley.registerControllers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ac = req.startAsync();
        ServletInputStream in = req.getInputStream();
        System.out.println(req.getServletPath());
        ObservableServlet.create(in).subscribe(new ReadObserver(req, resp, ac, riley).setServletPath(req.getServletPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext ac = req.startAsync();
        ServletInputStream in = req.getInputStream();
        ObservableServlet.create(in).subscribe(new ReadObserver(req, resp, ac, riley));
    }

    static class ReadObserver implements Observer<ByteBuffer> {

        private HttpServletResponse resp;
        private HttpServletRequest req;
        private final AsyncContext ac;
        private final Riley riley;
        private final HttpHelper httpHelper;
        private String path;

        public ReadObserver(HttpServletRequest req, HttpServletResponse resp, AsyncContext ac, Riley riley) {
            this.req = req;
            this.resp = resp;
            this.ac = ac;
            this.riley = riley;
            this.httpHelper = new HttpHelper();
        }

        @Override
        public void onCompleted() {
            Observable<Route> routersObservable = null;
            try {
                routersObservable = Observable.from(riley.registerControllers());
                routersObservable.filter( r -> { return r.getHttpMethod().equals("GET"); })
                        .filter( r -> { return httpHelper.matchUrl(r.getRouteRegex(), path); })
                        .forEach( route -> { delegateRequest(route); });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void delegateRequest(Route route) {
            Request request = null;
            request = httpHelper.buildRequest(path, route, null);

            Response response = null;
            try {
                response = httpHelper.buildResponse(resp);
                if (route.getHttpMethod().equals(req.getMethod())) {
                    Response responseCallback = route.getHandler().handler(request, response);
                    resp.setContentType("application/json");
                    resp.setStatus(responseCallback.getCode());
                    ac.complete();
                    System.out.println("Read onCompleted=" + Thread.currentThread());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("read onError=" + Thread.currentThread());
            e.printStackTrace();
        }

        @Override
        public void onNext(ByteBuffer buf) {
        }

        Observable<ByteBuffer> data() {
            ByteBuffer[] data = new ByteBuffer[1000000];
            for(int i=0; i < data.length; i++) {
                data[i] = ByteBuffer.wrap((i+"0000000000000\n").getBytes());
            }
            return Observable.from(data);
        }

        public ReadObserver setServletPath(String path) {
            this.path = path;
            return this;
        }
    }

    static class WriteObserver implements Observer<Void> {
        private final AsyncContext ac;

        public WriteObserver(AsyncContext ac) {
            this.ac = ac;
        }

        @Override
        public void onCompleted() {
            System.out.println("Composite Write onCompleted");
            ac.complete();
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("write onError=" + Thread.currentThread());
            e.printStackTrace();
        }

        @Override
        public void onNext(Void args) {
        }
    }

}