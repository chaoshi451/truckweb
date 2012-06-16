jQuery.extend({
	/**
	 * Extend jQuery to serialize a object to json string.
	 * 
	 * @param Object,
	 *            support type:
	 *            object,array,string,function,number,boolean,regexp *
	 * @return json string
	 */
	toJSON : function(object) {
		if (object === null) return "null";
		if (!object)
			return null;
		var type = typeof object;
		if ('object' == type) {
			if (Array == object.constructor)
				type = 'array';
			else if (RegExp == object.constructor)
				type = 'regexp';
			else
				type = 'object';
		}
		switch (type) {
		case 'undefined':
		case 'unknown':
			return;
			break;
		case 'function':
		case 'boolean':
		case 'regexp':
			return object.toString();
			break;
		case 'number':
			return isFinite(object) ? object.toString() : 'null';
			break;
		case 'string':
			return '"'
					+ object.replace(/(\\|\")/g, "\\$1").replace(
							/\n|\r|\t/g,
							function() {
								var a = arguments[0];
								return (a == '\n') ? '\\n'
										: (a == '\r') ? '\\r'
												: (a == '\t') ? '\\t' : ""
							}) + '"';
			break;
		case 'object':
			if (object === null)
				return 'null';
			var results = [];
			for ( var property in object) {
				var value = jQuery.toJSON(object[property]);
				if (value !== undefined)
					results.push(jQuery.toJSON(property) + ':' + value);
			}
			return '{' + results.join(',') + '}';
			break;
		case 'array':
			var results = [];
			for ( var i = 0; i < object.length; i++) {
				var value = jQuery.toJSON(object[i]);
				if (value !== undefined)
					results.push(value);
			}
			return '[' + results.join(',') + ']';
			break;
		}
	},
	fromJSON : function(jStr) {
		return (new Function('return ' + jStr))();
	}
});

String.prototype.trim=function(){
     return this.replace(/(^\s*)|(\s*$)/g, '');
}
$.isEmpty = function(v) {
	switch (typeof v){
	case 'undefined' : 
		return true;
		break;
	case 'string' : 
		if(v.length == 0 || v.trim().length == 0) {
			return true; 
		}
		break;
	case 'boolean' : 
		if(!v) {
			return true;
		}
		break;
	case 'number' : 
		if(0 === v) {
			return true; 
		}
		break;
	case 'object' :
		if(null === v) {
			return true;
		}
		if(undefined !== v.length && v.length==0) {
			return true;
		}
		for(var k in v) {
			return false;
		}
		return true;
		break;
	}
	return false;
}

/*!
 * Map:
 * 	方法：
 * 	1.put(key,value)
 * 	2.get(key) --> value
 * 	3.size() -->the size of map
 *  4.remove(key)
 *  5.setValue(key, value)
 *  6.getEntry(index) -->{key:xx,value:xx}
 *  7.indexOf(key) -->the index of key
 *  8.isEmpty() --> true/false.
 *  9.clear()
 */
var Map = function(){
    var Entry = function(key, value){
        this.key = key;
        this.value = value;
    };
	this.entries = new Array();
    this.put = function(key, value){
        for (var i = 0; i < this.entries.length; i++) {
            if (this.entries[i].key === key) {
                return false;
            }
        }
        this.entries.push(new Entry(key, value));
		return true;
    };
    this.get = function(key){
        for (var i = 0; i < this.entries.length; i++) {
            if (this.entries[i].key === key) {
                return this.entries[i].value;
            }
        }
        return null;
    };
	this.remove = function(key) {
		var index = this.indexOf(key);
		if(index != -1) {
			this.entries.splice(index,1);
		}
	};
	this.setValue = function(key, value) {
		var index = this.indexOf(key);
		if(index != -1) {
			this.entries[index].value = value;
		}
	};
	this.getEntry = function(index) {
		if(index >= 0 && index < this.size()) {
			return this.entries[index];
		}
		return null;
	};
    this.size = function(){
        return this.entries.length;
    };
    this.isEmpty = function(){
        return this.entries.length <= 0;
    };
	this.clear = function() {
		this.entries = [];
	};
	this.indexOf = function(key) {
		var index = -1;
		for(var i=0; i<this.size(); i++) {
			if(key == this.entries[i].key) {
				index = i;
				break;
			}
		}
		return index;
	};
	this.toString = function() {
		var str = '[';
		for(var i=0; i<this.size(); i++) {
			str += (this.entries[i].key + '=' 
				+ this.entries[i].value + ',')
		}
		str = str.substr(0, str.length-1);
		str += ']';
		return str;
	};
}

function clone(jsonObj, newName) {
    var buf;
    if (jsonObj instanceof Array) {
        buf = [];
        var i = jsonObj.length;
        while (i--) {
            buf[i] = clone(jsonObj[i], newName);
        }
        return buf;
    }else if (typeof jsonObj == "function"){
        return jsonObj;
    }else if (jsonObj instanceof Object){
        buf = {};
        for (var k in jsonObj) {
	        if (k!="parentNode") {
	            buf[k] = clone(jsonObj[k], newName);
	            if (newName && k=="name") buf[k] += newName;
	        }
        }
        return buf;
    }else{
        return jsonObj;
    }
}

/**
 * 获取根路径(eg:http://localhost:8080/pmsapp)
 */
function getRootPath(){
	var curWwwPath=window.document.location.href;
	var pathName=window.document.location.pathname;
	var pos=curWwwPath.indexOf(pathName);
	var localhostPaht=curWwwPath.substring(0,pos);
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	return(localhostPaht+projectName);
}

/**
 * 公用ajax方法(json数据格式)
 * @param {Object} url
 * @param {Object} data
 * @param {Object} callback
 * @param {Object} failureCallback
 */
function ajax(url, data, callback, failureCallback) {
	//if (url == null) url = getRootPath() + "/dispatcher.action";
	$.ajax({
		url : url,
		dataType : "json",
		type : "post",
		data : data,
		timeout : 300000,
		success : function(data, textStatus, XMLHttpRequest) {
			if (data) {
				callback(data, textStatus, XMLHttpRequest);
			}
		},
		error : function() {
			
		}
	});
}

/**
 * 获取地址栏参数值
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	} 
	return null;
}

function getUrlParamByKey(key){
	key = key+"=";
    var str = window.location.search;
    if (str.indexOf(key) != -1) {
        var pos_start = str.indexOf(key) + key.length;
        var pos_end = str.indexOf("&", pos_start);
        if (pos_end == -1) {
            return str.substring(pos_start);
        }
        else {
            return str.substring(pos_start, pos_end)
        }
    }
    else {
        return null;
    }
}