<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>彩期编辑</title>
		<meta name="heading" content="彩期编辑" />
		<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type=text/javascript></script>
		<script src="../skin/01/js/jquery-1.3.2.min.js"></script>
   <script type="text/javascript">
    function onsubmit(id,term){
    $("#termId").val(id);
    $("#f_term").val(term);
    }

    function dosubmit(){
   
       if(!confirm('保存确认！')){
        return false;
       }
    }
    function test()
    {
		var match  = "${lotteryTerm.result}".split("-");
    	$("#lotteryTerm_result").val(match[1]);
    }
    
    function sub(s){
    	$("#from").val(s);
    }
    </script>
	</head>
	<body>
	 <s:form action="manageTerm" method="post" namespace="/oss/term">
			<s:hidden name="action" value="zq" />
			<s:hidden name="termId" id="termId"/>
			<s:hidden name="from" value="" id="from"/>
			<s:hidden name="type" id="type"/>
			<div style="height:20px;padding:10px 0 10px 260px;"><label>**********<s:property value="type"/>-彩期编辑**********</label>
				<input type="submit" name="submit" value="大赢家抓取" onclick="sub(0);" >
				<input type="submit" value="500万抓取" onclick="sub(1);"/>
			</div>
     </s:form>
		<label><s:actionmessage/></label>
		<s:form action="manageTerm" method="post" namespace="/oss/term">
			<s:hidden name="action" id="action" value="save" />
			<s:hidden name="lotteryTerm.id" />
			<s:hidden name="lotteryTerm.prizeLevels[0].id" />
			<s:hidden name="lotteryTerm.prizeLevels[1].id" />
			<s:hidden name="lotteryTerm.outPoint" />
			<s:hidden name="lotteryTerm.type" />
			<table width="60%" border="1" align="left" cellspacing="0" bordercolorlight="#003399" bordercolordark="#FFFFFF">
			<tr>
			<td width="20%">当前彩期期号:</td>
			<td>${lotteryTerm.termNo }</td>
			</tr>
			<tr>
			<td width="30%">当前彩期开售时间: </td>
			<td><s:date name="lotteryTerm.startSaleTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
			<td width="30%">当前彩期停销时间:</td>
			<td><s:date name="lotteryTerm.stopSaleTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
			<td width="30%">当前彩期合买截止时间:</td>
			<td><s:date name="lotteryTerm.stopTogetherSaleTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
			<td width="20%">当前彩期开奖时间: </td>
			<td><s:date name="lotteryTerm.openPrizeTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
			<tr>
			<td width="20%">当前彩期派奖时间:</td>
			<td><s:date name="lotteryTerm.sendPrizeTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
			
			<tr>
			<td width="20%" rowspan="1">开奖结果:</td>
			
				<td ><input type="button" value="cut" onclick="test()" /><input type="text"  name="lotteryTerm.result" id="lotteryTerm_result" value="${lotteryTerm.result }"/></td>
			</tr>
			
			<tr>
			<td width="20%">奖池:</td>
			<td><input type="text" name="lotteryTerm.prizePool" value="${lotteryTerm.prizePool}"/></td>
			</tr>
			
			<tr>
			<td width="20%">销售金额:</td>
			<td><input type="text" name="lotteryTerm.totalSale" value="${lotteryTerm.totalSale }"/></td>
			</tr>
			
			<tr>
			<td width="20%">彩期状态:</td>
			<td><s:select list="termStatus" name="lotteryTerm.termStatus" /></td>
			</tr>
			<tr>
			<td width="20%">出票地:</td>
			<td>${lotteryTerm.outPoint }</td>
			</tr>
			</table>
			<div style="clear:both"></div>
			
   			<div style="height:20px;padding:10px 0 10px 260px;"><label>**********开奖详情********</label></div>
 			<table width="60%" border="1" align="left" cellspacing="0" bordercolorlight="#003399" bordercolordark="#FFFFFF">
 			<tr>
			<td width="20%">一等奖:<input type="hidden" name="lotteryTerm.prizeLevels[0].name" value="一等奖"></td>
			<td><input type="text" name="lotteryTerm.prizeLevels[0].betNum" value="${lotteryTerm.prizeLevels[0].betNum }"/>注</td>
			<td><input type="text" name="lotteryTerm.prizeLevels[0].prize" value="${lotteryTerm.prizeLevels[0].prize }"/>元</td>
			</tr>
			<tr>
			<td width="20%">二等奖:<input type="hidden" name="lotteryTerm.prizeLevels[1].name" value="二等奖"></td>
			<td><input type="text" name="lotteryTerm.prizeLevels[1].betNum" value="${lotteryTerm.prizeLevels[1].betNum }"/>注</td>
			<td><input type="text" name="lotteryTerm.prizeLevels[1].prize" value="${lotteryTerm.prizeLevels[1].prize }"/>元</td>
			</tr>
	        </table>
			<div style="clear:both;padding:10px;text-align:center;height:20px;"></div>
			<input type="submit" name="submit" value="保存" onclick="return dosubmit()">
			<input name="Submit22" type="button" value="取 消"  class="button" onmouseover="this.className='button1'" onmouseout="this.className='button'"  onclick="history.back()"/>
		</s:form>
		<br />
		<br />
		<br />
		<br />
	</body>
</html>