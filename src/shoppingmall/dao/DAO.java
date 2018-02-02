package shoppingmall.dao;

import org.apache.ibatis.session.SqlSession;

public class DAO {

	private SqlSession session;
	
	private DAOUser daoUser;
	private DAOGoods daoGoods;
	private DAOGoodsOrder daoGoodsOrder;
	private DAOGoodsRefund daoGoodsRefund;
	private DAOBalance daoBalance;
	private DAOSysMessage daoSysMessage;
	
	public DAO(SqlSession session){
		this.session = session;
	}
	
	public DAOBalance getDAOBalance()throws Exception{
		if(daoBalance == null){
			daoBalance = session.getMapper(DAOBalance.class);
		}
		return daoBalance;
	}
	
	public DAOUser getDAOUser()throws Exception{
		if(daoUser == null){
			daoUser = session.getMapper(DAOUser.class);
		}
		return daoUser;
	}
	
	public DAOGoods getDAOGoods()throws Exception{
		if(daoGoods == null){
			daoGoods = session.getMapper(DAOGoods.class);
		}
		return daoGoods;
	}
	
	public DAOGoodsOrder getDAOGoodsOrder()throws Exception{
		if(daoGoodsOrder == null){
			daoGoodsOrder = session.getMapper(DAOGoodsOrder.class);
		}
		return daoGoodsOrder;
	}
	
	public DAOGoodsRefund getDAOGoodsRefund()throws Exception{
		if(daoGoodsRefund == null){
			daoGoodsRefund = session.getMapper(DAOGoodsRefund.class);
		}
		return daoGoodsRefund;
	}
	
	public DAOSysMessage getDAOSysMessage()throws Exception{
		if(daoSysMessage == null){
			daoSysMessage = session.getMapper(DAOSysMessage.class);
		}
		return daoSysMessage;
	}
}
