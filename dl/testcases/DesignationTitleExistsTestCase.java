import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;

public class DesignationTitleExistsTestCase
{
 public static void main(String gg[])
 {
 String title=gg[0];
 try
  {
  DesignationDAOInterface designationDAO=new DesignationDAO();
  System.out.println(title +" exists:"+designationDAO.titleExists(title));
     
  }
 catch(DAOException daoException)
 {
 System.out.println(daoException.getMessage());
 }
 }
}