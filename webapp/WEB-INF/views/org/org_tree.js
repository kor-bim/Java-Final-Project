var orgtreeClass = (function(){
	
	function orgtreeClass(){}
	
	orgtreeClass.prototype = {
		_callee : null,
		_node_data : null,
		_parent : null,
		_show_count : false,
		_show_no_member : true,	// 소속없음 표시여부
		_selectedEvent : null,
		
		// 이벤트 콜러
		setCallee : function(callee){
			this._callee = callee;
		},
		
		getCallee : function(){
			return this._callee;
		},
		
		// show count
		showCount : function(flag){
			if(flag == undefined){
				this._show_count = false;
			}
			this._show_count = flag;
		},
		getShowCount : function(){
			return this._show_count;
		},
		
		// 소속없음 표시여부
		setShowNoMember : function(flag){
			if(flag == undefined){
				flag = true;
			}
			this._show_no_member = flag;
		},
		getShowNoMember : function(){
			return this._show_no_member;
		},
		
		// 데이터 셋
		setNodeData : function(data){
			this._node_data = data;
		},
		getNodeData : function(){
			return this._node_data;
		},
		
		// 노드를 생성 할 영역
		setParentElement : function(el){
			this._parent = el;
		},
		getParentElement : function(){
			return this._parent;
		},
		
		setSelectedEvent : function(funcName)
		{
			this._selectedEvent = funcName;
		}
		
		,draw : function(){
			console.log(this);
			var data = this.getNodeData();
			var parent = this.getParentElement();
			this._draw(data, parent);
		},
		
		_draw : function(node, parent){
			if(parent.children('ul').length==0){
				parent.append('<ul class="node"></ul>');
			}
			
			var continue_idx = -1;
			if(this.getShowNoMember() == false){
				$j.grep(node, function(val, key){
					if(val.node_id == '-1'){
						continue_idx = key;
					}
				});
			}
			
			var _this = this;
			
			var selectedNodeEvent = _this.getCallee() + '.selectedNode(this, event);';
			if(_this._selectedEvent !== undefined && _this._selectedEvent !== null){
				selectedNodeEvent = _this._selectedEvent + '(this, event);';
			}

			$j.each(node, function(key, val){
				if(key == continue_idx){
					return true;
				}
				var li = $j('<li>',{'class':'last', 'no':val.node_id});
				//var child = (val.rgt - val.lft -1) / 2;
				var img = '<img onclick="orgtree.toggleTree(this, event);" src="/static/ui/images/icon_minus.png" class="plus">';
				li.append('<div class="container"></div>');
				
				var div_container = li.children('div.container');
				
				/*
				if(child > 0 || val.node_id == '-1' || val.userlist != undefined){
					if(val.node_id == '-1' && val.userlist == undefined){
						//img = '';
					}
					//div_container.append(img);
				}
				*/
					
				div_container.append('<strong onclick="'+selectedNodeEvent+'">'+val.node_name+'</strong></div>');

				// 조직원 수 표시 여부
				if((_this.getShowCount()) == true){
					div_container.append('<span style="color:silver; font-size:8pt">('+val.count+')</span>');
				}
				var pNode = parent.find('li[no='+val.parent_no+']');
				
				if(pNode.length){
					if(pNode.children('ul[class=node]').length){
						pNode.children('ul[class=node]').children('li.last').removeClass('last');
						pNode.children('ul[class=node]').append(li);
					}else{
						pNode.append('<ul class="node"></ul>');
						pNode.children('ul[class=node]').append(li);
						pNode.children('div.container').append(img);
					}
				}else{
					parent.children('ul[class=node]').children('li.last').removeClass('last');
					parent.children('ul[class=node]').append(li);
				}
				
				// 사용자 추가
				if(val.userlist != undefined){
					var list_len = val.userlist.length;
					var its_me = parent.find('li[no='+val.node_id+']');
					var ul_user = its_me.find('ul[class=user]:first');
					
					if(ul_user.length == 0){
						its_me.append('<ul class="user"></ul>');
						ul_user = its_me.find('ul[class=user]:first');
					}
					
					for(var i=0; i<list_len; i++){	
						ul_user.append('<li user_no="'+val.userlist[i].user_no+'"><div><img src="/static/ui/images/icon_man.png" /><a href="javascript:void(0)"><span name="position">'+ val.userlist[i].position + '</span> <span name="user_name">' + val.userlist[i].user_name +'</span></a></div></li>');
					}
				}
			});
			
			_this.firstNodeHide();
		},
		
		toggleTree : function(obj, e){
			e.preventDefault();
			e.stopPropagation();
			e.stopImmediatePropagation();
			if($j.nodeName(obj, 'img')){
				var img = $j(obj);
				var mode = (img.prop('src').indexOf('icon_minus') > 0) ? 'collapse' : 'expand';

				// 닫기
				if(mode == 'collapse'){
					img.prop({'src':img.prop('src').replace('icon_minus', 'icon_plus')});
					$j(obj).closest('li').children('ul').hide();
				}
				// 펼치기
				else{
					img.prop({'src':img.prop('src').replace('icon_plus', 'icon_minus')});
					$j(obj).closest('li').children('ul').show();
				}
			}
		},
		
		firstNodeHide : function(){
			var parent = this.getParentElement();
			var re = 0;
			
			parent.find('li').each(function(i, el){
				if($j(el).parent('ul').parent('div').get(0) == parent.get(0)){
					return true;
				}
				if($j(el).children('ul').length){
					var img = $j(el).children('div').children('img');
					if(img.length){
						img.prop({'src':img.prop('src').replace('icon_minus', 'icon_plus')});
						img.closest('li').children('ul').hide();
					}
				}
			});
		},
		
		firstNodeShow : function(){
			var parent = this.getParentElement();
			var re = 0;
			
			parent.find('li').each(function(i, el){
				if($j(el).parent('ul').parent('div').get(0) == parent.get(0)){
					return true;
				}
				if($j(el).children('ul').length){
					var img = $j(el).children('div').children('img');
					if(img.length){
						img.prop({'src':img.prop('src').replace('icon_plus', 'icon_minus')});
						img.closest('li').children('ul').show();
					}
				}
			});
		}
	};
	
	return orgtreeClass;
})();

var orgtree = new orgtreeClass();