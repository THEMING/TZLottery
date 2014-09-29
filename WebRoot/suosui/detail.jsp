<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>在线缩水</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="description" content="" />
<meta name="keywords" content=""/>
<link rel="stylesheet" href="../css/style.css" type="text/css"/>
<script type="text/javascript" src="../js/clip.js"></script>
<script src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
 function copy(){
  copyToClipboard($("#div1").html())
  }
  function dcwb(){
  location.href="/lottery/suosui.htm?action=suosui&code=${code}"
  }
  </script>
</head>
<body>
<div class="whole-box">
    <div class="nifty">
        <div class="rtop">
         <div class="r1"></div>
         <div class="r2"></div>
         <div class="r3"></div>
         <div class="r4"></div>
        </div>
        <div class="shuangseqiusoft">
           <div class="topTitle">
              <span>福彩双色球实用工具</span>
           </div>
            <div class="gongju-name">
               双色球单复式旋转矩阵选号工具
            </div>
            <div class="gongjuinfo">
                <p>工具说明：旋转矩阵是一款经典的缩水工具。它利用数学上的“覆盖设计”原理，通过矩阵公式，将大复式或者较多的投注组合化解为小复式或者较少的组合。其缩水后的保证程度一般均为100%。</p>
            </div>
            <div class="area-of-use">
                <div class="use-title">
                    双色球旋转矩阵结果：
                </div>
                <div>
                    <p style="font-size:13px;letter-spacing:1px;font-weight:bold;">您共选择了${num }个基号。${haoma }</p>
                    <p style="font-size:13px;">选用旋转矩阵公式${mode1 }，生成单式${totalnum } 注，共计金额 ${ totalnum*2}元：<img src="../images/notepad.png" onclick="dcwb()" alt="导出文本文件" style="margin-left:20px;"><span onclick="dcwb()">导出到文本文件</span>
                                                                   <img src="../images/note-add.png" onclick="copy()" alt="复制到剪切板" style="margin-left:20px;"><span onclick="copy()">复制到剪切板</span></p>
              </div>
              <div id="div1" style="padding:10px;line-height:2;">
    <%
    int n=((List<String>)request.getAttribute("list")).size();
    List<String> list2=(List<String>)request.getAttribute("list");
    for(int i=0;i<n;i++)
    {
    if(i!=0&&(i+1)%4==0)
    {
     out.write(list2.get(i)+"<br/>");
    }
    else{
      out.write(list2.get(i)+"&nbsp;&nbsp;&nbsp;");
      }
    }
     %>
              </div>
            </div> 
        </div>
    </div>
</div>

</body>
</html>