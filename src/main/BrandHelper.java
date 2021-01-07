import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandHelper {
    private static Statement stmt = null;

    BrandHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    //INSERT INTO BRAND VALUES (BNAME,DESCRIPTION);
    public void insertBrand(String Bname,String Description)
    {
        try {
            String query = "INSERT INTO BRAND(BNAME,DESCRIPTION) VALUES (\""+Bname+"\",\""+Description+"\");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllBrand(){

        String query = "select BNAME from BRAND ORDER BY BNAME;";
        List<String> allBrand= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allBrand.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allBrand;
    }

    public List<String> getAllBrandDescription(){

        String query = "select DESCRIPTION from BRAND ORDER BY BNAME;";
        List<String> allDesc= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allDesc.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allDesc;
    }

    public int getBrandid(String name){

        String query = "SELECT BRANDID FROM BRAND WHERE BNAME=\""+name+"\";";
        List<Integer> Brandid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Brandid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Brandid.get(0);
    }

    public String getBrandName(int id){

        String query = "SELECT BNAME FROM BRAND WHERE BRANDID="+id+";";
        List<String> Brandname= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Brandname.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Brandname.toString();
    }

    public String getBrandDescription(int id){

        String query = "SELECT DESCRIPTION FROM BRAND WHERE BRANDID="+id+";";
        List<String> desc= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                desc.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return desc.toString();
    }

    public void updateBrandName(String Name, int id)
    {
        try {
            String query = "UPDATE BRAND\n" +
                    "    SET BNAME = \""+Name+"\"\n" +
                    "    WHERE BRANDID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateBrandDescription(String Description, int id)
    {
        try {
            String query = "UPDATE BRAND\n" +
                    "    SET DESCRIPTION = \""+Description+"\"\n" +
                    "    WHERE BRANDID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBrand(int id) {
        try {
            String query = "DELETE FROM BRAND\n" +
                    "WHERE BRANDID="+id+" AND BRANDID NOT IN(SELECT BRANDID\n" +
                    "FROM PRODUCT\n" +
                    "WHERE BRANDID="+id+");\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args){
        BrandHelper helper = new BrandHelper();
        System.out.println(helper.getBrandDescription(4));
    }

}
