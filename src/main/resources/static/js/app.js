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
	};
}(app));
