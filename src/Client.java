import com.mysql.cj.protocol.Resultset;

import ConnectAdapter.mySQLConnection;

public class Client {
    public static void main(String[] args) {
        mySQLConnection test = new mySQLConnection("jdbc:mysql://localhost/helloworld","root","root");
        test.connect();
        if (test.connection!=null) {
            System.out.println("connect");
        } 
        test.close();
        try {
            test.connection.getMetaData();
          } catch (Exception e) {
            System.out.println("Connection is closed");
          }
    }
}
