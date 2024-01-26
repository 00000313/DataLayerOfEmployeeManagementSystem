import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;

public class DesignationUpdateTestCase
{
 public static void main(String gg[])
 { 
 int code=Integer.parseInt(gg[0]);
 String title=gg[1];
 try
 {
  System.out.println("ff");
  DesignationDAOInterface designationDAO;
  DesignationDTOInterface designationDTO;
  designationDTO=new DesignationDTO();
  designationDAO=new DesignationDAO();
  designationDTO.setCode(code);
  designationDTO.setTitle(title);
  designationDAO.update(designationDTO);
  System.out.println("Designation updated");
  }
 catch(DAOException daoException)
 { 
 System.out.println(daoException.getMessage());
 }
 }
}