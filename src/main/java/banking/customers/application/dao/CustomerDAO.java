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

import banking.customers.domain.entity.Customer;

@Service
public class CustomerDAO implements ICustomerDAO {
	  
	
JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	public List<Customer> getLoginCustomer(String user, String password) {
		// TODO Auto-generated method stub
		 String sql="select * from customer";  
		 return template.query(sql,new ResultSetExtractor<List<Customer>>(){  
		    
		     public List<Customer> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<Customer> list=new ArrayList<Customer>();  
		        
		        while(rs.next()){  
		        	Customer e=new Customer();  
		        e.setId(rs.getInt(1));
		        e.setFirstName(rs.getString(2));
		        e.setLastName(rs.getString(3));
		        list.add(e);  
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
	
}
