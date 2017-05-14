/**
 * 轮播器设置页面脚本，定义公用方法在页面调用
 */
$(function() {
	// 下线节点的容器
	var offLineTab = $("#offLineTab");
	// 在线节点容器
	var onLineTab = $("#onLineTab");

	// 添加选中
	$("#addSec").click(
			function() {
				addItem(false, offLineTab.find("label"), offLineTab
						.find("input.adRollCheck"), offLineTab, onLineTab);
			});
	// 添加所有
	$("#addAll").click(
			function() {
				addItem(true, offLineTab.find("label"), offLineTab
						.find("input.adRollCheck"), offLineTab, onLineTab);
			});
	// 删除选中
	$("#delSec").click(
			function() {
				addItem(false, onLineTab.find("label"), onLineTab
						.find("input.adRollCheck"), onLineTab, offLineTab);
			});
	// 删除所有
	$("#delAll").click(
			function() {
				addItem(true, onLineTab.find("label"), onLineTab
						.find("input.adRollCheck"), onLineTab, offLineTab);
			});
	// 上移
	$("#up").click(function() {
		exchange(true, onLineTab.find("label"), onLineTab.find("input.adRollCheck"));
	});
	// 上移
	$("#down").click(function() {
		exchange(false, onLineTab.find("label"), onLineTab.find("input.adRollCheck"));
	});
});

/** **************************************方法定义************************************************* */
/**
 * 从所有的checkbox中得到选中的节点数组
 * 
 * @param addItems
 *            可操作的节点数组
 * @param boxItems
 *            复选框数组，每个可操作的节点对应的一个
 * @return 选中的节点数组
 */
function getCheckedBoxs(addItems, boxItems) {
	var arr = new Array();
	for (var j = 0; j < boxItems.length; j++) {
		var boxItem = boxItems[j];
		if (boxItem.checked) {
			arr.push(addItems[j]);
		}
	}
	return arr;
}

/**
 * 向在线部分添加节点或删除节点
 * 
 * @param isAll
 *            true是添加/删除所有，false是添加/删除选中
 * @param addItems
 *            可操作的节点数组
 * @param boxItems
 *            复选框数组，每个可操作的节点对应的一个
 * @param from
 *            节点移出方
 * @param to
 *            节点移入方
 * @return 操作的结果
 */
function addItem(isAll, addItems, boxItems, from, to) {
	var arr = new Array();
	for (var i = 0; i < addItems.length; i++) {
		var addItem = $(addItems[i]);
		if (isAll) {
			arr.push(addItem);
		} else {
			arr = getCheckedBoxs(addItems, boxItems);
		}
	}
	if (arr.length <= 0) {
		alert("没有可操作项");
		return false;
	}
	// 移动节点
	if (arr.length > 0) {
		to.append(arr);
		from.remove(arr);
	}
	return true;
}

/**
 * 上下移动节点
 * 
 * @param isUp
 *            true是向前移动，false是向后移动
 * @param exchItems
 *            移动的子节点数组
 * @param boxItems
 *            复选框数组，每个移动节点对应的一个
 * @return 移动的结果
 */
function exchange(isUp, exchItems, boxItems) {
	var arr = getCheckedBoxs(exchItems, boxItems);
	if (arr.length > 1) {
		alert("一次只能移动一个节点");
		return false;
	} else if (arr.length == 0) {
		alert("请选择一个要移动的节点");
		return false;
	}
	var exchItem = $(arr[0]);
	var temp = exchItem.clone();
	if (isUp) {
		var prev = exchItem.prev();
		if (exchItems.index(exchItem) == 0) {
			alert("已经是第一个元素");
			return true;
		} else {
			exchItem.remove();
			prev.before(temp);
		}
	} else {
		var next = exchItem.next();
		if (exchItems.index(exchItem) == exchItems.length - 1) {
			alert("已经是最后一个元素");
			return true;
		} else {
			exchItem.remove();
			next.after(temp);
		}
	}
	return true;
}

/**
 * 获取在线数据的对应ID字符串
 * 
 * @param onlineItems
 *            在线节点集合
 * @return id以","分割的字符串
 */
function getOnlineIds(onlineItems) {
	var ids = "";
	// 如果记录为0，返回
	if (onlineItems.length < 1) {
		alert("至少保留一条记录");
		return "";
	}
	// 更新数据
	for (var i = 0; i < onlineItems.length; i++) {
		ids += $(onlineItems[i]).attr("id") + ",";
	}
	return ids;
}

/**
 * 保存结果
 * 
 * @param action
 *            请求action
 * @param postData
 *            post请求json数据
 * @return
 */
function saveChange(action, postData) {
	$.post(action, postData, function(data) {
		if (data) {
			alert("修改成功！");
			location.reload();
		} else {
			alert("修改失败！");
		}
	});
}