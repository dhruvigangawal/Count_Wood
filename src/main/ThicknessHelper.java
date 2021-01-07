import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThicknessHelper {
    private static Statement stmt = null;

    ThicknessHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }
    public void insertThickness(Double thickness)
    {
        try {
            String query = "INSERT INTO THICKNESS (value) VALUES (" + thickness.toString() +")";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAllThickness(){

        String query = "Select value from THICKNESS;";
        List<String> allThickness = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allThickness.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allThickness;
    }
    public int getThicknessid(int Value){

        String query = "Select thicknessid from THICKNESS where value = ("+Value+") ;";
        List<Integer> thicknessid = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                thicknessid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return thicknessid.get(0);
    }

    public int getThickness(int Name){

        String query;
        query = "SELECT VALUE FROM THICKNESS WHERE THICKNESSID=("+Name+")";
        List<Integer> thickness = new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                thickness.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return thickness.get(0);
    }

    public void updateThickness(Double thickness, int id)
    {
        try {
            String query = "UPDATE THICKNESS SET VALUE =("+thickness+") WHERE THICKNESSID=("+id+");";
            int resultSet = stmt.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    

    public static void main(String[] args){
        ThicknessHelper helper = new ThicknessHelper();
        helper.updateThickness(1.0,3);
    }
}

