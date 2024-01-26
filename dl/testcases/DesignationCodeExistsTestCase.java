import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;

public class DesignationCodeExistsTestCase
{
 public static void main(String gg[])
 {
  int code=Integer.parseInt(gg[0]);
  try
 {
  
  DesignationDAOInterface designationDAO;
  designationDAO=new DesignationDAO();
  designationDAO.codeExists(code);  
  System.out.println(code+" exists:" +designationDAO.codeExists(code));
 }catch(DAOException daoException)
 {
 System.out.println(daoException.getMessage());
 }
}
}