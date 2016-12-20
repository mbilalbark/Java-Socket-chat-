package chat_with_socket;


import java.awt.Component;
import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class chat_client extends JFrame {

	private JPanel contentPane;
	private static JTextField textField;
    
    
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
    private static JTextArea textArea;
    static JFrame parent = new JFrame();
    
    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_client frame = new chat_client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		
		});
		
		String msjin="";
		
	    
		
		try{
			s=new Socket("127.0.0.1",1201);
			dis =new DataInputStream(s.getInputStream());
			dos =new DataOutputStream(s.getOutputStream());
			 
			while(!msjin.equals("-cýk")){
				
            	 msjin=dis.readUTF();
            	 textArea.setText(textArea.getText().trim()+"\n Server: \n"+msjin);
            	
            }
			
			
		}catch(Exception e){
			
		  JOptionPane.showMessageDialog(parent,
					"Baðlantý kurulmadý veya koptu!",
				    "Uyarý",
				    JOptionPane.WARNING_MESSAGE);
			
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public chat_client() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    
		textArea = new JTextArea();
		textArea.setBounds(10, 11, 642, 157);
		contentPane.add(textArea);
		 
		textField = new JTextField();
		textField.setBounds(20, 180, 543, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	   
		
		JButton btnNewButton = new JButton("Gönder");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msjout="";
				try{
					 msjout=textField.getText().trim();
					 dos.writeUTF(msjout);
					 textArea.setText(textArea.getText().trim()+"\n Client: \n"+msjout);
					 textField.setText("");
				}catch(Exception e1){
					 JOptionPane.showMessageDialog(parent,
								"Mesaj gönderilemedi",
							    "Uyarý",
							    JOptionPane.WARNING_MESSAGE);
						
				}
				
			}
		});
		btnNewButton.setBounds(573, 179, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}
}
