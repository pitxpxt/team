package Practice;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oracle")
public class OracleJDBCTest extends HttpServlet 
{
   
   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
            IOException 
   {
      System.out.println("Oracle 11gR2 XE, ojdbc8.jar 테스트");

      Connection conn = null;

      
      try {
         Class.forName("oracle.jdbc.OracleDriver");
         conn = DriverManager.getConnection(
                   "jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM emp");
         
         while(rs.next())
         {
            int empno = rs.getInt("EMPNO");
            String ename = rs.getString("ENAME");
            java.sql.Date hiredate = rs.getDate("HIREDATE");
            float salary = rs.getFloat("SAL");
            
            System.out.printf("%d\t%s\t%s\t%f \n", empno,ename,hiredate,salary);
         }
         
         rs.close();
         stmt.close();
         conn.close();
         
        } catch (Exception e) {
            e.printStackTrace();;
        }

   }

}