package com.thinking.machines.hr.dl.DAO;
import com.thinking.machines.hr.dl.interfaces.DAO.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.DTO.*;
import java.util.*;
import java.io.*;

public class DesignationDAO implements DesignationDAOInterface
{
private final static String FILE_NAME="designation.data";
public  void add(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO==null) throw new DAOException("Designation is null");
String title=designationDTO.getTitle();
if(title==null) throw new DAOException("Designation is null");

title=title.trim();
if(title.length()==0) throw new DAOException("Length of designation is zero");

try
{
File file=new File(FILE_NAME);
RandomAccessFile raf=new RandomAccessFile(file,"rw");
int lastGeneratedCode=0;
int recordCount=0;
String lastGeneratedCodeString="";
String recordCountString="";
if(raf.length()==0)
{
lastGeneratedCodeString="0";
while(lastGeneratedCodeString.length()<10) lastGeneratedCodeString+=" ";
recordCountString="0";
while(recordCountString.length()<10) recordCountString+=" ";
raf.writeBytes(lastGeneratedCodeString);
raf.writeBytes("\n");
raf.writeBytes(recordCountString);
raf.writeBytes("\n");
}
else
{
lastGeneratedCodeString=raf.readLine().trim();
recordCountString=raf.readLine().trim();
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString);
recordCount=Integer.parseInt(recordCountString);
}
int fCode;
String fTitle;
while(raf.getFilePointer()<raf.length())
{
fCode=Integer.parseInt(raf.readLine());
fTitle=raf.readLine();
if(fTitle.equalsIgnoreCase(title)==true)
{
raf.close();
throw new DAOException("Designation :"+title+" exists");
}
}
int code=lastGeneratedCode+1;

raf.writeBytes(String.valueOf(code));
raf.writeBytes("\n");
raf.writeBytes(title);
raf.writeBytes("\n");

designationDTO.setCode(code);

raf.seek(0);
lastGeneratedCode++;
recordCount++;
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
while(lastGeneratedCodeString.length()<10) lastGeneratedCodeString+=" ";
recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10) recordCountString+=" ";

raf.writeBytes(lastGeneratedCodeString);
raf.writeBytes("\n");
raf.writeBytes(recordCountString);
raf.writeBytes("\n");
raf.close();
}
catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(DesignationDTOInterface designationDTO) throws DAOException
{
if(designationDTO==null) throw new DAOException("Designation is null");
int code=designationDTO.getCode();

if(code<=0) throw new DAOException("Invalid code:"+code);

String title=designationDTO.getTitle();

if(title==null) throw new DAOException("Designation is null");
title=title.trim();

if(title.length()==0) throw new DAOException("Length of designation is zero");
try
{
File file=new File(FILE_NAME);
if(file.exists()==false) throw new DAOException("Invalid code:"+code);
RandomAccessFile raf=new RandomAccessFile(file,"rw");
if(raf.length()==0)
{
 raf.close();
 throw new DAOException("Invalid designation");
}

raf.readLine();
raf.readLine();
int fCode;
String fTitle;
boolean found=false;
while(raf.getFilePointer()<raf.length())
{
 fCode=Integer.parseInt(raf.readLine());
 if(fCode==code) 
 {
 
  found=true;
  break;
 }
 raf.readLine();
}
if(found==false)
{
raf.close();
throw new DAOException("Invalid code:"+code);
}
raf.seek(0);
raf.readLine();
raf.readLine();
while(raf.getFilePointer()<raf.length())
{
 fCode=Integer.parseInt(raf.readLine());
 fTitle=raf.readLine();
 if(code!=fCode && title.equalsIgnoreCase(fTitle)==true)
 {
  raf.close();
  throw new DAOException(title+" exists");
 }
}

File tmpFile=new File("tmp.data");
if(tmpFile.exists()) tmpFile.delete();
 RandomAccessFile tmpRaf=new RandomAccessFile(tmpFile,"rw");
 raf.seek(0);
 tmpRaf.writeBytes(raf.readLine());
 tmpRaf.writeBytes("\n");
 tmpRaf.writeBytes(raf.readLine());
 tmpRaf.writeBytes("\n");
 
while(raf.getFilePointer()<raf.length())
{
 fCode=Integer.parseInt(raf.readLine());
 fTitle=raf.readLine();
 if(fCode!=code)
 {
  tmpRaf.writeBytes(String.valueOf(fCode));
  tmpRaf.writeBytes("\n");
  tmpRaf.writeBytes(fTitle);
  tmpRaf.writeBytes("\n");
 }
 else
 {
  tmpRaf.writeBytes(String.valueOf(code));
  tmpRaf.writeBytes("\n");
  tmpRaf.writeBytes(title);
  tmpRaf.writeBytes("\n");
 }
}
raf.seek(0);
tmpRaf.seek(0);

while(tmpRaf.getFilePointer()<tmpRaf.length())
{
 raf.writeBytes(tmpRaf.readLine());
 raf.writeBytes("\n");
}
raf.setLength(tmpRaf.length());
tmpRaf.setLength(0);
tmpRaf.close();
raf.close();
}catch(IOException ioException)
 {
 throw new DAOException(ioException.getMessage());
 }
}

