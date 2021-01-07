import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnetor {

    private static Connection con;
    private static Statement stmt;

    JDBCConnetor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tes1", "root", "27548");
            stmt = con.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Statement getConnection(){
        return stmt;
    }

    public String returnPassword(String UserName) {
        String password = "";
        try {
            ResultSet rs = stmt.executeQuery("select GSTNo from Company where name = '" + UserName + "' limit 1;");
            while (rs.next())
                password = rs.getString(1);
        } catch (Exception e) {
            //System.out.println(e);
        }
        return password;

    }

    public static void main(String args[]){
        JDBCConnetor jdbcConnetor = new JDBCConnetor();
        //System.out.println(jdbcConnetor.returnPassword("User1"));
    }
}
