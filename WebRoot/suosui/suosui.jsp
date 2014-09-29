<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>在线缩水</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="description" content="" />
<meta name="keywords" content=""/>
<link rel="stylesheet" href="../css/style.css" type="text/css"/>
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/lottery.css" type="text/css" rel="stylesheet" />
<script src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/lottery/common.js"></script>
<script type="text/javascript" src="../js/lottery/ssq.js"></script>
<script type="text/javascript">
$(document).ready(function()
		{
			initBallList();
		}
	);
function tijiao(){
	if(g_std_cur_redBallList.length<7)
	  {alert("基号选择至少7个")
	  return;}
	var code=getCodeFromList(g_std_cur_redBallList);
	var mode=$("#randomselect_0").val();
	location.href="/lottery/suosui.htm?action=ss&code="+code+"|"+mode;
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
                    旋转矩阵公式<select id="randomselect_0" name="item"  id="select">
                          			<option value="1"> 中6保5</option>
									<option value="2"> 中6保4</option>
									<option value="3"> 中5保5</option>
									<option value="4"> 中5保4</option>
									<option value="5"> 中4保4</option>
                          		</select>
                </div>
                <div >
                    <table width="921" height="141" border="0">
                      <tr>
                        <td><li id="std_red_ball_1" class="redarea_ball_li" onclick="onClickRedBall(this,01)">01</li></td>
                        <td><li id="std_red_ball_2" class="redarea_ball_li" onclick="onClickRedBall(this,02)">02</li></td>
                        <td><li id="std_red_ball_3" class="redarea_ball_li" onclick="onClickRedBall(this,03)">03</li></td>
                        <td><li id="std_red_ball_4" class="redarea_ball_li" onclick="onClickRedBall(this,04)">04</li></td>
                        <td><li id="std_red_ball_5" class="redarea_ball_li" onclick="onClickRedBall(this,05)">05</li></td>
                        <td><li id="std_red_ball_6" class="redarea_ball_li" onclick="onClickRedBall(this,06)">06</li></td>
                        <td><li id="std_red_ball_7" class="redarea_ball_li" onclick="onClickRedBall(this,07)">07</li></td>
                        <td><li id="std_red_ball_8" class="redarea_ball_li" onclick="onClickRedBall(this,08)">08</li></td>
                        <td><li id="std_red_ball_9" class="redarea_ball_li" onclick="onClickRedBall(this,09)">09</li></td>
                        <td><li id="std_red_ball_10" class="redarea_ball_li" onclick="onClickRedBall(this,10)">10</li></td>
                        <td><li id="std_red_ball_11" class="redarea_ball_li" onclick="onClickRedBall(this,11)">11</li></td>
                      </tr>
                      <tr>
                        <td><li id="std_red_ball_12" class="redarea_ball_li" onclick="onClickRedBall(this,12)">12</li></td>
                        <td><li id="std_red_ball_13" class="redarea_ball_li" onclick="onClickRedBall(this,13)">13</li></td>
                        <td><li id="std_red_ball_14" class="redarea_ball_li" onclick="onClickRedBall(this,14)">14</li></td>
                        <td><li id="std_red_ball_15" class="redarea_ball_li" onclick="onClickRedBall(this,15)">15</li></td>
                        <td><li id="std_red_ball_16" class="redarea_ball_li" onclick="onClickRedBall(this,16)">16</li></td>
                        <td><li id="std_red_ball_17" class="redarea_ball_li" onclick="onClickRedBall(this,17)">17</li></td>
                        <td><li id="std_red_ball_18" class="redarea_ball_li" onclick="onClickRedBall(this,18)">18</li></td>
                        <td><li id="std_red_ball_19" class="redarea_ball_li" onclick="onClickRedBall(this,19)">19</li></td>
                        <td><li id="std_red_ball_20" class="redarea_ball_li" onclick="onClickRedBall(this,20)">20</li></td>
                        <td><li id="std_red_ball_21" class="redarea_ball_li" onclick="onClickRedBall(this,21)">21</li></td>
                        <td><li id="std_red_ball_22" class="redarea_ball_li" onclick="onClickRedBall(this,22)">22</li></td>
                      </tr>
                      <tr>
                        <td><li id="std_red_ball_23" class="redarea_ball_li" onclick="onClickRedBall(this,23)">23</li></td>
                        <td><li id="std_red_ball_24" class="redarea_ball_li" onclick="onClickRedBall(this,24)">24</li></td>
                        <td><li id="std_red_ball_25" class="redarea_ball_li" onclick="onClickRedBall(this,25)">25</li></td>
                        <td><li id="std_red_ball_26" class="redarea_ball_li" onclick="onClickRedBall(this,26)">26</li></td>
                        <td><li id="std_red_ball_27" class="redarea_ball_li" onclick="onClickRedBall(this,27)">27</li></td>
                        <td><li id="std_red_ball_28" class="redarea_ball_li" onclick="onClickRedBall(this,28)">28</li></td>
                        <td><li id="std_red_ball_29" class="redarea_ball_li" onclick="onClickRedBall(this,29)">29</li></td>
                        <td><li id="std_red_ball_30" class="redarea_ball_li" onclick="onClickRedBall(this,30)">30</li></td>
                        <td><li id="std_red_ball_31" class="redarea_ball_li" onclick="onClickRedBall(this,31)">31</li></td>
                        <td><li id="std_red_ball_32" class="redarea_ball_li" onclick="onClickRedBall(this,32)">32</li></td>
                        <td><li id="std_red_ball_33" class="redarea_ball_li" onclick="onClickRedBall(this,33)">33</li></td>
                      </tr>
                    </table>
              </div>
            </div>
            <div class="gongjubottom">
                数据提供：<a href="http://www.369cai.com/">一彩票网</a>，如意见或建议，请给我们<a href="mailto:sudongfeng@369cai.com">留言</a>。<span class="span1" onclick="tijiao()">开始旋转</span> <span class="span2" onclick="clearSelectedBalls(0)">重新选择</span> <span class="span"></span>
            </div>
        </div>
    </div>
</div>
</body>
</html>