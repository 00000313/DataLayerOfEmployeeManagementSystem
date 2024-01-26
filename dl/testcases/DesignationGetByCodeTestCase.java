import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;

public class DesignationGetByCodeTestCase 
{
 public static void main(String gg[])
 {
  int code=Integer.parseInt(gg[0]);
  try
   {
    DesignationDTOInterface designationDTO;
    designationDTO=new DesignationDTO();
    DesignationDAOInterface designationDAO;
    designationDAO=new DesignationDAO();
    designationDTO= designationDAO.getByCode(code);
    System.out.printf("Code %d,Title %s\n",designationDTO.getCode(),designationDTO.getTitle());
   }
   catch(DAOException daoException)
   {
    System.out.println(daoException.getMessage());
   }
  }
}
  