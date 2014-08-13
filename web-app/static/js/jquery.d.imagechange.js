/********************************************************************************************************
 * D-ImageChange
 *----------------------------------------------------------------------------------------------------
 * @Desc 图片轮换插件
 *----------------------------------------------------------------------------------------------------
 * @Author D.夏亦知非
 * @Email DeclanZhang@gmail.com
 * @QQ 29540200
 * @Blog http://onblur.javaeye.com
 * @Date 2009-10-19
 * @Version V1.3@2010-03-16
 * @JQueryVersion 1.3.2+ (建议使用1.4以上版本)
 * 
 * @update v1.1 增加清空原始内容功能,以免页面加载完成之前显示大片空白
 * 		   v1.2 修正IE6每次从服务器读取背景图片的BUG
 *         v1.3 修正了宽度过大产生的BUG, 兼容JQ1.4.1, 建议使用JQ1.4+, 效率更高
 **/

// 修正IE6每次从服务器读取背景图片的BUG
try {
	document.execCommand('BackgroundImageCache', false, true);
}catch(e){

}


(function($){

jQuery.fn.extend({
	
	d_imagechange:function(setting){
		
		var config = $.extend({
			bg:true,						// 是否背景色
			title:true,						// 是否有标题
			desc:true,						// 是否有描述
			btn:true,						// 是否显示按钮
			repeat:'no-repeat',				// 重复规则 'no-repeat' 'repeat-x' 'repeat-y' 'repeat'
			
			bgColor:'#000',					// 背景色
			bgOpacity:.5,					// 背景透明度
			bgHeight:40,					// 背景高
			
			titleSize:14,					// 标题文字大小
			titleFont:'Verdana,宋体',		// 标题文本字体
			titleColor:'#FFF',				// 标题文本颜色
			titleTop:4,						// 标题上边距
			titleLeft:4,					// 标题左边距
			
			descSize:12,					// 描述文字大小
			descFont:'Verdana,宋体',			// 描述文本字体
			descColor:'#FFF',				// 描述文本颜色
			descTop:2,						// 描述上边距
			descLeft:4,						// 描述左边距
			
			btnAreaWidth:null,						//按钮区域的高度，不指定，则根据按钮数量和按钮宽度自动计算
			btnAreabgImage:"none",				//按钮区域背景图片
			btnAreabgRepeat:"no-repeat",	//按钮区域背景图重复规则
			btnAreabgPositionX:0,					//按钮区域背景图postionX
			btnAreabgPositionY:0,					//按钮区域背景图postionY
			btnAreaPaddingTop:0,					//按钮区域PaddingTop
			btnAreaPaddingLeft:0,					//按钮区域PaddingLeft
			btnColor:'',									// 按钮北景颜色
			btnBgImage:'none',						//按钮背景图片
			btnOpacity:.5,								//未选中按钮透明度
			btnFont:'Verdana',						// 按钮文本字体
			btnFontSize:12,								// 按钮文字大小(注意:Chrome有默认最小字号的限制)
			btnFontColor:'#000',					// 按钮文本颜色
			btnText:true,									// 是否显示文本
			btnWidth:15,									// 默认按钮宽
			btnHeight:15,									// 默认按钮高			
			btnMargin:0,									// 按钮间距
			btnTop:4,											// 按钮上边距
			btnPaddingTop:0,							// 按钮内边距
			btnPaddingLeft:0,							// 按钮内边距
			
			selectBtnWidth:15,						// 选中的按钮宽
			selectBtnHeight:15,						// 选中的按钮高
			selectBtnFontColor:'#000',		// 选中的按钮文本颜色
			selectBtnBgImage:'none',			//按钮背景图片
			
			playTime:2000,					// 轮换间隔时间,单位(毫秒)
			animateTime:500,				// 动画执行时间,单位(毫秒)
			animateStyle:'o',				// 动画效果:'o':渐显 'x':横向滚动 'y':纵向滚动 'show':原地收缩伸展 'show-x':横向收缩伸展 'show-y':纵向收缩伸展' none':无动画
			width:576,						// 宽, 不设定则从DOM读取
			height:371						// 高, 不设定则从DOM读取
		},setting);
		
		return $(this).each(function(){
			var _this = $(this);
			var _w = config.width || _this.width();			// 宽
			var _h = config.height || _this.height();		// 高
			var _n = config.data.length;					// 数目
			var _i = 0;										// 当前显示的item序号
			
			_this.empty()
				 .css('overflow','hidden')
				 .width(_w)
				 .height(_h);
			
			// 半透明背景
			if(config.bg){
			$('<div />').appendTo(_this)
						.width(_w)
						.height(config.bgHeight)
						.css('background-color',config.bgColor)
						.css('opacity',config.bgOpacity)
						.css('position','absolute')
						.css('marginTop',_h-config.bgHeight)
						.css('left',0)
						.css('zIndex',3333);
			}
			
			// 文字区
			var _textArea = 
			$('<div />').appendTo(_this)
						.width(_w)
						.height(config.bgHeight)
						.css('position','absolute')
						.css('marginTop',_h-config.bgHeight)
						.css('textAlign','left')
						.css('zIndex',6666);
			// 按钮区
			//alert(config.btnAreaWidth==null?(config.data.length * (config.btnWidth + config.btnMargin)):config.btnAreaWidth);
			//alert(_h-config.bgHeight);
			var _btnArea = 
			$('<div />').appendTo(_this)
						.width(config.btnAreaWidth==null?(config.data.length * (config.btnWidth + config.btnMargin)):config.btnAreaWidth)
						.height(config.bgHeight)
						.css('position','absolute')
						.css('marginTop',_h-config.bgHeight)
						.css('marginLeft',_w-(config.btnAreaWidth==null?(_n * (config.btnWidth + config.btnMargin)):config.btnAreaWidth))
						.css('zIndex',9999)
						.css('background-image',config.btnAreabgImage)
						.css('background-repeat',config.btnAreabgRepeat)
						.css('background-position-x',config.btnAreabgPositionX)
						.css('background-position-y',config.btnAreabgPositionY)
						.css('paddingTop',config.btnAreaPaddingTop)
						.css('paddingLeft',config.btnAreaPaddingLeft)
						//.css('border',"1px solid #ff0000")
						.css('display',config.btn?'block':'none');
			// 插入空div修正IE的绝对定位BUG
			$('<div />').appendTo(_this);
			
			// 图片区
			var _imgArea = 
			$('<div />').appendTo(_this)
						.width('x,show-x'.indexOf(config.animateStyle)!=-1?_w*_n:_w)
						.height('y,show-y'.indexOf(config.animateStyle)!=-1?_h*_n:_h);
						//.css('border','1px #ff0000 solid');
	
			// 初始化图片 文字 按钮
			$.each(config.data,function(i,n){
				$('<a />').appendTo(_imgArea)
						  .width(_w)
						  .height(_h)
						  .attr('href',n.href?n.href:'')
						  .attr('target',n.target?n.target:'')
						  .css('display','block')
						  .css('background-image','url('+n.src+')')
						  .css('background-repeat',config.repeat)
						  .css('display','block')
						  .css('left',0)
						  .css('top',0)
						  .css('float','x,show-x'.indexOf(config.animateStyle)!=-1?'left':'');
						  
				if(config.title){
				$('<b />').appendTo(_textArea)
						  .html(n.title?n.title:'')
						  .css('display',i==0?'block':'none')
						  .css('fontSize',config.titleSize)
						  .css('fontFamily',config.titleFont)
						  .css('color',config.titleColor)
						  .css('marginTop',config.titleTop)
						  .css('marginLeft',config.titleLeft);
				}
				
				if(config.desc){
				$('<p />').appendTo(_textArea)
						  .html(n.desc?n.desc:'')
						  .css('display',i==0?'block':'none')
						  .css('fontSize',config.descSize)
						  .css('fontFamily',config.descFont)
						  .css('color',config.descColor)
						  .css('marginTop',config.descTop)
						  .css('marginLeft',config.descLeft);
				}
				  
						  
				$('<a />').appendTo(_btnArea)
						  .width(i==0?config.selectBtnWidth:config.btnWidth)
						  .height(i==0?config.selectBtnHeight:config.btnHeight)
						  .html(config.btnText?i+1:'')
						  .css('fontSize',config.btnFontSize)
						  .css('fontFamily',config.btnFont)
						  .css('textAlign','center')
						  .css('display','block')
						  .css('float','left')
						  .css('overflow','hidden')
						  .css('marginTop',config.btnTop)
						  .css('marginRight',config.btnMargin)
						  .css('background-color',config.btnColor)
						  .css('opacity',i==0?1:config.btnOpacity)
						  .css('color',i==0?config.selectBtnFontColor:config.btnFontColor)
						  .css('background-image',i==0?config.selectBtnBgImage:config.btnBgImage)
						  .css('cursor','pointer')
						  .css('paddingTop',config.btnPaddingTop)
						  .css('paddingLeft',config.btnPaddingLeft)

			});
			
			// 保存所有元素集合的引用,方便在事件中使用
			var _bs = _btnArea.children('a');
			var _ts = _textArea.children('b');
			var _ds = _textArea.children('p');
			var _is = _imgArea.children('a');

			// 针对不同的动画效果的附加设置, 主要是block的问题, 若在初始化时设置block:none会造成之后无block效果
			if('o,show,none'.indexOf(config.animateStyle)!=-1){
				_is.not(':first').hide();
				_is.css('position','absolute');
			}
			
			// 添加按钮事件
			_bs.click(function(){
				var ii = _bs.index(this);
				if(ii==_i){return;}
				
				_ts.eq(_i).css('display','none');
				_ts.eq(ii).css('display','block');
				_ds.eq(_i).css('display','none');
				_ds.eq(ii).css('display','block');
				_bs.eq(_i).css('opacity',config.bgOpacity);
				_bs.eq(ii).css('opacity',1);
				_bs.eq(_i).css('background-image',config.btnBgImage);
				_bs.eq(ii).css('background-image',config.selectBtnBgImage);
				_bs.eq(_i).width(config.btnWidth);				
				_bs.eq(_i).height(config.btnHeight);
				_bs.eq(ii).width(config.selectBtnWidth);
				_bs.eq(ii).height(config.selectBtnHeight);
				_bs.eq(_i).css('color',config.btnFontColor);
				_bs.eq(ii).css('color',config.selectBtnFontColor);				
				
				switch(config.animateStyle){
				case 'o' :
					_is.eq(_i).fadeOut(config.animateTime);
					_is.eq(ii).fadeIn(config.animateTime);
					break;
				case 'x' :
					_imgArea.animate({marginLeft:-ii*_w},config.animateTime);
					break;
				case 'y' :
					_imgArea.animate({marginTop:-ii*_h},config.animateTime);
					break;
				case 'show' :
				case 'show-x' :
				case 'show-y' :
					_is.eq(_i).hide(config.animateTime);
					_is.eq(ii).show(config.animateTime);
					break;				
				case 'none' :
					_is.eq(_i).hide();
					_is.eq(ii).show();
					break;				
				}
				_i = ii;
			});

			// 添加轮换任务
			var _play = setInterval(play,config.playTime);
			
			function play(){
				if(typeof(stop)=="undefined" || !stop)
					_bs.eq((_i+1)%_n).click()
			}		
			// 鼠标进入事件
			_this.mouseover(function(){
				clearInterval(_play);
			});
						
			// 鼠标离开事件
			_this.mouseout(function(){
				_play = setInterval(play,config.playTime);
			});
			
		});
	}
});












})(jQuery);