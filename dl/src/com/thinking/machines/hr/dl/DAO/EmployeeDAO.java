package com.thinking.machines.hr.dl.DAO;
import java.util.*;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.DTO.*;
import com.thinking.machines.enums.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.io.*;
import java.math.*;
import java.text.*;


public class EmployeeDAO implements EmployeeDAOInterface
{
private static final String FILE_NAME="employee.data";

public void add(EmployeeDTOInterface employeeDTO) throws DAOException
{
 if(employeeDTO==null) throw new DAOException("Employee is null");
 String employeeId=employeeDTO.getEmployeeId();
 String name=employeeDTO.getName();
 if(name==null) throw new DAOException("Name is null");
 name=name.trim();
 if(name.length()==0)
 {
 throw new DAOException("Length of employee's name is 0");
 }
 
 int designationCode=employeeDTO.getDesignationCode();
 if(designationCode<=0) 
 {
 throw new DAOException("Invalid designation code:"+designationCode);
 }
  
 DesignationDAOInterface designationDAO=new DesignationDAO();
 boolean isDesignationValid=designationDAO.codeExists(designationCode);
 if(isDesignationValid==false)
  {
 throw new DAOException("Invalid designation code"+designationCode);
 }

 Date dateOfBirth=employeeDTO.getDateOfBirth();
 if(dateOfBirth==null) 
  {
 throw new DAOException("Date of birth is null");
 }
  char gender ;

  gender=employeeDTO.getGender();
 boolean isIndian=employeeDTO.getIsIndian();
 
 BigDecimal basicSalary=employeeDTO.getBasicSalary();

 if(basicSalary==null)
 {
 throw new DAOException("Basic salary is null");
 }
 if(basicSalary.signum()==-1) 
 {
 throw new DAOException("Basic salary is negative");
 }
 String panNumber=employeeDTO.getPANNumber();
 if(panNumber==null)
  {
 throw new DAOException("Pan number is null");
 }

  panNumber=panNumber.trim();
 if(panNumber.length()==0)
  {
 throw new DAOException("Length of pan Number is 0");
 }

 String aadharCardNumber=employeeDTO.getAadharCardNumber();
 if(aadharCardNumber==null)
  {
 throw new DAOException("Aadhar card number is null");
 }

 aadharCardNumber=aadharCardNumber.trim();
 if(panNumber.length()==0)
  {
 throw new DAOException("Length of aadhar Number is 0");
 }

try{
   int lastGeneratedEmployeeId=10000000;
   String lastGeneratedEmployeeIdString="";
   int recordCount=0;
   String recordCountString="";
   
   File file=new File(FILE_NAME);
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0)
   {
    lastGeneratedEmployeeIdString=String.format("%-10s","10000000");
    raf.writeBytes(lastGeneratedEmployeeIdString+"\n");
    
    recordCountString=String.format("%-10s","0");
    raf.writeBytes(recordCountString+"\n");
   } 
  else
   {
    lastGeneratedEmployeeId=Integer.parseInt(raf.readLine().trim());
    recordCount=Integer.parseInt(raf.readLine().trim());
   }
 boolean panNumberExists=false,aadharCardNumberExists=false;
 String fPANNumber="";
 String fAadharCardNumber="";
 int x;  
while(raf.getFilePointer()<raf.length())
 {
  for(x=1;x<=7;x++) raf.readLine();
  fPANNumber=raf.readLine();
  fAadharCardNumber=raf.readLine();
  if(panNumberExists==false && fPANNumber.equalsIgnoreCase(panNumber))
  {  
   panNumberExists=true; 
  }
  if(aadharCardNumberExists==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
  {
   aadharCardNumberExists=true;
  }
  if(panNumberExists==true && aadharCardNumberExists==true)
  {
   raf.close();
   throw new DAOException("Pan number " +panNumber+ " as well as aadhar number "+aadharCardNumber+" exists");
  } 
  if(panNumberExists)
  {
    raf.close();
   throw new DAOException("Pan number " +panNumber+ " exists");
  }
  if(aadharCardNumberExists)
  {
   raf.close();
   throw new DAOException("aadhar card Number "+aadharCardNumber+" exists");
  } 
 }
lastGeneratedEmployeeId++;
employeeId="A"+lastGeneratedEmployeeId;

raf.writeBytes(employeeId+"\n");
raf.writeBytes(name+"\n");
raf.writeBytes(designationCode+"\n");

SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String dateString=sdf.format(dateOfBirth);
raf.writeBytes(dateString+"\n");

raf.writeBytes(gender+"\n");
raf.writeBytes(isIndian+"\n");

raf.writeBytes(basicSalary.toPlainString()+"\n");
raf.writeBytes(panNumber+"\n");
raf.writeBytes(aadharCardNumber+"\n");
raf.seek(0);

lastGeneratedEmployeeIdString=String.format("%-10d",lastGeneratedEmployeeId);
raf.writeBytes(lastGeneratedEmployeeIdString+"\n");
recordCount++;
recordCountString=String.format("%-10d",recordCount);
raf.writeBytes(recordCountString+"\n");
raf.close();
employeeDTO.setEmployeeId(employeeId);

}catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }
 }



