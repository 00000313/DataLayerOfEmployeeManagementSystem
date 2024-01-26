import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;



public class DesignationGetByTitleTestCase 
{
 public static void main(String gg[])
 {
 String title=gg[0];
 try
 {
 DesignationDAOInterface designationDAO=new DesignationDAO();
 DesignationDTOInterface designationDTO=new DesignationDTO();
 designationDTO=designationDAO.getByTitle(title);
 System.out.printf("Code :%d , Title:%s",designationDTO.getCode(),
 designationDTO.getTitle());
 }
 catch(DAOException daoException)
  {
      System.out.println(daoException.getMessage());   
  }
}
}