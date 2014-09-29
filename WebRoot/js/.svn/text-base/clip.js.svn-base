//复制到剪切版
function copyToClipboard(txt) {    
    
    var reg = new RegExp("<BR>", "g");
    txt = txt.replace(reg, "\r\n");
    var reg = new RegExp("&nbsp;", "g");
    txt = txt.replace(reg, " ");
    if(window.clipboardData) {  
        window.clipboardData.clearData();    
        window.clipboardData.setData("Text", txt);    
    } else if(navigator.userAgent.indexOf("Opera") != -1) {    
        window.location = txt;    
    } else if (window.netscape) {        
        try {    
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");    
        } catch (e) {    
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");    
        }    
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);    
        if (!clip)    
            return;    
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);    
        if (!trans)    
            return;    
        trans.addDataFlavor('text/unicode');    
        var str = new Object();    
        var len = new Object();    
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);    
        var copytext = txt;    
        str.data = copytext;    
        trans.setTransferData("text/unicode",str,copytext.length*2);    
        var clipid = Components.interfaces.nsIClipboard;    
        if (!clip)    
            return false;    
        clip.setData(trans,null,clipid.kGlobalClipboard);    
    }/* else if (window.MessageEvent && !document.getBoxObjectFor) {
        alert("Google Chrome!");
        window.location = txt;  
    }  else {
        var flashcopier = 'flashcopier';
        if(!document.getElementById(flashcopier)) {
            alert('123456');
            var divholder = document.createElement('div');
            divholder.id = flashcopier;
            document.body.appendChild(divholder);
        }
        alert('123456fdafdafda'); 
        document.getElementById(flashcopier).innerHTML = '';
        alert('aaaaaaaaaaa');  
        var divinfo = "<embed src='_clipboard.swf' FlashVars='clipboard="+encodeURIComponent(txt)+"' width='0' height='0' type='application/x-shockwave-flash'></embed>";
        document.getElementById(flashcopier).innerHTML = divinfo;
        alert('bbbbbbbbbbb');  
     }*/
    alert("复制成功！");
}