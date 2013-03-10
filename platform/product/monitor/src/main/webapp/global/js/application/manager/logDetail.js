$(function(){
    $("body").layout({
        top:{topHeight:100},
        bottom:{bottomHeight:30}
    });
    $("#myDesk").height($("#layout_center").height());
    $("#nav").delegate('li', 'mouseover mouseout', navHover);
    $("#nav,#menu").delegate('li', 'click', navClick);
    getBarLength();
    $("#detail_grid").Grid({
        url : ctx+"/application/manager/appmethod/getParamDetail/"+logId,
        dataType: "json",
        height: 'auto',
        colums:[
            {id:'1',text:'名称',name:"methodName",width:'',index:'1',align:'',color:''},
            {id:'2',text:'数值',name:"maxTime",width:'',index:'1',align:'',color:''}
        ],
        rowNum:10,
        rowList:[10,20,30],
        pager : false,
        number:false,
        multiselect:false
    });
    getExceptionInfo();

});
function getBarLength(){
    $.ajax({
        url:ctx+"/application/manager/appmethod/getLogDetail/"+logId,
        type:"GET",
        dataType:"json",
        async:false,
        success:function(data){
            var len = data.length;
            $("#logDetail").html("");
            if(len > 0){
                var rowData = "";
                var sumLen = 0;
                var beginPosition = 0;
                for(var i = 0; i < len; i++){
                    if(data[i].type == "url"){
                        sumLen = data[i].consumeTime;
                        rowData = rowData + "<tr><td width='20%' class='m_name'><a>"+ data[i].urlOrMethod +"</a></td>"+
                        "<td width='20%' class='m_time' id='total_time'>总耗时:"+ data[i].consumeTime +"ms</td>" +
                        "<td width='60%'><div><div class='green_bar'>&nbsp;</div></div></td></tr>";
                    }else if(data[i].type == "method"){
                        var percent = data[i].consumeTime / sumLen *100;
                        rowData = rowData + "<tr onclick='getParam(\""+data[i].id+"\")'><td class='m_name'><a>"+ data[i].urlOrMethod +"</a></td>" +
                            "<td class='m_time'>耗时:"+ data[i].consumeTime +"ms</td>" +
                            "<td><div><a><div class='yellow_bar' style='margin-left:"+beginPosition+"%;width:"+percent+"%'>&nbsp;</div></a></div></td></tr>";
                        beginPosition= beginPosition+percent;
                    }
                }
                $("#logDetail").append("<tr><th width='20%' class='m_name'><a>请求路径/方法</a></th>" +
                "<th width='20%' class='m_time' id='total_time'>执行时间</th>" +
                "<th width='60%'>进度</th></tr>");
                $("#logDetail").append(rowData);
            }
        },
        error:function(a,b,c){
            alert("暂时无法获得详细数据");
        }
    });
}
function navHover(){
    $(this).toggleClass("hover")
}
function navClick(){
    $(this).addClass("seleck").siblings().removeClass("seleck");
    if($(this).hasClass('has_sub')){
        var subMav = $(this).children("ul.add_sub_menu");
        var isAdd = false;
        if($(this).parent().attr("id") == "menu"){
            isAdd = true;
        };
        subMav.slideDown('fast',function(){
            $(document).bind('click',{dom:subMav,add:isAdd},hideNav);
            return false;
        });
    };
}
function hideNav(e){
    var subMenu = e.data.dom;
    var isAdd = e.data.add;
    subMenu.slideUp('fast',function(){
        if(isAdd){
            subMenu.parent().removeClass('seleck');
        };
    });
    $(document).unbind();
}

function getExceptionInfo(){
    $.ajax({
        url:ctx+"/application/manager/appmethod/getExceptionInfo/"+logId,
        type:"GET",
        dataType:"text",
        async:false,
        success:function(data){
            var detailInfo = "<strong> 异常信息：</strong>";
            $("#exceptionInfo").html(detailInfo+data);
        },
        error:function () {
            alert("暂时无法获得异常信息");
        }
    });
}

function getParam(methodId){
    window.location.href = ctx+"/application/manager/appmethod/getMethodDetail/"+methodId;
        //ctx+"/views/application/manager/methodDetail.jsp?methodId=" + methodId;
}