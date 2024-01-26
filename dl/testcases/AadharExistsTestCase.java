import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;

public class AadharExistsTestCase
{
 public static void main(String gg[])
 {
  int code=Integer.parseInt(gg[0]);
  try
   {
   EmployeeDAOInterface employeeDAO =new EmployeeDAO();
   boolean b=employeeDAO.aadharCardExists(code);
   System.out.println("Aadhar card Number"+code+" exists:"+b);
   }
   catch(DAOException daoException)
   {
    System.out.println(daoException.getMessage());
   } 
 }
}