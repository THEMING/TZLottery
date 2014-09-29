/**
 * 后台公用js类和方法
 */

/* 后台js命名空间 */
var caiso = {
	version : "1.0"
};
/* 后台公用方法命名空间 */
caiso.fun = {};
/* 后台共用类组件命名空间 */
caiso.base = {};
/* 浏览器版本 */
caiso.browser = {};
(function() {
	var ua = navigator.userAgent.toLowerCase();
	if (ua.indexOf("msie") > 0) {
		caiso.browser.ie = true;
	} else if (ua.indexOf("firefox") > 0) {
		caiso.browser.firefox = true;
	} else if (ua.indexOf("chrome") > 0) {
		caiso.browser.chrome = true;
	} else if (ua.indexOf("opera") > 0) {
		caiso.browser.opera = true;
	}
})();

/**
 * 给String类添加trim（）支持，截断前后空格
 * 
 * @return 返回截断空格的字符串
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

/**
 * 自定义JS Map对象
 */
caiso.base.Map = function() {
	var array = new Array();
	// 存放一个Map元素
	this.put = function(key, value) {
		this.remove(key);
		var struct = new Struct(key, value);
		array.push(struct);
	};
	// 删除Map中的一个元素
	this.remove = function(key) {
		for ( var i = 0; i < array.length; i++) {
			var v = array.pop();
			if (v.key == key) {
				continue;
			}
			array.unshift(v);
		}
	};
	// 根据key查询map中的元素
	this.get = function(key) {
		for ( var i = 0; i < array.length; i++) {
			if (array[i].key == key) {
				return array[i].value;
			}
		}
		return null;
	};
	this.getMap = function() {
		return array;
	};
	// Map的数据结构
	function Struct(key, value) {
		this.key = key;
		this.value = value;
	}
};

/**
 * 查找复选框选中值
 * 
 * @param name
 *            复选框名称
 * @return 选中值数组
 */
caiso.fun.getCheckbox = function(name) {
	var __box = null;
	/* 一组复选框 */
	var __select = new Array();
	/* 返回的选中结果 */
	__box = document.getElementsByName(name);
	for ( var i = 0; i < __box.length; i++) {
		if (__box[i].checked === true) {
			__select.push(__box[i].value);
		}
	}
	return __select;
};

/**
 * 组合url参数
 * 
 * @param array
 *            要组合的数组
 * @param name
 *            传递参数的名称
 * @return 组合后的参数 如 ?name=1&name=2
 */
caiso.fun.joinArgs = function(array, name) {
	var __join_url = "";
	/* 组合后的url参数 */
	if (array == null || array.length <= 0) {
		return "";
	}
	for ( var i = 0; i < array.length; i++) {
		if (i === 0)
			__join_url += "?" + name + "=" + array[i];
		else {
			__join_url += "&" + name + "=" + array[i];
		}
	}
	return __join_url;
};

/**
 * 公用批量删除方法
 * 
 * @param __del_url
 *            删除后台路径
 * @param __ids
 *            删除传入ID名称
 * @param __checkboxName
 *            记录的复选框名称
 * @param formid
 *            页面formID
 */
caiso.fun.deleted = function(__del_url, __ids, __checkboxName, formid) {
	var __param_ids = "";/* 组合后要删除的操作员ID */
	if (document.getElementsByName(__checkboxName) == null
			|| document.getElementsByName(__checkboxName).length <= 0) {
		alert("没有要删除的数据!");
		return null;
	}
	/* 选中的复选框值 */
	var __select = caiso.fun.getCheckbox(__checkboxName);
	if (__select == null || __select.length <= 0) {
		alert("请选择您要删除的数据!");
		return null;
	}
	/* 组合求情参数 */
	__param_ids = caiso.fun.joinArgs(__select, __ids);
	if (confirm("确定删除选中记录?")) {
		var form = document.getElementById(formid);
		var ac = form.action;
		form.action = __del_url + __param_ids;
		form.submit();
		form.action = ac;
	}
};

/**
 * 公用批量导入数据方法
 * 
 * @param __imp_url
 *            导入后台路径
 * @param __ids
 *            删除传入ID名称
 * @param __checkboxName
 *            记录的复选框名称
 * @param formid
 *            页面formID
 */
