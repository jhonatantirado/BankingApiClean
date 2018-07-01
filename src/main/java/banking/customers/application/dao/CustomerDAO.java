package banking.customers.application.dao;

import java.util.List;

import org.flywaydb.core.internal.dbsupport.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import banking.customers.domain.entity.Customer;

@Service
public class CustomerDAO implements ICustomerDAO {
	JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		//template = new JdbcTemplate(dataSource);
	}
	
	public List<Customer> getLoginCustomer(String user, String password) {
		// TODO Auto-generated method stub
		 String sql="select * from Student limit ";  
		 return null;  
		    
		  
	}
}
