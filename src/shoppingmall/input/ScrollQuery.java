package shoppingmall.input;

import java.util.Date;


public abstract class ScrollQuery {
	private int pageSize;
	private long minvalue;
	private Date mindate;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getMinvalue() {
		return minvalue;
	}
	public void setMinvalue(long minvalue) {
		this.minvalue = minvalue;
	}
	public Date getMindate() {
		return mindate;
	}
	public void setMindate(Date mindate) {
		this.mindate = mindate;
	}

	
}
