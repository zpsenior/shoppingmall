drop table user;
create table user(
	userseq			numeric(12) not null,  #用户唯一序号
	loginname 		varchar(20) not null,  #登录名
	nationcode      varchar(3)  not null,  #手机国家码
	mobileno		varchar(15) not null,  #手机
	openid          varchar(64),           #微信id
	nickname		varchar(30) ,          #昵称
	headpoto		varchar(200),          #头像
	nation          varchar(20)  not null, #国家
	province        varchar(30)  not null, #省
	city            varchar(30)  not null, #城市
	status			char(1) default '0',   #用户状态 0-正常；1-锁定
	platform		varchar(10) ,          #手机系统
	device			varchar(64) ,          #手机系统序号
	createtime		datetime,
	lastlogin		datetime,
	primary key(userseq),
	unique key idx_username(username),
	unique key idx_mobileno(nationcode, mobileno)
);

drop table user_auth;
create table user_auth(
	userseq			numeric(12) not null,  #用户唯一序号
	password		varchar(60),           #密码。MD5加密
	truename		varchar(30) ,          #认证真名
	idtype			char(1) default '0',   #证件类型：0-身份证
	idno			varchar(18),           #证件号码
	backchannel		char(1),			   #W-微信，A-支付宝, B-银行 ,w-微信
	bankname		varchar(40),           #银行名称
	username  		varchar(30) ,          #我的姓名
	cardno			varchar(20),		   #银行卡号
	alipay			varchar(40),           #支付宝账号
	wpay			varchar(40),           #微信账号
	certify			char(1) default '0',   #0-未认证,1-已付款等待认证,2-已认证
	certifytime		datetime,              #认证时间
	updatetime      datetime
	primary key(userseq)
);

drop table user_contact;
create table user_contact(
	userseq			numeric(12) not null,  #用户唯一序号
	mobileno		varchar(30) not null,  #联系电话
	contactname		varchar(50) not null,  #联系人
	createtime		datetime,
	primary key(userseq, contactname, mobileno)
);

drop table user_addr;
create table user_addr(
	userseq		numeric(12)  not null, 	#用户唯一序号
	addrno		int not null, 		    #地址序号
	recipients 	varchar(20)  not null,  #收件人
	nation      varchar(20)  not null,  #国家
	province    varchar(30)  not null,  #省
	city        varchar(30)  not null,  #城市
	addr        varchar(128) not null,  #邮寄地址
	mobileno    varchar(20),            #联系电话
	defaultaddr char(1) default '0',    #缺省地址： 0-非缺省；1-缺省
	createtime		datetime,
	primary key(userseq, addrno)
);

drop table feedback;
create table feedback(
	fbseq			numeric(16) not null,   #意见反馈唯一序号
	userseq			numeric(12) not null,   #用户唯一序号
	content			varchar(200),           #消息内容
	status			char(1) default '0',    #意见反馈状态，0-未读，1-已读
	createtime		datetime,
	primary key(fbseq),
	unique key idx_accu(fbseq, userseq)
);

drop table wpay_notify;
create table wpay_notify(
	payid			varchar(32) not null,   #
	transaction_id	varchar(32) not null,   #微信单号
	tradedate		varchar(8),
	nonce_str		varchar(32),
	sign			varchar(32),
	sign_type		varchar(32),
	result_code		varchar(16),
	openid			varchar(128),
	is_subscribe	varchar(1),
	trade_type		varchar(16),
	bank_type		varchar(16),
	total_fee		int,
	fee_type		varchar(8),
	cash_fee		int,
	cash_fee_type	varchar(16),
	coupon_fee		int,
	coupon_count	int,
	attach			varchar(128),
	time_end		datetime,
	err_code		varchar(32),
	err_code_des	varchar(128),
	createtime		datetime,
	primary key(payid)
);

drop table wpay_coupon;
create table wpay_coupon(
	payid			varchar(32) not null,  
	no				int not null,   
	coupon_type		varchar(20),
	coupon_id		varchar(20),
	coupon_fee		int,
	createtime		datetime,
	primary key(payid, no)
);

drop table wpay_bill;
create table wpay_bill(
	tradedate				varchar(8) not null,	#日期
	total_count				int default 0,
	total_amount			int default 0,
	refund_count			int default 0,
	refund_amount			int default 0,
	coupon_refund_amount	int default 0,
	total_fee				int default 0,
	createtime				datetime,
	primary key(tradedate)
);

