import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;

public class DesignationAddTestCase
{
 public static void main(String gg[])
 {
  String title=gg[0];
  try
  {
  DesignationDTOInterface designationDTO;
  designationDTO=new DesignationDTO(); 
  designationDTO.setTitle(title);
  DesignationDAOInterface designationDAO;
  designationDAO=new DesignationDAO();
  designationDAO.add(designationDTO);
  System.out.println("Designation :"+title+" added with code as :"+ designationDTO.getCode());
  }
  catch(DAOException daoException)
  { 
   System.out.println(daoException.getMessage());
  }
 }
}