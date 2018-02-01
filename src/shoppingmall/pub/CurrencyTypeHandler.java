package shoppingmall.pub;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import shoppingmall.alipay.Currency;

@MappedTypes({Currency.class})
public class CurrencyTypeHandler implements TypeHandler<Currency> {

	public CurrencyTypeHandler() {
	}

	@Override
	public Currency getResult(ResultSet rs, String columnName) throws SQLException {
		Double columnValue = rs.getDouble(columnName); 
		if(columnValue == null){
			return null;
		}
		return new Currency(columnValue);
	}

	@Override
	public Currency getResult(ResultSet rs, int columnIndex) throws SQLException {
		Double columnValue = rs.getDouble(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return new Currency(columnValue);
	}

	@Override
	public Currency getResult(CallableStatement cs, int columnIndex)throws SQLException {
		Double columnValue = cs.getDouble(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return new Currency(columnValue);
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, Currency parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			ps.setNull(i, Types.NUMERIC);
		else {
			ps.setDouble(i, parameter.doubleValue());
		}
	}

}
