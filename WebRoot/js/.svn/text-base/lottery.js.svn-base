var LotteryType = {
    'DLT': 1,
    'QXC': 2,
    'PL3': 3,
    'PL5': 4,
    'SFC': 7,
    'SFR9': 8,
    'JQC': 9,
    'BQC': 10,

    'DC_SFP': 30,
    'DC_SXDS': 31,
    'DC_JQS': 32,
    'DC_BF': 33,
    'DC_BCSFP': 34,

    'SSQ': 50,
    'QLC': 51,
    'FC3D': 52,
    'DF6J1': 54,
    'HD15X5': 55,

    'SD11X5': 20,
    'JX11X5': 22,
    'CQSSC': 200,
    'SHSSL': 201,

    'JCLQ_SF': 70,
    'JCLQ_RFSF': 71,
    'JCLQ_SFC': 72,
    'JCLQ_DXF': 73,
    
    'JCZQ_SPF' : '80',
    'JCZQ_BF' : '81',
    'JCZQ_JQS' : '82',
    'JCZQ_BQC' : '83',

    'NYFC_HC1': 555
};
jQuery.extend(true, C, {
        'FIVELEAGUES' : ['英超', '意甲', '西甲', '德甲', '法甲'],
        'LEAGUECOLOR' : {},
        'AVAILCOLOR' : [ '#da0000', '#075b06', '#036', '#930', '#8c0839',
            '#f16e0d', '#141d8b', '#93008a', '#090', '#ce3402',  '#009092',
            '#4e2482', '#e73131', '#476f18', '#b00000', '#3ac6c1',
            '#c45ed6', '#0068ab', '#39C', '#369', '#609', '#36C', '#06F', '#006',
            '#906', '#060', '#960', '#8B0000', '#8B008B', '#00008B',
            '#551A8B', '#8B1C62', '#6959CD', '#F00',
            '#EE1289', '#A020F0', '#CD6839', '#228B22', '#B22222',
            '#8B2323', '#CD7054', '#8A2BE2', '#BA55D3', '#F0F', '#D02090',
            '#7A67EE', '#473C8B', '#27408B', '#1874CD', '#68228B',
            '#CD0000', '#EE7600', '#9370DB', '#B03060',
            '#DAA520', '#CD853F', '#CD5C5C',
            '#BDB76B', '#CD8500', '#CD8162', '#FF7F00', '#FF4500', '#CD3700',
            '#8B2500', '#8B0A50', '#CD6090', '#CD919E',
            '#EE799F', '#CDC1C5', '#8B8386',
            '#00F', '#0000CD',
            '#AB82FF', '#2F4F4F',
            '#FA8072' ],
        'PLAY_TYPE': {
            'UPLOAD_LATER': 1
        },
        'OMIT_TYPE': {
            'OMIT': 1,//当前遗漏
            'COLD_HOT': 2,//当前冷热
            'OMIT_MAX' : 3 //最大遗漏

        },
        'CHASE_SELECT_TYPE': {
            'STANDARD': 1,
            'INTELLIGENT': 2
        },
        'ANIMAL_SIGN': ['鼠', '牛', '虎', '兔', '龙', '蛇', '马', '羊', '猴', '鸡', '狗', '猪']
    }
);

var PlayType = {
    'DEFAULT': '0'
};
var PlayTypeGroup = {
    count: function(line) {
        if (PlayTypeGroup[line.playType].counter) {
            // 是否有自定义的注数计算器
            count = PlayTypeGroup[line.playType].counter(line);
        }
        else {
            // 否则用通用的注数计算
            count = PlayTypeGroup._counter(line);
        }
        return count;
    },
    _counter: function(line) {
        var count = 1;
        var groupdef = PlayTypeGroup[line.playType].groupdef;
        for (var i = 0, imax = groupdef.length; i < imax; i ++) {
            var group = line.groups[i];
            var required = groupdef[i].required;
            var danN = group.dan ? group.dan.length : 0;
            var tuoN = group.tuo.length;
            count *= Math.combine(tuoN, required - danN);
        }
        return count;
    },
    add: function(playType, viewname, arr, counter) {
        var groupdef = [];
        for (var i = 0, imax = arr.length; i < imax; i ++) {
            var def = arr[i];
            groupdef.push({
                'required': def[0],
                'dan_max': def[1],
                'dan_min': def[2],
                'tuo_max': def[3],
                'tuo_min': def[4]
            });
        }
        PlayTypeGroup[playType] = {};
        PlayTypeGroup[playType].groupdef = groupdef;
        PlayTypeGroup[playType].viewname = viewname;
        PlayTypeGroup[playType].counter = counter;
    }
};
var SelectType = {
    'UPLOAD': 1,
    'CHOOSE': 2
};
var ChaseType = {
    'CHOOSE_SELF': 1,
    'CHOOSE_RANDOM': 2,
    'CHOOSE_DINDAN': 3
};
var StopChaseType = {
    'STOP_NEVER': 1,
    'STOP_AFTER_AWARDS': 2
};
var PublicStatus = {
    'PRIVATE': 1,
    'PUBLIC': 2,
    'PUBLIC_AFTER_TERMINATED': 3,
    'PUBLIC_AFTER_DREW': 4,
    'PUBLIC_AFTER_WON': 5
};
var PlayTypeMultipleLimit = {};
// 默认倍数限制
PlayTypeMultipleLimit[PlayType['DEFAULT']] = 99;
var LotteryTypeMultipleLimit = {};

/**
 * 按玩法获取倍数限制
 * 如果未限制特定玩法的倍数，取该玩法所属彩种的倍数限制
 * 如果该玩法所属彩种未设置倍数限制，取全局默认的倍数限制
 */
function getPlayTypeMultipleLimit(play_type) {
    if (typeof(PlayTypeMultipleLimit[play_type]) != 'undefined') {
        return PlayTypeMultipleLimit[play_type];
    }
    // TODO 支持更规范的从玩法到彩种的转换
    var lottery_type = Math.floor(parseInt(play_type, 10) / 100);
    return getLotteryTypeMultipleLimit(lottery_type);
};
function getLotteryTypeMultipleLimit(lottery_type) {
    if (typeof(LotteryTypeMultipleLimit[lottery_type]) != 'undefined') {
        return LotteryTypeMultipleLimit[lottery_type];
    }
    return PlayTypeMultipleLimit[PlayType['DEFAULT']];
};

/**
 * 选号区实现
 * @author xuyannan
 */
