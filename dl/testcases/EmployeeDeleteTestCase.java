import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;

public class EmployeeDeleteTestCase
{
 public static void main(String gg[])
 {
 String empId=gg[0];
 try
 {
  EmployeeDAO employeeDAO=new EmployeeDAO();
  employeeDAO.delete(empId);
   System.out.printf("Employee %s Deleted",empId);
  }
 catch(DAOException daoException)
 { 
 System.out.println(daoException.getMessage());
 }
 }
}