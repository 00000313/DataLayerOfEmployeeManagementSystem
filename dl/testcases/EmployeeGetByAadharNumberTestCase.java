import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.DTO.*;
import java.text.*;
import java.util.*;
import java.math.*;
public class EmployeeGetByAadharNumberTestCase
{
 public static void main(String gg[])
 {
   try
  {
   EmployeeDTOInterface employeeDTO=new EmployeeDTO();
   EmployeeDAOInterface employeeDAO;
   employeeDAO=new EmployeeDAO();
   employeeDTO=employeeDAO.getByAadharCardNumber(gg[0]);
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
  
   System.out.println("Employee Id :"+employeeDTO.getEmployeeId());
   System.out.println("Name :"+employeeDTO.getName());
   System.out.println("Designation code :"+employeeDTO.getDesignationCode());
   
   System.out.println("Date of birth :"+sdf.format(employeeDTO.getDateOfBirth()));
   System.out.println("Gender :"+employeeDTO.getGender());
   System.out.println("Is indian :"+employeeDTO.getIsIndian());
   System.out.println("Basic salary :"+employeeDTO.getBasicSalary().toPlainString()); 
   System.out.println("Pan number :"+employeeDTO.getPANNumber()); 
   System.out.println("Aadhar number :"+employeeDTO.getAadharCardNumber()); 
   
  }
  catch(DAOException daoException)
  {
  System.out.println(daoException.getMessage());
  }
 }
}