caiso.fun.impdata = function(__imp_url, __ids, __checkboxName, formid, _tips) {
	var __param_ids = "";/* 组合后要删除的操作员ID */
	if (document.getElementsByName(__checkboxName) == null
			|| document.getElementsByName(__checkboxName).length <= 0) {
		alert("没有要导入的数据!");
		return null;
	}
	/* 选中的复选框值 */
	var __select = caiso.fun.getCheckbox(__checkboxName);
	if (__select == null || __select.length <= 0) {
		alert("请选择您要导入的数据!");
		return null;
	}
	/* 组合求情参数 */
	__param_ids = caiso.fun.joinArgs(__select, __ids);
	if (confirm("确定导入选中记录?")) {
		var form = document.getElementById(formid);
		var ac = form.action;
		form.action = __imp_url + __param_ids;
		form.submit();
		form.action = ac;
	}
};

/**
 * 公用批量操作
 * 
 * @param __imp_url
 *            导入后台路径
 * @param __ids
 *            操作传入ID名称
 * @param __checkboxName
 *            记录的复选框名称
 * @param formid
 *            页面formID
 * @param _tips
 * 			弹出提示        
 */
caiso.fun.batch = function(__imp_url, __ids, __checkboxName, formid, _tips) {
	var __param_ids = "";/* 组合后要删除的操作员ID */
	if (document.getElementsByName(__checkboxName) == null
			|| document.getElementsByName(__checkboxName).length <= 0) {
		alert("没有要"+_tips+"!");
		return null;
	}
	/* 选中的复选框值 */
	var __select = caiso.fun.getCheckbox(__checkboxName);
	if (__select == null || __select.length <= 0) {
		alert("请选择您要"+_tips+"!");
		return null;
	}
	/* 组合求情参数 */
	__param_ids = caiso.fun.joinArgs(__select, __ids);
	if (confirm("确定导入选中记录?")) {
		var form = document.getElementById(formid);
		var ac = form.action;
		form.action = __imp_url + __param_ids;
		form.submit();
		form.action = ac;
	}
};

/**
 * 移动两个select中的元素
 * 
 * @param selectBoxId
 *            减少元素的控件
 * @param resultBoxId
 *            添加元素的控件
 */
caiso.fun.moveSelectItem = function(selectBoxId, resultBoxId) {
	/* 权限列表box */
	var __selectBox = document.getElementById(selectBoxId);
	/* 结果列表box */
	var __resultBox = document.getElementById(resultBoxId);
	if (__selectBox.selectedIndex == -1) {
		alert("请选择您要操作的数据！");
		return;
	}
	var __option_items_index = new Array();
	for ( var i = 0; i < __selectBox.options.length; i++) {
		if (__selectBox.options[i].selected) {
			__option_items_index.push(i);
		}
	}
	/* 必须从数组末端往前端删除，因为删除select的一个选项时，它内部的索引会改变 */
	for ( var i = __option_items_index.length - 1; i >= 0; i--) {
		var __item = __selectBox.options[__option_items_index[i]];
		caiso.fun.addItems(__resultBox, __item.value, __item.innerHTML);
		caiso.fun.removeItems(__selectBox, __option_items_index[i]);
	}
};

/**
 * select控件添加选项
 * 
 * @param currentBox
 *            当前控件对象
 * @param value
 *            控件值
 * @param label
 *            控件显示值
 */
caiso.fun.addItems = function(currentBox, value, label, color) {
	var __option = document.createElement("option");
	__option.value = value;
	__option.innerHTML = label;
	if (color) {
		__option.style.color = color;
	}
	currentBox.appendChild(__option);
};

/**
 * 删除select选项
 * 
 * @param currentBox
 *            当前select控件
 * @param index
 *            删除的索引
 */
caiso.fun.removeItems = function(currentBox, index) {
	currentBox.remove(index);
};

/**
 * 删除selectbox所有option
 * 
 * @param currentBox
 *            select控件对象
 */
caiso.fun.removeAllItems = function(currentBox) {
	if (currentBox.options <= 0)
		return;
	for ( var i = currentBox.options.length - 1; i >= 0; i--) {
		currentBox.remove(i);
	}
};