(function(jQuery) {
    function paddingOutput(val, padding) {
        if (val instanceof Array) {
            if (padding > 0) {
                var output = [];
                for (var i = 0, max = val.length; i < max; i ++) {
                    output.push(val[i].toString().leftpad(padding));
                }
                return output;
            }
            return val;
        }
        else {
            var v = parseInt(val, 10);
            if (padding > 0) {
                return v.toString().leftpad(padding);
            }
            return v;
        }
    };

    //外部调用的对象
    var NumberSelector = function(elements, settings) {
        this.elements = elements;
        this.selected = {};
        this.settings = settings;
    };
    NumberSelector.prototype.getSelected = function() {
        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var output = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            var isSelected = this.isSelected(v);
            if (isSelected) {
                if (padding > 0) {
                    output.push(paddingOutput(v, padding));
                }
                else {
                    output.push(v);
                }
            }
        }
        return output;
    };
    NumberSelector.prototype.getUnSelected = function() {
        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var output = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            var isSelected = this.isSelected(v);
            if (!isSelected) {
                if (padding > 0) {
                    output.push(paddingOutput(v, padding));
                }
                else {
                    output.push(v);
                }
            }
        }
        return output;
    };
    NumberSelector.prototype.isSelected = function(num) {
        var v = parseInt(num, 10);
        if (isNaN(v)) {
            return false;
        }
        return this.selected[v] === 1;
    };
    NumberSelector.prototype.getAllValues = function(){
        return this.values;
    };
    NumberSelector.prototype.selectAll = function() {
        this.select(this.values);
    };
    NumberSelector.prototype.clear = function() {
        var toUnselect = [];
        for (var v in this.selected) {
            toUnselect.push(v);
        }
        this.unselect(toUnselect);
        this.selected = {};
    };
    NumberSelector.prototype.selectOdd = function(){
        var toSelect = [];
        var toUnselect = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            if (v % 2 != 0) {
                toSelect.push(v);
            }
            else {
                toUnselect.push(v);
            }
        }
        this.unselect(toUnselect);
        this.select(toSelect);
    };
    NumberSelector.prototype.selectEven = function() {
        var toSelect = [];
        var toUnselect = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            if (v % 2 == 0) {
                toSelect.push(v);
            }
            else {
                toUnselect.push(v);
            }
        }
        this.unselect(toUnselect);
        this.select(toSelect);
    };
    NumberSelector.prototype.select = function(val, unselectOthers) {
        var pointer = this;

        function selectSingle(v) {
            var pop;
            if (typeof(pointer.settings.numberSelectBefore) == 'function') {
                pop = pointer.settings.numberSelectBefore(v);
            }
            if (pop === false) {
                return;
            }

            v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            var index = pointer.valuesMap[v];
            jQuery(pointer.elements[index]).addClass(pointer.settings.selectedStyle).removeClass(pointer.settings.defaultStyle);

            pointer.selected[v] = 1;
        };

        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var vout = paddingOutput(val, padding);

        if (unselectOthers === true) {
            this.clear();
        }

        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                selectSingle(val[i]);
            }
        }
        else {
            selectSingle(val);
        }

        if (typeof(this.settings.numberSelectAfter) == 'function') {
            this.settings.numberSelectAfter(vout);
        }
    };
    NumberSelector.prototype.unselect = function(val){
        var pointer = this;

        function unselectSingle(v) {
            v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            var index = pointer.valuesMap[v];
            jQuery(pointer.elements[index]).removeClass(pointer.settings.selectedStyle).addClass(pointer.settings.defaultStyle);

            delete pointer.selected[v];
        };

        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var vout = paddingOutput(val, padding);

        var pop;
        if (typeof(this.settings.numberUnselectBefore) == 'function') {
            pop = this.settings.numberUnselectBefore(vout);
        }
        if (pop === false) {
            return;
        }

        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                unselectSingle(val[i]);
            }
        }
        else {
            unselectSingle(val);
        }

        if (typeof(this.settings.numberUnselectAfter) == 'function') {
            this.settings.numberUnselectAfter(vout);
        }
    };
    NumberSelector.prototype.toggleSelect = function(val) {
        var pointer = this;
        function toggleSingle(v) {
            var v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            if (pointer.selected[v] === 1) {
                pointer.unselect(v);
            }
            else {
                pointer.select(v);
            }
        }
        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                toggleSingle(val[i]);
            }
        }
        else {
            toggleSingle(val);
        }
    };
    
    var DanShaNumberSelector = function(elements, settings) {
        this.elements = elements;
        this.selected = {};
        this.settings = settings;
    };
    DanShaNumberSelector.prototype.getUnSelected = function() {
        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var output = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            var isUnSelected = this.isUnSelected(v);
            if (isUnSelected) {
                if (padding > 0) {
                    output.push(paddingOutput(v, padding));
                }
                else {
                    output.push(v);
                }
            }
        }
        return output;
    };
    DanShaNumberSelector.prototype.getDan = function() {
        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var output = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            var isDan = this.isDan(v);
            if (isDan) {
                if (padding > 0) {
                    output.push(paddingOutput(v, padding));
                }
                else {
                    output.push(v);
                }
            }
        }
        return output;
    };
    DanShaNumberSelector.prototype.getSha = function() {
        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var output = [];
        for (var i = 0, max = this.values.length; i < max; i ++) {
            var v = this.values[i];
            var isSha = this.isSha(v);
            if (isSha) {
                if (padding > 0) {
                    output.push(paddingOutput(v, padding));
                }
                else {
                    output.push(v);
                }
            }
        }
        return output;
    };
    DanShaNumberSelector.prototype.isUnSelected = function(num) {
        var v = parseInt(num, 10);
        if (isNaN(v)) {
            return false;
        }
        return !(this.selected[v] === 1 || this.selected[v] === 2);
    };
    DanShaNumberSelector.prototype.isDan = function(num) {
        var v = parseInt(num, 10);
        if (isNaN(v)) {
            return false;
        }
        return this.selected[v] === 1;
    };
    DanShaNumberSelector.prototype.isSha = function(num) {
        var v = parseInt(num, 10);
        if (isNaN(v)) {
            return false;
        }
        return this.selected[v] === 2;
    };
    DanShaNumberSelector.prototype.getAllValues = function(){
        return this.values;
    };
    DanShaNumberSelector.prototype.selectAll = function() {
        this.select(this.values);
    };
    DanShaNumberSelector.prototype.clear = function() {
        var toUnselect = [];
        for (var v in this.selected) {
            toUnselect.push(v);
        }
        this.unselect(toUnselect);
        this.selected = {};
    };
    DanShaNumberSelector.prototype.selectAsDan = function(val, unselectOthers) {
        var pointer = this;

        function selectSingle(v) {
            var pop;
            if (typeof(pointer.settings.numberSelectBefore) == 'function') {
                pop = pointer.settings.numberSelectBefore(v);
            }
            if (pop === false) {
                return;
            }

            v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            var index = pointer.valuesMap[v];
            
        	jQuery(pointer.elements[index]).removeClass().addClass(pointer.settings.selectedStyle);
        	pointer.selected[v] = 1;
           
        };

        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var vout = paddingOutput(val, padding);

        if (unselectOthers === true) {
            this.clear();
        }

        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                selectSingle(val[i]);
            }
        }
        else {
            selectSingle(val);
        }

        if (typeof(this.settings.numberSelectAfter) == 'function') {
            this.settings.numberSelectAfter(vout);
        }
    };
    DanShaNumberSelector.prototype.selectAsSha = function(val, unselectOthers) {
        var pointer = this;
        var ignore = arguments[2] ? true : false;
        function shaSingle(v) {
        	if(!ignore){
	            var pop;
	            if (typeof(pointer.settings.numberSelectBefore) == 'function') {
	                pop = pointer.settings.numberSelectBefore(v);
	            }
	            if (pop === false) {
	                return;
	            }
        	}
            v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            var index = pointer.valuesMap[v];
            
        	jQuery(pointer.elements[index]).removeClass().addClass(pointer.settings.shaStyle);
        	pointer.selected[v] = 2;
        };

        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var vout = paddingOutput(val, padding);

        if (unselectOthers === true) {
            this.clear();
        }

        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
            	shaSingle(val[i]);
            }
        }
        else {
        	shaSingle(val);
        }

        if (typeof(this.settings.numberSelectAfter) == 'function') {
            this.settings.numberSelectAfter(vout);
        }
    };
    DanShaNumberSelector.prototype.unselect = function(val){
        var pointer = this;

        function unselectSingle(v) {
            v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
            var index = pointer.valuesMap[v];
            jQuery(pointer.elements[index]).removeClass().addClass(pointer.settings.defaultStyle);

            delete pointer.selected[v];
        };

        var padding = (this.settings.numberPadding > 0 && this.settings.outputPadding) ? this.settings.numberPadding : 0;
        var vout = paddingOutput(val, padding);

        var pop;
        if (typeof(this.settings.numberUnselectBefore) == 'function') {
            pop = this.settings.numberUnselectBefore(vout);
        }
        if (pop === false) {
            return;
        }

        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                unselectSingle(val[i]);
            }
        }
        else {
            unselectSingle(val);
        }

        if (typeof(this.settings.numberSelectAfter) == 'function') {
            this.settings.numberSelectAfter(vout);
        }
    };
    DanShaNumberSelector.prototype.toggleSelect = function(val) {
    	
        var pointer = this;
        function toggleSingle(v) {
            var v = parseInt(v, 10);
            if (isNaN(v)) {
                return false;
            }
           
            if (pointer.selected[v] === 2){
                pointer.unselect(v);
            }
            else if (pointer.selected[v] === 1) {
                pointer.selectAsSha(v);
            
            } else {
            	pointer.selectAsDan(v);
            }
        }
        if (val instanceof Array) {
            for (var i = 0, max = val.length; i < max; i ++) {
                toggleSingle(val[i]);
            }
        }
        else {
            toggleSingle(val);
        }
    };
    /**
     * 两种初始化方式：
     * settings.elements：页面上已经存在了元素
     * settings.min与settings.max:页面上没有元素，只是指定了范围
     * @param {Object} settings
     */
    jQuery.numberSelect = function(settings){
        settings = jQuery.extend({
            elements: [],
            containerId: '',
            min: 1,
            numberSelectBefore: undefined,
            numberUnselectBefore: undefined,
            numberSelectAfter: undefined,
            numberUnselectAfter: undefined,
            numberPadding: 0,
            outputPadding: false,
            defaultStyle: 'ball_default',
            selectedStyle: 'ball_selected',
            valueAttribute: 'v',
            valueAutoAssign: false,
            valueAutoAssignStart: 1,
            selected: [],
            extendData : false,//是否有扩展数据
            extendDataValueAttribute: 'val',
            renderExtendDataArea : undefined,//渲染扩展数据所在容器
            extendDataStyle : 'ball_extend_data',
            wrapperStyle : 'ball_wrapper',
            isDansha: false,//是否胆杀
            shaStyle: 'ball_sha'//杀
            
        }, settings);
        var elements = settings.elements;
        var container;
        if (settings.containerId) {
            container = jQuery('#' + settings.containerId);
        }

        var renderExtendDataArea = settings.renderExtendDataArea;
        if (typeof(renderExtendDataArea) != 'function') {
            renderExtendDataArea = function(v) {
                var sb_ext = new StringBuffer();
                sb_ext.append('<div class="').append(settings.extendDataStyle).append('" ')
                    .append(settings.extendDataValueAttribute).append('="').append(v).append('"></div>');
                return sb_ext.toString();
            };
        }
        if (elements.length == 0) {
            var sb = new StringBuffer();
            for (var i = settings.min; i <= settings.max; i++) {
                var sb_line = new StringBuffer();
                sb_line.append('<div class="').append(settings.wrapperStyle).append('">');
                sb_line.append('<div class="').append(settings.defaultStyle).append('" ')
                    .append(settings.valueAttribute).append('="').append(i).append('">');
                if (settings.numberPadding > 0) {
                    sb_line.append(i.toString().leftpad(settings.numberPadding));
                }
                else {
                    sb_line.append(i);
                }
                sb_line.append('</div>');

                //如果有扩展数据，则给扩展数据预留出位置
                if(settings.extendData){
                    sb_line.append(renderExtendDataArea(i));
                }
                sb_line.append('</div>');
                sb.append(sb_line.toString());
            }
            jQuery(container).append(sb.toString());
            elements = jQuery(container).find('.' + settings.defaultStyle);
        }
        else {
            var valueAttribute = settings.valueAttribute;
            var valueAutoAssign = settings.valueAutoAssign;
            jQuery.each(elements, function(i, elem){
                if (valueAutoAssign) {
                    jQuery(elem).attr(valueAttribute, (i + settings.valueAutoAssignStart)).addClass(settings.defaultStyle);
                }
                else {
                    jQuery(elem).addClass(settings.defaultStyle);
                }
                var wrapper = document.createElement('div');
                jQuery(wrapper).addClass(settings.wrapperStyle);
                jQuery(elem).wrap(jQuery(wrapper));

                if(settings.extendData){
                    if(valueAutoAssign) {
                        jQuery(elem).after(renderExtendDataArea(i + settings.valueAutoAssignStart));
                    }else {
                        jQuery(elem).after(renderExtendDataArea( jQuery(elem).attr(valueAttribute)));
                    }
                }
            });
        }
        
        var numberSelector = settings.isDansha ? new DanShaNumberSelector(elements, settings) : new NumberSelector(elements, settings);

        var values = [];
        var valuesMap = {};

        for (var i = 0, max = elements.length; i < max; i ++) {
            var $elem = jQuery(elements[i]);
            var v = parseInt($elem.attr(settings.valueAttribute), 10);
            if (isNaN(v)) {
                logger.error('invalid number', v, $elem);
                continue;
            }
            values.push(v);
            valuesMap[v] = i;

            //绑定点击事件
            $elem.click(function(){
                var v = parseInt(jQuery(this).attr(settings.valueAttribute), 10);
                if (isNaN(v)) {
                    return;
                }

                numberSelector.toggleSelect(v);
            });
        };

        numberSelector.values = values;
        numberSelector.valuesMap = valuesMap;

        //给默认选中的数字绑定样式
        if (settings.selected.length > 0) {
            numberSelector.select(settings.selected);
        }

        return numberSelector;

    };
})(jQuery);