public void update(EmployeeDTOInterface employeeDTO) throws DAOException
 {
  if(employeeDTO==null) throw new DAOException("Employee is null");
  String employeeId=employeeDTO.getEmployeeId();
  if(employeeId==null) throw new DAOException("Employee id is null");
  employeeId=employeeId.trim();
  if(employeeId.length()==0) throw new DAOException("Length of Employee id is zero");
  
  String name=employeeDTO.getName();
  
  if(name==null) throw new DAOException("Name is null");
  name=name.trim();
  if(name.length()==0)
  {
  throw new DAOException("Length of employee's name is 0");
  }
 
  int designationCode=employeeDTO.getDesignationCode();
  if(designationCode<=0) 
  {
  throw new DAOException("Invalid designation code:"+designationCode);
  }
 
   
  DesignationDAOInterface designationDAO=new DesignationDAO();
  boolean isDesignationValid=designationDAO.codeExists(designationCode);
  if(isDesignationValid==false)
   {
   throw new DAOException("Invalid designation code"+designationCode);
   }
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyy");
  Date dateOfBirth=employeeDTO.getDateOfBirth();
  if(dateOfBirth==null) 
   {
   throw new DAOException("Invalid date of birth");
   }
    
   boolean isIndian=employeeDTO.getIsIndian();
   char gender=employeeDTO.getGender();
   System.out.println(gender);   
   BigDecimal basicSalary=employeeDTO.getBasicSalary();
  if(basicSalary==null) 
   {
   throw new DAOException("Basic salary is null");
   }
    
   if(basicSalary.signum()==-1) 
   {
   throw new DAOException("Basic salry is negative");
   }
   
   String panNumber=employeeDTO.getPANNumber();
  if(panNumber==null)
   {
    throw new DAOException("Pan number is null");
   }

  panNumber=panNumber.trim();
  if(panNumber.length()==0)
  {
  throw new DAOException("Length of pan Number is 0");
  }

  String aadharCardNumber=employeeDTO.getAadharCardNumber();
  if(aadharCardNumber==null)
  {
   throw new DAOException("Aadhar card number is null");
  }

 aadharCardNumber=aadharCardNumber.trim();
 if(aadharCardNumber.length()==0)
  {
 throw new DAOException("Length of aadhar Number is 0");
 }

 try
   {
   File file=new File(FILE_NAME);
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0)
   {
   throw new DAOException("Invalid employeeId"+employeeId);
   }
    
   boolean panNumberFound=false,aadharCardNumberFound=false,employeeIdFound=false;
   String fPANNumber="";
   String fAadharCardNumber="";
   String fEmployeeId="";
   int x;
   
  raf.readLine();
  raf.readLine();
  long foundAt=0;
  String panNumberFoundAgainstEmployeeId="";
  String aadharNumberFoundAgainstEmployeeId="";
  
  while(raf.getFilePointer()<raf.length())
 {
  if(employeeIdFound==false) foundAt=raf.getFilePointer();
  fEmployeeId=raf.readLine();
  for(x=1;x<=6;x++) raf.readLine();
  fPANNumber=raf.readLine();
  fAadharCardNumber=raf.readLine();
  if(fEmployeeId.equalsIgnoreCase(employeeId))
  {
   employeeIdFound=true;
  }
  if(panNumberFound==false && fPANNumber.equalsIgnoreCase(panNumber))
  { 
   panNumberFoundAgainstEmployeeId=fEmployeeId; 
   panNumberFound=true; 
  }
  if(aadharCardNumberFound==false && fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))
  {
   aadharNumberFoundAgainstEmployeeId=fEmployeeId;
   aadharCardNumberFound=true;
  }
  if(employeeIdFound && aadharCardNumberFound && panNumberFound) break;
  
 }
  if(employeeIdFound==false)
  {

   raf.close();
   throw new DAOException("Invalid employee id"+employeeId);
 } 
  boolean panNumberExists=false;
  if(panNumberFound && panNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
  {
   panNumberExists=true;
  } 
 
 boolean aadharNumberExists=false;
 if(aadharCardNumberFound && aadharNumberFoundAgainstEmployeeId.equalsIgnoreCase(employeeId)==false)
 {
  aadharNumberExists=true;
 }

  if(panNumberExists==true && aadharNumberExists==true)
  {
   raf.close();
   throw new DAOException("Pan number " +panNumber+ " as well as aadhar number "+aadharCardNumber+" exists");
  } 
  if(panNumberExists)
  {
    raf.close();
   throw new DAOException("Pan number " +panNumber+ " exists");
  }
  if(aadharNumberExists)
  {
   raf.close();
   throw new DAOException("aadhar card Number "+aadharCardNumber+" exists");
  } 
 
  raf.seek(foundAt);
  for(x=1;x<=9;x++) raf.readLine();
  
  File tmpFile=new File("tmp.data");
  if(tmpFile.exists()) tmpFile.delete();
  RandomAccessFile tmpRaf=new RandomAccessFile(tmpFile,"rw");
 
  while(raf.getFilePointer()<raf.length()) tmpRaf.writeBytes(raf.readLine()+"\n");

  raf.seek(foundAt);
    
  
  raf.writeBytes(employeeId+"\n");
  raf.writeBytes(name+"\n");
  raf.writeBytes(designationCode+"\n");
  raf.writeBytes(sdf.format(dateOfBirth)+"\n");
  raf.writeBytes(gender+"\n");
  raf.writeBytes(isIndian+"\n");
  raf.writeBytes(basicSalary.toPlainString()+"\n");
  raf.writeBytes(panNumber+"\n");
  raf.writeBytes(aadharCardNumber+"\n");
  
  tmpRaf.seek(0); 
  while(tmpRaf.getFilePointer()<tmpRaf.length()) 
 {
  raf.writeBytes(tmpRaf.readLine()+"\n");
 }  
 raf.setLength(raf.getFilePointer());
 tmpRaf.setLength(0);
 raf.close();
 tmpRaf.close();  
 }catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }
 }
 
 
