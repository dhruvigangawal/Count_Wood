import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class SizeHelper {
    private static Statement stmt = null;

    SizeHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    //INSERT INTO SIZE VALUES(MEASUREMENT);
    public void insertSize(String Measurement)
    {
        try {
            String query = "INSERT INTO SIZE(MEASUREMENT) VALUES (\""+Measurement+"\");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllSize(){

        String query = "select MEASUREMENT from SIZE ORDER BY MEASUREMENT;";
        List<String> allSize= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allSize.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allSize;
    }

    public String getSizeName(int id){

        String query = "SELECT MEASUREMENT FROM SIZE WHERE SIZEID="+id+";";
        List<String> Size= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Size.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Size.toString();
    }

    public int getSizeid(String name){

        String query = "SELECT SIZEID FROM SIZE WHERE MEASUREMENT=\""+name+"\";";
        List<Integer> Sizeid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Sizeid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Sizeid.get(0);
    }

    public void updateSize(String Size, int id)
    {
        try {
            String query = "UPDATE SIZE\n" +
                    "    SET MEASUREMENT = \""+Size+"\"\n" +
                    "    WHERE SIZEID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteSize(int id) {
        try {
            String query = "DELETE FROM SIZE\n" +
                    "WHERE SIZEID="+id+" AND SIZEID NOT IN(SELECT SIZEID\n" +
                    "FROM TYPE\n" +
                    "WHERE SIZEID="+id+");\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args){
        SizeHelper helper = new SizeHelper();
        //helper.deleteSize(12);
        System.out.println(helper.getSizeid("5x2.5 ft"));
    }

}
