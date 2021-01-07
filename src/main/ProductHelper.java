import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductHelper {
    private static Statement stmt = null;

    ProductHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }
    TypeHelper help = new TypeHelper();
    ThicknessHelper h = new ThicknessHelper();
    DesignHelper he = new DesignHelper();
    BrandHelper hh = new BrandHelper();
    SizeHelper S = new SizeHelper();
    //INSERT INTO PRODUCT VALUES(TYPEID,THICKNESSID,DESIGNID,BRANDID,PRICE);
    public void insertProduct(String type,String Size,int thickness,String design,String brand,float price)
    {
        int tid=help.getTypeid(type,Size);
        int thid = h.getThicknessid(thickness);
        int did = he.getDesignid(design);
        int bid = hh.getBrandid(brand);
        try {
            String query = "INSERT INTO PRODUCT(TYPEID,THICKNESSID,DESIGNID,BRANDID,PRICE,COUNT) VALUES ("+tid+","+thid+","+did+","+bid+","+price+",0);";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getStock()
    {
        String query = "SELECT BRAND.BNAME,TYPE.TNAME,DESIGN.DNAME,THICKNESS.VALUE,SIZE.MEASUREMENT,PRODUCT.COUNT\n" +
                "    FROM(( PRODUCT\n" +
                "                    INNER JOIN BRAND ON PRODUCT.BRANDID=BRAND.BRANDID\n" +
                "                 INNER JOIN TYPE ON PRODUCT.TYPEID=TYPE.TYPEID\n" +
                "                 INNER JOIN DESIGN ON PRODUCT.DESIGNID=DESIGN.DESIGNID\n" +
                "                 INNER JOIN THICKNESS ON PRODUCT.THICKNESSID=THICKNESS.THICKNESSID\n" +
                "                 INNER JOIN SIZE ON TYPE.SIZEID=SIZE.SIZEID))\n" +
                "    GROUP BY BRAND.BNAME,TYPE.TNAME,THICKNESS.VALUE\n" +
                "    ORDER BY BRAND.BNAME,TYPE.TNAME,THICKNESS.VALUE,SIZE.MEASUREMENT;";
        List<String> Stock = new ArrayList<>();
        try {
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Stock.add(resultSet.getString(1));
                Stock.add(resultSet.getString(2));
                Stock.add(resultSet.getString(3));
                Stock.add(resultSet.getString(4));
                Stock.add(resultSet.getString(5));
                Stock.add(resultSet.getString(6));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Stock;
    }

    public List<String> getPriceList()
    {
        String query = "SELECT BRAND.BNAME,TYPE.TNAME,DESIGN.DNAME,THICKNESS.VALUE,PRODUCT.PRICE\n" +
                "    FROM(( PRODUCT\n" +
                "                    INNER JOIN BRAND ON PRODUCT.BRANDID=BRAND.BRANDID\n" +
                "                 INNER JOIN TYPE ON PRODUCT.TYPEID=TYPE.TYPEID\n" +
                "                 INNER JOIN DESIGN ON PRODUCT.DESIGNID=DESIGN.DESIGNID\n" +
                "                 INNER JOIN THICKNESS ON PRODUCT.THICKNESSID=THICKNESS.THICKNESSID))\n" +
                "    GROUP BY BRAND.BNAME,TYPE.TNAME,THICKNESS.VALUE\n" +
                "    ORDER BY BRAND.BNAME,TYPE.TNAME,THICKNESS.VALUE;";
        List<String> PriceList = new ArrayList<>();
        try {
            System.out.println(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                PriceList.add(resultSet.getString(1));
                PriceList.add(resultSet.getString(2));
                PriceList.add(resultSet.getString(3));
                PriceList.add(resultSet.getString(4));
                PriceList.add(resultSet.getString(5));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return PriceList;
    }

    public int getProductId(String type,String Size,int thickness,String design,String brand)
    {
        int t = help.getTypeid(type,Size);
        int th = h.getThicknessid(thickness);
        int did = he.getDesignid(design);
        int bid= hh.getBrandid(brand);
        String query = "SELECT PRODUCTID FROM PRODUCT \n" +
                "WHERE TYPEID="+t+" AND THICKNESSID="+th+" AND DESIGNID="+did+" AND BRANDID="+bid+";\n";
        List<Integer> Productid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Productid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Productid.get(0);
    }

    public String getProductType(int id){

        String query = "SELECT TYPEID FROM PRODUCT WHERE PRODUCTID="+id+"";
        List<Integer> Typeid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Typeid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return help.getTypeName(Typeid.get(0));
    }

    public int getProductThickness(int id){

        String query = "SELECT THICKNESSID FROM PRODUCT WHERE PRODUCTID="+id+"";
        List<Integer> Thicknessid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Thicknessid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return h.getThickness(Thicknessid.get(0));
    }

    public String getProductBrand(int id){

        String query = "SELECT BRANDID FROM PRODUCT WHERE PRODUCTID="+id+"";
        List<Integer> Brandid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Brandid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return hh.getBrandName(Brandid.get(0));
    }

    public String getProductDesign(int id){

        String query = "SELECT DESIGNID FROM PRODUCT WHERE PRODUCTID="+id+"";
        List<Integer> Designid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Designid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return he.getDesignName(Designid.get(0));
    }

    public String getProductSize(int id){

        String query = "SELECT TYPEID FROM PRODUCT WHERE PRODUCTID="+id+"";
        List<Integer> Typeid= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Typeid.add(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int sid = help.getTypeSize(Typeid.get(0));
        return S.getSizeName(sid);
    }

    public float getProductPrice(int id){

        String query = "SELECT PRICE FROM PRODUCT WHERE PRODUCTID ="+id+";";
        List<Float> price= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                price.add(resultSet.getFloat(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price.get(0);
    }

    public void updateProductPrice(float Price, int id)
    {
        try {
            String query = "UPDATE PRODUCT\n" +
                    "    SET PRICE = "+Price+"\n" +
                    "    WHERE PRODUCTID = "+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProductType(String Type,String Size,int id)
    {
        int tid=help.getTypeid(Type,Size);
        try {
            String query = "UPDATE PRODUCT\n" +
                    "    SET TYPEID="+tid+"\n" +
                    "    WHERE PRODUCTID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProductThickness(int Thickness,int id)
    {
        int thid=h.getThicknessid(Thickness);
        try {
            String query = "UPDATE PRODUCT\n" +
                    "    SET THICKNESSID="+thid+"\n" +
                    "    WHERE PRODUCTID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProductBrand(String Brand,int id)
    {
        int bid = hh.getBrandid(Brand);
        try {
            String query = "UPDATE PRODUCT\n" +
                    "    SET BRANDID="+bid+"\n" +
                    "    WHERE PRODUCTID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateProductDesign(String Design,int id)
    {
        int did = he.getDesignid(Design);
        try {
            String query = "UPDATE PRODUCT\n" +
                    "    SET DESIGNID="+did+"\n" +
                    "    WHERE PRODUCTID="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //DELETE FROM PRODUCT WHERE PRODUCTID= ‘GIVEN VALUE’ AND COUNT = 0;

    public void deleteProduct(int id) {
        try {
            String query = "DELETE FROM PRODUCT WHERE PRODUCTID= "+id+" AND COUNT = 0;";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args){
        ProductHelper helper = new ProductHelper();
       //helper.deleteProduct(3);
        System.out.println(helper.getPriceList());
    }
}