drop table wpay_bill_item;
create table wpay_bill_item(
	payid				varchar(32) not null,	#商户单号
	transaction_id		varchar(32) not null,   #微信单号
	tradedate			varchar(8),
	trade_time			datetime,				#交易时间
	openid				varchar(128),			#用户标识
	trade_type			varchar(16),			#交易类型
	trade_state			varchar(32),			#交易状态
	bank_type			varchar(16),			#付款银行
	fee_type			varchar(8),				#货币种类
	total_fee			int,					#总金额
	coupon_fee			int,					#代金券或立减优惠金额
	refund_id			varchar(32),			#微信退款单号
	out_refund_no		varchar(64),			#商户退款单号
	refund_fee			int,					#退款金额
	coupon_refund_fee	int,					#代金券或立减优惠退款金额
	refund_channel		varchar(16),			#退款类型
	refund_status		varchar(16),			#退款状态
	refund_apply_time	datetime,				#退款申请时间
	refund_success_time	datetime,				#退款成功时间
	body				varchar(128),			#商品名称
	attach				varchar(128),			#商户数据包
	charge				numeric(10,5),			#手续费
	charge_rate			numeric(10,4),			#费率
	billType			char(1),
	createtime			datetime,
	primary key(payid, refund_id)
);

#alipay

drop table apay_notify;
create table apay_notify(
	notify_time			datetime,		#通知时间
	notify_type			varchar(64),	#通知类型
	notify_id			varchar(128) not null,	#通知校验ID
	trade_no			varchar(64)  not null,	#支付宝交易号
	payid				varchar(64)  not null,	#商户订单号 out_trade_no
	out_biz_no			varchar(64),	#商户业务号
	buyer_id			varchar(16),	#买家支付宝用户号
	buyer_logon_id		varchar(100),	#买家支付宝账号
	seller_id			varchar(30),	#卖家支付宝用户号
	seller_email		varchar(100),	#卖家支付宝账号
	trade_status		varchar(32),	#交易状态
	total_amount		numeric(9,2),	#订单金额
	receipt_amount		numeric(9,2),	#实收金额
	invoice_amount		numeric(9,2),	#开票金额
	buyer_pay_amount	numeric(9,2),	#付款金额
	point_amount		numeric(9,2),	#集分宝金额
	refund_fee			numeric(9,2),	#总退款金额
	subject				varchar(256),	#订单标题
	body				varchar(400),	#商品描述
	gmt_create			datetime,		#交易创建时间
	gmt_payment			datetime,		#交易付款时间
	gmt_refund			datetime,		#交易退款时间
	gmt_close			datetime,		#交易结束时间
	fund_bill_list		varchar(512),	#支付金额信息
	passback_params		varchar(512),	#回传参数
	voucher_detail_list	varchar(1000),	#优惠券信息
	createtime			datetime,
	primary key(notify_id)
);


drop table apay_bill;
create table apay_bill(
	tradedate		           varchar(10) not null,   #日期
	total_trade_count          int default 0,
	total_trade_amount         numeric(9,2) default 0,
	total_trade_pref_amount    numeric(9,2) default 0,
	total_refund_count         int default 0,
	total_refund_amount        numeric(9,2) default 0,
	total_refund_pref_amount   numeric(9,2) default 0,
	total_alipay_dis_amount    numeric(9,2) default 0,
	total_store_dis_amount     numeric(9,2) default 0,
	total_card_cons_amount     numeric(9,2) default 0,
	total_fee                  numeric(9,2) default 0,
	total_share_profit         numeric(9,2) default 0,
	createtime		           datetime,
	primary key(tradedate)
);

drop table apay_bill_item;
create table apay_bill_item(
		trade_no                    varchar(64) not null,
		payid                       varchar(64) not null,
		trade_type                  varchar(5),
		subject                     varchar(256),
		gmt_create                  datetime,
		gmt_payment                 datetime,
		store_id                    varchar(32),
		store_name                  varchar(512),
		operator_id                 varchar(28),
		terminal_id                 varchar(32),
		buyer_logon_id              varchar(100),
		total_amount                numeric(9,2) default 0,  #订单金额
		receipt_amount              numeric(9,2) default 0,  #交易金额
		alipay_red_packet_amount    numeric(9,2) default 0,  #阿里红包
		point_amount                numeric(9,2) default 0,  #集分宝抵扣
		alipay_discount_amount      numeric(9,2) default 0,  #阿里优惠
		store_discount_amount       numeric(9,2) default 0,  #商家优惠
		coupon_verify_amount        numeric(9,2) default 0,  #券核销金额
		coupon_name                 varchar(128),            #优惠券名称
		store_red_packet_amount     numeric(9,2) default 0,  #商家红包
		card_consume_amount         numeric(9,2) default 0,  #卡消费金额
		out_request_no              varchar(64),             #退款批次号
		fee                         numeric(9,2) default 0,  #服务费
		share_profit                numeric(9,2) default 0,  #分润
		remark                      varchar(128),            #备注
		tradedate			        varchar(8),
		createtime			        datetime,
		primary key(payid, out_request_no)
);

