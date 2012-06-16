var ioc = {
	attributeSevice : {
		type : "com.truck.service.AttributeService",
		args : [ {
			refer : "dao"
		} ]
	},
	attributeModule : {
		type : "com.truck.mvc.AttributeModule",
		fields : {
			attributeService : {
				refer : "attributeSevice"
			}
		}
	},
	fileModule : {
		type : "com.truck.mvc.FileModule",
		fields : {
			attributeService : {
				refer : "attributeSevice"
			}
		}
	}
};