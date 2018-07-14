package banking.customers.application.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import banking.customers.application.dto.CustomerDto;
import banking.customers.domain.entity.Customer;
import banking.security.domain.entity.User;

@Service
public class CustomerDAO implements ICustomerDAO {
	  
	
JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	public List<Customer> getLoginCustomer(String Message,String user, String password) {		
		String sql="SELECT c.customer_id ,c.first_name,c.last_name,c.birth_date,c.document_number,c.isactive,c.cellphone,c.email,c.user,c.password, i.detalle AS perfil  FROM customer c INNER JOIN rol i ON c.customer_id = i.id_rol  WHERE  isactive = 1 and c.user ="+"'"+user+"'" + " AND c.password ="+"'"+password+"'"  ;
		System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<Customer>>(){  		    
		     public List<Customer> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  		      
		        List<Customer> list=new ArrayList<Customer>();
		        while(rs.next()){  
		        	Customer customer=new Customer();
		        	customer.setId(rs.getInt(1));
		        	customer.setFirstName(rs.getString(2));
		        	customer.setLastName(rs.getString(3));
		        	customer.setBirthDate(rs.getDate(4));
		        	customer.setDocumentNumber(rs.getString(5));
		        	customer.setIsactive(rs.getBoolean(6));	 
		        	customer.setCellphone(rs.getString(7));
		        	customer.setEmail(rs.getString(8));
		        	customer.setUser(rs.getString(9));
		        	customer.setPassword(rs.getString(10)); 
		        	customer.setId_rol(rs.getString(11));
		        	customer.setMessage("bearer " + Message);
		        list.add(customer);  
		        }  
		        return list;  
		        }  
		    });  
		  }
	
	public List<Customer> getLoginCustomerN(String user, String password) {		
		String sql="SELECT c.customer_id ,c.first_name,c.last_name,c.cellphone ,c.document_number , i.detalle AS perfil  FROM customer c INNER JOIN rol i ON c.customer_id = i.id_rol  WHERE  isactive = 1 and c.user ="+"'"+user+"'" + " AND c.password ="+"'"+password+"'"  ;
		System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<Customer>>(){  		    
		     public List<Customer> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  		      
		        List<Customer> list=new ArrayList<Customer>();
		        while(rs.next()){  
		        	Customer customer=new Customer(); 
		        	customer.setId(rs.getInt(1));
		        	customer.setFirstName(rs.getString(2));
		        	customer.setLastName(rs.getString(3));
		        	customer.setCellphone(rs.getString(4));
		        	customer.setDocumentNumber(rs.getString(5));
		        	customer.setId_rol(rs.getString(6));
		        list.add(customer);  
		        }  
		        return list;  
		        }  
		    });  
		  }
	
	private static List<String> convertDelimitedStringToList(String delimitedString) {
		List<String> result = new ArrayList<String>();
		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;
	}

	@Override
	public List<Customer> getallCustomer(int offset, int limit) {		
		String sql="SELECT customer_id,first_name,last_name,birth_date,document_number,isactive,cellphone,email,USER,PASSWORD,id_rol FROM customer  LIMIT "+ ""+offset+", "+""+limit+"" ;
		System.out.println(sql);
		 return template.query(sql,new ResultSetExtractor<List<Customer>>(){  		    
		     public List<Customer> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  		      
		        List<Customer> list=new ArrayList<Customer>();
		        while(rs.next()){  
		        	Customer customer=new Customer(); 		        	
		        	customer.setId(rs.getInt(1));
		        	customer.setFirstName(rs.getString(2));
		        	customer.setLastName(rs.getString(3));
		        	customer.setBirthDate(rs.getDate(4));
		        	customer.setDocumentNumber(rs.getString(5));
		        	customer.setIsactive(rs.getBoolean(6));	 
		        	customer.setCellphone(rs.getString(7));
		        	customer.setEmail(rs.getString(8));
		        	customer.setUser(rs.getString(9));
		        	customer.setPassword(rs.getString(10)); 
		        	customer.setId_rol(rs.getString(11));
		       
		        list.add(customer);  
		        }  
		        return list;  
		        }  
		    });  
		  }

	@Override
	public List<Customer> getNrodocCustomer(String documentNumber) {
		String sql="SELECT customer_id,first_name,last_name,birth_date,document_number,isactive,cellphone,email,USER,PASSWORD,id_rol FROM customer where isactive = 1 and  document_number = "+ ""+documentNumber+"" ;
		System.out.println(sql);
		 return template.query(sql,new ResultSetExtractor<List<Customer>>(){  		    
		     public List<Customer> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  		      
		        List<Customer> list=new ArrayList<Customer>();
		        while(rs.next()){  
		        	Customer customer=new Customer(); 		        	
		        	customer.setId(rs.getInt(1));
		        	customer.setFirstName(rs.getString(2));
		        	customer.setLastName(rs.getString(3));
		        	customer.setBirthDate(rs.getDate(4));
		        	customer.setDocumentNumber(rs.getString(5));
		        	customer.setIsactive(rs.getBoolean(6));	 
		        	customer.setCellphone(rs.getString(7));
		        	customer.setEmail(rs.getString(8));
		        	customer.setUser(rs.getString(9));
		        	customer.setPassword(rs.getString(10)); 
		        	customer.setId_rol(rs.getString(11));
		       
		        list.add(customer);  
		        }  
		        return list;  
		        }  
		    });  
		  }

	@Override
	public List<CustomerDto> findByUserName(String username, String password) {
		String sql="SELECT c.customer_id ,c.first_name,c.last_name,c.birth_date,c.document_number,c.isactive,c.cellphone,c.email,c.user,c.password, i.detalle AS perfil  FROM customer c INNER JOIN rol i ON c.customer_id = i.id_rol  WHERE  isactive = 1 and c.user ="+"'"+username+"'" + " AND c.password ="+"'"+password+"'"  ;
		System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<CustomerDto>>(){  		    
		     public List<CustomerDto> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  		      
		        List<CustomerDto> list=new ArrayList<CustomerDto>();
		        while(rs.next()){  
		        	CustomerDto customerDto=new CustomerDto(); 
		        	customerDto.setId(rs.getInt(1));
		        	customerDto.setFirstName(rs.getString(2));
		        	customerDto.setLastName(rs.getString(3));
		        	customerDto.setBirthDate(rs.getDate(4));
		        	customerDto.setDocumentNumber(rs.getString(5));
		        	customerDto.setIsactive(rs.getBoolean(6));	 
		        	customerDto.setCellphone(rs.getString(7));
		        	customerDto.setEmail(rs.getString(8));
		        	customerDto.setUser(rs.getString(9));
		        	customerDto.setPassword(rs.getString(10)); 
		        	customerDto.setId_rol(rs.getString(11));
		        list.add(customerDto);  
		        }  
		        return list;  
		        }  
		    });  
		  }

	
	
		
}
