var ioc = {
	articleSevice : {
		type : "com.truck.service.ArticleService",
		args : [ {
			refer : "dao"
		} ]
	},
	articleModule : {
		type : "com.truck.mvc.ArticleModule",
		fields : {
			articleService : {
				refer : "articleSevice"
			}
		}
	}
};