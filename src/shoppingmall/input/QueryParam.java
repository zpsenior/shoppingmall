package shoppingmall.input;

public abstract class QueryParam {
	private int pageSize;
	private long minseq;
	private long maxseq;
	private String page;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getMinseq() {
		return minseq;
	}
	public void setMinseq(long minseq) {
		this.minseq = minseq;
	}
	public long getMaxseq() {
		return maxseq;
	}
	public void setMaxseq(long maxseq) {
		this.maxseq = maxseq;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

	public String getNext(){
		if("next".equals(page)){
			return "1";
		}
		return null;
	}

	public String getPrev(){
		if("prev".equals(page)){
			return "1";
		}
		return null;
	}
}
