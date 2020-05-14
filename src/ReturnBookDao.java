import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookDao {
	public static int delete(String bookno,int userid){
		int status=0;
		try{
			Connection con=DB.getConnection();
			
			status=updatebook(bookno);//updating quantity and issue
			
			if(status>0){
			PreparedStatement ps=con.prepareStatement("delete from issuebooks where bookissueno=? and userid=?");
			ps.setString(1,bookno);
			ps.setInt(2,userid);
			status=ps.executeUpdate();
			}
			
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
	public static int updatebook(String bookno){
		int status=0;
		int quantity=0,issued=0;
		try{
			Connection con=DB.getConnection();
			
			PreparedStatement ps=con.prepareStatement("select quantity,issued from books where bookno=?");
			ps.setString(1,bookno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				quantity=rs.getInt("quantity");
				issued=rs.getInt("issued");
			}
			
			if(issued>0){
			PreparedStatement ps2=con.prepareStatement("update books set quantity=?,issued=? where bookno=?");
			ps2.setInt(1,quantity+1);
			ps2.setInt(2,issued-1);
			ps2.setString(3,bookno);
			
			status=ps2.executeUpdate();
			}
			con.close();
		}catch(Exception e){System.out.println(e);}
		return status;
	}
}