public void delete(int code) throws DAOException
{
if(code<=0) throw new DAOException("Invalid code "+code);
try
 {
  File file=new File(FILE_NAME);
  if(file.exists()==false)
  {
  throw new DAOException("Invalid code:"+code);
  }
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  raf.readLine();
  raf.readLine();
  int fCode;
  String fTitle="";
  boolean found=false;
  while(raf.getFilePointer()<raf.length())
  {
   fCode=Integer.parseInt(raf.readLine());
   fTitle=raf.readLine();
   if(fCode==code)
   {
    found=true;
    break;
   }        
  }
  
  if(found==false)
  {
   raf.close();
   throw new DAOException("Invalid code:"+code);
  }
  if(new EmployeeDAO().isDesignationAlloted(code))
  {
  raf.close();
  throw new DAOException("Employee exists with designation "+fTitle);
  }
  
  raf.seek(0);
  File tmpFile=new File("tmp.data");
  if(tmpFile.exists()) tmpFile.delete();
  RandomAccessFile tmpRaf=new RandomAccessFile(tmpFile,"rw");
  
  int recordCount;
  tmpRaf.writeBytes(raf.readLine());
  tmpRaf.writeBytes("\n");
  recordCount=Integer.parseInt(raf.readLine().trim());
  String recordCountString=String.valueOf(recordCount-1);
  while(recordCountString.length()<10) recordCountString+=" ";
  tmpRaf.writeBytes(recordCountString);
  tmpRaf.writeBytes("\n");
  while(raf.getFilePointer()<raf.length())
  {
   fCode=Integer.parseInt(raf.readLine());
   fTitle=raf.readLine();
   if(fCode!=code)
   {
   tmpRaf.writeBytes(String.valueOf(fCode));
   tmpRaf.writeBytes("\n");
   tmpRaf.writeBytes(fTitle);
   tmpRaf.writeBytes("\n");
   }
  }
 tmpRaf.seek(0);
 raf.seek(0);
 raf.writeBytes(tmpRaf.readLine());
 raf.writeBytes("\n");
 raf.writeBytes(tmpRaf.readLine());
 raf.writeBytes("\n");
 while(tmpRaf.getFilePointer()<tmpRaf.length())
 {
  raf.writeBytes(tmpRaf.readLine());
  raf.writeBytes("\n");
 }
 raf.setLength(tmpRaf.length());
 tmpRaf.seek(0);
 raf.close();
 tmpRaf.close();
}catch(IOException ioException)
 {
  throw new DAOException("Invalid code:"+code);
 }   
}
 
public Set<DesignationDTOInterface> getAll() throws DAOException
{
 
  Set<DesignationDTOInterface> designations;
  designations=new TreeSet<>();
  try
  {
  File file=new File(FILE_NAME);
  if(file.exists()==false) return designations;
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  if(raf.length()==0) 
  {
   raf.close();
   return designations;
  }
  raf.readLine();  
  int recordCount=Integer.parseInt(raf.readLine().trim());
  if(recordCount==0)
  {
   raf.close();
   return designations;
  }
  DesignationDTOInterface designationDTO;
  int fCode;
  String fTitle;
  while(raf.getFilePointer()<raf.length())
  {
   fCode=Integer.parseInt(raf.readLine());
   fTitle=raf.readLine();
   designationDTO=new DesignationDTO();
   designationDTO.setCode(fCode);
   designationDTO.setTitle(fTitle); 
   designations.add(designationDTO);
  }
  raf.close();
  return designations;
  }catch(IOException ioException)
  {
  throw new DAOException(ioException.getMessage());
  }
 }
 
