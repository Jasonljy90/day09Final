package day09;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpClientConnection implements Runnable {

	private final Socket socket;
	private String[] docRoot;

	public HttpClientConnection(Socket socket, String[] docRoot) {
		this.socket = socket;
		this.docRoot = docRoot;
	}

	@Override
	public void run() {

		try {
			//System.out.println("Here in step 3");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();

			String httpHeader = br.readLine();
			System.out.printf(">>> line: %s\n", httpHeader);
			String[] headerParts = httpHeader.split(" ");
			String httpMethod = headerParts[0];
			String httpPath = headerParts[1];
			if(httpMethod.equals("GET")) {
				//System.out.println("get method");
				get(httpPath, os);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try { socket.close(); } catch (Exception ex) { }
		}
	}

	private void get(String path, OutputStream os) throws IOException {
        for (String folder : docRoot) {
			System.out.println("inside for");
            Path filePath = Paths.get(folder, path);
            if (Files.exists(filePath)) { //this returns false
				System.out.println("inside if");
                // serve the file
				Files.copy(filePath, os);
            }
        }
    }
}
