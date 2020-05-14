import java.sql.*;
public class IssueBookDao {
	
public static boolean checkBook(String bookno){
	boolean status=false;
	
	try{
		int quantity=0;
		int issued=0;
		Connection con=DB.getConnection();
		PreparedStatement ps=con.prepareStatement("select quantity,issued from books where bookno=?");
		ps.setString(1,bookno);
	    ResultSet rs=ps.executeQuery();
		status=rs.next();
		if(rs.next()) {
			quantity=rs.getInt("quantity");
			issued=rs.getInt("issued");
			if(quantity > issued) {
				status = true;
			}
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}

public static int save(String bookissueno,int userid,String username,String usercontact){
	int status=0;
	try{
		Connection con=DB.getConnection();
		
		status=updatebook(bookissueno);//updating quantity and issue
		
		if(status>0){
		PreparedStatement ps=con.prepareStatement("insert into issuebooks(bookissueno,userid,username,usercontact) values(?,?,?,?)");
		ps.setString(1,bookissueno);
		ps.setInt(2,userid);
		ps.setString(3,username);
		ps.setString(4,usercontact);
		status=ps.executeUpdate();
		}
		
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
public static int updatebook(String bookissueno){
	int status=0;
	int quantity=0,issued=0;
	try{
		Connection con=DB.getConnection();
		
		PreparedStatement ps=con.prepareStatement("select quantity,issued from books where bookno=?");
		ps.setString(1,bookissueno);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			quantity=rs.getInt("quantity");
			issued=rs.getInt("issued");
		}
		
		if(quantity>0 && quantity > issued){
		PreparedStatement ps2=con.prepareStatement("update books set quantity=?,issued=? where bookno=?");
		ps2.setInt(1,quantity-1);
		ps2.setInt(2,issued+1);
		ps2.setString(3,bookissueno);
		
		status=ps2.executeUpdate();
		}
		con.close();
	}catch(Exception e){System.out.println(e);}
	return status;
}
}
