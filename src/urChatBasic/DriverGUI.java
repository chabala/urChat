package urChatBasic;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class DriverGUI
{
	public static Connection chatSession = null;
	public static UserGUI gui = null;
	
	public static void main(String[] args) throws IOException{
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    //TODO something meaningful
		}
		
		DriverGUI driver = new DriverGUI();	
		driver.startGUI();
	}
	
	public void startGUI(){
		gui = new UserGUI();
		new Thread(gui).start();
		
		
		JFrame frame = new JFrame ("urChat: Last Updated 07 DEC 14");
		
		
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(gui);
		frame.pack();
		frame.setVisible(true); 
		frame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
							try {
								if(!gui.isCreatedServersEmpty())
									Connection.sendClientText("/quit Goodbye cruel world", "Server");
								
							} catch (IOException x) {
								// TODO Auto-generated catch block
								x.printStackTrace();
							}
				  }
				});
					

		
	}

	public static void startConnection(){
		chatSession =  new Connection();
		
	}
}
