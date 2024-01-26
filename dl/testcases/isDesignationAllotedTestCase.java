import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;

public class isDesignationAllotedTestCase
{
 public static void main(String gg[])
 {
  int title=Integer.parseInt(gg[0]);
  try
   {
   EmployeeDAOInterface employeeDAO =new EmployeeDAO();
   boolean b=employeeDAO.isDesignationAlloted(title);
   System.out.println("Designation code "+title+" exists:"+b);
   }
   catch(DAOException daoException)
   {
    System.out.println(daoException.getMessage());
   } 
 }
}