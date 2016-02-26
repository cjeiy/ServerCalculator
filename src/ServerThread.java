import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ServerThread extends Thread {
	
	CalculatingServer server;
	Socket socket;
	ObjectOutputStream outputStream;
	Model model;
	String mathExpression;
	String[] result;
	
	public ServerThread(CalculatingServer server, Socket socket, ObjectOutputStream outputStream){
		
		model = new Model();
		this.server =server;
		this.socket =socket;
		this.outputStream = outputStream;
		
		start();
	}

	public void run(){
		
		try{
			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
			
			while(true){

				mathExpression = (String) inputStream.readObject();
				result = model.calculate(mathExpression);
				sendToClient(result);
				
				
				
				
			}
		}catch(IOException ioException){
			ioException.printStackTrace();
		}catch(ClassNotFoundException classNotFoundException0){
			classNotFoundException0.printStackTrace();
		}
		
	}
	
	
	private void sendToClient(String[] result){
		
		try {
			outputStream.writeObject(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
