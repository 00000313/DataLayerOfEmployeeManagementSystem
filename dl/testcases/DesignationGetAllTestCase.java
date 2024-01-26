import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.DTO.*;
import java.util.*;
public class DesignationGetAllTestCase
{
 public static void main(String gg[])
 {
  try
  {
   DesignationDAOInterface designationDAO;
   designationDAO=new DesignationDAO();
   Set<DesignationDTOInterface> designations;
   designations=designationDAO.getAll();
   designations.forEach((designationDTO)->{
   System.out.printf("Code:%d ,Title %s\n",
   designationDTO.getCode(),designationDTO.getTitle());
   });
  }
  catch(DAOException daoException)
  {
  System.out.println(daoException.getMessage());
  }
 }
}