package banking.accounts.application.dao;

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

import banking.accounts.domain.entity.BankAccount;
import banking.customers.domain.entity.Customer;

@Service
public class AccountsDAO implements IAccountsDAO {

JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<BankAccount> getAccountIdCustomer(Long id_customer) {		
		String sql="SELECT 	bank_account_id, number, balance, islocked,customer_id FROM bank_account WHERE customer_id = "+"'"+id_customer+"'"  ;
 	 return template.query(sql,new ResultSetExtractor<List<BankAccount>>(){  
		    
		     public List<BankAccount> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException {  
		      
		        List<BankAccount> list=new ArrayList<BankAccount>();  
		        
		        while(rs.next()){  
		        	BankAccount bankAccount=new BankAccount(); 
		        	
		        	bankAccount.setId(rs.getInt(1));
		        	bankAccount.setNumber(rs.getString(2));
		        	bankAccount.setBalance(rs.getBigDecimal(3));
		        	bankAccount.setIslocked(rs.getBoolean(4));
		        	bankAccount.setCustomer_id(rs.getLong(5));
		        list.add(bankAccount);  
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
	public List<BankAccount> getallAccount(int offset, int limit) {
		String sql="SELECT 	bank_account_id, number, balance, islocked,customer_id FROM bank_account LIMIT "+ ""+offset+", "+""+limit+""  ;
		System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<BankAccount>>(){  
		    
		     public List<BankAccount> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException { 
		        List<BankAccount> list=new ArrayList<BankAccount>(); 		        
		        while(rs.next()){  
		        	BankAccount bankAccount=new BankAccount();
		        	bankAccount.setId(rs.getInt(1));
		        	bankAccount.setNumber(rs.getString(2));
		        	bankAccount.setBalance(rs.getBigDecimal(3));
		        	bankAccount.setIslocked(rs.getBoolean(4));
		        	bankAccount.setCustomer_id(rs.getLong(5));
		        list.add(bankAccount);  
		        }  
		        return list;  
		        }  
		    });  
		  }

	@Override
	public List<BankAccount> getAccountNroCuenta(String accountNumber) {
		
		String sql="SELECT bank_account_id, number, balance, islocked,customer_id FROM bank_account WHERE islocked = 0 and number =  "+"'"+accountNumber+"'";
				System.out.println(sql);
		return template.query(sql,new ResultSetExtractor<List<BankAccount>>(){  
		    
		     public List<BankAccount> extractData(ResultSet rs) throws SQLException,  
		            DataAccessException { 
		        List<BankAccount> list=new ArrayList<BankAccount>(); 		        
		        while(rs.next()){  
		        	BankAccount bankAccount=new BankAccount();
		        	bankAccount.setId(rs.getInt(1));
		        	bankAccount.setNumber(rs.getString(2));
		        	bankAccount.setBalance(rs.getBigDecimal(3));
		        	bankAccount.setIslocked(rs.getBoolean(4));
		        	bankAccount.setCustomer_id(rs.getLong(5));
		        list.add(bankAccount);  
		        }  
		        return list;  
		        }  
		    });  
		  }
	
}
