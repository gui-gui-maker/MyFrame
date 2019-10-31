/*
 Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
 For licensing, see LICENSE.html or http://ckeditor.com/license
 */
CKEDITOR.editorConfig = function (config) {
    config.skin = 'office2003';
    config.language = 'zh-cn';//中文
    config.height="400";
    //config.uiColor = '#BFEE62';//编辑器颜色
    config.font_names = '宋体;楷体_GB2312;新宋体;黑体;隶书;幼圆;微软雅黑;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana';
    //配置工具条
    config.toolbar_Full =
        [
            ['Source', 'Preview'],
            ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord'],
            ['Undo', 'Redo', 'Replace', '-', 'SelectAll', 'RemoveFormat'],
            ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
            ['Link', 'Unlink', 'Anchor'],
            ['Image', 'Flash','flvPlayer','Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
            '/',
            ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent'],
            ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
            ['Styles', 'Format', 'Font', 'FontSize'],
            ['TextColor', 'BGColor'],
            ['Maximize']
        ];
    config.extraPlugins = 'flvPlayer';
    //编辑器中回车产生的标签
    config.enterMode = CKEDITOR.ENTER_BR; //可选： CKEDITOR.ENTER_P或CKEDITOR.ENTER_BR或CKEDITOR.ENTER_DIV
    //当输入：shift+Enter时插入的标签
    config.shiftEnterMode = CKEDITOR.ENTER_BR;  //可选：CKEDITOR.ENTER_BR或CKEDITOR.ENTER_DIV
    //当从word里复制文字进来时，是否进行文字的格式化去除 plugins/pastefromword/plugin.js
    config.pasteFromWordIgnoreFontFace = true; //默认为忽略格式
    //从word中粘贴内容时是否移除格式 plugins/pastefromword/plugin.js
    config.pasteFromWordRemoveStyle = true;
    //是否使用HTML实体进行输出 plugins/entities/plugin.js
    config.entities = true;
//设置文件的浏览路径
    //config.filebrowserBrowseUrl = "/app/webmanage/FileManager/FileView.jsp";
//设置图片的浏览路径
    //config.filebrowserImageBrowseUrl = "/app/webmanage/FileManager/FileView.jsp";
//设置flash文件浏览路径
    //config.filebrowserFlashBrowseUrl = "/app/webmanage/FileManager/FileView.jsp";


};
