//顶部伸展广告
if ($("#js_ads_banner_top_slide").length){
	var $slidebannertop = $("#js_ads_banner_top_slide"),$bannertop = $("#js_ads_banner_top");
	setTimeout(function(){$bannertop.slideUp(500);$slidebannertop.slideDown(500);},2500);
	setTimeout(function(){$slidebannertop.slideUp(500,function (){$bannertop.slideDown(500);});},5500);
}