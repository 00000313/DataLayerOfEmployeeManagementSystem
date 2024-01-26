import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;

public class GetCountByDesignation
{
 public static void main(String gg[])
 {
  int designationCode=Integer.parseInt(gg[0]);
  try
   {
   EmployeeDAOInterface employeeDAO=new EmployeeDAO();
   int count=employeeDAO.getCountByDesignation(designationCode);
   System.out.println(count);
   }
   catch(DAOException daoException)
 {
   System.out.println(daoException.getMessage());
 }
 }
}