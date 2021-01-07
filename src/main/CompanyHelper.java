import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyHelper {
    private static Statement stmt = null;

    CompanyHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    public void insertCompany(String Name,String gstno, String password)
    {
        try {
            String query = "INSERT INTO COMPANY(GSTNO,CONAME,PASSWORD) VALUES(\""+ gstno +"\",\""+ Name +"\",\""+ password +"\");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int getCompanyid(String Name)
    {
        String query = "SELECT COMPANY_ID FROM COMPANY WHERE CONAME=\""+Name+"\";";
        List<Integer> companyid = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                companyid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companyid.get(0);
    }





    public String getCompanyname(int id)
    {
        String query = "SELECT CONAME FROM COMPANY WHERE COMPANY_ID="+id+";";
        List<String> companyname = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                companyname.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return companyname.toString();
    }

    public void updateCompanyPassword(String password, int id)
    {
        try {
            String query = "UPDATE COMPANY SET PASSWORD = \""+password+"\" WHERE COMPANY_ID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllCompany(){

        String query = "Select CONAME from COMPANY ORDER BY CONAME;";
        List<String> allCompany = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allCompany.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allCompany;
    }

    public List<String> getAllCoGstno(){

        String query = "Select GSTNO from COMPANY ORDER BY CONAME;";
        List<String> allgstno = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allgstno.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allgstno;
    }

    public void updateCompanyName(String Name, int id)
    {
        try {
            String query = "UPDATE COMPANY SET CONAME = \""+Name+"\" WHERE COMPANY_ID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCompanygst(String gst, int id)
    {
        try {
            String query = "UPDATE COMPANY SET GSTNO = \""+gst+"\" WHERE COMPANY_ID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteCompany(int id)
    {
        try {
            String query = "DELETE FROM COMPANY\n" +
                    "WHERE COMPANY_ID= "+id+"\n" +
                    "AND COMPANY_ID NOT IN ( (SELECT COMPANY_ID FROM CUSTOMER WHERE COMPANY_ID = "+id+")\n" +
                    "UNION \n" +
                    "(SELECT COMPANY_ID FROM VENDOR WHERE COMPANY_ID = "+id+")\n" +
                    "UNION\n" +
                    "(SELECT COMPANY_ID FROM TRANSACTION WHERE COMPANY_ID = "+id+")\n" +
                    ");\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args){
        CompanyHelper helper = new CompanyHelper();
        System.out.println(helper.getAllCompany());
        System.out.println(helper.getAllCoGstno());
        //helper.DeleteCompany(2);
    }
}