public DesignationDTOInterface getByCode(int code) throws DAOException
{
 if(code<=0) throw new DAOException("Invalid code:"+code);
try
{
 File file=new File(FILE_NAME);
 if(file.exists()==false) 
 {
  throw new DAOException("Invalid code: "+code);
 }
 RandomAccessFile raf;
 raf=new RandomAccessFile(file,"rw");
 if(raf.length()==0) 
 {
 raf.close();
 throw new DAOException("Invalid code: "+code);
 } 
 raf.readLine();
 int recordCount=Integer.parseInt(raf.readLine().trim());
 if(recordCount==0) 
 {
  throw new DAOException("No record added yet");
 }
 int fCode;
 String fTitle;
 while(raf.getFilePointer()<raf.length())
 {
 fCode=Integer.parseInt(raf.readLine().trim());
 fTitle=raf.readLine();
  if(fCode==code)
  { 
   DesignationDTOInterface designationDTO;
   designationDTO=new DesignationDTO();
   designationDTO.setTitle(fTitle);
   designationDTO.setCode(fCode);
   raf.close();
   return designationDTO;
  }
 }
 raf.close();
throw new DAOException("Invalid code:"+code);
}catch(IOException ioException)
 {
 throw new DAOException(ioException.getMessage());
 }
}

public DesignationDTOInterface getByTitle(String title) throws DAOException
{
 if(title==null) throw new DAOException("Invalid title");
 title=title.trim();
 if(title.length()==0) throw new DAOException("Invalid title");
 try
 {
  File file=new File(FILE_NAME); 
  if(file.exists()==false) throw new DAOException("Invalid title :"+title);
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  if(raf.length()==0)
   {
   raf.close();
   throw new DAOException("Invalid code::"+title);
   }
  raf.readLine();
  int recordCount=Integer.parseInt(raf.readLine().trim());
  if(recordCount==0)
  {
  raf.close();
  throw new DAOException("no record found");
  }
  int fCode;
  String fTitle;
  while(raf.getFilePointer()<raf.length())
  {
   fCode=Integer.parseInt(raf.readLine());
   fTitle=raf.readLine();
   if(fTitle.equalsIgnoreCase(title))
   {
    DesignationDTOInterface designationDTO=new DesignationDTO();
    designationDTO.setCode(fCode);  
    designationDTO.setTitle(fTitle);
    raf.close();
    return designationDTO;
   }
  }
  raf.close();
  throw new DAOException("Invalid Title :"+title);
  }catch(IOException ioException)
 {
 throw new DAOException(ioException.getMessage());
 }
}

 
public boolean codeExists(int code) throws DAOException
{
 if(code<=0) return false;
 try
 {
  File file=new File(FILE_NAME);
  if(file.exists()==false) return false;
  RandomAccessFile raf=new RandomAccessFile(file,"rw");
  if(raf.length()==0) 
  {
  raf.close();
  return false;
  }
  raf.readLine();
  int recordCount=Integer.parseInt(raf.readLine().trim());
  if(recordCount==0) return false;
  while(raf.getFilePointer()<raf.length())
  {
  int fCode=Integer.parseInt(raf.readLine());
  if(fCode==code) 
  {
   raf.close();
   return true;
  }
  raf.readLine();
  }
 raf.close();
 return false;
 }
 catch(IOException ioException)
 {
  throw new DAOException(ioException.getMessage());
 }
}
 
public boolean titleExists(String title) throws DAOException
{
if(title==null || title.trim().length()==0) return false;
title=title.trim();
try
{
File file=new File(FILE_NAME);
if(file.exists()==false)
{
 return false;
}
RandomAccessFile raf=new RandomAccessFile(file,"rw");
if(raf.length()==0) 
{
raf.close();
return false;
}
raf.readLine();
int recordCount=Integer.parseInt(raf.readLine().trim());
if(recordCount==0) 
{
raf.close();
return false;
}
String fTitle;
while(raf.getFilePointer()<raf.length())
{
raf.readLine();
fTitle=raf.readLine();
if(title.equalsIgnoreCase(fTitle))
{
 raf.close();
 return true;
}
}
raf.close();
return false;
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
 if(file.exists()==false) 
 { 
   return 0;
 }
 
 RandomAccessFile raf=new RandomAccessFile(file,"rw");
 
 if(raf.length()==0)
 {
  raf.close();
  return 0;
 }
 raf.readLine();
 int recordCount;
 recordCount=Integer.parseInt(raf.readLine().trim());
 raf.close(); 
 return recordCount;
}
 catch(IOException ioException)
 {
  throw new DAOException(ioException.getMessage());
 }
} 
}