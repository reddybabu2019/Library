import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IssueBookForm extends JFrame {
	static IssueBookForm frame;
	private JPanel contentPane;
	private JTextField txtFieldBookId;
	private JTextField txtFieldUserId;
	private JTextField txtFieldUserName;
	private JTextField txtFieldContact;
	public static String userName;
	private int noBooksTaken=0;
	private int userId=0;
	private String contact="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if(args.length >0)
			userName=args[0];
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new IssueBookForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IssueBookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Issue Book ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.GRAY);
		
		JLabel lblBookName = new JLabel("Book no:");
		
		txtFieldBookId = new JTextField();
		txtFieldBookId.setColumns(10);
		
		txtFieldUserId = new JTextField();
		txtFieldUserId.setColumns(10);
		
		txtFieldUserName = new JTextField();
		txtFieldUserName.setColumns(10);
		
		txtFieldContact = new JTextField();
		txtFieldContact.setColumns(10);
		
		JLabel lblUserId = new JLabel("User Id:");
		
		JLabel lblUserName = new JLabel("User Name:");
		
		JLabel lblUserContact = new JLabel("User Contact:");
		
		JButton btnIssueBook = new JButton("Take Book");
		
		
		
		try{
			Connection con=DB.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from Users where name=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ps.setString(1,userName);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				userId = rs.getInt("id");
				contact = rs.getString("contact");
				txtFieldUserId.setText(String.valueOf(userId));
				txtFieldUserName.setText(userName);
				txtFieldContact.setText(contact);
														
			}
			
			
			ps=con.prepareStatement("select count(*) from issuebooks where username=?");
			ps.setString(1, userName);
			rs=ps.executeQuery();
			if(rs.next())
				noBooksTaken = rs.getInt(1);
			//System.out.println(noBooksTaken);
			con.close();
		}catch(Exception e1){System.out.println(e1);}
		
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			
			
			String bookno=txtFieldBookId.getText();
			//System.out.println(userName);
			
			txtFieldUserId.invalidate();
			txtFieldUserName.validate();
			txtFieldContact.repaint();
			
			if(noBooksTaken < 2 && IssueBookDao.checkBook(bookno)){
			
			int i=IssueBookDao.save(bookno, userId, userName, contact);
			if(i>0){
				JOptionPane.showMessageDialog(IssueBookForm.this,"Book issued successfully!");
				UserSuccess.main(new String[]{});
				frame.dispose();
				
			}else{
				JOptionPane.showMessageDialog(IssueBookForm.this,"Sorry, unable to issue: no copies found for issue!");
			}//end of save if-else
			
			}else{
				if(noBooksTaken == 2) {
					JOptionPane.showMessageDialog(IssueBookForm.this,"Sorry, you have already taken 2 books");
				}
				else {
					JOptionPane.showMessageDialog(IssueBookForm.this,"Sorry ! book doesn't exist");
				}
			}//end of checkbook if-else
			
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSuccess.main(new String[]{});
				frame.dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Note: Please check User ID Carefully before issuing book!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.RED);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBookName)
								.addComponent(lblUserId)
								.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUserContact, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFieldUserId, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldBookId, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldUserName, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtFieldContact, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE))
							.addGap(48))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
									.addGap(47)
									.addComponent(btnBack)))
							.addGap(100))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(146)
					.addComponent(lblNewLabel)
					.addContainerGap(235, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(lblNewLabel)
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookName)
						.addComponent(txtFieldBookId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserId)
						.addComponent(txtFieldUserId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtFieldUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserContact)
						.addComponent(txtFieldContact, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIssueBook, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack))
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(25))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