/**
 * 获取单选按钮的值
 * 
 * @param name
 *            单选按钮名称
 */
caiso.fun.getRadioValue = function(name) {
	var names = document.getElementsByName(name);
	if (names == null || names.length == 0) {
		return null;
	} else {
		var ra = null;
		for ( var i = 0; i < names.length; i++) {
			if (names[i].checked) {
				ra = names[i].value;
			}
		}
		return ra;
	}
};

/**
 * 
 * @param url
 *            路径
 * @param data
 *            参数
 * @param selectId
 *            控件ID
 * @param defaultOption
 *            默认选项
 */
caiso.fun.ajaxCascade = function(url, data, selectId, resultName,
		lableProperty, valueProperty, defaultOption) {
	caiso.fun.send(url, data, function(json) {
		/* 后台返回list数据 */
		var __result = json[resultName];
		/* 要级联的selectbox对象 */
		var __currentBox = document.getElementById(selectId);
		/* 删除所有option */
		caiso.fun.removeAllItems(__currentBox);
		caiso.fun.addItems(__currentBox, "", defaultOption);
		for ( var i = 0; i < __result.length; i++) {
			caiso.fun.addItems(__currentBox, __result[i][valueProperty],
					__result[i][lableProperty]);
		}
		if (document.getElementById("span_" + selectId)) {
			document.getElementById("span_" + selectId).style.display = "";
		}
	});
};

/**
 * 统一ajax访问
 * 
 * @param url
 *            访问路径
 * @param data
 *            参数，json格式
 * @param success
 *            成功后调用的方法
 */
caiso.fun.send = function(url, data, success) {
	$.ajax( {
		type : "post",
		url : url,
		data : data,
		dataType : "json",
		success : function(data, status) {
			success(data);
		},
		error : function(data, status) {
			alert("ajax请求出现异常.");
		}
	});
};

caiso.fun.ajaxHtml = function(url, data, success) {
	$.ajax( {
		type : "post",
		url : url,
		data : data,
		dataType : "html",
		success : function(data, status, httpXmlRequest) {
			success(data);
		},
		error : function(data, status) {
			alert("ajax请求出现异常.");
		}
	});
};

/**
 * 表单提交，解决用js提交时，不能验证表单的问题
 */
caiso.fun.submitCheck = function(formid) {
	document.getElementsByName("PaginationButton_CurrentPage4list")[0].value = 1;
	var form = document.getElementById(formid);
	if (form.onsubmit) {
		if (form.onsubmit()) {
			form.submit();
		}
	} else {
		form.submit();
	}
};

/**
 * 数字验证
 * 
 * @param f
 *            要验证的数字
 */
caiso.fun.validateInt = function(f) {
	var regex = /^[0-9]+$/;
	if (!regex.test(f)) {
		return false;
	}
	if (parseInt(f) <= 0) {
		return false;
	}
	return true;
};

caiso.fun.validateEmpty = function(str) {
	if (str === null || str.trim() == "") {
		return true;
	}
	return false;
};

caiso.fun.validateDate = function(date) {
	var regex = /^[1-9][0-9]{3}-(0?[1-9]|1[0|1|2])-(0?[1-9]|[1|2][0-9]|3[0|1])\s(0?[1-9]|1[0-9]|2[0-3]):(0?[0-9]|[1|2|3|4|5][0-9])$/;
	return regex.test(date);
};

/**
 * 时间比较
 * 
 * @param start
 *            开始时间
 * @param end
 *            结束时间
 * @return -1 : start小于end 0 : start等于end 1 ：start大于end
 */
caiso.fun.compareDate = function(start, end) {
	var _start = start.replace(/\-/, '\/');
	var _end = end.replace(/\-/, '\/');
	if (_start > _end) {
		return 1;
	} else if (_start == _end) {
		return 0;
	} else {
		return -1;
	}
};

caiso.fun.enterSub = function(domId, success) {
	document.getElementById(domId).onkeydown = function(event) {
		event = (event == null) ? window.event : event;
		if (event.keyCode == 13) {
			success();
		}
	};
};
