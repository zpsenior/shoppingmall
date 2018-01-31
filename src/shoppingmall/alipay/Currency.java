package shoppingmall.alipay;

import java.math.BigDecimal;

public class Currency {

	public final static Currency ZERO = new Currency("0");
	
	private BigDecimal value;
	
	public Currency() {
		this.value = BigDecimal.ZERO;
	}

	public Currency(String amount) {
		if(amount == null){
			this.value = BigDecimal.ZERO;
		}else{
			this.value = new BigDecimal(amount);
		}
	}

	public Currency(Double amount) {
		if(amount == null){
			this.value = BigDecimal.ZERO;
		}else{
			this.value = new BigDecimal(amount);
		}
	}
	
	public String toString(){
		return value.toString();
	}
	
	public final static BigDecimal ONE_HUNDRED = BigDecimal.TEN.multiply(BigDecimal.TEN);
	
	public static Currency parseFen(int fen){
		BigDecimal amt = new BigDecimal(fen);
		Currency cur = new Currency();
		cur.value = amt.divide(ONE_HUNDRED, 2, BigDecimal.ROUND_HALF_UP);
		return cur;
	}
	
	public Currency add(Currency cur){
		Currency ret = new Currency();
		ret.value = value.add(cur.value);
		return ret;
	}
	
	public Currency subtract(Currency cur){
		Currency ret = new Currency();
		ret.value = value.subtract(cur.value);
		return ret;
	}
	
	public Currency multiply(Currency cur){
		Currency ret = new Currency();
		ret.value = value.multiply(cur.value);
		return ret;
	}
	
	public Currency divide(Currency cur){
		Currency ret = new Currency();
		ret.value = value.divide(cur.value, 2);
		return ret;
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}

	public BigDecimal getValue() {
		return this.value;
	}
	
	public int getHundredValue(){
		value = value.multiply(BigDecimal.TEN).multiply(BigDecimal.TEN);
		return value.intValue();
	}
}