/**
 * 投注区的实现
 * @author xuyannan
 */
(function(jQuery){
    jQuery.multiSelect = function(settings){
        if(!settings.elementId) return false;
        settings = jQuery.extend({
            removeCallback: undefined,
            clickCallback: undefined,
            addCallback: undefined,
            removeAllCallback: undefined
        }, settings);
        var elem = document.getElementById(settings.elementId);
        //add an option to select
        elem.addOption = function(value, text) {
            var option = document.createElement('OPTION');
            option.text = text;
            option.value = value;

            elem.options.add(option);
            if (typeof(settings.addCallback) == 'function') {
                settings.addCallback(value, text);
            }
        };

        elem.addOptions = function(options) {
            if (typeof(options) != 'object' || !(options instanceof Array)) {
                return false;
            }
            for (var i = 0, max = options.length; i < max; i ++) {
                var obj = options[i];
                var option = document.createElement('OPTION');
                option.text = obj.text;
                option.value = obj.value;

                elem.options.add(option);
            }
            if (typeof(settings.addCallback) == 'function') {
                settings.addCallback();
            }
        };

        //are there any options added
        elem.isEmpty = function() {
            return elem.options.length == 0;
        };

        //remove all
        elem.removeAll = function() {
            jQuery(elem).empty();
            if (typeof(settings.removeAllCallback) == 'function') {
                settings.removeAllCallback();
            }
        };

        //remove selected
        elem.removeSelected = function(){
            jQuery.each(elem.options ,function(i, opt) {
                if(jQuery(opt).attr('selected')) {
                    var value = jQuery(opt).val();
                    jQuery(opt).remove();
                    if (typeof(settings.removeCallback) == 'function') {
                        settings.removeCallback(value);
                    }
                }
            });
        };
        elem.getItems = function() {
            var items = [];
            for (var i = 0, max = elem.options.length; i < max; i ++) {
                items.push(elem.options[i].value);
            }
            return items;
        };
        jQuery(elem).click(function(){
            if (typeof(settings.clickCallback) == 'function') {
                var selectedIndex = elem.selectedIndex;
                if (selectedIndex >= 0) {
                    settings.clickCallback(elem.options[selectedIndex].value);
                }
            }
        });

        return elem;
    };
})(jQuery);

/**
 * Plan Content
 */
// 分隔符等常量定义
jQuery.extend(true, C, {
    'PLANCONTENT': {
        'ELEMENT': ',',
        'ATTRIBUTE': ';',
        'DANTUO': '#',
        'GROUP': '|',
        'PLAY': '%',
        'PLAYSUB': ':',
        'PLAYELEMENT': ',',
        'MULTIPLE': '!',
        'LINE': '^'
    }
});
// 方案元素
function PlanContentElement(element) {
    this.element = element;
    this.attributes = undefined;
};
PlanContentElement.prototype.toString = function() {
    var sb = new StringBuffer();
    sb.append(this.element);
    if (this.attributes && this.attributes.length > 0) {
        sb.append('(');
        sb.append(this.attributes.join(C.PLANCONTENT.ATTRIBUTE));
        sb.append(')');
    }
    return sb.toString();
};
// 方案元素组
function PlanContentGroup() {
    this.dan = undefined;
    this.tuo = [];
};
PlanContentGroup.prototype.toString = function() {
    var sb = new StringBuffer();

    if (this.dan && this.dan.length > 0) {
        var danStrArr = [];
        for (var i = 0, max = this.dan.length; i < max; i ++) {
            var element = this.dan[i];
            danStrArr.push(element.toString());
        }
        sb.append(danStrArr.join(C.PLANCONTENT.ELEMENT));
        sb.append(C.PLANCONTENT.DANTUO);
    }
    var tuoStrArr = [];
    for (var i = 0, max = this.tuo.length; i < max; i ++) {
        var element = this.tuo[i];
        tuoStrArr.push(element.toString());
    }
    sb.append(tuoStrArr.join(C.PLANCONTENT.ELEMENT));
    return sb.toString();
};
// 单行方案内容
function PlanContentLine() {
    this.playType = 0;
    this.playTypeSubs = undefined;

    this.groups = [];
};
PlanContentLine.prototype.toString = function() {
    var sb = new StringBuffer();
    sb.append(this.playType);
    if (this.playTypeSubs && this.playTypeSubs.length > 0) {
        sb.append(C.PLANCONTENT.PLAYSUB);
        sb.append(this.playTypeSubs.join(C.PLANCONTENT.PLAYELEMENT));
    }
    sb.append(C.PLANCONTENT.PLAY);
    var groupStrArr = [];
    for (var i = 0, max = this.groups.length; i < max; i ++) {
        var group = this.groups[i];
        groupStrArr.push(group.toString());
    }
    sb.append(groupStrArr.join(C.PLANCONTENT.GROUP));
    return sb.toString();
};
/**
 * 转换成投注区的可视字符串
 * 标准格式为：[玩法名称][注数] 投注内容
 */
function formatPlanContentLineToViewString(line) {
    var sb = new StringBuffer();
    sb.append('[').append(PlayTypeGroup[line.playType].viewname).append(']');
    var count = PlayTypeGroup.count(line);
    sb.append('[').append(count).append('注] ');
    for (var i = 0, imax = line.groups.length; i < imax; i ++) {
        sb.append(line.groups[i].toString());
        if (i != imax - 1) {
            sb.append(C.PLANCONTENT.GROUP);
        }
    }
    return sb.toString();
};
/**
 * 解析元素
 */
function unpackPlanContentElement(str) {
    var toparse = str;
    var pattern = /^([^\(\)]+)+?(\(([^\(\)]+)\))?$/;
    var result = toparse.toString().match(pattern);
    if (!result || result.length != 4) {
        logger.error('解析元素失败', toparse);
        return false;
    }
    var element = new PlanContentElement(result[1]);
    var attributeStr = result[3];
    if (attributeStr) {
        element.attributes = attributeStr.split(C.PLANCONTENT.ATTRIBUTE);
    }
    return element;
};
function unpackPlanContentElementArray(str) {
    var elementArray = [];
    var toparse = str;
    var tmp = toparse.split(C.PLANCONTENT.ELEMENT);
    for (var i = 0, max = tmp.length; i < max; i ++) {
        var elementStr = tmp[i];
        var element = unpackPlanContentElement(elementStr);
        if (element === false) {
            logger.error('解析单个元素失败', elementStr);
            return false;
        }
        elementArray.push(element);
    }
    return elementArray;
};
/**
 * 解析一个元素组
 */
function unpackPlanContentGroup(str) {
    var group = new PlanContentGroup();

    var toparse = str;

    // 解析胆拖
    var tmp = toparse.split(C.PLANCONTENT.DANTUO);
    if (tmp.length > 2) {
        logger.error('不正确的分组元素', toparse);
        return false;
    }
    var danStr, tuoStr;
    if (tmp.length == 2) {
        danStr = tmp[0];
        tuoStr = tmp[1];
    }
    else {
        tuoStr = tmp[0];
    }

    // 解析胆码元素
    if (danStr) {
        var dan = unpackPlanContentElementArray(danStr);
        if (dan === false) {
            logger.error('解析胆码元素失败', danStr);
            return false;
        }
        group.dan = dan;
    }

    // 解析拖码元素
    var tuo = unpackPlanContentElementArray(tuoStr);
    if (tuo === false) {
        logger.error('解析拖码元素失败', tuoStr);
        return false;
    }
    group.tuo = tuo;

    return group;
};
/**
 * 解析单行方案投注内容
 */
function unpackPlanContentLine(str) {
    var line = new PlanContentLine();

    var toparse = str;
    // 解析玩法
    var tmp = toparse.split(C.PLANCONTENT.PLAY);
    if (tmp.length != 2) {
        logger.error('解析玩法失败', str);
        return false;
    }
    var playstr = tmp[0];
    toparse = tmp[1];

    tmp = playstr.split(C.PLANCONTENT.PLAYSUB);
    if (tmp.length > 2) {
        logger.error('解析子玩法失败', playstr);
        return false;
    }
    var playParent = parseInt(tmp[0], 10);
    if (isNaN(playParent)) {
        logger.error('错误的玩法编号', tmp[10]);
        return false;
    }
    line.playType = playParent;

    // 解析子玩法
    if (tmp.length == 2) {
        tmp = tmp[1].split(C.PLANCONTENT.PLAYELEMENT);
        line.playTypeSubs = [];
        for (var i = 0, max = tmp.length; i < max; i ++) {
            var playSub = parseInt(tmp[i], 10);
            if (isNaN(playSub)) {
                logger.error('错误的子玩法编号', tmp[i]);
                return false;
            }
            line.playTypeSubs.push(playSub);
        }
    }

    // 解析数据区
    tmp = toparse.split(C.PLANCONTENT.GROUP);
    for (var i = 0, max = tmp.length; i < max; i ++) {
        var groupstr = tmp[i];
        var group = unpackPlanContentGroup(groupstr);
        if (group === false) {
            logger.error('解析元素组失败', groupstr);
            return false;
        }
        line.groups.push(group);
    }

    return line;
};
/**
 * 转换解析出来的元素数组到字符串数组
 * 用于无元素属性的常规彩种（足彩和北单等除外）
 */
