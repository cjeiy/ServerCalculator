import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;


public class CalculatingClient extends JFrame implements Runnable{
	
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;
	
	private TextField tf;
	private TextField resultField;
	private TextField costField;
	
	public CalculatingClient(String host,int port){
		
		
		tf = new TextField();
		resultField = new TextField();
		costField = new TextField();
		
		
		//Set up the screen

		setLayout(new BorderLayout());
		add("North", tf);

		add("Center", resultField);
		add("South", costField);
		setSize(400,150);
		setVisible(true);
		//Ta emot medellanden när någon skriver och trycker på enter
		
		tf.addActionListener( new ActionListener() 
		{
			public void actionPerformed( ActionEvent e ) 
			{
			processMessage( e.getActionCommand() );
			
			}
			} );
		
		
		try {
			Socket socket = new Socket(host,port);
			
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			
			new Thread(this).start();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		
		
	
	}
	
	public void run(){
		
		String[] result = null;
		
		while(true){
			try {
				result = (String[]) inputStream.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			
			resultField.setText("Result: " + result[0]);
			costField.setText("Cost of calculation: " + result[1]);

		}
		
	}
	
	private void processMessage( String message ) {
	try {
	// Send it to the server
	outputStream.writeObject( message );
	// Clear out text input field
	tf.setText( "" );
	} catch( IOException ie ) { System.out.println( ie ); }
	}

	public static void main(String[] args) {
		CalculatingClient client = new CalculatingClient("127.0.0.1", 2057);
	}
}
