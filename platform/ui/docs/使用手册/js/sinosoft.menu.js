(function(a){a.fn.extend({menu:function(p){var m={dataType:"json",url:"",type:"get",loadText:"加载中,请稍候…",width:110,eventType:"click",animation:"toggle"};var m=a.extend(m,p);var u=a(this);var e=a('<div class="ui_menu"></div>');var q=a('<div class="sub_menu"><div class="sub_inner"></div></div>');var r=a('<div class="loading">'+m.loadText+"</div>");var b=true;var c=1;var g=u.attr("id")+"_box";var l=a('<div id="'+g+'"></div>');a("body").append(l);a.ajax({url:m.url,dataType:m.dataType,type:m.type,async:false,processData:false,beforeSend:function(){h()},success:function(z){r.hide();if(m.dataType=="xml"){w(z)}else{if(m.dataType=="json"){o(z)}else{if(m.dataType=="html"){f(z)}}}e.children("div:not(.side_inner)").bind(m.eventType,i);a("div.sub_inner > div.sub").bind("click",s).bind(m.eventType,i).bind({mouseover:function(){a(this).addClass("hover")},mouseout:function(){a(this).removeClass("hover")}})},error:function(z,A){alert("数据加载出错！"+A)},});function h(){u.append(e);e.append(r)}var y;function i(){if(!b){b=true;return false}if(!a(this).is(":animated")){var B=a(this).attr("id")+"_sub"}var D=a(this).offset();var z=a(this).parent().parent().attr("level");var A=parseInt(z)+10;a(document).bind("click",x);if(a(this).hasClass("has_sub")){y=a(this).attr("id");a("div.sub_menu,div.menu_shadow").hide();var C=a("#"+B).css({left:D.left,top:D.top+a(this).height()});j(a(this),C);if(m.animation=="fadeToggle"){C.fadeOut("fast",function(){k(C)})}else{if(m.animation=="slideToggle"){C.slideDown("fast",function(){k(C)})}else{C.show();k(C)}}return false}else{var E=a(this).attr("id");a("div.sub_menu:not(:hidden)").each(function(){if(a(this).attr("level")>z){a(this).hide().next("div.menu_shadow").hide()}});if(a("a > span.has_sub",a(this)).length>0){var C=a("#"+B).css({left:D.left+a(this).width()+4,top:D.top-2,"z-index":A});j(a(this),C);if(m.animation=="fadeToggle"){C.fadeOut("fast",function(){k(C,A)})}else{if(m.animation=="slideToggle"){C.slideDown("fast",function(){k(C,A)})}else{C.show();k(C,A)}}}if(a(this).parent().hasClass("ui_menu")){a("body > div.sub_menu,body > div.menu_shadow").hide()}return false}}function j(A,C){var E=C.width();var M=C.height();var I=A.offset().left;var F=A.offset().top;var B=A.width();var K=A.height();var J=a(window).width();var G=a(window).height();var D=a(window).scrollLeft();var L=a(window).scrollTop();var z=I+B+E>J+D;var H=F+K+M>G+L;if(z&&H){C.css({left:I-E,top:F+K-M})}else{if(z){C.css({left:I-E})}else{if(H){C.css({top:F+K-M})}}}}function s(){if(a(this).find("span.has_sub").length>0){b=false;return false}if(a(this).hasClass("selected")){a(this).removeClass("selected")}else{a(this).addClass("selected")}}function x(z){a("div.sub_menu,div.menu_shadow",l).hide();a("div.menu_shadow").hide();a("div.ui_menu > div.selected").removeClass("selected");a(document).unbind("click",x);return false}function k(A,C){var E=A.next();if(E.is("div.menu_shadow")){E.show()}else{var B=a('<div class="menu_shadow"></div>');var z=A.offset().left+2;var F=A.offset().top+2;var D=A.height()+2;B.css({width:parseInt(m.width)+2,height:D,left:z,top:F,"z-index":C-1});A.after(B)}}function w(z){if(m.silder){var B=a('<div class="side_inner"></div>');var A=a('<div class="sub_menu"><div class="sub_inner"></div></div>');A.width(m.width);B.append(A);e.append($switch).append(B)}a(z).find("MenuItems > MenuItem").each(function(){var C=a('<div><a href="#"><span></span></a></div>');if(a(this).attr("href")!=undefined){C=a("<div><a href='"+a(this).attr("href")+"'><span></span></a></div>")}var D=a('<strong><b class="sub_icon"></b></strong>');if(a(this).children().length>0){C.addClass("has_sub");C.children("a").append(D)}if(a(this).attr("icon")!=undefined){a("span",C).append("<img src='"+a(this).attr("icon")+"' />")}if(a(this).attr("callback")){C.attr("onclick",a(this).attr("callback"))}a("span",C).append(a(this).attr("text"));C.attr("id",a(this).attr("id"));e.append(C);if(a(this).children().length>0){d(a(this))}});u.prepend(e)}function d(z){q=a('<div class="sub_menu"><div class="sub_inner"></div></div>');z.children().each(function(){if(a(this).get(0).tagName=="group"){a(".sub_inner",q).append("<div class='menu_part'><span></span></div>")}else{var B=a('<div class="sub"><a href="#"></a></div>');if(a(this).attr("href")!=undefined){B=a("<div class='sub'><a href='"+a(this).attr("href")+"'></a></div>")}var C=a('<span class="has_sub"></span>');if(a(this).children().length>0){B.children("a").append(C)}if(a(this).attr("icon")!=undefined){a("a",B).css("background-image","url("+a(this).attr("icon")+")")}if(a(this).attr("callback")){B.attr("onclick",a(this).attr("callback"))}if(a(this).attr("selected")){B.addClass("selected")}a("a",B).append(a(this).attr("text"));B.attr("id",a(this).attr("id"));a(".sub_inner",q).append(B);var A=z.attr("id")+"_sub";q.attr({id:A,level:c}).width(m.width)}});l.append(q);z.children().each(function(){if(a(this).children().length>0){c++;d(a(this))}})}function o(C){for(var A=0;A<C.MenuItems.length;A++){var z=a('<div><a href="#"><span></span></a></div>');if(C.MenuItems[A].href!=undefined){z=a("<div><a href='"+C.MenuItems[A].href+"'><span></span></a></div>")}if(C.MenuItems[A].callback){z.attr("onclick",C.MenuItems[A].callback)}var B=a('<strong><b class="sub_icon"></b></strong>');if(C.MenuItems[A].MenuItems!=undefined){z.addClass("has_sub");z.children("a").append(B)}if(C.MenuItems[A].icon!=undefined){a("span",z).append("<img src='"+C.MenuItems[A].icon+"' />")}a("span",z).append(C.MenuItems[A].text);z.attr("id",C.MenuItems[A].id);e.append(z);t(C.MenuItems[A])}u.prepend(e)}function t(B){if(B.MenuItems!=undefined){q=a('<div class="sub_menu"><div class="sub_inner"></div></div>');for(var A=0;A<B.MenuItems.length;A++){var C=a('<div class="sub"><a href="#"></a></div>');if(B.MenuItems[A].href!=undefined){C=a("<div class='sub'><a href='"+B.MenuItems[A].href+"'></a></div>")}if(B.MenuItems[A].selected=="true"){C.addClass("selected")}if(B.MenuItems[A].callback){C.attr("onclick",B.MenuItems[A].callback)}var D=a('<span class="has_sub"></span>');if(B.MenuItems[A].MenuItems!=undefined){C.children("a").append(D)}if(B.MenuItems[A].icon!=undefined){a("a",C).css("background-image","url("+B.MenuItems[A].icon+")")}a("a",C).append(B.MenuItems[A].text);C.attr("id",B.MenuItems[A].id);a(".sub_inner",q).append(C);if(B.MenuItems[A].group=="true"){a(".sub_inner",q).append("<div class='menu_part'><span></span></div>")}var z=B.id+"_sub";q.attr({id:z,level:c}).width(m.width)}l.append(q);for(var A=0;A<B.MenuItems.length;A++){if(B.MenuItems[A].MenuItems!=undefined){c++;t(B.MenuItems[A])}}}}function f(z){a(z).children().each(function(){var A=a('<div><a href="#"><span></span></a></div>');if(a("a",this).attr("href")!=undefined){A=a("<div><a href='"+a("a",this).attr("href")+"'><span></span></a></div>")}var B=a('<strong><b class="sub_icon"></b></strong>');if(a(this).children("ul").length>0){A.addClass("has_sub");A.children("a").append(B)}if(a(this).children("img").length>0){a("span",A).append("<img src='"+a("img",this).attr("src")+"' />")}A.attr("id",a(this).attr("id"));a("span",A).append(a("a",this).html());e.append(A)});u.prepend(e);a(z).children().each(function(){var A=a(this).children("ul");if(a(this).children("ul").length>0){v(A)}})}function v(z){q=a('<div class="sub_menu"><div class="sub_inner"></div></div>');q.width(m.width);a(z).children().each(function(){if(a(this).get(0).tagName=="LI"){var B=a('<div class="sub"><a href="#"></a></div>');if(a("a",this).attr("href")!=undefined){B=a("<div class='sub'><a href='"+a("a",this).attr("href")+"'></a></div>")}var C=a('<span class="has_sub"></span>');if(a(this).children("ul").length>0){B.children("a").append(C)}if(a(this).children("img").length>0){a("a",B).css("background-image","url("+a("img",this).attr("src")+")")}if(a(this).attr("callback")){B.attr("onclick",a(this).attr("callback"))}if(a(this).attr("selected")){B.addClass("selected")}B.attr("id",a(this).attr("id"));a("a",B).append(a("a",this).html());a(".sub_inner",q).append(B)}else{a(".sub_inner",q).append("<div class='menu_part'><span></span></div>")}var A=a(z).parent().attr("id")+"_sub";q.attr({id:A,level:c}).width(m.width)});l.append(q);a(z).children().each(function(){var A=a(this).children("ul");if(a(this).children("ul").length>0){c++;v(A)}})}var n="";return{addcallback:function(z){var C;var A;var B;if(a.isFunction(z)){if(n==""){C=a("div.ui_menu > div",u);C.bind("click",z);A=l.find("div.sub");A.bind("click",z)}else{C=a("#"+n);C.bind("click",z);n=""}}else{alert("参数不能为空或非函数参数！")}},id:function(z){n=z;return this}}}})})(jQuery);