public Set<EmployeeDTOInterface> getAll() throws DAOException
 {
  try
  {
   Set<EmployeeDTOInterface> employees;
   employees=new TreeSet<>();
   
   File file=new File(FILE_NAME);
   if(file.exists()==false) return employees;
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0) return employees;
   String employeeId;
   String name;
   int designationCode;
   Date dateOfBirth=null;
   
   boolean isIndian;
   BigDecimal basicSalary;
   String panNumber;
   String aadharNumber;
   raf.readLine();
   raf.readLine();
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   EmployeeDTOInterface employeeDTO;
   char fGender;       
   
   while(raf.getFilePointer()<raf.length())
   {
    employeeId=raf.readLine();
    name=raf.readLine();
    designationCode=Integer.parseInt(raf.readLine());
    try{
    dateOfBirth=sdf.parse(raf.readLine());
    }catch(ParseException pe)
    {
    //do nothing
    }
    
    fGender=(raf.readLine().charAt(0));
    isIndian=Boolean.parseBoolean(raf.readLine());
    basicSalary=new BigDecimal(raf.readLine());
    panNumber=raf.readLine();
    aadharNumber=raf.readLine();
    
    employeeDTO=new EmployeeDTO();  
    employeeDTO.setEmployeeId(employeeId);
    employeeDTO.setName(name);
    employeeDTO.setDesignationCode(designationCode);
    employeeDTO.setDateOfBirth(dateOfBirth);
    if(fGender=='M') employeeDTO.setGender(GENDER.MALE);
    else if(fGender=='F') employeeDTO.setGender(GENDER.FEMALE);
    employeeDTO.setIsIndian(isIndian);
    employeeDTO.setBasicSalary(basicSalary);
    employeeDTO.setPANNumber(panNumber);
    employeeDTO.setAadharCardNumber(aadharNumber);
    
    employees.add(employeeDTO);
    
   }
   raf.close();
   return employees;
  }catch(IOException ioException)
  {
    throw new DAOException(ioException.getMessage());
  }  
 }
 


