var ioc = {
	productSevice : {
		type : "com.truck.service.ProductService",
		args : [ {
			refer : "dao"
		} ]
	},
	
	productModule : {
		type : "com.truck.mvc.ProductModule",
		fields : {
			productService : {
				refer : "productSevice"
			}
		}
	}
	
};