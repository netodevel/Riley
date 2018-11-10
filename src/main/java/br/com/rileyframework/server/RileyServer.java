package br.com.rileyframework.server;

import br.com.rileyframework.servers.ServerFactory;
import br.com.rileyframework.servers.Servers;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RileyServer {

    private ServerFactory serverFactory;

    @Getter
    private String server;

    public RileyServer(String server) {
        this.server = server;
    }

    public void startup(String typeServer) {
        try {
            this.serverFactory.create(typeServer);
        } catch (Exception e) {
            System.out.println("[ERROR] ==> location: RileyServer.startup(), error: " + e.getMessage());
            throw new RileyServerException(e.getMessage());
        }
    }

    public void setServerFactory(ServerFactory serverFactory) {
        this.serverFactory = serverFactory;
    }

    public void shutDown() throws Exception {
        this.serverFactory.shutdownServer(this.server != null ? this.server : Servers.JETTY);
    }

}
