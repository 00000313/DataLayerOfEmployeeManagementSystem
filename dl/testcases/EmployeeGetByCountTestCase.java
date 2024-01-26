import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;


public class EmployeeGetByCountTestCase
{
 public static void main(String gg[])
 {
  try
  {
   EmployeeDAOInterface employeeDAO=new EmployeeDAO();
   int count=employeeDAO.getCount();
   System.out.println(count+" Records are in the file");
   
  } 
  catch(DAOException daoException) 
  {
   System.out.println(daoException.getMessage());
  }
 }
}