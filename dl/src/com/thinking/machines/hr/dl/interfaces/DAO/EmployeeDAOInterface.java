package com.thinking.machines.hr.dl.interfaces.DAO;
import java.util.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.DTO.*;

public interface EmployeeDAOInterface
{
 public void add(EmployeeDTOInterface employeeDTO) throws DAOException;

 public void update(EmployeeDTOInterface employeeDTO) throws DAOException;
 
 public void delete(String employeeId) throws DAOException;
 
 public Set<EmployeeDTOInterface> getAll() throws DAOException;
 
 public Set<EmployeeDTOInterface> getByDesignationCode(int designationCode) throws DAOException;

 public boolean isDesignationAlloted(int designationCode) throws DAOException;

 public EmployeeDTOInterface getByEmployeeId(String employeeId) throws DAOException;
 
 public EmployeeDTOInterface getByPANNumber(String panNumber) throws DAOException;

 public EmployeeDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;

 public EmployeeDTOInterface getByCode(int code) throws DAOException;
 
 public boolean panNumberExists(String title) throws DAOException;
 
 public boolean aadharCardExists(int code) throws DAOException;
 public int getCount() throws DAOException;
 
 public int getCountByDesignation(int designationCode) throws DAOException;
}