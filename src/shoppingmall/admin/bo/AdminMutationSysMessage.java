package shoppingmall.admin.bo;

import shoppingmall.po.SysMessage;
import shoppingmall.pub.BOBase;
import shoppingmall.pub.Environment;
import graphql4j.annotation.GraphQLArgument;
import graphql4j.annotation.GraphQLField;
import graphql4j.annotation.GraphQLObject;

@GraphQLObject("MMessage")
public class AdminMutationSysMessage extends BOBase {
	
	public AdminMutationSysMessage(Environment env) {
		super(env);
	}

	@GraphQLField("sendMessage")
	public boolean sendMessage(@GraphQLArgument("message") SysMessage message)throws Exception{
		return false;
	}

}
