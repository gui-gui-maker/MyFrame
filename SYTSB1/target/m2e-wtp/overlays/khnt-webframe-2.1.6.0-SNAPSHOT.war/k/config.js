//屏蔽所有JS错误，使得出错的情况下不会弹出调试信息，建议在生产环境使用
//window.onerror = function(e) {return true;};

//载入系统配置文件，通常为动态的文件 13-4-1 下午4:57 lybide k app
//=======================================================================================
//加载系统自定义的systrem-config.js，该文件会在server启动时自动生成，默认可以把生成的路径放在app目录下,如果该文件不存在，则使用上面默认的配置
document.write(unescape('%3Cscript type="text/javascript" src="app/system-config.js"%3E%3C/script%3E'));

//动态配置项，如 base等
document.write(unescape('%3Cscript type="text/javascript" src="app/system-config.jsp"%3E%3C/script%3E'));
//=======================================================================================