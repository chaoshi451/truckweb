var ioc = {
	linksSevice : {
		type : "com.truck.service.LinksService",
		args : [ {
			refer : "dao"
		} ]
	},
	linksModule : {
		type : "com.truck.mvc.LinksModule",
		fields : {
			linksService : {
				refer : "linksSevice"
			}
		}
	},
	webInfoService : {
		type : "com.truck.service.WebInfoService",
		args : [{
			refer : "dao"
		}]
	},
	webInfoModule : {
		type : "com.truck.mvc.WebInfoModule",
		fields : {
			webInfoService : {
				refer : "webInfoService"
			}
		}
	}	
};