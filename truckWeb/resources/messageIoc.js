var ioc = {
	messageSevice : {
		type : "com.truck.service.MessageService",
		args : [ {
			refer : "dao"
		} ]
	},
	messageModule : {
		type : "com.truck.mvc.MessageModule",
		fields : {
			messageService : {
				refer : "messageSevice"
			}
		}
	}
};