public void delete(String employeeId) throws DAOException
 {
  if(employeeId==null) throw new DAOException("Employee id is null");
  employeeId=employeeId.trim();
  if(employeeId.length()==0) throw new DAOException("Length of Employee id is zero");
  
  
  try
   {
   File file=new File(FILE_NAME);
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0)
   {
   throw new DAOException("Invalid employeeId"+employeeId);
   }
    
   boolean employeeIdFound=false;
   String fEmployeeId="";
   int x;
   
   raf.readLine();
   raf.readLine();
   long foundAt=0;
  
 while(raf.getFilePointer()<raf.length())
 {
  if(employeeIdFound==false) foundAt=raf.getFilePointer();
  fEmployeeId=raf.readLine();
  if(fEmployeeId.equalsIgnoreCase(employeeId))
  {
   
   employeeIdFound=true;
   break;
  }
  for(x=1;x<=8;x++) raf.readLine();
 }
  
  if(employeeIdFound==false)
  {
  raf.close();
  throw new DAOException("Invalid employeeId"+employeeId);
  }
  
  for(x=1;x<=8;x++) raf.readLine();

  File tmpFile=new File("tmp.data");
  if(tmpFile.exists()) tmpFile.delete();
  RandomAccessFile tmpRaf=new RandomAccessFile(tmpFile,"rw");
 
  while(raf.getFilePointer()<raf.length()) tmpRaf.writeBytes(raf.readLine()+"\n");

  raf.seek(foundAt);
    
  tmpRaf.seek(0); 
  while(tmpRaf.getFilePointer()<tmpRaf.length()) 
 {
  raf.writeBytes(tmpRaf.readLine()+"\n");
 }  
 raf.setLength(raf.getFilePointer());
 tmpRaf.setLength(0);
 raf.close();
 tmpRaf.close();  
 }catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }
 }
 


public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException
 {
   try
  {
   Set<EmployeeDTOInterface> employees;
   employees=new TreeSet<>();
   
   File file=new File(FILE_NAME);
   if(file.exists()==false) return employees;
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0) return employees;
   String employeeId;
   String name;
   int fDesignationCode;
   raf.readLine();
   raf.readLine();
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   EmployeeDTOInterface employeeDTO;
   char fGender;      
   while(raf.getFilePointer()<raf.length())
   {
    employeeId=raf.readLine();
    name=raf.readLine();
    fDesignationCode=Integer.parseInt(raf.readLine());
    if(fDesignationCode!=designationCode)
    {
     
     for(int x=1;x<=6;x++)
     raf.readLine();
     continue;
    }    
    
    employeeDTO=new EmployeeDTO();  
    employeeDTO.setEmployeeId(employeeId);
    employeeDTO.setName(name);
    employeeDTO.setDesignationCode(designationCode);
    try
    {
    employeeDTO.setDateOfBirth(sdf.parse(raf.readLine()));
    }catch(ParseException pe)
    {
     }
    fGender=raf.readLine().charAt(0);
    if(fGender=='F') 
    employeeDTO.setGender(GENDER.MALE);
    else
    employeeDTO.setGender(GENDER.FEMALE);
    employeeDTO.setIsIndian(Boolean.parseBoolean(raf.readLine()));
    employeeDTO.setBasicSalary(new BigDecimal(raf.readLine()));
    employeeDTO.setPANNumber(raf.readLine());
    employeeDTO.setAadharCardNumber(raf.readLine());
   
    employees.add(employeeDTO);
    
   }
   raf.close();
   return employees;
  }catch(IOException ioException)
  {
    throw new DAOException(ioException.getMessage());
  }  
 
 }

