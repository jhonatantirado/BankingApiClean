package banking.transdetalle.application.dao;

import java.util.List;
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
import banking.transdetalle.domain.entity.transDetalle;

@Service
public class transDetalleDAO implements ItransDetalleDAO  {
	
JdbcTemplate template;  
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new JdbcTemplate(dataSource);
	}
	
	
	public void savetransDetalle(transDetalle p) {	
		
	String sql="INSERT INTO trans_detalle( numb_origen, numb_destino, balance,islocked,bank_account_id, customer_id) "
			+ "values('"+p.getNumb_destino()+"','"+p.getNumb_destino()+"',"+p.getBalance()+","+p.getIslocked()+","+p.getBank_account_id()+","+p.getCustomer_id()+")";
	System.out.println(sql);
	 template.update(sql);
			}
	
	@Override
	public List<transDetalle> getCustomertransDetalle(Long customerid) {			
			String sql="SELECT 	id_trans,numb_origen,numb_destino,balance,islocked,bank_account_id,customer_id	FROM trans_detalle WHERE customer_id ="+"'"+customerid+"'" ;
			System.out.println(sql);
			return template.query(sql,new ResultSetExtractor<List<transDetalle>>(){  
			    public List<transDetalle> extractData(ResultSet rs) throws SQLException,  
			            DataAccessException {  
			       List<transDetalle> list=new ArrayList<transDetalle>(); 
			        while(rs.next()){  
			        	transDetalle ttransDetalle=new transDetalle();
			        	ttransDetalle.setId(rs.getInt(1));
			        	ttransDetalle.setNumb_origen(rs.getString(2));
			        	ttransDetalle.setNumb_destino(rs.getString(3));
			        	ttransDetalle.setBalance(rs.getBigDecimal(4));
			        	ttransDetalle.setIslocked(rs.getBoolean(5));
			        	ttransDetalle.setBank_account_id(rs.getLong(6));
			        	ttransDetalle.setCustomer_id(rs.getLong(6));
			            list.add(ttransDetalle);  
			        }  
			        return list;  
			        }  
			    });  
			  }

}
