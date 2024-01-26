import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.enums.*;
import java.text.*;
import java.util.*;
import java.math.*;

public class EmployeeUpdateTestCase
{
 public static void main(String gg[])
 {
 String empId=gg[0];
 String name=gg[1];
 int designationCode=Integer.parseInt(gg[2]);
 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
 Date dateOfBirth=null;
 try
 {
 dateOfBirth=sdf.parse(gg[3]);
 }catch(ParseException pe)
 {
  System.out.println(pe.getMessage());
   return;
 } 
 char gender=gg[4].charAt(0);
 boolean isIndian=Boolean.parseBoolean(gg[5]);
 BigDecimal basicSalary=new BigDecimal(gg[6]);
 String panNumber=gg[7];
 String aadharNumber=gg[8]; 
 try
 {
  
  EmployeeDAOInterface employeeDAO;
  EmployeeDTOInterface employeeDTO;
  employeeDTO=new EmployeeDTO();
  employeeDAO=new EmployeeDAO();
  employeeDTO.setEmployeeId(empId);
  employeeDTO.setName(name);
  employeeDTO.setDesignationCode(designationCode);
  
  employeeDTO.setDateOfBirth(dateOfBirth);
  
  if(gender=='M' || gender=='m')
  employeeDTO.setGender(GENDER.MALE);
  else if(gender=='f' || gender=='F')
  employeeDTO.setGender(GENDER.FEMALE);
  employeeDTO.setIsIndian(isIndian);
  employeeDTO.setBasicSalary(basicSalary);
  employeeDTO.setPANNumber(panNumber);
  employeeDTO.setAadharCardNumber(aadharNumber);
  
  employeeDAO.update(employeeDTO);
  System.out.println("Employee updated");
  }
 catch(DAOException daoException)
 { 
 System.out.println(daoException.getMessage());
 }
 }
}