#sys

drop table sys_message;
create table sys_message(
	msgseq			numeric(16) not null,   #唯一序号
	touserseq		numeric(16) not null,   #接收者
	kind			char(1),				#消息类型：0-系统通知，1-视频回复通知
	title			varchar(100),          	#标题
	content			varchar(1000),          #内容
	status			char(1) default '0',    #状态，0-未读，1-已读
	createtime		datetime,
	primary key(msgseq, touserseq)
);

#--- back office

drop table admin;
create table admin(
	adminseq		numeric(10) not null,   #管理员唯一序号
	adminname 		varchar(20) not null,   #登录名
	mobileno		varchar(15) ,           #手机                                                                                                                                                                                                                                  
	status			char(1) default '0',    #用户状态 0-正常；1-锁定
	password		varchar(60),            #密码。MD5加密
	adminrole		numeric(10),            #权限
	createtime		datetime,
	primary key(adminseq)
);

drop table admin_log;
create table admin_log(
	logseq			numeric(16) not null,   #意见反馈唯一序号
	adminseq		numeric(10) not null,   #管理员唯一序号
	adminname 		varchar(20) not null,   #登录名
	content			varchar(200),           #日志内容
	createtime		datetime,
	primary key(logseq),
	unique key idx_admin_log(logseq, adminseq)
);

#goods

drop table goods_kind;
create table goods_kind(
	kind            int not null,           #商品类型
	name            varchar(20) not null,   #商品类型名称
	url             varchar(100),           #商品类型图片路径
	ord				numeric(10),            #排列序号                                                                                                                                                                         
	status			char(1) default '0',    #用户状态 0-正常；1-锁定
	createtime		datetime,
	primary key(goodskind)
);


drop table goods_favorite;
create table goods_favorite(
	userseq		    numeric(12) not null,   #收藏者
	goodsseq	    numeric(12) not null,   #商品序号
	createtime		datetime,
	primary key(userseq, goodsseq)
);

drop table shopping_cart;
create table shopping_cart(
	userseq		    numeric(12) not null,   #所有者
	goodsseq	    numeric(12) not null,   #商品序号
	count	        int not null,           #商品数量
	createtime      datetime,
	primary key(userseq, goodsseq)
);

drop table post_company;
create table post_company(
	compid	    varchar(10) not null,  #快递公司编号
	compname	varchar(30),           #快递公司名称
	primary key(compid)
);

drop table express_trace;
create table express_trace(
	postcompid    varchar(10) not null,   #快递公司编号
	postorder     varchar(30) not null,   #快递单号
	traceseq      int not null,           #状态序号
	orderseq      int,                    #订单序号
	acceptstation varchar(100),           #状态描述
	accepttime    datetime,
	remark        varchar(100),
	primary key(postcompid, postorder, traceseq)
);

drop table nation_code;
create table nation_code(
	nationname	varchar(100),
	chinesename varchar(100),
	abbr        varchar(3) not null,
	code        varchar(5) not null,
	primary key(code, abbr)
);


drop table goods_order;
create table goods_order(
	orderseq 	    numeric(12) not null,   #订单编号
	buyerseq        numeric(12) not null,   #购买者
	total           int not null,           #总金额
	postfee         int not null,           #邮费
	title           varchar(500) not null,  #描述
	paychannel	    char(1),                #支付渠道
	prepayid	    varchar(64),            #预付订单号
	payid	        varchar(64),            #支付订单号
	paytime	        datetime,               #支付时间
	status	        char(1) default '0',    #状态：0-未支付待付款,1-已支付待发货,2-已发货,3-已关闭,4-拒绝，5-已完成（收货）
	recipients	    varchar(20),            #收件人
	nation          varchar(50),            #国家
	province        varchar(50),            #省
	city            varchar(50),            #城市
	addr	        varchar(128),           #邮寄地址
	mobileno	    varchar(20),            #联系电话
	postorder     	varchar(60),             #快递单号
	postcomp	    varchar(10),             #快递公司
	deliverytime    datetime,                #发货时间
	createtime      datetime,
	primary key(orderseq)
);

