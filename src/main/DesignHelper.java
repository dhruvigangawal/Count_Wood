import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DesignHelper {

    private static Statement stmt = null;

    DesignHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    public void insertDesign(String Dname,String Description)
    {
        try {
            String query = "INSERT INTO DESIGN(DNAME,DESCRIPTION) VALUES (\""+Dname+"\",\""+Description+"\");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //INSERT INTO DESIGN VALUES (DNAME,DESCRIPTION);
    //SELECT DESIGNID FROM DESIGN WHERE DNAME=’GIVEN VALUE’;
    public List<String> getAllDesign(){

        String query = "select DNAME from DESIGN ORDER BY DNAME;";
        List<String> allDesign= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allDesign.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allDesign;
    }

    public List<String> getAllDesignDescription(){

        String query = "select DESCRIPTION from DESIGN ORDER BY DNAME;";
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

    public int getDesignid(String name){

        String query = "SELECT DESIGNID FROM DESIGN WHERE DNAME=\""+name+"\";";
        List<Integer> Designid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Designid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Designid.get(0);
    }

    public String getDesignName(int id){

        String query = "SELECT DNAME FROM DESIGN WHERE DESIGNID="+id+";";
        List<String> Designname= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Designname.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Designname.toString();
    }

    public String getDesignDescription(int id){

        String query = "SELECT DESCRIPTION FROM DESIGN WHERE DESIGNID="+id+";";
        List<String> Designname= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Designname.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Designname.toString();
    }

    public void updateDesignName(String Name, int id)
    {
        try {
            String query = "UPDATE DESIGN\n" +
                    "    SET DNAME = \""+Name+"\"\n" +
                    "    WHERE DESIGNID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateDesignDescription(String Description, int id)
    {
        try {
            String query = "UPDATE DESIGN\n" +
                    "    SET DESCRIPTION = \""+Description+"\"\n" +
                    "    WHERE DESIGNID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void deleteDesign(int id) {
        try {
            String query = "DELETE FROM DESIGN\n" +
                    "WHERE DESIGNID = "+id+" AND DESIGNID NOT IN(SELECT DESIGNID\n" +
                    "FROM PRODUCT\n" +
                    "WHERE DESIGNID="+id+");\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args){
        DesignHelper helper = new DesignHelper();
        //helper.insertDesign("PHF-001","Has a good wood");
        //System.out.println(helper.getDesignid("PD-01"));
        //System.out.println(helper.getDesignName(1));
        //helper.updateDesignName("PD-001",1);
        //helper.updateDesignDescription("With Upma cut",1);
        helper.deleteDesign(29);
    }
}
