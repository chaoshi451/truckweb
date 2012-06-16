$(function(){
	initLeftMenu();
});
var proId;
var articleId;
// 初始化左侧菜单栏
function initLeftMenu() {
	$("#nav").accordion({animate:false});
	
	// 初始化左侧所有菜单
	$.each(_menus.menus, function(i, n) {
		var menulist = '';
		menulist += '<ul>';
		$.each(n.menus, function(j, o) {
			menulist += '<li><div><a ref="'+ o.menuid +'" href="#" rel="'+ o.url 
			+'"><span class="icon '+ o.icon +'">&nbsp;</span><span class="nav">' + o.menuname + '</span></a></div></li>';
		});
		menulist += '</ul>';
		$('#nav').accordion('add', {
			title: n.menuname,
			content: menulist,
			iconCls: 'icon ' + n.icon
		});
	});
	
	// 默认展开《产品管理》面板
	$('#nav').accordion('select','产品管理');
	
	// 处理菜单链接
	$('.easyui-accordion li a').click(function(){
		var tabTitle = $(this).children('.nav').text();
		var url = $(this).attr('rel');
		var menuid = $(this).attr('ref');
		var icon = getIcon(menuid);
		addTab(tabTitle, url, icon);
	});
}

// 获取左侧导航的图标
function getIcon(menuid) {
	var icon = "icon ";
	$.each(_menus.menus, function(i, n) {
		$.each(n.menus, function(j, o) {
			if (o.menuid == menuid) {
				icon += o.icon;
			}
		});
	});
	return icon;
}

function addTab(subtitle, url, icon) {
	if(!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title: subtitle,
			href: url,
			closable: true,
			icon: icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

function tabClose() {
	$('.tabs-inner').dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	$('.tabs-inner').bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
		var subtitle = $(this).children(".tabs-closable").text();
		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}