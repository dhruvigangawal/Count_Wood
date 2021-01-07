import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class CustomerHelper {
    private static Statement stmt = null;

    CustomerHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    public void insertCustomer(String coname,String Name, String gstno,String Address,String Phoneno)
    {
        CompanyHelper help = new CompanyHelper();
        int cid = help.getCompanyid(coname);
        try {

            String query = "INSERT INTO CUSTOMER(NAME,GSTNO,ADDRESS,PHONENO,BALANCE,COMPANY_ID) VALUES(\""+Name+"\",\""+gstno+"\",\""+Address+"\","+Phoneno+",0,"+cid+")";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllCustomer(){

        String query = "select NAME from CUSTOMER ORDER BY name;";
        List<String> allCustomer= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allCustomer.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCustomer;
    }

    public int getCustomerid(String name){

        String query = "SELECT CUSTOMER_ID FROM CUSTOMER WHERE NAME = \""+name+"\";";
        List<Integer> Customerid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Customerid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Customerid.get(0);
    }

    //SELECT NAME FROM CUSTOMER WHERE CUSTOMER_ID= ‘GIVEN VALUE’;

    public String getCustomerName(int id){

        String query = "select NAME from CUSTOMER WHERE CUSTOMER_ID="+id+";";
        List<String> Customername= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Customername.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Customername.toString();
    }

    public void updateCustomerAddress(String Address, int id)
    {
        try {
            String query = "UPDATE CUSTOMER \n" +
                    "SET ADDRESS =\""+Address+"\"\n" +
                    "WHERE CUSTOMER_ID="+id+";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //UPDATE CUSTOMER
    //SET PHONENO = ‘GIVEN VALUE’
    //WHERE CUSTOMER_ID= ‘GIVEN VALUE’;
    public void updateCustomerPhoneno(String Phoneno, int id) {
        try {
            String query = "UPDATE CUSTOMER \n" +
                    "SET PHONENO =\"" + Phoneno + "\"\n" +
                    "WHERE CUSTOMER_ID=" + id + ";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCustomer(int id) {
        try {
            String query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID="+id+" AND CUSTOMER_ID NOT IN(SELECT CLIENTID FROM TRANSACTION WHERE TRANSACTIONTYPE=1);";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args){
        CustomerHelper helper = new CustomerHelper();
        //System.out.println();
        helper.deleteCustomer(2);
    }
}
