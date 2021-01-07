import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TypeHelper {
    private static Statement stmt = null;

    TypeHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }

    //INSERT INTO TYPE VALUES(TNAME,SIZEID);
    public void insertType(String name,String Description,String Size)
    {
        SizeHelper help=new SizeHelper();
        int id= help.getSizeid(Size);
        try {
            String query = "INSERT INTO TYPE(TNAME,DESCRIPTION,SIZEID) VALUES (\""+name+"\",\""+Description+"\","+id+");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getAlltype(){

        String query = "select TNAME from TYPE ORDER BY TNAME;";
        List<String> allType= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                allType.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allType;
    }

    public String getTypeName(int id){

        String query = "SELECT TNAME FROM TYPE WHERE TYPEID="+id+";";
        List<String> Type= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Type.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Type.toString();
    }

    public int getTypeid(String name,String size){
        SizeHelper h = new SizeHelper();
        int sid = h.getSizeid(size);
        String query = "SELECT TYPEID FROM TYPE WHERE TNAME=\""+name+"\" AND SIZEID = "+sid+";";
        List<Integer> Typeid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Typeid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Typeid.get(0);
    }

    public int getTypeSize(int id){
        String query = "SELECT SIZEID FROM TYPE WHERE TYPEID=\""+id+"\";";
        List<Integer> TypeSize= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                TypeSize.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return TypeSize.get(0);
    }

    public void updateTypeName(String Name, int id)
    {
        try {
            String query = "UPDATE TYPE\n" +
                    "    SET TNAME = \""+Name+"\"\n" +
                    "    WHERE TYPEID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTypeDescription(String Desc, int id)
    {
        try {
            String query = "UPDATE TYPE\n" +
                    "    SET DESCRIPTION = \""+Desc+"\"\n" +
                    "    WHERE TYPEID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTypeSize(String Size, int id)
    {
        SizeHelper help=new SizeHelper();
        int Sid= help.getSizeid(Size);
        try {
            String query = "UPDATE TYPE\n" +
                    "    SET SIZEID = "+Sid+"\n" +
                    "    WHERE TYPEID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteSize(int id) {
        try {
            String query = "DELETE FROM TYPE\n" +
                    "WHERE TYPEID="+id+" AND TYPEID NOT IN(SELECT TYPEID\n" +
                    "FROM PRODUCT\n" +
                    "WHERE TYPEID="+id+");\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args){
        TypeHelper helper = new TypeHelper();
        helper.deleteSize(10);
        //helper.insertType("Black Board","NULL","5x4 ft");
        //System.out.println(helper.getTypeid("Ply"));
    }
}