function convertFromPlanContentElementArray(elementArray) {
    var strArr = [];
    for (var i = 0, max = elementArray ? elementArray.length : 0; i < max; i ++) {
        strArr.push(elementArray[i].element);
    }
    return strArr;
};
/**
 * 转换字符串数组到元素数组
 */
function convertToPlanContentElementArray(strArray) {
    var elementArr = [];
    for (var i = 0, max = strArray.length; i < max; i ++) {
        var element = unpackPlanContentElement(strArray[i]);
        if (element === false) {
            return false;
        }
        elementArr.push(element);

    }
    return elementArr;
};
/**
 * 从N个元素数组转换成一个方案内容对象
 * 每一个元素数组认为是一个元素组
 */
function convertToPlanContentLine() {
    var line = new PlanContentLine();
    for (var i = 0, max = arguments.length; i < max; i ++) {
        var group = new PlanContentGroup();
        group.tuo = arguments[i];
        line.groups.push(group);
    }
    return line;
};

/**
 * Lottery Functions
 */
jQuery.extend({
    getLeagueColor : function(leagueName){
        var color = C.LEAGUECOLOR[leagueName];
        if (color) {
            return color;
        }
        var colorIndex = Math.floor(Math.random() * C.AVAILCOLOR.length);//0<={I}<lehe.config.AVAILCOLOR.length;
        color = C.AVAILCOLOR[colorIndex];
        C.AVAILCOLOR.splice(colorIndex, 1)//删除已经使用的颜色
        C.LEAGUECOLOR[leagueName] = color;
        return color;
    },
    ajaxCurrentPhase: function(params) {
        params = jQuery.extend({
            'lottery_type': undefined,
            'global': false,
            'success': undefined,
            'error': undefined
        }, params);

        if (!params.lottery_type) {
            logger.error('invalid lottery type specified');
            return false;
        }

        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_current.php',
            type: 'GET',
            data: 'lottery_type=' + params.lottery_type,
            dataType: 'json',
            global: params.global,
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: false,
                    errorCloseOverlay: false,
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxChasePhaseList: function(params) {
        params = jQuery.extend({
            'lottery_type': undefined,
            'num': 20,
            'success': undefined,
            'error': undefined
        }, params);

        if (!params.lottery_type) {
            logger.error('invalid lottery type specified');
            return false;
        }

        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_chase_phaselist.php',
            type: 'GET',
            dataType: 'json',
            data: 'lottery_type=' + params.lottery_type + '&num=' + params.num,
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxSyndicateList: function(params) {
        params = jQuery.extend({
            'lottery_type': 0,
            'page': 1,
            'pagesize': 10,
            'success': undefined,
            'error': undefined
        }, params);
        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_syndicate_list.php',
            data: 'lottery_type=' + params.lottery_type + '&page=' + params.page + '&pagesize=' + params.pagesize,
            global: false,
            type: 'GET',
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxFollowSyndicate: function(params) {
        params = jQuery.extend({
            'plan_id': 0,
            'parts': 0,
            'success': undefined,
            'error': undefined
        }, params);
        if (params.plan_id <= 0) {
            jQuery.showError('方案编号不正确！');
            return;
        }
        if (params.parts <= 0) {
            jQuery.showError('请正确输入要购买的份数！');
            return;
        }
        var doSubmit = function(){
            jQuery.ajax({
                url: G.baseUrl + '/lottery/ajax_follow.php',
                type: 'POST',
                data: 'plan_id=' + params.plan_id + '&parts=' + params.parts,
                dataType: 'json',
                success: function(jobj) {
                    jQuery.registerErrorTable({}, {
                        '0x7020004': errorHandler_0x7020004_plan
                    });
                    var obj = jQuery.ajaxResultHandler(jobj, {
                        successCloseOverlay: false,
                        success: params.success,
                        error: params.error
                    });
                    if (typeof(obj) != 'object' || !obj.success) {
                        return false;
                    }
                },
                error: function() {
                    logger.error('Ajax call failed!');
                    if (typeof(params.error) == 'function') {
                        params.error();
                    }
                }
            });
        }
        if (!UID) {
            $.executeErrorHandler(C.ERROR.UNLOGIN);
            return false;
        }
        //confirm
        if(params && params.confirm){
            jQuery.showConfirm(params.confirm.title , {
                'detail' : params.confirm.text,
                'confirmButton' : {'text' : '确认投注' ,
                    'styleClass' : 'sub_btn' ,
                    'click' : function(){
                         doSubmit();
                    }
                }
            });
        }else{
            doSubmit();
        }
    },
    ajaxLatestDrawn: function(params) {//快彩右侧开奖公告的倒计时和最新开奖彩期
        params = jQuery.extend({
            'lottery_type': 0,
            'success': undefined,
            'error': undefined
        }, params);

        if (params.lottery_type <= 0) {
            jQuery.showError('彩种编码不正确！');
            return;
        }
        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_latestdrawn.php',
            data: 'lottery_type=' + params.lottery_type,
            global: false,
            type: 'GET',
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: false,
                    errorCloseOverlay: false,
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    /**
     * 各彩种遗漏值以及冷热号等数据
     */
    ajaxOmitData: function(params) {
        params = jQuery.extend({
            'lottery_type': 0,
            'phase': '',
            'force_update': false,
            'play_type': '',
            'success': undefined,
            'error': undefined
        }, params);

        if (params.lottery_type <= 0) {
            jQuery.showError('彩种编码不正确！');
            return false;
        }
        // 建立遗漏数据缓存
        if (typeof(G.omitData) == 'undefined') {
            G.omitData = {};
        }
        // 针对每个彩种建立缓存
        if (typeof(G.omitData[params.lottery_type]) == 'undefined') {
            G.omitData[params.lottery_type] = {};
        }
        var lottery_cache = G.omitData[params.lottery_type];
        var play_type_data = lottery_cache[params.play_type];
        if (typeof(play_type_data) == 'object'
                && play_type_data.phase == params.phase
                && params.force_update !== true) {
            // 找到已缓存数据，直接执行回调
            if (typeof(params.success) == 'function') {
                params.success(play_type_data);
            }
            return;
        }
        // 通过AJAX请求最新数据
        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_get_stats_omit.php',
            global: false,
            type: 'GET',
            data: 'lottery_type=' + params.lottery_type + '&play_type=' + params.play_type,
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: false,
                    errorCloseOverlay: false,
                    error: params.error
                });
                if (typeof(obj) != 'object' && !obj.success) {
                    logger.error('Ajax call failed!', obj);
                    return false;
                }
                play_type_data = obj.data;
                // 对数据进行预处理

                for (var i = 0; i < play_type_data.group_count; i ++) {
                    var group_data = play_type_data.group[i];

                    // 最大当前遗漏数据
                    var max_omit_count = -1;
                    var max_omit_data;

                    // 冷号
                    var cold_count = -1;
                    var cold_data;

                    // 热号
                    var hot_count = -1;
                    var hot_data;

                    // 高频彩最大遗漏数据
                    var hf_max_omit_count = -1;
                    var hf_max_omit_data;

                    for (var num in group_data.numbers) {
                        // 计算每组元素的最大当前遗漏值
                        var item = group_data.numbers[num];

                        if (item.omit_current > max_omit_count) {
                            max_omit_count = item.omit_current;
                            max_omit_data = [];
                        }
                        if (item.omit_current == max_omit_count) {
                            max_omit_data.push(num);
                        }

                        // 计算每组元素的最大"最大遗漏"值
                        if (item.omit_max > hf_max_omit_count) {
                            hf_max_omit_count = item.omit_max;
                            hf_max_omit_data = [];
                        }
                        if (item.omit_max == hf_max_omit_count) {
                            hf_max_omit_data.push(num);
                        }

                        if (item.appear_recently > hot_count) {
                            hot_count = item.appear_recently;
                            hot_data = [];
                        }
                        if (item.appear_recently == hot_count) {
                            hot_data.push(num);
                        }

                        if (cold_count == -1 || item.appear_recently < cold_count) {
                            cold_count = item.appear_recently;
                            cold_data = [];
                        }
                        if (item.appear_recently == cold_count) {
                            cold_data.push(num);
                        }

                        // 如果超过100次，优化显示
                        if (parseInt(item.omit_current, 10) >= 100) {
                            item.omit_current_str = '99+';
                        }
                        else {
                            item.omit_current_str = item.omit_current + '';
                        }
                        // 如果超过100次，优化显示
                        if (parseInt(item.omit_max, 10) >= 100) {
                            item.omit_max_str = '99+';
                        }
                        else {
                            item.omit_max_str = item.omit_max + '';
                        }
                    }
                    // 保存最大遗漏的号码数组
                    group_data.max_omit_data = max_omit_data;
                    group_data.hf_max_omit_data = hf_max_omit_data;
                    // 保存冷号和热号
                    group_data.cold_data = cold_data;
                    group_data.hot_data = hot_data;
                }

                // 存储缓存数据
                lottery_cache[params.play_type] = play_type_data;
                if (typeof(params.success) == 'function') {
                    params.success(play_type_data);
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxRenderOmitData: function(params) {
        params = jQuery.extend({
            'type': C.OMIT_TYPE.OMIT,
            'selector': [], // 显示遗漏数据等的容器的选择器
            'group_index': undefined,
            'custom_render': false, // 是否使用自定义的渲染方法
            'render': undefined,
            'lottery_type': 0,
            'phase': '',
            'force_update': false,
            'play_type': '',
            'success': undefined,
            'error': undefined
        }, params);

        if (params.lottery_type <= 0) {
            jQuery.showError('彩种编码不正确！');
            return false;
        }
        if (params.custom_render !== true && params.selector.length == 0) {
            logger.error('未指定有效的选择器');
            return false;
        }
        // 渲染方法
        var render = {};
        (function() {
            render[C.OMIT_TYPE.OMIT] = function(num, group_data) {
                // 遗漏最高的标红
                var html = new StringBuffer();
                var is_max = ($.inArray(num, group_data.max_omit_data) >= 0);
                if (is_max) {
                    html.append('<label class="red">');
                }
                html.append(group_data.numbers[num].omit_current_str);
                if (is_max) {
                    html.append('</label>');
                }
                return html.toString();
            };
            render[C.OMIT_TYPE.OMIT_MAX] = function(num, group_data) {
                // 最大遗漏最高的标红
                var html = new StringBuffer();
                var is_max = ($.inArray(num, group_data.hf_max_omit_data) >= 0);
                if (is_max) {
                    html.append('<label class="red">');
                }
                html.append(group_data.numbers[num].omit_max_str);
                if (is_max) {
                    html.append('</label>');
                }
                return html.toString();
            };
            render[C.OMIT_TYPE.COLD_HOT] = function(num, group_data) {
                // 最冷和最热的标红
                var html = new StringBuffer();
                var has_color;
                if ($.inArray(num, group_data.cold_data) >= 0) {
                    has_color = 'blue';
                }
                else if ($.inArray(num, group_data.hot_data) >= 0) {
                    has_color = 'red';
                }
                if (has_color) {
                    html.append('<label class="').append(has_color).append('">');
                }
                html.append(group_data.numbers[num].appear_recently);
                if (has_color) {
                    html.append('</label>');
                }
                return html.toString();
            };
        })();
        render = $.extend(render, params.render);
        $.ajaxOmitData({
            'lottery_type': params.lottery_type,
            'phase': params.phase,
            'force_update': params.force_update,
            'play_type': params.play_type,
            'success': function(play_type_data) {
                if (params.custom_render === true) {
                    if (typeof(params.success) == 'function') {
                        params.success(play_type_data);
                    }
                    return;
                }
                // 默认的渲染方法
                for (var i = 0; i < params.selector.length; i ++) {
                    var render_fun = render[$.isArray(params.type) ? params.type[i] : params.type];
                    if (typeof(render_fun) != 'function') {
                        return false;
                    }
                    var selector_str = params.selector[i];
                    var group_data;
                    if (typeof(params.group_index) != 'undefined' && params.group_index.length > 0) {
                        group_data = play_type_data.group[params.group_index[i]];
                    }
                    else {
                        group_data = play_type_data.group[i];
                    }
                    for (var num in group_data.numbers) {
                        $(selector_str + '[val=' + parseInt(num, 10) + ']').html(render_fun(num, group_data));
                    }
                }
            },
            'error': params.error
        });
    },
    ajaxMissing: function(params) {//快彩遗漏值
        params = jQuery.extend({
            'lottery_type': 0,
            'play_type': '',
            'success': undefined,
            'error': undefined
        }, params);

        if (params.lottery_type <= 0) {
            jQuery.showError('彩种编码不正确！');
            return;
        }
        jQuery.ajax({
            url: G.baseUrl + '/lottery/ajax_get_stats_omit.php',
            global: false,
            type: 'GET',
            data: 'lottery_type=' + params.lottery_type + '&play_type=' + params.play_type,
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: false,
                    errorCloseOverlay: false,
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxCancelOrder: function(params) {//合买撤单
        params = jQuery.extend({
            'successCloseOverlay': true,
            'successCloseOverlay': true,
            'order_id': 0,
            'success': undefined,
            'error': undefined
        }, params);

        jQuery.ajax({
            url: G.baseUrl + '/user/ajax_cancel_order.php',
            data: 'order_id=' + params.order_id,
            global: false,
            type: 'GET',
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: params.successCloseOverlay,
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    },
    ajaxUploadPlanParsed: function(params) { 
        params = jQuery.extend({
            'lottery_type': 0,
            'phase': '',
            'content': '',
            'successCloseOverlay': false,
            'success': undefined
        }, params);

        var submit_data = {
            'upload_lottery_type': params.lottery_type,
            'upload_phase': params.phase,
            'upload_content': params.content
        };

        // 提交上传方案地址，只有标准选号需要，文件上传通过表单设置的地址提交
        $.ajax({
            'url': G.baseUrl + '/lottery/ajax_upload_plan_parsed.php',
            'type': 'POST',
            'dataType': 'json',
            'data': submit_data, 
            'success': function(jobj) {
                var obj = $.ajaxResultHandler(jobj, {
                    'successCloseOverlay': params.successCloseOverlay 
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
                if (typeof(params.success) == 'function') {
                    params.success(obj.data);
                }
            }
        });
    },
    ajaxLotteryUploadLater: function(params) {
        params = jQuery.extend({
            'plan_id': '',
            'upload_id': 0,
            'upload_tickets': 0,
            'preset_tickets': 0,
            'successCloseOverlay': false,
            'success': undefined
        }, params);

        // 必须指定方案号
        if (!params.plan_id) {
            $.showError('未指定方案号！');
            return false;
        }
        // 获得上传后的文件id
        if (!params.upload_id || params.upload_id <= 0) {
            $.showError('上传方案出错！');
            return false;
        }
        if (params.upload_tickets != params.preset_tickets || params.upload_tickets <= 0) {
            $.showError('上传方案注数与订单设定不同，请检查！');
            return false;
        }

        var submit_data = {
            'plan_id': params.plan_id,
            'upload_id': params.upload_id
        };
        
        $.ajax({
            url: G.baseUrl + '/lottery/ajax_asyncupload.php',
            type: 'POST',
            data: submit_data,
            dataType: 'json',
            success: function(jobj) {
                var obj = $.ajaxResultHandler(jobj, {
                    'successCloseOverlay': params.successCloseOverlay
                });
                if (typeof(obj) != 'object' || !obj.success) {
                    return false;
                }
                if (typeof(params.success) == 'function') {
                    params.success(obj.data);
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
            }
        });
    }
});

/**
 * 合买区域的通用类
 */
function LotterySyndicateArea(params) {
    this.params = jQuery.extend({
        'container': undefined
    }, params);

    if (!this.params.container || jQuery('#' + this.params.container).length == 0) {
        logger.error('wrong container id given');
        return false;
    }
    var $container = jQuery('#' + this.params.container);
    var $parts_founder = jQuery('input[name=parts_founder]', $container);
    var $parts_reserved = jQuery('input[name=parts_reserved]', $container);

    function refreshFreezeAmount() {
        var founder_amount = parseInt($parts_founder.val(), 10);
        if (isNaN(founder_amount)) {
            founder_amount = 0;
        }
        var reserved_amount = parseInt($parts_reserved.val(), 10);
        if (isNaN(reserved_amount)) {
            reserved_amount = 0;
        }
        jQuery('#syndicate_freeze_amount').html((founder_amount + reserved_amount));
    };

    $parts_founder.keydown(numberKeyOnly).blur(refreshFreezeAmount);
    $parts_reserved.keydown(numberKeyOnly).blur(refreshFreezeAmount);
};
/**
 * 获取总投注金额的抽象方法
 * 各彩种需要单独实现此方法
 */
LotterySyndicateArea.prototype.getTotalAmount = function() {
    return 0;
};
/**
 * 刷新合买区的选框
 */
LotterySyndicateArea.prototype.refresh = function() {
    var selfobj = this;
    jQuery('#' + this.params.container).find('input[name=parts_total]').each(function() {
                jQuery(this).val(selfobj.getTotalAmount.apply(selfobj));
            }).end();
};
/**
 * 重置合买区的选框
 */
LotterySyndicateArea.prototype.reset = function() {
    var selfobj = this;
    jQuery('#' + this.params.container).find('input[name=parts_total]').each(function() {
                jQuery(this).val(selfobj.getTotalAmount.apply(selfobj));
            }).end()
        .find('input[name=parts_founder]').each(function() {
                jQuery(this).val('');
            }).end()
        .find('input[name=parts_reserved]').each(function() {
                jQuery(this).val('');
            }).end()
        .find('input[name=title]').each(function() {
                jQuery(this).val('');
            }).end()
        .find('textarea[name=description]').each(function() {
                jQuery(this).val('');
            }).end()
        .find('#syndicate_freeze_amount').each(function() {
                jQuery(this).html('0.00');
            }).end();
};
/**
 * 获取合买数据
 */
LotterySyndicateArea.prototype.getSyndicateData = function() {
    var data = {};
    var selfobj = this;

    var $container = jQuery('#' + this.params.container);
    $container.find('input[name=parts_total]').each(function() {
                data.parts_total = parseInt(jQuery(this).val(), 10);
                if (isNaN(data.parts_total)) {
                    data.parts_total = 0;
                }
            }).end()
        .find('input[name=parts_founder]').each(function() {
                data.parts_founder = parseInt(jQuery(this).val(), 10);
                if (isNaN(data.parts_founder)) {
                    data.parts_founder = 0;
                }
            }).end()
        .find('input[name=parts_reserved]').each(function() {
                data.parts_reserved = parseInt(jQuery(this).val(), 10);
                if (isNaN(data.parts_reserved)) {
                    data.parts_reserved = 0;
                }
            }).end()
        .find('select[name=rebate]').each(function() {
                data.rebate = jQuery.trim(jQuery(this).val());
            }).end()
        .find('input[name=title]').each(function() {
                data.title = jQuery.trim(jQuery(this).val());
            }).end()
        .find('textarea[name=description]').each(function() {
                data.description = jQuery.trim(jQuery(this).val());
            }).end();

    data.public_status = $container.find('input[name=public_status]:checked').val();

    return data;
};


/**
 * 追号区域的通用类
 */
function LotteryChaseArea(params) {
    this.params = jQuery.extend({
        'container': undefined,
        'lottery_type': undefined,
        'select_all_on_load' : true
    }, params);

    if (!this.params.lottery_type) {
        logger.error('wrong lottery type specified');
        return false;
    }

    if (!this.params.container || jQuery('#' + this.params.container).length == 0) {
        logger.error('wrong container id given');
        return false;
    }

    this.chase_phase_list = [];
    this.chase_phase_list_map = {};

    this.selected_phase_list = [];
    this.selected_phase_list_map = {};
};
/**
 * 抽象方法
 * 追号区域的实际渲染方法
 * 各彩种显示时必须实现此方法
 */
LotteryChaseArea.prototype.display = function(num) {
};
/**
 * 抽象方法
 * 获取方案金额的方法
 * 各彩种必须实现此方法
 */
LotteryChaseArea.prototype.getPlanAmount = function() {
    return 0;
};
/**
 * 抽象方法
 * 获取追号方案的注数
 * 各彩种必须实现此方法
 */
LotteryChaseArea.prototype.getPlanPiecesCount = function() {
    return 0;
};
/**
 * 追号区域的渲染
 * 通过调用display方法实现页面渲染
 * 此方法内部做了公用的事件绑定
 */
LotteryChaseArea.prototype.render = function(num) {
    this.unbind();

    // 清除所有选中的缓存
    this.selected_phase_list_map = {};

    // 调用抽象方法显示
    this.display(num);
    if(this.params.select_all_on_load){
        this.selectAll();
    }
    this.bind();
};
/**
 * 从当前期开始载入指定数量的期数
 * 如果缓存中已有足够数量的彩期，不做重复请求
 */
LotteryChaseArea.prototype.load = function(num, callback) {
    if (num <= this.chase_phase_list.length) {
        logger.debug('缓存中已载入足够的彩期，不需要查询');
        //renderChasePhaseList(num);
        // 默认先选中当前期
        //jQuery('#chase_phase_' + current_phase).attr('checked', true);
        if (typeof(callback) == 'function') {
            callback.apply(this, [num]);
        }
        return false;
    }

    var chase_phase_list = this.chase_phase_list;
    var chase_phase_list_map = this.chase_phase_list_map;

    var selfobj = this;
    jQuery.ajaxChasePhaseList({
        'lottery_type': this.params.lottery_type,
        'num': num,
        'success': function(obj) {
            var list = obj.data.data;
            for (var i = 0, max = list.length; i < max; i ++) {
                var phase_obj = list[i];

                if (chase_phase_list_map[phase_obj.phase]) {
                    continue;
                }

                chase_phase_list.push(phase_obj);
                chase_phase_list_map[phase_obj.phase] = true;
            }
            if (typeof(callback) == 'function') {
                callback.apply(selfobj, [num]);
            }
            return false;
        }
    });
    return false;
};
/**
 * 选中一期或多期追号
 * 接受参数为期号或者期号的数组
 */
LotteryChaseArea.prototype.select = function(arr) {
    var plan_amount = this.getPlanAmount();
    var chase_multiple_all = parseInt(jQuery('#chase_multiple_all').val(), 10);
    function selectSingle(phase) {
        var $chase_phase = jQuery('#chase_phase_' + phase);
        var $chase_multiple = jQuery('#chase_multiple_' + phase);
        var $chase_amount = jQuery('#chase_amount_' + phase);

        var multiple = parseInt($chase_multiple.val(), 10);
        // 如果已经设置了倍数，不做更改
        if (multiple == 0 || isNaN(multiple)) {
            // 优先使用总体倍数，否则设为初始值1倍 
            if (isNaN(chase_multiple_all)) {
                multiple = 1;
            }
            else {
                multiple = chase_multiple_all;
            }
            $chase_multiple.val(multiple);
        }

        $chase_amount.html('￥' + multiple * plan_amount);

        $chase_phase.attr('checked', true);
        this.selected_phase_list_map[phase] = true;
    };
    if (typeof(arr) == 'object' && arr instanceof Array) {
        for (var i = 0, max = arr.length; i < max; i ++) {
            selectSingle.apply(this, [arr[i]]);
        }
    }
    else {
        selectSingle.apply(this, [arr.toString()]);
    }
};
/**
 *请求智能追号数据
 *
 */
LotteryChaseArea.prototype.loadIntelligentChaseData = function (params){
    params = $.extend({
        'type': 0,
        'lottery_type': 0,
        'play_type': 0,
        'unit': 2,
        'num': 10,
        'phase': '',
        'multiple': 1,
        'success': undefined
    }, params);
    var query_string = [];
    for (var attr in params) {
        if (typeof(params[attr]) != 'object'
                && typeof(params[attr]) != 'function') {
            query_string.push(attr + '=' + encodeURIComponent(params[attr]));
        }
    }
    // TODO validation
    jQuery.ajax({
        url: G.baseUrl + '/lottery/ajax_intelligent_chase.php',
        type: 'GET',
        data: query_string.join('&'),
        dataType: 'json',
        success: function(jobj) {
            var obj = jQuery.ajaxResultHandler(jobj, {
                'errorHandlers': {
                    '0x4000010': function(obj) {
                        $.showConfirm(obj.message, {
                            'detail' : '',
                            'confirmButton' : {
                                'text' : '是' ,
                                'styleClass' : 'sub_btn'
                            },
                            'cancelButton': {
                                'text' : '否' ,
                                'styleClass' : 'sub_btn'
                            },
                            'confirmCallback' : function() {
                                if (typeof(params.success) == 'function') {
                                    params.success(obj.data);
                                }
                            }
                        });
                        return false;
                    }
                }
            });
            if (typeof(obj) != 'object' || !obj.success) {
                return false;
            }
            if (typeof(params.success) == 'function') {
                params.success(obj.data);
            }
        },
        error: function() {
            logger.error('Ajax call failed!');
        }
    });
}

/**
 * 取消选中一期或多期追号
 * 接受参数为期号或者期号的数组
 */
LotteryChaseArea.prototype.unselect = function(arr) {
    function unselectSingle(phase) {
        var $chase_phase = jQuery('#chase_phase_' + phase);
        var $chase_multiple = jQuery('#chase_multiple_' + phase);
        var $chase_amount = jQuery('#chase_amount_' + phase);

        $chase_multiple.val('');
        $chase_amount.html('￥0');

        $chase_phase.attr('checked', false);
        delete this.selected_phase_list_map[phase];
    };
    if (typeof(arr) == 'object' && arr instanceof Array) {
        for (var i = 0, max = arr.length; i < max; i ++) {
            unselectSingle.apply(this, [arr[i]]);
        }
    }
    else {
        unselectSingle.apply(this, [arr.toString()]);
    }
};
/**
 * 切换彩期的选中状态
 */
LotteryChaseArea.prototype.toggleSelect = function(arr) {
    function toggleSelectSingle(phase) {
        if (this.selected_phase_list_map[phase] === true) {
            this.unselect(phase);
        }
        else {
            this.select(phase);
        }
    };
    if (typeof(arr) == 'object' && arr instanceof Array) {
        for (var i = 0, max = arr.length; i < max; i ++) {
            toggleSelectSingle.apply(this, [arr[i]]);
        }
    }
    else {
        toggleSelectSingle.apply(this, [arr.toString()]);
    }
};
/**
 * 获取所选追号彩期号的数组
 */
LotteryChaseArea.prototype.getSelected = function() {
    var selected = [];
    for (var i = 0, max = this.chase_phase_list.length; i < max; i ++) {
        var phase = this.chase_phase_list[i].phase;
        if (this.selected_phase_list_map[phase] === true) {
            selected.push(phase);
        }
    }
    return selected;
};
/**
 * 全选追号彩期号
 */
LotteryChaseArea.prototype.selectAll = function() {
    var selfobj = this;
    var $chase_list = jQuery('#' + this.params.container);
    $chase_list.find('input[name="chase_phase[]"]').each(function() {
            var phase = jQuery(this).val();
            selfobj.select.apply(selfobj, [phase]);
        }).end();
    //将全选的checkbox设为checed，防止selectAll函数被外部调用时，checkbox状态不同步
    jQuery('#chase_phase_all').attr('checked' , true);
};
/**
 * 取消全选追号彩期号
 */
LotteryChaseArea.prototype.unselectAll = function() {
    var selfobj = this;
    var $chase_list = jQuery('#' + this.params.container);
    $chase_list.find('input[name="chase_phase[]"]').each(function() {
            var phase = jQuery(this).val();
            selfobj.unselect.apply(selfobj, [phase]);
        }).end();
};
/**
 * 投注完成后清空复位追号区
 * 追号期数下拉框和中奖后停止的选择框保持不变
 */
LotteryChaseArea.prototype.reset = function() {
    var num = jQuery('#chase_select').val();
    jQuery('#chase_name').val('');
    this.render(num);
};
/**
 * 刷新显示，重新计算金额
 */
LotteryChaseArea.prototype.refresh = function() {
    // 只需要更新已选中的彩期的金额
    var plan_amount = this.getPlanAmount();
    var selected = this.getSelected();
    for (var i = 0, max = selected.length; i < max; i ++) {
        var phase = selected[i];

        var $chase_multiple = jQuery('#chase_multiple_' + phase);
        var $chase_amount = jQuery('#chase_amount_' + phase);

        var multiple = parseInt($chase_multiple.val(), 10);
        if (isNaN(multiple)) {
            continue;
        }

        $chase_amount.html('￥' + multiple * plan_amount);
    }
};
/**
 * 解除追号区的事件绑定
 * 每次渲染前都需要解除事件绑定防止内存泄露
 */
LotteryChaseArea.prototype.unbind = function() {
    var $chase_list = jQuery('#' + this.params.container);
    $chase_list.find('#chase_phase_all').unbind().end()
        .find('#chase_multiple_all').unbind().end()
        .find('input[name="chase_phase[]"]').unbind().end()
        .find('input[name="chase_multiple[]"]').unbind().end();
};
/**
 * 获得追号结果。包括所追期数，倍数，总金额
 */
LotteryChaseArea.prototype.getChaseResult = function() {
    var chase_num = 0;
    var pieces_count = 0;
    var total_amount = 0;
    var plan_pieces_count = this.getPlanPiecesCount();
    var plan_amount = this.getPlanAmount();

    if (plan_pieces_count && plan_amount) {
        var data = chase_area.getData();
        chase_num = data.chase_total;
        for (var i = 0; i < chase_num; i ++) {
            var multiple = data.chase_multiple[i];
            pieces_count += plan_pieces_count * multiple;
            total_amount += plan_amount * multiple;
        }
    }
    return {'chase_num' : chase_num  , 'pieces_count' :  pieces_count , 'total_amount' : total_amount};
}
/**
 *渲染普通追号的追号结果
 *每个彩种都需要实现
 */
LotteryChaseArea.prototype.refreshChaseResult = function(result){
    return false;
}
/**
 * 绑定追号区的事件
 * 每次渲染后需要重新绑定
 */
LotteryChaseArea.prototype.bind = function() {
    var selfobj = this;
    var $chase_list = jQuery('#' + this.params.container);
    $chase_list.find('#chase_phase_all').click(function() {
            if (jQuery(this).attr('checked')) {
                selfobj.selectAll.apply(selfobj);
            }
            else {
                selfobj.unselectAll.apply(selfobj);
            }
            selfobj.refreshChaseResult.apply(selfobj);
        }).end()
        .find('#chase_multiple_all').keydown(function(evt) {
            return numberKeyOnly(evt);
        }).blur(function() {
            var multiple_all = jQuery(this).val();
            // 如果未输入倍数，不做修改
            if (multiple_all == '') {
                return;
            }
            // 只影响已选中追号的期数
            var selected = selfobj.getSelected();
            for (var i = 0, max = selected.length; i < max; i ++) {
                var phase = selected[i];
                var $chase_multiple = jQuery('#chase_multiple_' + phase).val(multiple_all);
            }
            selfobj.refresh.apply(selfobj);
            selfobj.refreshChaseResult.apply(selfobj);
        }).end()
        .find('input[name="chase_phase[]"]').click(function() {
            var phase = jQuery(this).val();
            selfobj.toggleSelect.apply(selfobj, [phase]);
            selfobj.refreshChaseResult.apply(selfobj);
        }).end()
        .find('input[name="chase_multiple[]"]').keydown(function(evt) {
            return numberKeyOnly(evt);
        }).blur(function() {
            var $chase_multiple = jQuery(this);
            var id = $chase_multiple.attr('id');
            var phase = id.substring("chase_multiple".length + 1);

            // 如果手动输入了倍数，自动选中该彩期进行追号
            if ($chase_multiple.val() != '') {
                selfobj.select.apply(selfobj, [phase]);
            }
            // 否则取消该期追号
            else {
                selfobj.unselect.apply(selfobj, [phase]);
            }
            selfobj.refreshChaseResult.apply(selfobj);
        }).end();
};
/**
 * 获取追号数据供表单提交使用
 */
LotteryChaseArea.prototype.getChaseData = function() {
    var selected_phase = this.getSelected();
    var selected_multiple = [];
    for (var i = 0, max = selected_phase.length; i < max; i ++) {
        var phase = selected_phase[i];
        var multiple = jQuery('#chase_multiple_' + phase).val();
        selected_multiple.push(multiple);
    }
    return {
        'chase_total': selected_phase.length,
        'chase_phase': selected_phase,
        'chase_multiple': selected_multiple
    };
};


/**
 * 彩种通用投注类
 */
function LotterySubmit(params) {
    this.params = jQuery.extend({
        'global': true,
        'successCloseOverlay': false,
        'errorCloseOverlay': false,
        'usingAlert': false,
        'is_chase': false,                          // 是否追号
        'is_syndicate': false,                      // 是否合买
        'lottery_type': 0,                          // 彩种类型
        'phase': '',                                // 彩期号
        'select_type': SelectType.CHOOSE,           // 选号类型
        'multiple': 1,                              // 倍数
        'content': '',                              // 投注内容
        'public_status': PublicStatus.PUBLIC,       // 方案公开状态
        'dlt_addition': false,                      // 是否大乐透追加
        'upload_id': 0,                             // 上传文件ID
        'upload_later': false,                      // 是否先创建后上传
        'upload_parts': 0,                          // 先创建后上传需要指定注数
        'upload_play_type': 0,                             // 玩法只有在上传时有用，选号通过方案内容识别
        'chase_type': ChaseType.CHOOSE_SELF,        // 追号类型
        'chase_name': '',                           // 追号名称
        'win_operate': StopChaseType.STOP_AFTER_AWARDS,
        'chase_phase': undefined,                   // 追号彩期列表
        'chase_multiple': undefined,                // 追号倍数列表
        'chase_total': undefined,                   // 追号总期数
        'title': '',                                // 合买标题
        'description': '',                          // 合买描述
        'parts_total': 0,                           // 合买总份数
        'parts_founder': 0,                         // 发起者认购份数
        'parts_reserved': 0                         // 发起者保底份数
    }, params);

    logger.debug('params for submit is:', this.params);

    this.valid = true;

    // 基本规则校验
    // 普通、合买、追号只可能是其中一个
    if (this.params.is_chase && this.params.is_syndicate) {
        jQuery.showError('追号和合买不能同时进行！', {usingAlert: this.params.usingAlert});
        logger.error('cannot be both chasing and syndicating at the same time!');
        this.valid = false;
        return;
    }
    // lottery type必须指定且为正数
    if (!this.params.lottery_type || this.params.lottery_type < 0) {
        jQuery.showError('未指定有效的彩种类型！', {usingAlert: this.params.usingAlert});
        logger.error('invalid lottery type given!');
        this.valid = false;
        return;
    }
    // 非追号时必须指定期数和倍数
    if (!this.params.is_chase) {
        if (!this.params.phase || this.params.phase < 0) {
            jQuery.showError('未指定期数！', {usingAlert: this.params.usingAlert});
            logger.error('phase has not been specified!');
            this.valid = false;
            return;
        }
        if (this.params.multiple <= 0) {
            jQuery.showError('未指定有效的倍数！', {usingAlert: this.params.usingAlert});
            logger.error('invalid multiple given!');
            this.valid = false;
            return;
        }
    }
    else {
        // 追号总期数必须大于0
        if (!this.params.chase_total || this.params.chase_total < 0) {
            jQuery.showError('追号时至少选择一期！', {usingAlert: this.params.usingAlert});
            logger.error('must have more than 1 chase phase!');
            this.valid = false;
            return;
        }
        // 追号期数和追号倍数列表必须是数组
        if (!(this.params.chase_phase instanceof Array) || !(this.params.chase_multiple instanceof Array)) {
            jQuery.showError('追号数据错误！', {usingAlert: this.params.usingAlert});
            logger.error('chase phase and multiple must be array!');
            this.valid = false;
            return;
        }
        // 追号期数和追号倍数的数量必须和chase_total相等
        if (this.params.chase_phase.length != this.params.chase_total
                || this.params.chase_multiple.length != this.params.chase_total) {
            jQuery.showError('追号数据和追号数量不匹配！', {usingAlert: this.params.usingAlert});
            logger.error('chase phase and multiple array have wrong length!');
            this.valid = false;
            return;
        }
    }
    // 如果是合买，必须设定标题，发起者必须认购
    if (this.params.is_syndicate) {
        this.params.title = jQuery.trim(this.params.title);
        if (this.params.title == '') {
            jQuery.showError('方案标题不能为空！', {usingAlert: this.params.usingAlert});
            logger.error('empty plan title!');
            this.valid = false;
            return;
        }
        if (this.params.title.length > 50) {
            jQuery.showError('方案标题不能超过50个字符！', {usingAlert: this.params.usingAlert});
            logger.error('title length larger than 50!');
            this.valid = false;
            return;
        }
        if (this.params.parts_total <= 0) {
            jQuery.showError('合买份数必须大于0！', {usingAlert: this.params.usingAlert});
            logger.error('syndicate error: total parts:', this.params.parts_total);
            this.valid = false;
            return;
        }
        if (this.params.parts_founder / this.params.parts_total < 0.1) {
            jQuery.showError('发起者至少认购10%！', {usingAlert: this.params.usingAlert});
            logger.error('syndicate error: founder parts:', this.params.parts_founder);
            this.valid = false;
            return;
        }
        if (this.params.parts_founder + this.params.parts_reserved > this.params.parts_total) {
            jQuery.showError('认购+保底的份数超过了总份数！', {usingAlert: this.params.usingAlert});
            logger.error('syndicate error: more than total parts!');
            this.valid = false;
            return;
        }
    }
    // 如果选号类型是文件上传
    if (this.params.select_type == SelectType.UPLOAD) {
        // 如果不是先创建方案再上传的，必须有上传文件ID
        if (!this.params.upload_later) {
            if (this.params.upload_id <= 0) {
                jQuery.showError('方案文件未上传！', {usingAlert: this.params.usingAlert});
                logger.error('need to upload file first!');
                this.valid = false;
                return;
            }
        }
        else {
            /*
             if (this.params.upload_play_type <= 0) {
                jQuery.showError('未指定玩法！', {usingAlert: this.params.usingAlert});
                logger.error('need to specify play type!');
                this.valid = false;
                return;
            }
            */
            if (this.params.upload_parts <= 0) {
                jQuery.showError('方案注数未指定！', {usingAlert: this.params.usingAlert});
                logger.error('need to specify upload parts!');
                this.valid = false;
                return;
            }
        }
    }
    else {
        // 自选必须给定方案内容
        if (!this.params.content) {
            jQuery.showError('方案内容为空！', {usingAlert: this.params.usingAlert});
            logger.error('empty plan content!');
            this.valid = false;
            return;
        }
    }
    if (!this.validate()) {
        logger.error('failed to pass custom validator!', {usingAlert: this.params.usingAlert});
        this.valid = false;
        return;
    }

    /*
    this.params.is_normal = false;
    if (!this.params.is_chase && !this.params.is_syndicate) {
        this.params.is_normal = true;
    }
    else {
        this.params.is_normal = false;
    }
    */
};
/**
 * 是否通过校验
 * 所有提交请求在提交前必须调用此方法进行判断
 */
LotterySubmit.prototype.isValid = function() {
    return this.valid;
};
/**
 * 抽象校验方法，默认不校验
 * 需要特殊校验的彩种需要实现此方法
 */
LotterySubmit.prototype.validate = function() {
    return true;
};
LotterySubmit.prototype.serialize = function() {
    var dataArr = [];

    function serializeArray(key, arr) {
        for (var i = 0, max = arr.length; i < max; i ++) {
            dataArr.push(key + '=' + arr[i]);
        }
    }

    // 公用属性
    dataArr.push('lottery_type=' + this.params.lottery_type);
    dataArr.push('select_type=' + this.params.select_type);

    // 附加彩种扩展属性
    if (this.params.lottery_type == LotteryType.DLT) {
        dataArr.push('dlt_addition=' + (this.params.dlt_addition ? '1' : '0'));
    }

    // 合买属性
    if (this.params.is_syndicate) {
        dataArr.push('public_status=' + this.params.public_status);
        dataArr.push('title=' + encodeURIComponent(this.params.title));
        dataArr.push('description=' + encodeURIComponent(this.params.description));
        dataArr.push('parts_total=' + this.params.parts_total);
        dataArr.push('parts_founder=' + this.params.parts_founder);
        dataArr.push('parts_reserved=' + this.params.parts_reserved);
        dataArr.push('rebate=' + this.params.rebate);
    }

    // 非追号的，设置彩期和倍数
    if (!this.params.is_chase) {
        dataArr.push('phase=' + this.params.phase);
        dataArr.push('multiple=' + this.params.multiple);
    }
    else {
        // 附加追号的参数
        dataArr.push('chase_total=' + this.params.chase_total);
        dataArr.push('chase_name=' + encodeURIComponent(this.params.chase_name));
        dataArr.push('chase_type=' + this.params.chase_type);
        dataArr.push('win_operate=' + this.params.win_operate);
        serializeArray('chase_phase[]', this.params.chase_phase);
        serializeArray('chase_multiple[]', this.params.chase_multiple);
    }

    // 如果选号类型是文件上传
    if (this.params.select_type == SelectType.UPLOAD) {
        if (this.params.upload_later) {
            dataArr.push('upload_play_type=' + this.params.upload_play_type);
            dataArr.push('upload_parts=' + this.params.upload_parts);
        }
        else {
            dataArr.push('upload_id=' + this.params.upload_id);
        }
    }
    else {
        // 自选给定方案内容
        dataArr.push('content=' + encodeURIComponent(this.params.content));
    }

    return dataArr.join('&');
};
LotterySubmit.prototype.submit = function(submitParams) {
    if (!this.isValid()) {
        return false;
    }
    var me = this;
    var doSubmit = function(){
        var params_data = me.serialize();
        logger.debug('post query string:', params_data);

        // 常规投注方案创建请求地址
        var url = '/lottery/ajax_create_plan.php';

        // 追号方案创建地址
        if (me.params.is_chase) {
            url = '/lottery/ajax_create_chase.php';
            jQuery.registerErrorTable({}, {
                '0x7020004': errorHandler_0x7020004_chase
            });
        }
        else {
            if (me.params.is_syndicate) {
                url = '/lottery/ajax_create_syndicate.php';
            }
            jQuery.registerErrorTable({}, {
                '0x7020004': errorHandler_0x7020004_plan
            });
        }

        logger.debug('lottery submit post url is:', url);

        var params = me.params;
        jQuery.ajax({
            global: params.global,
            url: G.baseUrl + url,
            type: 'POST',
            data: params_data,
            dataType: 'json',
            success: function(jobj) {
                var obj = jQuery.ajaxResultHandler(jobj, {
                    successCloseOverlay: params.successCloseOverlay,
                    errorCloseOverlay: params.errorCloseOverlay,
                    usingAlert: params.usingAlert,
                    success: params.success,
                    error: params.error
                });
                if (typeof(obj) != 'object') {
                    return false;
                }
            },
            error: function() {
                logger.error('Ajax call failed!');
                if (typeof(params.error) == 'function') {
                    params.error();
                }
            }
        });
    };
    if (!UID) {
        $.executeErrorHandler(C.ERROR.UNLOGIN);
        return false;
    }
    //confirm投注
    if(submitParams && submitParams.confirm){
        jQuery.showConfirm(submitParams.confirm.title , {
            'detail' : submitParams.confirm.text,
            'confirmButton' : {'text' : '确认投注' ,
                'styleClass' : 'sub_btn' ,
                'click' : function(){
                     doSubmit();
                }
            }
        });
    }else{
        doSubmit();
    }
};

/**
 * 追号时余额不足冻结的错误处理器
 * 追号时如果余额不足不会创建追号
 * 需要特殊处理时覆盖此方法
 */
function errorHandler_0x7020004_chase(obj) {
    jQuery.showError('账户余额不足以发起追号！');
    return false;
};
/**
 * 非追号时余额不足冻结的错误处理器
 * 非追号时如果余额不足仍然会创建方案，提示余额不足以完成支付
 * 充值后可以在未支付订单中心完成支付
 */
function errorHandler_0x7020004_plan(obj) {
    jQuery.showError('订单创建成功，账户余额不足！', {
        detail: '充值后请前往“我的彩票”完成支付',
        closeButton: false,
        buttons: [
            {
                text: '立即充值',
                click: function() {
                    window.location.href = G.baseUrl + '/user/recharge';
                }
            }
        ]
    });
    return false;
};

/**
 * 
 * 智能过滤
 */
function LotteryGuolv(params){
	this.params = jQuery.extend({
		data_size_url	: G.filterUrl,
		config_split	: ',',
		config_ssq		: {'redBall':'6-16,1-33','blueBall':'1-16,1-33','sum':'1-163,21-183,61-183,61-149','mantissaSum':'1-49,3-51,14-51,14-34','mantissaGroup':'1-5,2-6,3-6,3-6','difference':'1-28,5-32,15-32,15-32','big':'1-7,0-6,1-6,1-4','odd':'1-7,0-6,1-6,1-4','primeNumber':'1-7,0-6,1-6,1-4','sequence':'1-7,0-6,0-6,0-2,1','sequenceGroup':'1-4,0-3,0-3,0-2','ac':'1-11,0-10,4-10,4-10'},
		config_dlt		: {'redBall':'5-18,1-35','blueBall':'2-12,1-12','sum':'1-151,15-165,65-165,65-135','mantissaSum':'1-42,2-43,13-43,13-32','mantissaGroup':'1-4,2-5,3-5,3-5','difference':'1-31,4-34,16-34,16-34','big':'1-6,0-5,1-5,1-4','odd':'1-6,0-5,1-5,1-4','primeNumber':'1-6,0-5,1-5,1-4','sequence':'1-6,0-5,0-5,0-2,1','sequenceGroup':'1-6,0-2,0-2,0-2','ac':'1-7,0-6,4-6,4-6'},
		config			: {},
		get_data		: {},
		lottery_type	: 0,
		global			: true,
		successCloseOverlay : false,
		errorCloseOverlay	: false,
		usingAlert			: false,
		success				: function(){},
		setSelectOptionCallBack : function(){},
		getSelectValueCallBack	: function(){}
	}, params);
	switch(this.params.lottery_type){
		case LotteryType.SSQ :
			this.params.config = this.params.config_ssq;break;
		case LotteryType.DLT :
			this.params.config = this.params.config_dlt;break;
	}
};

LotteryGuolv.prototype.setSelectOptions = function(){
	var params = this.params;
	jQuery.each(params.config, function(name, data){
		var row = data.split( params.config_split );
		if(row.length >= 4){
			params.setSelectOptionCallBack(name, row);
		}
	});
};

LotteryGuolv.prototype.getSelectValue = function(redBall, blueBall, l){
	var params = this.params;
	var values = {},tmp;
	values.lottery_type = params.lottery_type;
	values.redBall = redBall;
	values.blueBall = blueBall;
	values.limit = l;
	jQuery.each(params.config, function(name, data){
		if(tmp = params.getSelectValueCallBack(name)){
			values[name] = tmp;
		}
	});
	this.params.get_data = values;
};
//JSONP 无法处理 AJAX异常 
LotteryGuolv.prototype.getResult = function(){
	var params = this.params;
	var ajaxStat = true;
	var aaaa = jQuery.ajax({
		url			: params.data_size_url,
		type		: 'get',
		data		: params.get_data,
		dataType	: 'jsonp',
		global		: params.global,
		success 	: function(jobj){
			ajaxStat = false;
			var obj = jQuery.ajaxResultHandler(jobj, {
                successCloseOverlay: params.successCloseOverlay,
                errorCloseOverlay: params.errorCloseOverlay,
                usingAlert: params.usingAlert,
                success: params.success,
                error: params.error
            });
            if (typeof(obj) != 'object' || !obj.success) {
                return false;
            }
		}
	});
	setTimeout(function(){
		if(ajaxStat){
			jQuery.showError('应用服务器无法响应请求!');
			//TODO can abort ajax request when update to jQuery 1.5+ 
		}
	}, 10000);
};