public boolean isDesignationAlloted(int designationCode) throws DAOException
 {
   if(designationCode<=0)
  throw new DAOException("Invalid designation:"+designationCode);
  
  DesignationDAOInterface designationDAO=new DesignationDAO();
  boolean isDesignationCodeValid=designationDAO.codeExists(designationCode);
  if(!isDesignationCodeValid)
  throw new DAOException("Invalid desiganation:"+designationCode);

   try
  {
       File file=new File(FILE_NAME);
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    if(raf.length()==0)
  {
   raf.close();
   return false;
  }
  
  raf.readLine().trim();
  raf.readLine().trim();
  boolean isDesignationCodeAlloted=false;
  int fDesignationCode;
  int count=0,x;    
  while(raf.getFilePointer()<raf.length())
   {
   
    raf.readLine();
    raf.readLine(); 
    
    fDesignationCode=Integer.parseInt(raf.readLine());
    if(designationCode==fDesignationCode)
    {
     isDesignationCodeAlloted=true;
     break;
    }
     for(x=1;x<=6;x++) raf.readLine(); 
     }
  raf.close();
   return isDesignationCodeAlloted;
  }
  catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  } 
 }

public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException
 {
  try
  {
   
   File file=new File(FILE_NAME);
   if(file.exists()==false) throw new DAOException("Invalid employeeId:");
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0) throw new DAOException("Invalid employeeId::");
   String fEmployeeIdString="";
   raf.readLine();
   raf.readLine();
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   EmployeeDTOInterface employeeDTO;
   char fGender;           
   String fEmployeeId="";
   while(raf.getFilePointer()<raf.length())
   {
    fEmployeeId=raf.readLine();
       
    if(!fEmployeeIdString.equalsIgnoreCase(employeeId))
    {
     for(int x=1;x<=8;x++)
     raf.readLine();
     continue;
    }    
    
    employeeDTO=new EmployeeDTO();  
    employeeDTO.setEmployeeId(employeeId);
    employeeDTO.setName(raf.readLine());
    employeeDTO.setDesignationCode(Integer.parseInt(raf.readLine()));
    try
    {
    employeeDTO.setDateOfBirth(sdf.parse(raf.readLine()));
    }catch(ParseException pe)
    {
     }
    fGender=raf.readLine().charAt(0);
    if(fGender=='M')
    {
    employeeDTO.setGender(GENDER.MALE);
    }
    else if(fGender=='F')employeeDTO.setGender(GENDER.FEMALE);
    
    employeeDTO.setIsIndian(Boolean.parseBoolean(raf.readLine()));
    employeeDTO.setBasicSalary(new BigDecimal(raf.readLine()));
    employeeDTO.setPANNumber(raf.readLine());
    employeeDTO.setAadharCardNumber(raf.readLine());
    raf.close();
    return employeeDTO;
   }
   raf.close();
    throw new DAOException("Invalid employeeId");
  }catch(IOException ioException)
  {
    throw new DAOException(ioException.getMessage());
  }  
} 
 

