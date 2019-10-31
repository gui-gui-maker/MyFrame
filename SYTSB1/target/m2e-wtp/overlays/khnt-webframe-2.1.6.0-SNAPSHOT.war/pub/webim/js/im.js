Ext.BLANK_IMAGE_URL = 'pub/webim/images/s.gif';
Ext.onReady(function() {
    var windowTitle = top.document.title;
    var baseUrl = "webimcon?__method="
    if(Ext.isIE){
        Ext.getBody().insertHtml("beforeEnd","<embed id=bgmsgwav src='pub/webim/wav/msg.wav' width='0' height='0' autostart= false loop=false>");
    }
    Ext.QuickTips.init();
    Ext.Ajax.defaultHeaders = {
        'Powered-By': 'khnt'
    };

    var orgTree = {
        title:'内部机构',
        layout:'fit',
        items:
        {
            id:'menutree',
            xtype:'treepanel',
            bodyBorder: false,
            autoScroll:true,
            animate:true,//是否动画
//            containerScroll:true,
            useArrows: true,
            rootVisible:false,
            lines:false,
            loader:new Ext.tree.TreeLoader({
                dataUrl: baseUrl + "getUnitTree",
                listeners: {
                    beforeload:function(treeLoader, node) {
                        this.baseParams.guid = node.attributes.guid;
                    }
                }
            }),
            root:{
                expanded: false,
                text: '内部机构',
                guid:'0'
            },
            listeners: {
                click: function(node) {
                    if (node.isLeaf()) {
                        showMsgWindow(node.attributes);
                    }
                }
            }
        }

    };
    var groupTree = {
        title:'讨论组',
        layout:'fit',
        items:
        {
            id:'grouptree',
            xtype:'treepanel',
            bodyBorder: false,
            autoScroll:true,
            animate:true,//是否动画
//            containerScroll:true,
            useArrows: true,
            rootVisible:true,
            lines:false,
            loader:new Ext.tree.TreeLoader({
                dataUrl: baseUrl + "getGroupTree",
                listeners: {
                    beforeload:function(treeLoader, node) {
                        this.baseParams.guid = node.attributes.guid;
                    }
                }
            }),
            root:{
                expanded: true,
                text: '我的讨论组',
                guid:'0'
            },
            listeners: {
                click: function(node) {
                    if (!node.isLeaf() && node.attributes.guid != "0") {
                        showMsgWindow(node.attributes);
                    }
                },
                contextmenu: function(node, event) {
                    currentGroupNode = node;
                    setContextmenuVisible(false, ["addmembermenu","uncreategroupmenu","delusermenu","abortgroupmenu"]);
                    if (node.attributes.utype == "group") {
                        if (node.attributes.creater == myInfo.guid) {
                            setContextmenuVisible(true, ["addmembermenu","uncreategroupmenu"]);
                        } else {
                            setContextmenuVisible(true, ["abortgroupmenu"]);
                        }
                    }
                    if (node.isLeaf() && node.parentNode.attributes.creater == myInfo.guid && node.attributes.guid != myInfo.guid) {
                        setContextmenuVisible(true, ["delusermenu"]);
                    }
                    event.preventDefault(); //这行是必须的
                    groupContextmenu.showAt(event.getXY());//取得鼠标点击坐标，展示菜单
                        //                        }
                 }
            }
        }

    };
    var recentTree = {
        title:'最近联系人',
        layout:'fit',
        items:
        {
            id:'recenttree',
            xtype:'treepanel',
            bodyBorder: false,
            autoScroll:true,
            animate:true,//是否动画
//            containerScroll:true,
            useArrows: true,
            rootVisible:false,
            lines:false,
            loader:new Ext.tree.TreeLoader({
                dataUrl: baseUrl + "getRecentTree"
            }),
            root:{
                expanded: false,
                text: '最近联系人',
                guid:'0'
            },
            listeners: {
                click: function(node) {
                    if (node.isLeaf()) {
                        showMsgWindow(node.attributes);
                    }
                }
            }
        }
    };

    var recentTreeRoot;
    function addRecentUser(head) {
        if(!recentTreeRoot){
            recentTreeRoot=Ext.getCmp("recenttree").getRootNode();
        }
        if (recentTreeRoot.isExpanded()) {
            var node=recentTreeRoot.findChild("guid",head.guid);
            if(!node){
                node=new Ext.tree.TreeNode(head);
            }
            recentTreeRoot.insertBefore(node,recentTreeRoot.item(0));
        }
    }
    function setContextmenuVisible(visible,menus){
         for(var i=0;i<menus.length;i++){
             Ext.getCmp(menus[i]).setVisible(visible);
         }
    }

    var currentGroupNode;
    var groupContextmenu = new Ext.menu.Menu({
        id :'groupContextmenu',
        items : [
            {
                id:'creategroupmenu',
                text : '创建讨论组',
                icon:'pub/webim/images/creategroup.gif',
                handler:function () {
                    groupSet({guid:'1',icon:'pub/webim/images/header/group/group.jpg'})
                }
            },
            {
                id:'addmembermenu',
                text : '修改组信息',
                icon:'pub/webim/images/addmember.gif',
                handler:function () {
                    groupSet(currentGroupNode.attributes);
                }
            },
            {
                id:'uncreategroupmenu',
                text : '解散讨论组',
                icon:'pub/webim/images/uncreategroup.gif',
                handler:function () {
                    Ext.Msg.confirm('提示', '您确定要解散讨论组吗？', function(btn, text){
                        if (btn == 'yes'){
                            Ext.Ajax.request({
                            method:'POST',
                            url: baseUrl + 'unGroup',
                            success: function(response, opts) {
                                var obj = Ext.decode(response.responseText);
                                if (obj.success) {
                                    currentGroupNode.remove();
                                }
                            },
                            params: {id:currentGroupNode.attributes.guid}
                        });
                        }
                    });
                }
            },
            {
                id:'delusermenu',
                text : '删除组成员',
                icon:'pub/webim/images/deluser.gif',
                leaf:'1',
                handler:function () {
                    Ext.Msg.confirm('提示', '您确定要删除该成员吗？', function(btn, text){
                        if (btn == 'yes'){
                            Ext.Ajax.request({
                            method:'POST',
                            url: baseUrl + 'delGroupUser',
                            success: function(response, opts) {
                                var obj = Ext.decode(response.responseText);
                                if (obj.success) {
                                    currentGroupNode.remove();
                                }
                            },
                            params: {id:currentGroupNode.parentNode.attributes.guid,userId:currentGroupNode.attributes.guid}
                        });
                        }
                    });
                }
            },
            {
                id:'abortgroupmenu',
                text : '退出讨论组',
                icon:'pub/webim/images/abortgroup.gif',
                handler:function () {
                    Ext.Msg.confirm('提示', '您确定要退出讨论组吗？', function(btn, text){
                        if (btn == 'yes'){
                            Ext.Ajax.request({
                            method:'POST',
                            url: baseUrl + 'abortGroup',
                            success: function(response, opts) {
                                var obj = Ext.decode(response.responseText);
                                if (obj.success) {
                                    currentGroupNode.remove();
                                }
                            },
                            params: {id:currentGroupNode.attributes.guid}
                        });
                        }
                    });
                }
            }
        ]
    });

    var headerTpl = new Ext.XTemplate(
            '<table><tr><td rowspan=2><img class="big-header-img" src="{icon}"></td><td>{text}</td></tr><tr><td>{signer}</td></tr></table>'
            );
    headerTpl.compile();
    var fontStyleTpl = new Ext.XTemplate(
            'font-family: {fontFamily}; font-size: {fontSize}pt; font-weight: {fontWeight}; font-style: {fontStyle}; text-decoration: {textDecoration}; color: #{color};'
            );
    fontStyleTpl.compile();
    var msgWin = new Ext.WindowGroup();
    var msgWinIdPre = "_msgwin_";
    var faceWin;

    function showFaceWin(x, y) {
        if (!faceWin) {
            var faceArray = new Array();
            for (var i = 0; i <= 104; i++) {
                faceArray.push(i);
            }
            var tpl = new Ext.XTemplate(
                    '<div id="faceImgPanel" class="faceImg" style="display: block;">',
                    '<tpl for=".">',
                    '<a id="face_{.}"></a>',
                    '</tpl>'
                    );
            faceWin = new Ext.TabPanel({
                renderTo: document.body,
                width:445,
                height:240,
                tabPosition:'bottom',
                tabMargin:0,
                activeTab: 0,
                cls:'faceImgPanel',
                html:tpl.apply(faceArray),
                items: [
                    {
                        title: '默认',
                        html:tpl.apply
                    },
                    {
                        title: '其它',
                        html: '<div align="center">开发中……</div>'
                    }
                ]});
            Ext.get("faceImgPanel").on("click", function(o, e) {
                setFace(e.id.replace(/^face_/, ""))
            });
        }
        faceWin.show();
        faceWin.setPosition(x, y - faceWin.getHeight() + 25);
    }

    var baseFaceUrl = "pub/webim/images/face/";

    function setFace(name) {
        var editor = Ext.get("htmleditor" + msgWin.getActive().guid);
        editor.insertHtml("beforeEnd", "<img src='" + baseFaceUrl + name + ".gif" + "'>");
    }


    function createMsgWin(head) {
        var winId = msgWinIdPre + head.guid;
        var win = new Ext.Window({
            //            自定义参数
            newMsgs:0,
            headInfo:head,
            guid:head.guid,

            renderTo:document.body,
            manager:msgWin,
            id:winId,
            title:headerTpl.apply(head),
            minWidth:500,
            minHeight:300,
            width:600,
            height:420,
            //                iconCls: 'gear',
            //                constrain:true,
            //                constrainHeader:true,
            minimizable :true,
            maximizable:true,

            shim:false,
            animCollapse:false,
            //                closeAction:'hide',

            layout: 'border',
            items: [
                {
                    region: 'center',
                    xtype: 'panel',
                    id:'msgBoxPanel' + head.guid,
                    autoScroll:true,
                    html:'<div class="msgBox" id="msgBox' + head.guid + '"></div>',
                    bbar:createFontbar('htmleditor' + head.guid)

                },
                {
                    region: 'south',
                    xtype: 'panel',
                    tbar:[
                        {
                            icon :"pub/webim/images/style_clean.png",
                            tooltip:"清除样式",
                            handler:function(button, state) {
                                var editor = Ext.get("htmleditor" + head.guid);
                                editor.dom.innerHTML=editor.dom.innerText;
                            }
                        },
                        "-",
                        {
                            icon :"pub/webim/images/face.gif",
                            tooltip:"选择表情",
                            handler:function(button, state) {
                                var xy = button.getPosition();
                                showFaceWin(xy[0], xy[1]);
                            }},
//                        "-",
//                        {icon :"images/filesend.gif"},
//                        "-",
//                        {icon :"images/snap.png"},
//                        "-",
//                        {icon :"images/picsend.png"},
                        "-",
                        {
                            icon :"pub/webim/images/clean.gif",
                            tooltip:"清除记录",
                            handler:function(button, state) {
                                Ext.get("msgBox" + head.guid).dom.innerHTML = "";
                            }
                        },
                        "-",
                        {
                        	id:winId+"_fileup",
                            icon :"pub/webim/images/up.png",
                            tooltip:"上传附件"
                        },
                        "->",
                        {
                            icon :"pub/webim/images/record.gif",
                            text:"消息记录",
                            handler:function(button, state) {
                                createHisMsgWin(head);
                            }
                        }
                    ],
                    layout: 'fit',
                    height:100,
                    autoScroll:true,
                    html:'<div contenteditable="true" id="htmleditor' + head.guid + '" class="editArea" style="' + fontStyleTpl.apply(fontStyle) + '"></div>',
                    keys:[
                        {
                            key : Ext.EventObject.ENTER,
                            fn : function(key, e) {
                                if (sendMsgKeytype == 1 || sendMsgKeytype == 3) {
                                    sendMsg(head);
                                }
                                else if (e.ctrlKey) {
                                    sendMsg(head);
                                }
                            },
                            scope : this
                        }
                    ]
                }
            ],
            keys:[
                {
                    alt:true,
                    key : "s",
                    fn : function() {
                        sendMsg(head);
                    },
                    scope : this
                },
                {
                    alt:true,
                    key : "c",
                    fn : function() {
                        msgWin.get(msgWinIdPre + head.guid).hide();
                        delTaskImg(head.guid);
                    },
                    scope : this
                }
            ],
            listeners:{
            	afterrender:function(){
            		//在body中加入一个隐藏iframe用于url文件下载
            		if(!Ext.get("webimiframe")){
            			var webimiframe = "<form id='webimiframe' style='display:none'></form>";
                		Ext.DomHelper.append(Ext.getBody(), webimiframe);
            		}
            		var em = Ext.get(winId+"_fileup").child('em');
    				var placeHolderId = winId+"_upfile";
    				em.setStyle({
    					position : 'relative',
    					display : 'block'
    				});
    				em.createChild({
    					tag : 'div',
    					id : placeHolderId
    				});
    				
    				fileuplaod = new SWFUpload(Ext.apply(uploadSetting,{
    					button_width : em.getWidth()+5,
    					button_height : em.getHeight()+5,
    					button_placeholder_id :placeHolderId,
    					upload_success_handler :function(file, serverData) {
    						var sucdata = Ext.util.JSON.decode(serverData);
    						if(sucdata.success&&file.filestatus==-4){
    							//发送信息
    							var href = base+"fileupload/download.do?id="+sucdata.data.id+"&path="+sucdata.data.path;
								var reg=/\\/g;
    							href = href.replace(reg, "/");
    							var editor = Ext.get("htmleditor" + msgWin.getActive().guid);
    						    editor.insertHtml("beforeEnd", "<a name='fj' href='"+href+"'>附件："+sucdata.data.name+"</a>");
    						    sendMsg(head);
    						}else{
    							Ext.Msg.alert("提示", "上传文件失败！");
    						}
    					}
    				}));
    				fileuplaod.uploadStopped = false;
    				Ext.get(fileuplaod.movieName).setStyle({
    					position : 'absolute',
    					top : 0,
    					left : "-2px",
    				});	
            	},
                minimize:function(obj) {
                    obj.hide();
                    taskView.deselect("task"+obj.guid);
                },
                beforeclose:function(obj) {
                    obj.hide();
                    delTaskImg(obj.guid);
                    return false;
                },
                activate:function(obj) {
                    if (obj.newMsgs != 0) {
                        subMsgBoxTitle(obj.newMsgs,obj.headInfo.utype=="group"?"g":"");
                        obj.newMsgs = 0;
                    }
                    taskView.select("task"+obj.guid);
                    taskStore.getById(obj.guid).set("hasnew", false);;
                }
            },
            buttons: [
                {
                    text: '关闭(C)',
                    guid:head.guid,
                    handler: function(obj) {
                        msgWin.get(msgWinIdPre + obj.guid).hide();
                        delTaskImg(obj.guid);
                    }
                },
                {
                    xtype:'splitbutton',
                    text: '发送(S)',
                    head:head,
                    handler:sendMsg,
                    menu:sendMenu
                }
            ]
        });
        win.getEl().on("click", function(e, b) {
            if (b.tagName == "BUTTON" || b.tagName == "I")return;
            if (faceWin && faceWin.isVisible())
                faceWin.hide();
        });
        return win;
    }
    function createSysWin() {
        var head={guid:"system",text:"系统消息",icon:"pub/webim/images/system.png",signer:""};
        var winId = msgWinIdPre + head.guid;
        var win = new Ext.Window({
            //            自定义参数
            newMsgs:0,
            headInfo:head,
            guid:head.guid,

            renderTo:document.body,
            manager:msgWin,
            id:winId,
            title:headerTpl.apply(head),
            minWidth:500,
            minHeight:300,
            width:600,
            height:420,
            //                iconCls: 'gear',
            //                constrain:true,
            //                constrainHeader:true,
            minimizable :true,
            maximizable:true,

            shim:false,
            animCollapse:false,
            //                closeAction:'hide',

            layout: 'border',
            items: [
                {
                    region: 'center',
                    xtype: 'panel',
                    id:'msgBoxPanel' + head.guid,
                    autoScroll:true,
                    html:'<div class="msgBox" id="msgBox' + head.guid + '"></div>'

                }
            ],
            listeners:{
                minimize:function(obj) {
                    obj.hide();
                    taskView.deselect("task"+obj.guid);
                },
                beforeclose:function(obj) {
                    obj.hide();
                    delTaskImg(obj.guid);
                    return false;
                },
                activate:function(obj) {
                    if (obj.newMsgs != 0) {
                        subMsgBoxTitle(obj.newMsgs,"s");
                        obj.newMsgs = 0;
                    }
                    taskView.select("task"+obj.guid);
                    taskStore.getById(obj.guid).set("hasnew", false);
                }
            }
//            buttons: [
//                {
//                    text: '关闭(C)',
//                    guid:head.guid,
//                    handler: function(obj) {
//                        msgWin.get(msgWinIdPre + obj.guid).hide();
//                        delTaskImg(obj.guid);
//                    }
//                }
//            ]
        });
        return win;
    }

    var fontStyle = {color:'#000000',fontFamily:'黑体',fontSize:'10',fontStyle:'normal',fontWeight:'normal',textDecoration:'none'};

    function createFontbar(editorId) {
        var bar = new Ext.Toolbar({
            hideParent :true,
            items:[
                {
                    xtype: 'combo',
                    width:100,
                    store: [
                        ['宋体','宋体'],
                        ['黑体','黑体'],
                        ['楷体_GB2312','楷体_GB2312'],
                        ['幼圆','幼圆'],
                        ['华文彩云','华文彩云'],
                        ['华文行楷','华文行楷'],
                        ['Arial','Arial'],
                        ['Arial Black','Arial Black'],
                        ['Verdana','Verdana']
                    ],
                    editable : false,
                    triggerAction: 'all',
                    listeners:{
                        beforerender:function() {
                            this.value = fontStyle.fontFamily
                        },
                        change:function(combo, newValue, oldValue) {
                            fontStyle.fontFamily = newValue;
                            Ext.get(editorId).setStyle("font-family", newValue);
                        }

                    }
                },
                "-",
                {
                    xtype: 'combo',
                    width:40,
                    listWidth:40,
                    store: [
                        ['8','8'],
                        ['9','9'],
                        ['10','10'],
                        ['11','11'],
                        ['12','12'],
                        ['13','13'],
                        ['14','14'],
                        ['15','15'],
                        ['16','16'],
                        ['17','17'],
                        ['18','18'],
                        ['19','19'],
                        ['20','20'],
                        ['21','21'],
                        ['22','22']
                    ],
                    editable : false,
                    triggerAction: 'all',
                    listeners:{
                        beforerender:function() {
                            this.value = fontStyle.fontSize
                        },
                        change:function(combo, newValue, oldValue) {
                            fontStyle.fontSize = newValue
                            Ext.get(editorId).setStyle("font-size", newValue + "pt");
                        }

                    }
                },
                "-",
                {
                    icon: 'pub/webim/images/swatch.png',
                    menu : new Ext.menu.ColorMenu({
                        allowReselect: true,
                        value:fontStyle.color,
                        plain:true,
                        listeners: {
                            select: function(cp, color) {
                                fontStyle.color = color;
                                Ext.get(editorId).setStyle("color", "#" + color);
                            }
                        }
                    })
                },
                "-",
                {
                    icon :"pub/webim/images/bold.png",
                    pressed :(fontStyle.fontWeight != "normal"),
                    enableToggle:true,
                    toggleHandler:function(button, state) {
                        fontStyle.fontWeight = state ? "bold" : "normal";
                        Ext.get(editorId).setStyle("font-weight", fontStyle.fontWeight);
                    }

                },
                {
                    icon :"pub/webim/images/italic.png",
                    pressed :(fontStyle.fontStyle != "normal"),
                    enableToggle:true,
                    toggleHandler:function(button, state) {
                        fontStyle.fontStyle = state ? "italic" : "normal";
                        Ext.get(editorId).setStyle("font-style", fontStyle.fontStyle);
                    }
                },
                {
                    icon :"pub/webim/images/underline.png",
                    pressed :(fontStyle.textDecoration != "none"),
                    enableToggle:true,
                    toggleHandler:function(button, state) {
                        fontStyle.textDecoration = state ? "underline" : "none";
                        Ext.get(editorId).setStyle("text-decoration", fontStyle.textDecoration);
                    }
                }
            ]
        });

        return bar;
    }


    var sendMsgKeytype = 1;
    var sendMenu = new Ext.menu.Menu({items:[
        {group:"font",value:1,checked: true,handler:ChangeSendTye,text: '按Enter键发送消息'},
        {group:"font",value:2,checked: false,handler:ChangeSendTye,text: '按Ctrl+Enter键发送消息'},
        {group:"font",value:3,checked: false,handler:ChangeSendTye,text: '以上两者均可发送消息'}
    ]});

    function ChangeSendTye(obj) {
        sendMsgKeytype = obj.value;
    }

    function showMsgWindow(head) {
        var winId = msgWinIdPre + head.guid;
        var win = msgWin.get(winId);
        var isnew=false;
        if (!win) {
            win = createMsgWin(head);
            isnew=true;
        }
        addTaskImg(head,isnew);
        win.show();
    }

    var msgTpl = new Ext.XTemplate(
            '<tpl for=".">',
            '<div>',
            '<p class="friend-msg">{name}&nbsp;&nbsp;{sendtime}</p>',
            '<div class="content" style="{style}">{msg}</div>',
            '</div>',
            '</tpl>'
            );
    msgTpl.compile();

    var msgMyTpl = new Ext.XTemplate(
            '<div>',
            '<p class="my-msg">{name}&nbsp;&nbsp;{sendtime}</p>',
            '<div class="content" style="{style}">{msg}</div>',
            '</div>'
            );
    msgMyTpl.compile();

    var index = 0;

    function sendMsg(a, b) {
        if(a.xtype == "splitbutton"){
            a=a.head;
        }
        var editor = Ext.get("htmleditor" + a.guid);
        var msgV = editor.dom.innerHTML;
        if (GetBytesLength(msgV) > 500) {
            var m="<br><font color='red' size='3'>提示：您输入的信息太多，请分段发送！</font>";
            msgAppend(a.guid,m);
            return;
        }
        addRecentUser(a);
        var style = Ext.isIE ? editor.dom.style : editor.dom.getAttribute("style");
        if (typeof style == "object") {
            style = style.cssText;
        }
        if (msgV == "")return;

       // msgV=msgV.replace(/http:\/\/[^>]+\//g,"pub/webim/images/face/");
        Ext.Ajax.request({
            msg:msgV,
            style:style,
            method:'post',
            url: baseUrl + "sendMsg",
            success: function(response, r) {
                var obj = Ext.util.JSON.decode(response.responseText);
                var msg=msgMyTpl.apply({name:myInfo.text,style:r.style,msg:r.msg,sendtime:obj.sendtime});
                msgAppend(a.guid,msg,editor);
                editor.dom.innerHTML = "";
            },
            //                failure: otherFn
            params:{style:style,msg:msgV,recuid:a.guid,utype:a.utype}
        });
    }

    function msgAppend(guid,msg){
        Ext.get("msgBox" + guid).insertHtml("beforeEnd", msg);
        var d = Ext.getCmp("msgBoxPanel" + guid).body.dom;
        d.scrollTop = d.scrollHeight - d.offsetHeight;
    }

    var showHisPreview=true;
    function createHisMsgWin(head) {
        var winId = "his" + head.guid;
        var histab = Ext.getCmp(winId);
        if (!histab) {
            var store = new Ext.data.JsonStore({
                root: 'root',
                totalProperty: 'totalProperty',
//                idProperty: 'id',
//                remoteSort: true,
                sortInfo:{field: 'sendtime', direction: 'asc'},
                fields: [
                     'msg', 'style', 'senduid','senduname', 'sendtime'
                ],
                url: baseUrl+'getHisMsg',
                baseParams:{guid:head.guid,utype:head.utype}
            });
            store.load({params:{start:0, limit:20}});
            var title='与 ' + head.text + ' 的聊天记录';
            var emptyText='<div align="center">没有聊天记录！点击用户聊天吧</div>';
            if(head.utype=="system"){
                title=head.text;
                emptyText='<div align="center">没有系统消息</div>';
            }
            histab = Ext.getCmp("imtab").add({
                id:winId,
                title:title,
                iconCls:'hismsgtab',
//                icon:head.icon,
                closable:true,
                xtype:'grid',
                layout:'fit',
                store: store,
                loadMask: true,
                enableHdMenu :false,
                autoExpandColumn: 'msg',
                columns:[
                    {
                        id: 'senduid',
                        header: "发送人",
                        dataIndex: 'senduid',
                        width: 80,
                        sortable: true,
                        renderer:function(val,metadata ,record){
                           if(val==myInfo.id){
                               return "<div style='color:green'>"+record.data.senduname+"</div>";
                           }else {
                               return "<div style='color:blue'>"+record.data.senduname+"</div>";
                           }
                           return val;
                        }
                    },
                    {
                        id: 'sendtime',
                        header: "时 间",
                        dataIndex: 'sendtime',
                        width: 120,
                        sortable: true
                    },
                    {
                        id: 'msg',
                        header: "消 息",
                        dataIndex: 'msg',
                        width: 125,
                        sortable: true,
                        renderer:function(value,metadata,record, rowIndex){
                            if (showHisPreview)
                                return '<div style="'+record.data.style+'">' + record.data.msg + '</div>';
                            else
                                return value;
                        }
                    }
                ], //todo

                // customize view config
                viewConfig: {
//                    forceFit:true,
                    emptyText : emptyText,
                    enableRowBody:true,
                    showPreview:false
//                    getRowClass : function(record, rowIndex, p, store) {
//                        if (this.showPreview) {
//                            p.body = '<div style="'+record.data.style+'">' + record.data.msg + '</div>';
//                            return 'x-grid3-row-expanded';
//                        }
//                        return 'x-grid3-row-collapsed';
//                    }
                },

                // paging bar on the bottom
                bbar: new Ext.PagingToolbar({
                    pageSize: 20,
                    store: store,
                    autoLoad:true,
                    items:[
                        '->', {
                            pressed: showHisPreview,
                            enableToggle:true,
                            text: '显示格式化信息',
                            icon: 'pub/webim/images/font.png',
                            toggleHandler: function(btn, pressed) {
                                var view = btn.findParentByType("grid").getView();
                                showHisPreview = pressed;
                                view.refresh();
                            }
                        }]
                })
            })
        }
        histab.show();
        msgWin.hideAll();
//        store.setDefaultSort('lastpost', 'desc');

    }

    var msgBox = {
        title:'消息盒子（0）',
        id:'msgBox-tab',
        iconCls:'icon-msg',
        cls:'largePadding',
        newMsgs:0,
        items:[
            {id:'msgBox-tab-friend',newMsgs:0,title:"用户留言(0)",xtype:"fieldset",iconCls:'icon-friend-msg',titleCollapse : true,collapsible :true,collapseFirst:false,width:"90%",html:"<br>您目前还没有留言，请从右侧列表中选择联系人发起聊天"},
            {id:'msgBox-tab-group',newMsgs:0,title:"讨论组(0)",xtype:"fieldset",iconCls:'icon-sys-msg',titleCollapse : true,collapsible :true,collapseFirst:false,width:"90%",html:"<br>您目前还没有讨论组留言，请从右侧“讨论组”中选择讨论组发起聊天"},
            {id:'msgBox-tab-sys',newMsgs:0,title:"系统消息(0)",xtype:"fieldset",iconCls:'icon-sys-msg',titleCollapse : true,collapsible :true,collapseFirst:false,width:"90%",
                html:"<br>您目前还没有系统消息",
                buttons: [
                {
                    icon :"pub/webim/images/record.gif",
                    text:"消息记录",
                    handler:function(button, state) {
                        var head={guid:"system",text:"系统消息",icon:"pub/webim/images/system.png",utype:"system"};
                        createHisMsgWin(head);
                    }
                }
            ]
            }
        ]
    }

    /**
     * 任务栏
     */

    function addTaskImg(obj,hasnew) {
        var oid = obj.guid;
        var myobj = {id:oid,name:obj.text,url:obj.icon,hasnew:!hasnew};
        var rcd = taskStore.getById(oid);
        if (!rcd)
            taskStore.insert(0, new Ext.data.Record(myobj, oid));
    }

    function delTaskImg(id) {
        taskStore.removeAt(taskStore.indexOfId(id));
    }

    var taskStore = new Ext.data.JsonStore({
        id:'id',
        fields:[
            'name', 'url','id','hasnew'
        ]
    });
    var shortcutStr = "<DIV class=shortcuts><DIV>快捷键：</DIV> <UL> <LI>Ctrl + Alt + X (关闭所有聊天窗口) </LI></UL> </DIV>";

    var taskView = new Ext.DataView({
        singleSelect : true,
        store: taskStore,
        overClass:'x-view-over',
        selectedClass :'x-view-selected',
        itemSelector: 'div.thumb-wrap',
        deferEmptyText:false,
        emptyText: shortcutStr,
        listeners :{
            click:function(obj) {
                var rcd = obj.getSelectedRecords()[0];
                msgWin.get(msgWinIdPre + rcd.id).show();
                rcd.set("hasnew", false);
            }
//            selectionchange:function(obj){
//                   var rcd = obj.getSelectedRecords()[0];
//                msgWin.get(msgWinIdPre + rcd.id).show();
//                rcd.set("hasnew", false);
//            }
        },
        tpl: new Ext.XTemplate(
                '<tpl for=".">',
                '<div class="thumb-wrap" id="task{id}">',
                '<div class="thumb"><img src="{url}" title="与 {name} 交谈中"></div>',
                '</div>',
                '</tpl>'
                )
    });
    var headImgWin;

    function changeHeadImg(a) {
        headImgWin = Ext.getCmp("headImgWin");
        if (!headImgWin) {
            var imgView = new Ext.DataView({
                singleSelect : true,
                store: new Ext.data.JsonStore({
                    //                    id:'id',
                    url: baseUrl + "getHead",
                    root: 'images',
                    autoLoad :true,
                    fields:[
                        'url'
                    ]
                }),
                overClass:'x-view-over',
                itemSelector: 'div.thumb-wrap',
                tpl: new Ext.XTemplate(
                        '<tpl for=".">',
                        '<div class="thumb-wrap">',
                        '<div class="thumb"><img src="{url}"></div>',
                        '</div>',
                        '</tpl>'
                        )
            });
            headImgWin = new Ext.Window({
                renderTo:document.body,
                id:"headImgWin",
                title:"请选择头像",
                modal :true,
                minWidth:300,
                minHeight:200,
                width:400,
                height:250,
                plain :false,
                autoScroll: true,
                items:imgView,
                buttons: [
                    {
                        text: '保存',
                        iconCls:'save',
                        handler:function(obj) {
                            var dataview = obj.findParentByType("window").findByType("dataview")[0];
                            if (dataview.getSelectionCount() > 0) {
                                var picurl = dataview.getRecord(dataview.getSelectedNodes()[0]).get("url");
                                Ext.Ajax.request({
                                    url: baseUrl + "setHead",
                                    params:{head:picurl,id:myInfo.guid},
                                    success: function(response, obj) {
                                        Ext.getCmp("headerpic").getEl().dom.src = obj.params.head;
                                        myInfo.icon = obj.params.head;
                                        Ext.getCmp("imcenter").setTitle(headerTpl.apply(myInfo));
                                    },
                                    failure: function() {
                                        Ext.Msg.alert("提示", "保存头像失败！");
                                    }
                                });
                            }
                            obj.findParentByType("window").hide();
                        }
                    },
                    {
                        text: '取消',
                        iconCls:'cancel',
                        handler:function(obj) {
                            obj.findParentByType("window").hide();
                        }
                    }
                ]

            });
        }
        var xy = Ext.getCmp("headerpic").getPosition();
        headImgWin.setPosition(xy[0] + 45, xy[1]);
        headImgWin.show();
    }


    function groupSet(group) {
        var groupId="groupsettab"+group.guid;
        var groupsettab = Ext.getCmp(groupId);
        if (!groupsettab) {
            groupsettab = Ext.getCmp("imtab").add({
                id:groupId,
                title: '讨论组设置',
                iconCls: 'groupset',
                closable:true,
                layout:'border',
                items: [
                    {
                        region: 'west',
                        xtype: 'form',
                        width: 250,
                        split: true,
                        activeTab: 0,
                        defaultType:'textfield',
                        labelAlign :'right',
                        labelWidth :40,
                        items:[
                            {
                                xtype:'box',
                                fieldLabel: '头像',
                                autoEl: {
                                    tag: 'img',
                                    cls:'big-header-img',
                                    src:group.icon
                                }
                            },
                            {
                                fieldLabel: '名称',
                                name: 'name',
                                emptyText  : '输入讨论组名称',
                                value:group.text,
                                maxLength:100,
                                allowBlank:false,
                                anchor:'95%'
                            },
                            {
                                fieldLabel: '签名',
                                name: 'signer',
                                value:group.signer,
                                maxLength:100,
                                anchor:'95%'
                            },
                            {
                                fieldLabel: '说明',
                                xtype:'textarea',
                                name: 'note',
                                value:group.note,
                                maxLength:200,
                                anchor:'95% -150'
                            }
                        ]
                    },
                    {
                        region: 'center',
                        xtype: 'panel',
                        layout:'column',
//                        height :'100%',
//                autoScroll:true,
                        items:[
                        new Ext.tree.TreePanel({
                            columnWidth: .5,
                            height:350,
                            title:'内部机构',
                            animate:true,
                            autoScroll:true,
                            loader: new Ext.tree.TreeLoader({dataUrl:baseUrl + "getUnitTree"}),
                            enableDD:true,
                            containerScroll: true,
                            border: true,
                            rootVisible:false,
                            dropConfig: {appendOnly:true},
                            root:{
                                expanded: true,
                                draggable:false,
                                text: '内部机构',
                                id:'0'
                            }
                        }),
                        new Ext.tree.TreePanel({
//                            animate:true,
                            columnWidth: .5,
                            title:'讨论组成员',
                            autoScroll:true,
                            loader: new Ext.tree.TreeLoader({dataUrl:baseUrl + "getGroupTree",listeners: {
                                beforeload:function(treeLoader, node) {
                                    this.baseParams.guid = node.attributes.guid;
                                }
                            }}),
                            enableDD:true,
                            containerScroll: true,
                            border: true,
                            height:350,
                            rootVisible:false,
                            dropConfig: {
                                appendOnly:true,
                                allowContainerDrop :true

                            },
                            root:{
                                expanded: false,
                                draggable:false,
                                text: '组成员',
                                guid:group.guid
                            }
                        })
                        ]
                    }
                ],
                buttonAlign : 'center',
                buttons: [
                    {
                        text: '保存',
                        iconCls:'save',

                        handler:function(obj) {
                            var form = Ext.getCmp(groupId).findByType("form")[0].getForm();
                            var root = Ext.getCmp(groupId).findByType("treepanel")[1].getRootNode();
                            var userIds=getLeafs(root);
                            if (form.isValid()) {
                                var values = form.getValues();
                                Ext.apply(group, values, {text:values.name,userid:userIds})
                                Ext.Ajax.request({
                                    url: baseUrl + "setGroupInfo",
                                    treeRoot:root,
                                    params:group,
                                    method:'post',
                                    success: function(response,o) {
                                        var gid=Ext.util.JSON.decode(response.responseText).id;
                                        if(o.params.guid=="1"){
                                            o.params.guid=gid;
                                            Ext.apply(o.params,{creater:myInfo.guid,utype:"group"});
                                            Ext.getCmp("grouptree").getRootNode().appendChild(o.params);
                                        }else{
                                            var node=Ext.getCmp("grouptree").getRootNode().findChild("guid",gid);
                                            Ext.apply(node.attributes, o.params);
                                            node.setText(node.attributes.text);
                                            if(node.isExpanded()){
                                                node.reload();
                                            }
                                        }
                                        try {
                                            obj.findParentByType("tabpanel").remove(groupId);
                                        } catch(e) {
                                        }
                                    },
                                    failure: function() {
                                        Ext.Msg.alert("提示", "保存群组设置失败！");
                                    }
                                });
                            }
                        }
                    },
                    {
                        text: '关闭',
                        iconCls:'cancel',
                        handler:function(obj) {
                            obj.findParentByType("tabpanel").remove(groupId);
                        }
                    }
                ]

            });

        }
        groupsettab.show();
        msgWin.hideAll();
    }
    function addSetTab() {
        var personlsettab = Ext.getCmp("personlsettab");
        if (!personlsettab) {
            personlsettab = Ext.getCmp("imtab").add({
                id:'personlsettab',
                title: '个性设置',
                iconCls: 'personlset',
                cls:'largePadding',
                closable:true,
                xtype:'form',
                defaultType:'textfield',
                labelAlign :'right',
                items: [
                    {
                        fieldLabel: '声音提醒',
                        name: 'isvoice',
                        xtype:'checkbox',
                        inputValue :myInfo.isvoice,
                        checked:(myInfo.isvoice == "1"),
                        anchor:'100%'
                    },
                    {
                        id:'headerpic',
                        xtype:'box',
                        fieldLabel: '头 像',
                        autoEl: {
                            tag: 'img',
                            cls:'big-header-img',
                            src:myInfo.icon
                        },
                        listeners : {
                            render:function() {
                                Ext.getCmp("headerpic").getEl().on("click", changeHeadImg);
                            }
                        }
                    },
                    {
                        fieldLabel: '昵 称',
                        name: 'name',
                        value:myInfo.text,
//                        readOnly:true,
                        maxLength:100,
                        anchor:'40%'
                    },
                    {
                        fieldLabel: '签 名',
                        name: 'signer',
                        value:myInfo.signer,
                        maxLength:100,
                        anchor:'100%'
                    },
                    {
                        fieldLabel: '说 明',
                        xtype:'textarea',
                        name: 'note',
                        value:myInfo.note,
                        maxLength:200,
                        anchor:'100% -150'
                    }
                ],
                buttonAlign : 'center',
                buttons: [
                    {
                        text: '保存',
                        iconCls:'save',
                        handler:function(obj) {
                            var form = obj.findParentByType("form").getForm();
                            if (form.isValid()) {
                                var values = form.getValues();
                                Ext.apply(myInfo, values, {isvoice:0,text:values.name})
                                Ext.Ajax.request({
                                    url: baseUrl + "setUserInfo",
                                    params:myInfo,
                                    success: function(response) {
                                        Ext.getCmp("imcenter").setTitle(headerTpl.apply(myInfo));
                                        obj.findParentByType("tabpanel").remove("personlsettab");
                                    },
                                    failure: function() {
                                        Ext.Msg.alert("提示", "保存个性设置失败！");
                                    }
                                });
                            }
                        }
                    },
                    {
                        text: '关闭',
                        iconCls:'cancel',
                        handler:function(obj) {
                            obj.findParentByType("tabpanel").remove("personlsettab");
                        }
                    }
                ]

            });

        }
        personlsettab.show();
        msgWin.hideAll();
    }

    new Ext.Viewport({
        layout: 'border',
        margins: '2 0 5 5',
        renderTo: Ext.getBody(),
        items: [
            {
                id: 'imcenter',
                region: 'center',
                xtype: 'panel',
                title:headerTpl.apply({icon:Ext.BLANK_IMAGE_URL,text:"ttaomeng@163.com"}),
                layout: 'fit',
                bodyBorder: false,
                tools: [
                    {id:'personlset',tooltip:'打开个人设置面板',handler :addSetTab}
                ],
                items:{
                    id:'imtab',
                    xtype: 'tabpanel',
//                    bodyStyle: 'padding:25px',//todo
                    bodyBorder: false,
                    activeTab: 0,
                    //                    layout: 'fit',
                    items:[
                        msgBox
                    ]
                }
            },
            {
                region: 'east',
                collapsible: true,
                title: 'WebIM',
                xtype: 'tabpanel',
                width: 250,
                split: true,
                activeTab: 0,
//                height :'100%',
                autoScroll:true,
                tbar: [ new Ext.form.ComboBox({
                    store: new Ext.data.JsonStore({
                        url: baseUrl + 'searchUser',
                        root: 'data',
                        id:"guid",
                        fields: ['guid','text', 'icon','signer']
                    }),
                    displayField:'text',
                    typeAhead: false,
                    triggerClass:'x-form-search-trigger',
                    loadingText: '查询中...',
                    emptyText:'搜索用户信息',
                    width: 240,
                    minChars:2,
                    tpl: new Ext.XTemplate(
                            '<tpl for="."><div class="search-item">',
                            '<img width="20" height="20" src="{icon}">{text}',
                            '</div></tpl>'
                            ),
                    itemSelector: 'div.search-item',
                    onSelect: function(combo,index) {
                        this.collapse();
                        var head=this.getStore().getAt(index).data;
                        Ext.apply(head,{utype:"u"});
                        showMsgWindow(head);
                    }
                })],
                items:[
                    orgTree,
                    groupTree,
                    recentTree
                ]
            },
            {//任务栏
                region: 'south',
                id:'imTaskbar',
                autoScroll: true,
                height:65,
                split:true,
                items:taskView
            }

        ]
    });


    var map = new Ext.KeyMap(Ext.getBody(), [

        {
            key:"x",
            ctrl:true,
            alt:true,
            fn: function() {
                msgWin.hideAll();
            }
        }
    ]);
    Ext.getBody().mask("登录中……");
    var myInfo;
    Ext.Ajax.request({
        url: baseUrl + "login",
        success: function(response) {
            Ext.getBody().unmask();
            myInfo = Ext.util.JSON.decode(response.responseText);
            if (myInfo) {
                Ext.getCmp("imcenter").setTitle(headerTpl.apply(myInfo));
            }
        },
        failure: function() {
            Ext.getBody().mask("登录失败！");
        }
    });

   function msgInterface(msgCount){
      if(parent.msgInterface){
         parent.msgInterface(msgCount);
      }
   }
    var runner = new Ext.util.TaskRunner();
    runner.start({
        run: function() {
            Ext.Ajax.request({
                url: baseUrl + "getMsg",
                success: function(response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if(obj.msgtype=="logout"){
                         Ext.Msg.alert("提示", obj.msg);
                         runner.stopAll();
                         return;
                    }
                    if(obj.length>0){
                        if (Ext.isIE && myInfo.isvoice == "1") {
                            document.getElementById("bgmsgwav").play();
                        }
                    }else{
                        return;
                    }
                    var msgType=new Array();
                    var msgArr=new Array();
                    if(obj.length>0){
                        msgInterface(obj.length);
                    }
                    for (var i = 0; i < obj.length; i++) {
                        var index=(obj[i].msgtype=="g"?obj[i].recuid:obj[i].senduid);
                        if(!msgArr[index]){
                            msgArr[index]=new Array();
                        }
                        msgArr[index].push({id:index,name:obj[i].senduname,msg:obj[i].msg,style:obj[i].style,sendtime:obj[i].sendtime,msgtype:obj[i].msgtype});
                        msgType[index]="";
                        if(obj[i].msgtype=="g"){
                             msgType[index]="group";
                        }else if(obj[i].msgtype=="s"){
                            msgType[index]="system";
                        }else{
                            msgType[index]="user";
                        }
                    }
                    for (var i in msgArr) {
                        if((typeof msgArr[i])=="function") continue;
                        var head = {guid:i,utype:msgType[i]};
                        var winId = msgWinIdPre + head.guid;
                        var win = msgWin.get(winId);
                        if (!win) {
                            if (head.utype == "system") {
                                win = createSysWin();
                                if(win){
                                    var head={guid:"system",text:"系统消息",icon:"pub/webim/images/system.png",signer:""};
                                    addTaskImg(head);
                                    addMsg(msgArr[i]);
                                }
                            } else {
                                Ext.Ajax.request({
                                    msgObj:msgArr[i],
                                    url: baseUrl + "getUser&guid=" + head.guid + "&utype=" + head.utype,
                                    success: function(response, obj) {
                                        var head = Ext.util.JSON.decode(response.responseText);
                                        win = createMsgWin(head);
                                        addMsg(obj.msgObj);
                                        addTaskImg(head);
                                        addRecentUser(head);
                                    }
                                });
                            }
                        } else {
                            addTaskImg(win.headInfo);
                            addMsg(msgArr[i]);
                            addRecentUser(win.headInfo);
                        }

                    }
                }
            });
        },
        interval: 2000
    });

    function requesFail() {

    }

    function addMsg(obj) {
        Ext.get("msgBox" + obj[0].id).insertHtml("beforeEnd", msgTpl.apply(obj))
        var d = Ext.getCmp("msgBoxPanel" + obj[0].id).body.dom;
        d.scrollTop = d.scrollHeight - d.offsetHeight;

        var winId = msgWinIdPre + obj[0].id;
        var win = msgWin.get(winId);
        var activeWin=msgWin.getActive();
        if ((!activeWin)||(activeWin.getId()!=winId)) {
            win.newMsgs+=obj.length;
            addMsgBoxTitle(obj.length,obj[0].msgtype);
            var rcd = taskStore.getById(obj[0].id);
            if (rcd) {
                rcd.set("hasnew", true);
            }
        }
    }

    function addMsgBoxTitle(num,msgType) {
        var cmp = Ext.getCmp("msgBox-tab");
        var cmpb,pre;
        if(msgType=="g"){
            cmpb = Ext.getCmp("msgBox-tab-group");
            pre="讨论组";
        }else if(msgType=="u"){
            cmpb = Ext.getCmp("msgBox-tab-friend");
            pre="用户留言";
        }else if(msgType=="s"){
            cmpb = Ext.getCmp("msgBox-tab-sys");
            pre="系统消息";
        }else{
            cmpb = Ext.getCmp("msgBox-tab-friend");
            pre="用户留言";
        }


        cmp.newMsgs = cmp.newMsgs + num;
        cmpb.newMsgs = cmpb.newMsgs + num;

        cmp.setTitle("消息盒子（" + cmp.newMsgs + "）");
        cmpb.setTitle(pre+"（" + cmpb.newMsgs + "）");

        cmpb.body.dom.innerHTML = "<br>您有新的消息！";
    }

    function subMsgBoxTitle(num,msgType) {
        var cmp = Ext.getCmp("msgBox-tab");
        var cmpb, pre,bodyPre;
        if(msgType=="g"){
            cmpb = Ext.getCmp("msgBox-tab-group");
            pre="讨论组";
        }else if(msgType=="u"){
            cmpb = Ext.getCmp("msgBox-tab-friend");
            pre="用户留言";
        }else if(msgType=="s"){
            cmpb = Ext.getCmp("msgBox-tab-sys");
            pre="系统消息";
        }else{
            cmpb = Ext.getCmp("msgBox-tab-friend");
            pre="用户留言";
        }

        cmp.newMsgs = cmp.newMsgs - num;
        cmpb.newMsgs = cmpb.newMsgs - num;

        cmp.setTitle("消息盒子（" + cmp.newMsgs + "）");
        cmpb.setTitle(pre+"（" + cmpb.newMsgs + "）");
        if(cmpb.newMsgs==0){
            if(msgType=="g"){
               bodyPre="您目前还没有讨论组留言，请从右侧“讨论组”中选择讨论组发起聊天！";
            }else if(msgType=="u"){
               bodyPre="您目前还没有用户留言，请从右侧列表中选择联系人发起聊天！";
            }else{
               bodyPre="您目前还没有系统消息！";
            }
            cmpb.body.dom.innerHTML = "<br>"+bodyPre;
        }
    }

    //    function tt(){}
    // Start a simple clock task that updates a div once per second
    //    window.setInterval("tt()",500);
    var task = {
        run: function() {
            var rcds = taskStore.query("hasnew", true);
            rcds.each(function(rcd) {
                var el = Ext.get("task" + rcd.id);
                if (el)
                    el.toggleClass("thumb-wrap-active");
            });
//            if (rcds.getCount() > 0) {
//                top.document.title = top.document.title.toggle("您有新的消息！", windowTitle);
//            } else {
//                top.document.title = windowTitle;
//            }
        },
        interval: 400
    }
    var runner1 = new Ext.util.TaskRunner();
    runner1.start(task);
});

