/*
*
*/

//tab切换
function lottery369_tab(params)
{
	var settings_ = {
		lottery_box: undefined,
		lottery_tabs: [],
		lottery_contents: []
	}

	var settings = $.extend(settings_, params);
	if (!settings.lottery_box) {
		return;
	}

	settings.lottery_tabs.each(function(i) {
		var to_show = i;
		var to_hide;

		settings.lottery_tabs.eq(i).click(function() {
			settings.lottery_tabs.each(function(key, val) {
				if(val.className == "on"){
					to_hide = key;
				}
			});
			
			settings.lottery_tabs.eq(to_hide).removeClass('on');
			settings.lottery_contents.eq(to_hide).hide();
			settings.lottery_tabs.eq(to_show).addClass('on');
			settings.lottery_contents.eq(to_show).show();
		});
	});
}