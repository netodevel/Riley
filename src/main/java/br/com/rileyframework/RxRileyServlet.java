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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        resp.setStatus(200);
//        PrintWriter pw = resp.getWriter();
//        pw.write("Working ...");
//        pw.flush();

        try {
             doProcess(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {

        AsyncContext ac = req.startAsync();
        ServletInputStream in = req.getInputStream();
        ObservableServlet.create(in).subscribe(new ReadObserver(resp, ac));

        //ObservableServlet.create(in).finallyDo(new WriteObserver(ac)).subscribe(new ReadObserver(resp, ac));
    }


    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        final String servletPath = req.getServletPath();
        for (Route route : listRoutes) {
            if (httpHelper.matchUrl(route.getRouteRegex(), servletPath)) {
                Request request = null;

                switch (route.getHttpMethod()) {
                    case "GET":
                        request = httpHelper.buildRequest(servletPath, route, null);
                        break;
                    case "POST":
                        request = httpHelper.buildRequest(servletPath, route, httpHelper.getBodyRequest(req));
                        break;
                    case "PUT":
                        request = httpHelper.buildRequest(servletPath, route, httpHelper.getBodyRequest(req));
                        break;
                    case "DELETE":
                        request = httpHelper.buildRequest(servletPath, route, null);
                        break;
                    default:
                        resp.setStatus(404);
                        break;
                }

                Response response = httpHelper.buildResponse(resp);

                if (route.getHttpMethod().equals(req.getMethod())) {
                    Response responseCallback = route.getHandler().handler(request, response);
                    resp.setContentType("application/json");
                    resp.setStatus(responseCallback.getCode());
                }
            }
        }
    }


    static class ReadObserver implements Observer<ByteBuffer> {
        private final HttpServletResponse resp;
        private final AsyncContext ac;

        ReadObserver(HttpServletResponse resp, AsyncContext ac) {
            this.resp = resp;
            this.ac = ac;
        }

        @Override
        public void onCompleted() {
            System.out.println("Read onCompleted=" + Thread.currentThread());
            resp.setStatus(HttpServletResponse.SC_OK);

            Observable<ByteBuffer> data = data();
            ServletOutputStream out = null;
            try {
                out = resp.getOutputStream();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            Observable<Void> writeStatus = ObservableServlet.write(data, out);
            writeStatus.subscribe(new WriteObserver(ac));
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("read onError=" + Thread.currentThread());
            e.printStackTrace();
        }

        @Override
        public void onNext(ByteBuffer buf) {
            //Thread.dumpStack();
            //System.out.println("read onNext=" + Thread.currentThread());
        }

        Observable<ByteBuffer> data() {
            ByteBuffer[] data = new ByteBuffer[1000000];
            for(int i=0; i < data.length; i++) {
                data[i] = ByteBuffer.wrap((i+"0000000000000\n").getBytes());
            }
            return Observable.from(data);
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
            //System.out.println("Composite Write onNext");
            // no-op
        }
    }

}