function getLeafs(node) {
    var userIds = [];
    node.eachChild(function(n) {
        if (n.isLeaf()) {
            var id=n.attributes.guid?n.attributes.guid:n.id;
            userIds.push(id);
        } else {
            var ids=getLeafs(n);
            for(var i=0;i<ids.length;i++){
                userIds.push(ids[i]);
            }
        }
    });
    return userIds;
}
//获取真的字符串长度
function GetBytesLength(str)
{
    var re = /[\x00-\xff]/g;
    var len = str.length;
    var array = str.match(re);
    if (array == null) {
        array = "";
    }
    return len * 2 - array.length;
}
var base = Ext.select("base").elements[0].getAttribute("href");
var fileuplaod = null;
var uploadSetting = {
		upload_url : base+"fileupload/upload.do?folder=msgfile",
		flash_url : "pub/webim/js/swfupload.swf",
		file_size_limit :100*1024,//上传文件体积上限，单位MB
		file_post_name : "file",
		file_types : "*.*", //允许上传的文件类型 
		file_types_description : "All Files", //文件类型描述
		file_upload_limit : "5", //限定用户一次性最多上传多少个文件，在上传过程中，该数字会累加，如果设置为“0”，则表示没有限制 
		use_query_string : true,
		debug : false,
		button_cursor : SWFUpload.CURSOR.HAND,
		button_window_mode : SWFUpload.WINDOW_MODE.TRANSPARENT,
		custom_settings : {//自定义参数
			scope_handler : this
		},
		file_queued_handler:function(){
			if (fileuplaod) {
				if (fileuplaod.getStats().files_queued > 0) {
					fileuplaod.uploadStopped = false;
					fileuplaod.startUpload();
				}
			}
		},
		swfupload_loaded_handler : function(){},// 当Flash控件成功加载后触发的事件处理函数
		file_dialog_start_handler:function(){},
		file_dialog_complete_handler:function(){},
		uploadProgress : function(file, bytesComplete, totalBytes){},//处理进度条
		upload_start_handler : function(file){
		   var post_params = uploadSetting.post_params;  
		   Ext.apply(post_params,{//处理中文参数问题
		   		//fileName : file.name,
		        fileName : encodeURIComponent(file.name)
		   });  
		   this.setPostParams(post_params);  
		},// 开始上传文件前触发的事件处理函数
		swfupload_loaded_handler:function(){},
		upload_complete_handler:function(){
			if (this.getStats().files_queued > 0 && fileuplaod.uploadStopped == false) {
				fileuplaod.uploadStopped = false;
				fileuplaod.startUpload();
			}
		},
		upload_error_handler:function(){},
		file_queue_error_handler:function(file,n){
			switch(n){
				case -100 : tip('待上传文件列表数量超限，不能选择！');
				break;
				case -110 : tip('文件太大，不能选择！');
				break;
				case -120 : tip('该文件大小为0，不能选择！');
				break;
				case -130 : tip('该文件类型不可以上传！');
				break;
			}
			function tip(msg){
				Ext.Msg.show({
					title : '提示',
					msg : msg,
					width : 280,
					icon : Ext.Msg.WARNING,
					buttons :Ext.Msg.OK
				});
			}
		}
};

function downFile(obj){
	var iframe = Ext.get("webimiframe");
	iframe.dom.action=obj;
	iframe.dom.target="_blank";
	iframe.dom.submit();
	/*iframe.dom.action = obj;
	iframe.submit();*/
}