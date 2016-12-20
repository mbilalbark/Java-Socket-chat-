package chat_with_socket;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Font;


import javax.swing.JLabel;

public class chat_server extends JFrame {
   
	private JPanel contentPane;
	private static JTextField textField;
    private static JTextArea textArea;
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
	static JFrame parent =new JFrame();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chat_server frame = new chat_server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String msjin ="";
		
        try{
        	ss =new ServerSocket(1201);
        	s=ss.accept();
        	dis=new DataInputStream(s.getInputStream());
        	dos =new DataOutputStream(s.getOutputStream());
        	
        	
        	while(!msjin.equals("-cýk"))
        	{
        
        		msjin=dis.readUTF();
        		textArea.setText(textArea.getText().trim()+"\n Client: \n"+msjin);
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
	public chat_server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	    textArea = new JTextArea();
		textArea.setBounds(10, 11, 642, 157);
		contentPane.add(textArea);
		
		JButton Button = new JButton("Gönder");
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msjout="";
				try{
					 msjout=textField.getText().trim();
					 dos.writeUTF(msjout);
					 textArea.setText(textArea.getText().trim()+"\nServer: \n"+msjout);
					 textField.setText("");
				}catch(Exception e1){
					 JOptionPane.showMessageDialog(parent,
								"Mesaj gönderilemedi",
							    "Uyarý",
							    JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		Button.setBounds(573, 179, 89, 23);
		contentPane.add(Button);
		
		textField = new JTextField();
		textField.setBounds(20, 180, 543, 20);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
