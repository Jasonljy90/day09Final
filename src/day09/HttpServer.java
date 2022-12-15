package day09;

import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private final Integer port;
	private String[] docRoot;

	public HttpServer(Integer port, String[] docRoot) {
		this.port = port;
		this.docRoot = docRoot;
	}

	public void start() throws Exception {

		ServerSocket server = new ServerSocket(port);
		
		while (true) {
			//System.out.println("Here in step 2");
			final Socket socket = server.accept();
			//System.out.println("After server accept");
			HttpClientConnection connection = new HttpClientConnection(socket, docRoot);
			connection.run();
		} 
	}
}
