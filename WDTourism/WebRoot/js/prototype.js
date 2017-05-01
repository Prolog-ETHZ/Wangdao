
var deriv1 ={};
deriv1.Graphic = function(svg_id){
	var self = this;
	var _event_handlers ={};
	var _disable_default_context_menu = function(){d3.event.stopPropagation();d3.preventDefault();return false;};
	var _node_date ={};
	var _link_data={};
	var _svg;
	if(svg_id){
		_svg = d3.select(svg_id);
	}else{
		_svg = d3.select("body").append("svg").attr("width",1000).attr("height",800).style("border","1px #000 solid");
	}
	var _link_layer = _svg.append("g");
	var _links;
	var _node_layer = _svg.append("g");
	var _nodes;
	var _force = d3.layout.force()
	               .charge(-300)
	               .friction(0.5)
	               .linkDistance(300)
	               .linkStrength(0.8)
	               .size([_svg.attr("width"),_svg.attr("height")])
	               .on("tick",function(){
	            	   if(_nodes){
	            		   _nodes.attr("transform",function(n){return "translate("+n.x+","+n.y+")";});
	            	   }
	            	   if(_links){
	            		   _links.selectAll("path").attr("d",function(d){
	            			   var dx = d.target.x-d.source.x,
	            			   dy = d.target.y - d.source.y,
	            			   dr = Math.sqrt(dx*dx+dy*dy),
	            			   theta = Math.atan2(dy, dx);
	            			   d90 = Math.PI/2,
	            			   d15 = Math.PI/12,
	            			   drr = Math.sqrt(2*(1-Math.cos(Math.PI/6)))*dr;
	            			   dtxs = d.target.x - drr*Math.cos(theta+d15);
	            			   dtys = d.target.y - drr*Math.sin(theta+d15);
	            			   return "M"+d.source.x+" "+d.source.y+" A "+dr+" "+dr+" 0 0 1"+d.target.x+" "+d.target.y+" A "+dr+" "+dr+" 0 0 0,"+d.source.x+","+d.source.y+"M"+dtxs+","+dtys+"l"+(7*Math.cos(d90-theta)-20*Math.cos(theta))+","+(-7*Math.sin(d90-theta)-20*Math.sin(theta))+"L"+(dtxs-7*Math.cos(d90-theta)-20*Math.cos(theta))+","+(dtys+7*Math.sin(d90-theta)-20*Math.sin(theta))+"z";
	            			   
	            		   });
	            	   }	   
	               });
	var _message_box = d3.select("body").append("div")
	                    .attr("class","message_box")
	                    .style("left","40%")
	                    .style("top","40%")
	                    .style("display","none")
	                    .call(d3.behavior.drag().on("drag",function(){
	                    	var dragTarget = d3.select(this);
	                    	dragTarget.style({
	                    		left:d3.event.dx+parseInt(dragTarget.style("left"))+"px",
	                    		//top:d3.event.dy+parseInt(dragTarget.style("top"))="px"                                      	
	                    	});
	                    }));
	var _message_title_bar = _message_box.append("div");
	var _message_title = _message_box.append("span");
	_message_title_bar.append("a")
	          .attr("href","javascript:{}")
	          .text("[close]")
	          .on("click",hide_message_box);
	_message_box.append("br");
	_message_box.append("br");
	var _message_box_text = _message_box.append("textarea")
	                                .attr("readonly","readonly")
	                                .attr("cols","40")
	                                .attr("rows","15")
	                                ;
	function hide_message_box(){
	    _message_box.style("display","none");
	}
	_self.show_message_box = function(t,s){
	    _message_title_text(t);
	    _message_box_text.text(s);
	    _message_box.style("left","40%");
	    _message_box.style("top","30%");
	    _message_box.style("display","inline-block");
	    
	}
	var _context_menu = d3.select("body").append("ul")
	                                     .attr("class","context-menu")
	                                     .attr("display","none")
	                                     .on("contextmenu",_disable_default_context_menu);
	var _hide_context_menu = function(){
	   _context_menu_style("display","none");
	};
	_svg.on("mousedown",function(){_hide_context_menu();_force_start();});
	var _default_node_menu ={
			"_lock":{
		       name:function(n){return n.fixed?"寻找临近景点":"寻找临近景点"},
		       action:function(n){console.log(n.data.name);}
	        },
	        "_delete":{
	        	name:"寻找路线匹配",
	        	action:function(n){delete _nodes_date[n.data["_id"]];_self.update();}
	        },
	        "catch":{
	        	name:"寻找相似景点",
	        	action :null
	        }
	        
	};
	var _util_is_function = function(functionToCheck){
	   var getType ={};
	   return functionToCheck && getType.toString.call(functionToCheck)=='[object Function]';
	   
	}
	var _show_context_menu = function(n){
		var id = n.data["_id"];
		var actions = n.data["actions"];
		if(id){
			_context_menu.selectAll("li").remove();
			var menu_items = d3.entries(actions);
			d3.entries(_default_node_menu).forEach(function(e){
				menu_items.push({key:e.key,value:_util_is_function(e.value["name"])?e.value["name"](n):e.value["name"]});
				
			});
			_context_menu.selectAll("li").data(menu_items,function(e){return id+"+"+e.key})
			                             .enter()
			                             .append("li")
			                             .on("click",function(e){
			                            	 _hide_context_menu();
			                            	 if(_default_node_menu[e.key]){
			                            		 _default_node_menu[e.key]["action"](n);
			                            	 }else{
			                            		 _event_handlers["action"](id,e.key,n);
			                            	 }
			                            	 _force.start();
			                             })
			                             .text(function(e){return e.value});
			_context_menu.style("display","block").style("left",d3.event.clientX).style("top",d3,event.clientY);
			_force.stop();
		}
	};
	_self.lock = function(b){
	   if(_nodes){
		 _nodes.data().forEach(function(n){n.fixed=b;});  
	   }
	};
	_self.removeAllLinks=function(){
		d3.keys(_link_data).forEach(function(id){delete _links_data[id];});
	}
	_self.removeAll=function(){
		_self.removeAllLinks();
		d3.keys(_nodes_data).forEach(function(id){delete _nodes_data[id];});
	}
	var _global_context_menu = {
		"_lock":{
		   name:"Lock All",
		   action:function(){_self.lock(true);}
	    },
	    "_unlock":{
	    	name:"UnLock All",
	    	action:function(){_self.lock(false);}
	    },
	    "_delete_links":{
	    	name:"Delete All Links",
	    	action:function(){_self.removeAllLinks();_self.update();}
	    },
	    "_delete":{
	    	name:"Delete All",
	    	action:function(){_self.removeAll();_self.update();}
	    }
	};
	var _show_global_context_menu = function(){
		_context_menu.selectAll("li").remove();
		var menu_items =[];
		d3.entries(_global_context_menu).forEach(function(e){
			menu_items.push({key:e.key,value:_util_is_function(e.value["name"])?e.value["name"]:e.value["name"]});
		});
		_context_menu.selectAll("li").data(menu_items,function(e){return "global#"+e.key})
		                             .enter()
		                             .append("li")
		                             .on("click",function(e){
		                            	 _hide_context_menu();
		                            	 if(_global_context_menu[e.key]){
		                            		 _global_context_menu[e.key]["action"]();
		                            	 }
		                            	 _force.start();
		                             })
		                             .text(function(e){return e.value;});
		_context_menu.style("display","block").style("left",d3.event.clientX).style("top",d3.event.clientY);
		_force.stop();
	};
	_svg.on("contextmenu",function(){_show_global_context_menu();_disable_defalut_context_menu();});
	_self.update = function(){
		var force_nodes = _force.nodes();
		d3.entries(_nodes_data).map(function(entry){
			entry.value["_id"]=entry.key;
			return entry.value;
		}).forEach(function(n){
			for(var i;i<force_nodes.length;i++){
			    var node = force_nodes[i].data;
			    if(node==n){
			       return;
			    }
			    if(node["_id"]==n["_id"]){
			       force_nodes[i].data = n;
			       return;
			    }
			}
			force_nodes.push({data:n});
		});
		force_nodes = force_nodes.filter(function(n){
		      return _nodes_data[n.data["_id"]]; 
		});
		_force.nodes(force_nodes);
		// Draw the node(cycle+text)
		_nodes = node_layer_selectAll("g")
		   .data(force_nodes,function(n){return n.data["_id"]});
		var g = _nodes.enter().append("g")
		   .attr("class",function(n){return n.data["class"]||"node";})
		   .call(_force.drag)
		   .on("contextmenu",function(n){
		       _show_context_menu(n);
		       _disabled_default_context_menu();
		   });
		var get_radius = function(n){return n.data["radius"]||15;};
		g.append("circle")
		 .attr("r",get_radius)
		 .on("click",function(n){
			 if(_event_handlers["select"]){
			   _event_handlers["select"](n);
			 }
		 })
		 .call(function(c){
			 if(_event_handers["new_node_circle"]){
			     _event_handlers["new_node_circle"](c);
			 }
		 });
		;
		g.append("text")
		 .attr("x",function(n){return 3+get_radius(n);})
		 .attr("y",".31em");
		;
		_nodes.exit().remove();
		// updata text
		_nodes.selectAll("text").text(function(n){return n.data["name"]||"";});
		
		var force_links=[];
		d3.entries(_link_data).map(function(entry){
			entry.value["_id"]=entry.key;
			return entry.value;
		}).forEach(function(l){
		     var source,target;
		     force_nodes.some(function(n){
		        if(n.data["_id"]==l.from){
		            source=n;
		        }
		        if(n.data["_id"]==l.to){
		           target = n;
		        }
		        return source&&target;
		     });
		     if(source&&target){
		        force_links.push({data:l,source:source,target:target});
		     }
		});
		_force.links(force_links);
		// Draw Links
		_links = _link_layer.selectAll("g")
		   .data(_force.links(),function(l){return l.data["_id"];});
		var entered = _links.enter().append("g")
		   .attr("class",function(l){return l.data["class"]||"links"});
		entered.append("path")
		      .attr("id",function(l){return l.data["_id"]})
		      .on("click",function(l){
		          if(_event_handlers["select.link"]){
		             _event_handlers["select.link"](l);
		          }
		      });
		entered.append("text")
		   .attr("text-anchor","middle")
		   .append("textPath")
		   .attr("startOffset","30%")
		   .attr("xlink:href",function(l){return "#"+l.data["_id"]})
		   .text(function(l){return l.data["name"];});
		_links.exit().remove();
		_force.start();
		
	}
	_self.get_nodes_data = function(){return _nodes_data};
	_self.get_links_data = function(){return _links_data};
	_self.on = function(s,f){_event_handlers[s]=f;};
	_self.clean_orphan_links = function(){
		var orphan =[];
		d3.entries(_link_data).forEach(function(e){
			if(!e.value["from"]||!_nodes_data[e.value["from"]]){
				orphan.push(e.key);
			}
			if(!e.value["to"]||!_nodes_data[e.value["to"]]){
				orphan.push(e.key);
			}
			
			
			
		});
		orphan.forEach(function(l){delete _links_data[l]});
	};
	_self.merge_graphic = function(g){
		var nodes = g["nodes"];
		if(nodes){
			d3.entries(nodes).forEach(function(e){
			   _nodes_data[e.key]=e.value;
			});
		}
		var links = g["links"];
		if(links){
			d3.entries(links).forEach(function(e){
				   _links_data[e.key]=e.value;
			});
		}
	};
	_self.call_service = function(url,f){
		console.log(url);
		d3.json(url,function(error,json){
			if(error){
			  _self.show_message_box("ERROR:failed to call service",JSON.stringify(error,null,2));
			  return console.error(error);
			}
			if(f){
			  f(json);
			}
		});
	};
	_self.call_action = function(url,id,action){
		var json = _self.call_service(url+"?id="+id+"&action="+action,function(json){
			console.log(json);
			_self.merge_graphic(json);
			_self.update();
		});
	};
}