drop table goods_order_item;
create table goods_order_item(
	orderseq 	    numeric(12)  not null,   #订单编号
	goodsseq 	    numeric(12)  not null,   #商品编号
	goodsname 	    varchar(100) not null,   #商品名称
	sellerseq       numeric(12)  not null,   #卖家
	count           int not null,            #数量
	price           int not null,            #单价
	status	        char(1) default '0',     #状态：0-正常，1-申请退款
	createtime      datetime,
	primary key(orderseq, goodsseq)
);

drop table goods_order_refund;
create table goods_order_refund(
	refundseq 	    numeric(12)  not null,   #退款编号
	orderseq 	    numeric(12)  not null,   #订单编号
	goodsseq 	    numeric(12)  not null,   #商品编号
	refundno        int not null,            #次数
	buyerseq        numeric(12)  not null,   #购买者
	sellerseq       numeric(12)  not null,   #卖家
	reason          varchar(200),            #申请退款原因
	imgs		    varchar(200),            #上传图片
	amount	        int default '0',         #退款金额
	count	        int default '0',         #退款数量
	status	        char(1) default '0',     #状态：0-正常，1-申请退款,2-取消
	postorder     	varchar(60),             #退款快递单号
	postcomp	    varchar(10),             #退款快递公司
	paychannel	    char(1),                 #退款渠道
	payid	        varchar(64),             #退款订单号
	refundtime	    datetime,                #退款时间
	createtime      datetime,
	primary key(refundseq)
);

drop table goods_review;
create table goods_review(
	orderseq 	    numeric(12) not null,   #订单编号
	goodsseq 	    numeric(12) not null,   #商品编号
	buyerseq        numeric(12) not null,   #购买者
	userseq         numeric(12) not null,   #卖家
	score           int default 0,          #评分
	review		    varchar(200),           #评论
	imgs		    varchar(200),           #上传图片
	status          char(1) default '0',    #评论状态：0-正常，1-屏蔽
	createtime      datetime,
	primary key(orderseq, goodsseq)
);

drop table user_review;
create table user_review(
	orderseq 	    numeric(12) not null,   #订单编号
	goodsseq 	    numeric(12) not null,   #商品编号
	sellerseq       numeric(12) not null,   #卖家
	userseq         numeric(12) not null,   #买家
	score           int default 0,          #评分
	review		    varchar(200),           #评论
	imgs		    varchar(200),           #上传图片
	status          char(1) default '0',    #评论状态：0-正常，1-屏蔽
	createtime      datetime,
	primary key(orderseq, goodsseq)
);

drop table user_balance;
create table user_balance(
	balseq        numeric(12) not null,   #流水号
	userseq       numeric(12) not null,   #所有者
	type          char(1),                #类型：0-手续费, 1-提现, 2-商品卖出， 3-商品退款， 4-视频卖出
	seq           numeric(12),            #订单号 orderseq 或 videoseq 
	descript      varchar(200),           #描述
	amount        int not null,           #交易金额
	balance       int not null,           #余额
	channel       char(1),                #支付渠道
	payid         varchar(64),            #订单号
	paytime       datetime,
	createtime    datetime,
	primary key(balseq)
);

drop table goods_sort;
create table tp_goods_sort(
	goodsseq 	   numeric(12) not null,   #商品编号
	browsecount    int default 0,          #浏览数
	saleamount     int default 0,          #交易总额权重
	salecount      int default 0,          #交易总数权重
	praisescore    numeric(15) default 0,  #评价总分权重
	praisecount    int default 0,          #评价次数
	refreshtime    datetime,               #刷新时间
	ord            numeric(15) default 0,  #排序号
	ontop          int default 0,          #置顶排位：1-9
	createtime     datetime,
	primary key(goodsseq)
);
create index idx_goods_sort on goods_sort(ord);


drop table user_score;
create table user_score(
	userseq       numeric(12) not null,   #所有者
	goodscount    int default 0,          #在售商品数量
	saleamount    int default 0,          #销售总额
	salecount     int default 0,          #销售总量
	praisescore   numeric(15) default 0,  #评价总分
	praisecount   int default 0,          #评价次数
	buyerpraise   numeric(15) default 0,  #商户评价总分
	buyercount    int default 0,          #商户评价次数
	primary key(userseq)
);

