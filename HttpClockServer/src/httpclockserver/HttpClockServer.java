package httpclockserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HttpClockServer {
    
    private int port;
    private HttpServer server;
    
    public HttpClockServer() throws IOException{
        this(80);
    }
    
    public HttpClockServer(int port) throws IOException{
        this.port = port;
        
        server = HttpServer.create(new InetSocketAddress(port), 0);
        
        server.createContext("/", new HourHandler());
    }
    
    
    public void start(){
        server.start();
    }
    
    private static class HourHandler implements HttpHandler {

        public HourHandler() {
            
        }

        @Override
        public void handle(HttpExchange he) throws IOException {
            String content = he.getRequestHeaders().getFirst("Content-Type");
            
            he.sendResponseHeaders(200, content.length());
            OutputStream os = he.getResponseBody();
            os.write(content.getBytes());
            os.close();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HttpClockServer httpClockServer = new HttpClockServer(80);
            httpClockServer.start();
        } catch (IOException ex) {
            Logger.getLogger(HttpClockServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
