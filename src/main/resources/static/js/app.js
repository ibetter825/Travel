/**
* @preserve app v1.0.0
*/
"use strict";
var app = {};
!(function(app) {
	//错误处理
	app.error = function(xhr, textStatus, exception){
    	var status = xhr.status;
    	var resp = $.parseJSON(xhr.responseText);
    	if(status){
    		switch (status) {
    		case 403:
    			layer.msg("访问被拒绝: ["+resp.message+"]", {icon:5});
    			break;
    		case 404:
    			layer.msg("访问地址不存在或已删除", {icon:5});
    			break;
    		case 504:
    			layer.msg("请求超时", {icon:5});
    			break;
    		case 500:
    			layer.msg("发生错误:["+resp.message+"]", {icon:5});
    			break;
    		case 0:
    			layer.msg("网络异常", {icon:5});
    			break;
    		default:
    			layer.msg("错误代码: ["+status+"]", {icon:5});
    			break;
    		}
    	}
    },
	//eval全局执行
	app.eval = function(code){
		var a = document .createElement ("script" );
        a.type= "text/javascript" ;
        a.text= code ;
        document .getElementsByTagName ("head" )[0 ].appendChild (a);
	},
	//JSONP
	app.jsonp = function (url, func) {
    	var callback = "jsonpCallback_" + new Date().getTime() + Math.floor(Math.random() * 1000000);
        url = url + (url.indexOf( '?' ) + 1 ? '&' : '?') + 'callback=' + callback;
	    window[callback] = function (data) {
	        func(data);
	        window[callback] = null;
	    };
	    script = document.createElement('script');
	    script.setAttribute('src', url);
	    document.body.appendChild(script);
	},
	/**
	 * localStorage
	 * key
	 * value == null 删除
	 */
	app.local = function(key, value) {
	    let storage = window.localStorage
	        //如果浏览器不支持localStorage存到Cookie中
	        //...
	        //如果value未定义则为获取key对应的值
	    if (value === undefined)
	        return storage.getItem(key)
	    else //反之则为设置或修改值或添加值或删除值
	        value === null ? storage.removeItem(key) : storage.setItem(key, value)
	};

	/**
	 * sessionStorage
	 * key
	 * value == null 删除
	 */
	app.session = function(key, value) {
	    let storage = window.sessionStorage
	        //如果浏览器不支持localStorage存到Cookie中
	        //...
	        //如果value未定义则为获取key对应的值
	    if (value === undefined)
	        return storage.getItem(key)
	    else //反之则为设置或修改值或添加值或删除值
	        value === null ? storage.removeItem(key) : storage.setItem(key, value)
	};

	/**
	 * cookie
	 * key
	 * days 过期天数
	 * value 值
	 * opt 'del' 删除
	 */
	app.cookie = function(key, value, days) {
	    if (value === undefined && document.cookie.length > 0) { //获取cookie
	        let arr, reg = new RegExp("(^| )" + key + "=([^;]*)(;|$)");
	        if (arr = document.cookie.match(reg))
	            return unescape(arr[2])
	        else
	            return null
	    } else {
	        let exdate = new Date();
	        if (value === null) {
	            exdate.setTime(exdate.getTime() - 1);
	            let cval = cookie(key);
	            if (cval != null)
	                document.cookie = key + "=" + cval + ";expires=" + exdate.toGMTString();
	        } else {
	            exdate.setDate(exdate.getDate() + days ? days : 0)
	            document.cookie = key + "=" + escape(value) +
	                (days ? "" : ";expires=" + exdate.toGMTString())
	        }
	    }
	}
}(app));
