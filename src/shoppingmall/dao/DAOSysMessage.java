package shoppingmall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import shoppingmall.input.QueryParamMessage;
import shoppingmall.input.ScrollQueryMessage;
import shoppingmall.po.SysMessage;

public interface DAOSysMessage {

	@Select({"select * from sys_message where msgseq=#{value}"})
	SysMessage getSysMessage(long msgseq)throws Exception;

	@Select({"select * from sys_message where ((touserseq=#{userseq} and kind='0') or kind='1') ",
		"and msgseq<#{minvalue} order by msgseq limit 0, ${pageSize}"})
	List<SysMessage> querySysMessageList(ScrollQueryMessage params)throws Exception;

	@Select({"select * from sys_message where "})
	List<SysMessage> querySysMessageListByAdmin(QueryParamMessage params)throws Exception;

}
