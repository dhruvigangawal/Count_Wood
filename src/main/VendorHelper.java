import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class VendorHelper {

    private static Statement stmt = null;

    VendorHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }


    public void insertVendor(String coname,String Name, String gstno,String Address,String Phoneno)
    {
        CompanyHelper help = new CompanyHelper();
        int cid = help.getCompanyid(coname);
        try {
            String query = "INSERT INTO VENDOR(VNAME,GSTNO,ADDRESS,PHONENO,BALANCE,COMPANYID) VALUES(\""+Name+"\",\""+gstno+"\",\""+Address+"\",\""+Phoneno+"\",0,"+cid+")";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllVendor(){

        String query = "select VNAME from VENDOR ORDER BY vname;";
        List<String> allVendor= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allVendor.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allVendor;
    }

    public int getVendorid(String name){

        String query = "SELECT VENDOR_ID FROM VENDOR WHERE VNAME = \""+name+"\";";
        List<Integer> Vendorid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Vendorid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Vendorid.get(0);
    }

    public String getVendorName(int id){

        String query = "select VNAME from VENDOR WHERE VENDOR_ID="+id+";";
        List<String> Vendorname= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Vendorname.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Vendorname.toString();
    }

    public void updateVendorAddress(String Address, int id)
    {
        try {
            String query = "UPDATE VENDOR SET ADDRESS = \""+Address+"\" WHERE VENDOR_ID="+id+"";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateVendorPhoneno(String Phoneno, int id) {
        try {
            String query = "UPDATE VENDOR \n" +
                    "SET PHONENO =\"" + Phoneno + "\"\n" +
                    "WHERE VENDOR_ID=" + id + ";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateVendorNmae(String Name, int id) {
        try {
            String query = "UPDATE VENDOR \n" +
                    "SET VNAME =\"" + Name + "\"\n" +
                    "WHERE VENDOR_ID=" + id + ";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteVendor(int id) {
        try {
            String query = "DELETE FROM VENDOR WHERE VENDOR_ID="+id+" AND VENDOR_ID NOT IN(SELECT CLIENTID FROM TRANSACTION WHERE TRANSACTIONTYPE=0);";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args){
        VendorHelper helper = new VendorHelper();
        //System.out.println(helper.getVendorName(1));
        //helper.insertVendor("Bhavani Trading Company","Ruhi","123rty456ghj78","Rajarajeswarinagar,Bangalore","1123456759");
        //helper.updateVendorPhoneno("1223456789",1);
        //helper.updateVendorNmae("Ruhi",1);
        //helper.deleteVendor(2);
    }
}
