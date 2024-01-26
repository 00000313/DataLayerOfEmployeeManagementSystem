import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;

public class DesignationDeleteTestCase
{
 public static void main(String gg[])
 {
 int code=Integer.parseInt(gg[0]);
 try
 { 
 DesignationDAOInterface designationDAO;
 designationDAO=new DesignationDAO();
 designationDAO.delete(code);
 System.out.println("Record deleted successfully");
 }
 catch(DAOException daoException)
 {
  System.out.println(daoException.getMessage());
 }
}
}