public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException
 {
  if(panNumber==null)
  {
   throw new DAOException("Pan number is null");
  }

  panNumber=panNumber.trim();
  if(panNumber.length()==0)
  {
   throw new DAOException("Length of pan Number is 0");
  }

  try
  {
   
   File file=new File(FILE_NAME);
   if(file.exists()==false) throw new DAOException("Invalid Pan number:");
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0)
   {
    raf.close();
    throw new DAOException("Invalid Pan number");
   }
   String fPanNumber="";
   raf.readLine();
   raf.readLine();
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   EmployeeDTOInterface employeeDTO;
   String employeeId;
   String name;
   String aadharNumber="";
   BigDecimal basicSalary;
   Date dateOfBirth=null;
   
   boolean isIndian;
   int designationCode;
   char fGender;        
   while(raf.getFilePointer()<raf.length())
   {
    employeeId=raf.readLine();
    name=raf.readLine();
    designationCode=Integer.parseInt(raf.readLine());
    try
    {
     dateOfBirth=sdf.parse(raf.readLine());
     }catch(ParseException pe)
     {
     }
    fGender=(raf.readLine().charAt(0));
    isIndian=Boolean.parseBoolean(raf.readLine());
    basicSalary=new BigDecimal(raf.readLine());
    fPanNumber=raf.readLine();
    if(!fPanNumber.equalsIgnoreCase(panNumber))
    {
     raf.readLine();
     continue;
    }    
    
    employeeDTO=new EmployeeDTO();  
    employeeDTO.setEmployeeId(employeeId);
    employeeDTO.setName(name);
    employeeDTO.setDesignationCode(designationCode);
    
    employeeDTO.setDateOfBirth(dateOfBirth);
    if(fGender=='M') 
    employeeDTO.setGender(GENDER.MALE);
    else if(fGender=='F') employeeDTO.setGender(GENDER.FEMALE);
    employeeDTO.setIsIndian(isIndian);
    employeeDTO.setBasicSalary(basicSalary);
    employeeDTO.setPANNumber(panNumber);
    employeeDTO.setAadharCardNumber(raf.readLine());
    raf.close();
    return employeeDTO;
   }
   raf.close();
    throw new DAOException("Invalid Pan number");
  }catch(IOException ioException)
  {
    throw new DAOException(ioException.getMessage());
  }
 }



public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
 {
try
  {
   
   File file=new File(FILE_NAME);
   if(file.exists()==false) throw new DAOException("Invalid aadhar number:");
   RandomAccessFile raf=new RandomAccessFile(file,"rw");
   if(raf.length()==0)
   {
    raf.close();
    throw new DAOException("Invalid aadhar number");
   }
   String fAadharNumber="";
   raf.readLine();
   raf.readLine();
   SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
   EmployeeDTOInterface employeeDTO;
   String employeeId;
   String name;
   String panNumber="";
   BigDecimal basicSalary;
   Date dateOfBirth=null;
   char fGender;
   boolean isIndian;
   int designationCode;        
   while(raf.getFilePointer()<raf.length())
   {
    employeeId=raf.readLine();
    name=raf.readLine();
    designationCode=Integer.parseInt(raf.readLine());
    try
    {
     dateOfBirth=sdf.parse(raf.readLine());
     }catch(ParseException pe)
     {
     }
    fGender=(raf.readLine().charAt(0));
    isIndian=Boolean.parseBoolean(raf.readLine());
    basicSalary=new BigDecimal(raf.readLine());
    panNumber=raf.readLine();
    fAadharNumber=raf.readLine();
    if(fAadharNumber.equalsIgnoreCase(aadharCardNumber))
    {
    employeeDTO=new EmployeeDTO();  
    employeeDTO.setEmployeeId(employeeId);
    employeeDTO.setName(name);
    employeeDTO.setDesignationCode(designationCode);
    
    employeeDTO.setDateOfBirth(dateOfBirth);
    if(fGender=='M')
    employeeDTO.setGender(GENDER.MALE);
    else if(fGender=='F')
    employeeDTO.setGender(GENDER.FEMALE);
    employeeDTO.setIsIndian(isIndian);
    employeeDTO.setBasicSalary(basicSalary);
    employeeDTO.setPANNumber(panNumber);
    employeeDTO.setAadharCardNumber(fAadharNumber);
    raf.close();
    return employeeDTO;
    }
    }    
    raf.close();
    throw new DAOException("Invalid aadhar number");
  }catch(IOException ioException)
  {
    throw new DAOException(ioException.getMessage());
  }
 }
 public EmployeeDTOInterface getByCode(int code) throws DAOException
 {
 throw new DAOException("Not yet implemented");
 }
  
  
  
  
 
