import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class CalculatingServer {
	
	private ServerSocket serverSocket;
	ObjectOutputStream outputStream;
	
	public CalculatingServer(int port) throws IOException{
		
		listenTo(port);
	}
	
	public static void main(String[] args) throws IOException {
		int port = 2057;
		new CalculatingServer(port);
	}
	
	
	
	private void listenTo(int port) throws IOException{
		
		serverSocket = new ServerSocket(port);
		
		System.out.println("The serve is listening on" + serverSocket);
		
		while(true){
			Socket connection = serverSocket.accept();
			outputStream = new ObjectOutputStream(connection.getOutputStream());
			new ServerThread(this,connection, outputStream);
		}
	}
	
}


