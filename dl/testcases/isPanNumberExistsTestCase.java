import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;

public class isPanNumberExistsTestCase
{
 public static void main(String gg[])
 {
  String title=gg[0];
  try
   {
   EmployeeDAOInterface employeeDAO =new EmployeeDAO();
   boolean b=employeeDAO.panNumberExists(title);
   System.out.println("Pan Number"+title+" exists:"+b);
   }
   catch(DAOException daoException)
   {
    System.out.println(daoException.getMessage());
   } 
 }
}