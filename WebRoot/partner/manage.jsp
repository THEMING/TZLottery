<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>一彩票 - 运营支撑系统</title>
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body status="no" scroll="no">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td height="104" colspan="2" valign="top"><iframe border="0" width="100%" name="topFrame" id="topFrame" height="100%" frameborder="0" src="index.aspx?action=head"></iframe></td>
  </tr>
  <tr>
    <td width="215" valign="top" style="border-right: 1px solid #cfcfcf;" id="leftFrameId"><iframe  width="100%" name="leftFrame" id="leftFrame" SCROLLING="yes" frameborder="0" src="index.aspx?action=left"></iframe></td>
    <td valign="top"><iframe width="100%" name="mainframe" id="mainFrame" frameborder="0" scrolling="auto" src="agent/agentInfo.aspx"></iframe></td>
  </tr>
</table>

</body>
</html>
<script language="javascript">
	document.getElementById("leftFrame").height=Math.max(document.documentElement.scrollHeight,document.documentElement.clientHeight)-105;
	document.getElementById("mainFrame").height=Math.max(document.documentElement.scrollHeight,document.documentElement.clientHeight)-105;
</script>