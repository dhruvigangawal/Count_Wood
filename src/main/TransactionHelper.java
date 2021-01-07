import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;


public class TransactionHelper {
    private static Statement stmt = null;

    TransactionHelper(){
        if(stmt == null){
            JDBCConnetor connetor = new JDBCConnetor();
            stmt = connetor.getConnection();
        }
    }
    //INSERT INTO TRANSACTION VALUES(TRANSACTIONTYPE,BILLNO,COMPANYID,CLIENTID,PRODUCTID,PROQUANTITY,AMOUNT,DATE,DESCRIPTION);

    public void insertTransaction(int Ttype, int billno, int cid, int cvid, int pid, int quantity, String desc, java.sql.Date date, float amount,java.sql.Time time)
    {
        try {
            String query = "INSERT INTO TRANSACTION(TRANSACTIONTYPE,BILLNO,COMPANYID,CLIENTID,PRODUCTID,PRODUCTQUANTITY,DESCRIPTION,AMOUNT,DATE,TIME)\n" +
                    " VALUES("+Ttype+","+billno+","+cid+","+cvid+","+pid+","+quantity+",\""+desc+"\","+amount+",\""+date+"\",\""+time+"\");";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<String> getCustomerbill(String name, Date d1,Date d2){
        String query = "SELECT DATE , BILLNO , AMOUNT\n" +
                "FROM TRANSACTION\n" +
                "WHERE TRANSACTIONTYPE=1 AND CLIENTID IN(SELECT CUSTOMER_ID\n" +
                "FROM CUSTOMER WHERE NAME=\""+name+"\") AND DATE BETWEEN \""+d1+"\" AND \""+d2+"\"\n" +
                "ORDER BY DATE;\n";
        List<String> Bill= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Bill.add(resultSet.getString(1));
                Bill.add(resultSet.getString(2));
                Bill.add(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Bill;
    }

    public List<String> getVendorbill(String name, Date d1,Date d2){
        String query = "SELECT DATE , BILLNO , AMOUNT\n" +
                "FROM TRANSACTION\n" +
                "WHERE TRANSACTIONTYPE=0 AND CLIENTID IN(SELECT VENDOR_ID\n" +
                "FROM VENDOR WHERE VNAME=\""+name+"\") AND DATE BETWEEN \""+d1+"\" AND \""+d2+"\"\n" +
                "ORDER BY DATE;\n";
        List<String> Bill= new ArrayList<>();
        try {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                Bill.add(resultSet.getString(1));
                Bill.add(resultSet.getString(2));
                Bill.add(resultSet.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Bill;
    }

    public void updateTransactionAmount(float amount,int id)
    {
        try {
            String query = "UPDATE TRANSACTION\n" +
                    "    SET AMOUNT="+amount+"\n" +
                    "    WHERE TRANSACTIONID = "+id+"";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTransactionDescription(String desc,int id)
    {
        try {
            String query = "UPDATE TRANSACTION\n" +
                    "    SET DESCRIPTION=\""+desc+"\"\n" +
                    "    WHERE TRANSACTIONID = "+id+"";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateBillDate(Date date,int id)
    {
        try {
            String query = "UPDATE TRANSACTION\n" +
                    "SET DATE =\""+date+"\"\n" +
                    "WHERE BILLNO="+id+";";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTransaction(int id,int billno) {
        try {
            String query = "DELETE FROM TRANSACTION\n" +
                    "WHERE TRANSACTIONID = "+id+" AND BILLNO ="+billno+";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteBill(int billno) {
        try {
            String query = "DELETE FROM TRANSACTION\n" +
                    "WHERE BILLNO ="+billno+";\n";
            System.out.println(query);
            boolean resultSet = stmt.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args){
        TransactionHelper helper = new TransactionHelper();
        /*Date date = new Date();
        LocalTime time = LocalTime.now();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat tformatter = new SimpleDateFormat("HH:mm:ss");
        DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String d=formatter.format(date);
        String t=time.format(tformatter);
        helper.insertTransaction(1, 1, 1, 1, 1, 0, "NULL", java.sql.Date.valueOf(d),(float) 23.45,java.sql.Time.valueOf(t));*/
        //System.out.println(helper.getCustomerbill("Dhruvi",java.sql.Date.valueOf("2021-01-03"),java.sql.Date.valueOf("2021-01-03")));
        //helper.updateTransactionAmount((float)2300.45,1);
        //helper.updateBillDate(java.sql.Date.valueOf("2021-01-01"),1);
        //helper.updateTransactionDescription("Very Good",1);
        //helper.deleteTransaction(1,1);
        //helper.deleteBill(1);
    }
}
