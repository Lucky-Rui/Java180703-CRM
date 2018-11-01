var util = {
	SUCCESS : 0,
	ERROR : 1,
	NEED_LOGIN : 2,
	ILLEGAL_PERMISSION : 3,
	isNull : function(value){
		if(value == '' || value == undefined || value == null) {
			return true;
		}
	},
	getSelectedIds : function(data) {
		var idArray = [];// [1,3,4]
	    for(var i in data){
	    	idArray.push(data[i].id);
	    }
	    var ids = idArray.join(","); // "1,3,4"
	    return ids;
	}
}