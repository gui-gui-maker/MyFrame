var formConfig = {
    title:"标题",
    tab:[
        {
            title:"tab1",
            fields:[
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label",columnWidth:1}
            ]
        },
        {
            title:"tab2",
            fields:[
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label",columnWidth:1}
            ]
        },
        {
            title:"tab3",
            fields:[
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label"},
                {name:"name1",xtype:"label",columnWidth:1}
            ]
        }
    ]
}

var systemFormConfig={
    renderTo: document.body,
    activeTab: 0
};
Ext.each(formConfig.tab,function(obj){
    var tab={tt:""};
    tab.title=obj.title;
    tab.items=function(){};
    systemFormConfig.items=tab;
})