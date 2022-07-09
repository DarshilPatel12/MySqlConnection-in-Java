package mysqlconnection;
import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySqlConnection {
    public static void main(String[] args) {
        Connection cn;
        Statement st;
        PreparedStatement pst;
        ResultSet rs;
        BufferedReader br;
        String nm;
        double pr;
        int ch,no;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/ProductMgt","root","");
//ProductMgt is Database Name, Root is Username and "" is Password
            br=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("1. Insert\n2. Update \n3. Delete \n4. View \n5. Search \nEnter Your Choice : ");
            ch=Integer.parseInt(br.readLine());
            switch(ch){
//Insert the Data into Database : 
                case 1:
                    st=cn.createStatement();
                    System.out.println("Enter Product Name :");
                    nm=br.readLine();
                    System.out.println("Enter Product Price : ");
                    pr=Long.parseLong(br.readLine());
                    st.execute("insert into Product(name,price) values('"+nm+"',"+pr+")");
                    st.close();
                    break;

//Update the Data into Database : 
                case 2:
                    System.out.println("Enter Product ID which you want to Update : ");
                    no=Integer.parseInt(br.readLine());
                    System.out.println("Enter Product Name : ");
                    nm=br.readLine();
                    System.out.println("Enter Product Price : ");
                    pr=Long.parseLong(br.readLine());
                    pst=cn.prepareStatement("update Product set name=?, price=? where pid=?");
                    pst.setString(1, nm);
                    pst.setDouble(2, pr);
                    pst.setInt(3, no);
                    pst.execute();
                    pst.close();
                    break;

//Delete the Data into Database : 
                case 3:
                    st=cn.createStatement();
                    System.out.println("Enter Product ID which you want to Delete : ");
                    no=Integer.parseInt(br.readLine());
                    st.executeUpdate("delete from Product where pid="+no);
                    st.close();
                    System.out.println("Record Deleted");
                    break;

//View the Data into Database : 
                case 4:
                    st=cn.createStatement();
                    rs=st.executeQuery("select * from Product");
                    while (rs.next()) {
                        System.out.println("Product ID : "+rs.getInt(1));
                        System.out.println("Product Name : "+rs.getString(2));
                        System.out.println("Product Price : "+rs.getDouble(3));
                        System.out.println("------------------------------------------------------------------------");
                    }
                    rs.close();
                    st.close();
                    break;
case 5:
		    st=cn.createStatement();
                    System.out.println("Enter Product ID which you want to Search : ");
                    no=Integer.parseInt(br.readLine());
                    rs=st.executeQuery("select * from Product where pid="+no);
                     while (rs.next()) {
                        System.out.println("Product ID : "+rs.getInt(1));
                        System.out.println("Product Name : "+rs.getString(2));
                        System.out.println("Product Price : "+rs.getDouble(3));
                    }
                    rs.close();
                    st.close();
                    st.close();
                    break;
                default:
                    System.out.println("Invalid Choice");
            }        
            cn.close();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
}
