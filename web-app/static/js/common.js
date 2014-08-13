function PageList(pageCount,currentPage,parameter){
	if(pageCount<1)pageCount=1;
    var strHtml = "";
    var ini_PageList_Step = 5;
    var ini_PageList_Start = currentPage - ini_PageList_Step;
    var ini_PageList_End = currentPage + ini_PageList_Step;
    if((currentPage - ini_PageList_Step)<=0){
        ini_PageList_End = ini_PageList_End -  (currentPage - ini_PageList_Step) + 1;
    }
    if((pageCount - currentPage) <= ini_PageList_Step){
        ini_PageList_Start = ini_PageList_Start - (ini_PageList_Step - (pageCount - currentPage)) - 1;
    }
    ini_PageList_Start = ini_PageList_Start <= 0?1:ini_PageList_Start;
    ini_PageList_End = ini_PageList_End < pageCount?ini_PageList_End:pageCount;
    if(pageCount <= 1){
        strHtml = '<a class="pages">1</a>';
    }else{
        for(i=ini_PageList_Start; i<=ini_PageList_End; i++){
            if(currentPage == i){
                strHtml += '<a class="pages">' + i + '</a>';
                if(i != (ini_PageList_End)){
                    strHtml += ' ';
                }
            }else{
                strHtml += '<a href="?currpage=' + i + parameter +'">' + i + '</a>';
                if(i != (ini_PageList_End)){
                    strHtml += '  ';
                }
            }
        }
    }
    return strHtml;
}

function fSend(){
 window.location.href="mailto:XXXX@XXX.com";
 }

String.prototype.trim=function()
{return this.replace(/(^[\s]*)|([\s]*$)/g,"");}
String.prototype.lTrim=function()
{return this.replace(/(^[\s]*)/g,"");}
String.prototype.rTrim=function()
{return this.replace(/([\s]*$)/g,"");}

Function.prototype.bind = function()  {   
	var __method   =   this;
	var arg   =   arguments;
	return  function(){
		__method.apply(window,   arg);
	}
}

lastScrollY = 0;
function MenuMoveWithScroll() 
{ 
	if($("#slide").length==0){
		return;
	}
	if(typeof window.pageYOffset != 'undefined') { 
        nowY = window.pageYOffset; 
    } 
     else if(typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') { 
        nowY = document.documentElement.scrollTop; 
     } 
     else if(typeof document.body != 'undefined') { 
        nowY = document.body.scrollTop; 
     }
    nowY = nowY - $("#pageleft_inner").offset().top;
	percent = .1*(nowY - lastScrollY);
	if(percent > 0) 
	{
		percent=Math.ceil(percent);
	} 
	else
	{
		percent=Math.floor(percent);
	}
	var itop = parseInt($("#slide").position().top) + percent;
	if(itop<0) {itop = 0;lastScrollY=0}
	//if(itop>nowY) {itop = nowY;lastScrollY = nowY;}
	$("#slide").css('top',itop);
	lastScrollY = lastScrollY + percent;
	//$("#SlideNum").append("#"+$("#slide").position().top);
	tOut = window.setTimeout(MenuMoveWithScroll,1);
}

var oldpid = null;
var oldinfotype = null;
var timeout4hidepinfo = null;
function ShowProductInfo(pid,infotype)
{
	if(oldpid!=null && pid!=oldpid)
	{
		$("#product_"+oldpid+"_title_"+oldinfotype).attr("class","close");
		$("#product_"+oldpid+"_info").fadeOut(1000,function(){});
	}
	oldpid = pid;
	oldinfotype = infotype;
	CancelHideProductInfo();
	var src = $("#product_"+pid+"_title_"+infotype);
	var items = src.parent().parent().find("div");
	for(i=0;i<items.length;i++)
	{
		$(items[i]).attr("class","close");
	}
	
	$("#product_"+pid+"_info_content").html("");
	$("#product_"+pid+"_info_content").html($("#product_"+pid+"_"+infotype).html());
	//$("#product_"+pid+"_info").css('display','block');
	
	src.attr("class","open");
	$("#product_"+pid+"_info").fadeIn(1000);
	$("#product_"+pid+"_info").css("margin-left",0);
	
	var pw = $("#product_"+pid+"_info").parent().width();
	var srcleft = src.offset().left;
	var objleft = $("#product_"+pid+"_info").offset().left;
	var objw = $("#product_"+pid+"_info").width();
	var srcw = src.width();
	
	ileft = (srcleft+srcw/2) - (objleft+objw/2);
	if(ileft<0) ileft = 0;
	if(ileft>(pw-objw)) ileft = pw-objw;
	$("#product_"+pid+"_info").css("margin-left",ileft);
	$("#product_"+pid+"_info").unbind("mouseout",function(){HideProductInfo(pid,infotype)});
	$("#product_"+pid+"_info").bind("mouseout",function(){HideProductInfo(pid,infotype)});
}

function HideProductInfo(pid,infotype)
{
	CancelHideProductInfo();
	timeout4hidepinfo = window.setTimeout(function(){
		$("#product_"+pid+"_title_"+infotype).attr("class","close");
		$("#product_"+pid+"_info").fadeOut(1000,function(){});
	},2000);
}

function CancelHideProductInfo()
{
	if(timeout4hidepinfo != null) clearInterval(timeout4hidepinfo);
}


/***********头部********/

		function showlang(){
			clearHide();
			$("#langdiv").fadeIn();
		}
		function hidelang(){
			$("#langdiv").hide();
		}
		
		var iobj = null;
		function HideLang()
		{
			clearHide();
			iobj = window.setTimeout(function(){
				$("#langdiv").fadeOut(100,function(){});
			},100);
		}
		function clearHide()
		{
			if(iobj != null) clearInterval(iobj);
		}