public boolean employeeIdExists(String title) throws DAOException
 {
  try
  {
       File file=new File(FILE_NAME);
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    if(raf.length()==0)
  {
   raf.close();
   return false;
  }
  
  raf.readLine().trim();
  raf.readLine().trim();
  boolean employeeIdExists=false;
  String fEmployeeIdString="";
  int count=0,x;    
  while(raf.getFilePointer()<raf.length())
   {
    fEmployeeIdString=raf.readLine().trim();
     if(title.equalsIgnoreCase(fEmployeeIdString))
    {
     employeeIdExists=true;
     break;
    }
    for(x=1;x<=8;x++) raf.readLine(); 
     }
  raf.close();
   return employeeIdExists;
  }
  catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  } 
 
 } 
 
public boolean panNumberExists(String title) throws DAOException
 {
  try
  {
       File file=new File(FILE_NAME);
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    if(raf.length()==0)
  {
   raf.close();
   return false;
  }
  
  raf.readLine().trim();
  raf.readLine().trim();
  boolean panNumberExists=false;
  String fpanNumber="";
  int count=0,x;    
  while(raf.getFilePointer()<raf.length())
   {
    for(x=1;x<8;x++) raf.readLine();
    fpanNumber=raf.readLine();
    raf.readLine();
     
    if(title.equalsIgnoreCase(fpanNumber))
    {
     panNumberExists=true;
     break;
    } 
     }
  raf.close();
   return panNumberExists;
  }
  catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  } 
 }
 
 public boolean aadharCardExists(int code) throws DAOException
 {
  String aadharCardNumber=String.valueOf(code);
  try
  {
       File file=new File(FILE_NAME);
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    if(raf.length()==0)
  {
   raf.close();
   return false;
  }
  
  raf.readLine().trim();
  raf.readLine().trim();
  boolean aadharCardExists=false;
  String fAadharCard="";
  int count=0,x;    
  while(raf.getFilePointer()<raf.length())
   {
    for(x=1;x<=8;x++) raf.readLine();
    fAadharCard=raf.readLine();
    if(aadharCardNumber.equalsIgnoreCase(fAadharCard))
    {
     System.out.println("kdf");
     aadharCardExists=true;
     break;
    } 
     }
  raf.close();
   return aadharCardExists;
  }
  catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }  
 }
 

public int getCount() throws DAOException
 {
  try
  {
  File file=new File(FILE_NAME);
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  if(raf.length()==0)
  {
   raf.close();
   return 0;
  }
  
  raf.readLine().trim();
  int recordCount;
  recordCount=Integer.parseInt(raf.readLine().trim());
  return recordCount;
  }
  catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }
 }
 
 public int getCountByDesignation(int designationCode) throws DAOException
 {
  if(designationCode<=0)
  throw new DAOException("Invalid designation:"+designationCode);
  
  DesignationDAOInterface designationDAO=new DesignationDAO();
  boolean isDesignationCodeValid=designationDAO.codeExists(designationCode);
  if(!isDesignationCodeValid)
  throw new DAOException("Invalid desiganation:"+designationCode);
 
  try
   {
    File file=new File(FILE_NAME);
    RandomAccessFile raf=new RandomAccessFile(file,"rw");
    if(raf.length()==0)
  {
   raf.close();
   return 0;
  }
  
  raf.readLine().trim();
  int recordCount;
  recordCount=Integer.parseInt(raf.readLine().trim());
  if(recordCount==0) return 0;
  int count=0,x;    
  while(raf.getFilePointer()<raf.length())
  {
   for(x=1;x<=2;x++) raf.readLine();
   if(designationCode==Integer.parseInt(raf.readLine()))
   {
    count++;
   }
   for(x=1;x<=6;x++) raf.readLine();
  }
  raf.close();
  return count;
  }catch(IOException ioException)
  {
   throw new DAOException(ioException.getMessage());
  }   
 }
}  