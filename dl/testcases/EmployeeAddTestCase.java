import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.hr.dl.DAO.*;
import com.thinking.machines.enums.*;
import java.math.*;
import java.text.*;
import java.util.*;
public class EmployeeAddTestCase
{
public static void main(String gg[])
 {
  String name=gg[0];
  int designationCode=Integer.parseInt(gg[1]);
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
  Date dateOfBirth=null;
  try
  {
   dateOfBirth=sdf.parse(gg[2]); 
  }
   catch(ParseException pe)
  {
   System.out.println(pe.getMessage());
   return;
  }
  
  char gender=gg[3].charAt(0);
  boolean isIndian=Boolean.parseBoolean(gg[4]);
  BigDecimal basicSalary=new BigDecimal(gg[5]);
  String panNumber=gg[6]; 
  String aadharCardNumber=gg[7];
  
 try
 {
  EmployeeDTOInterface employeeDTO;
  employeeDTO=new EmployeeDTO();
  EmployeeDAOInterface employeeDAO;
  employeeDAO=new EmployeeDAO();
  
  employeeDTO.setName(name);
  employeeDTO.setDesignationCode(designationCode);
  employeeDTO.setDateOfBirth(dateOfBirth);
  if(gender=='M' || gender=='m')
  {
  employeeDTO.setGender(GENDER.MALE);
  }
  else if(gender=='f' || gender=='F')
  {
  employeeDTO.setGender(GENDER.FEMALE);
  }
  employeeDTO.setIsIndian(isIndian);
  employeeDTO.setBasicSalary(basicSalary);
  employeeDTO.setPANNumber(panNumber);
  employeeDTO.setAadharCardNumber(aadharCardNumber);
  employeeDAO.add(employeeDTO);
  
  System.out.println("Employee added with employeeId: "+employeeDTO.getEmployeeId());
 }
 catch(DAOException daoException)
 {
  System.out.println(daoException.getMessage());
 }  
 }
}