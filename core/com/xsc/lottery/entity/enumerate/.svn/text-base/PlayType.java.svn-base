package com.xsc.lottery.entity.enumerate;

public enum PlayType
{
    ds("标准单式"), fs("标准复式"), dt("胆拖"), dlt_sxlds("生肖乐单式"), dlt_sxlfs("生肖乐复式"), zx_hz(
            "直选和值"), zs_ds("组三单式"), zs_fs("组三复式"), zs_bh("组三包号"), zs_hz("组三和值"), zl_ds(
            "组六单式"), zl_fs("组六复式"), zl_bh("组六包号"), zl_hz("组六和值"), pt_5("五星"), pt_3(
            "三星"), pt_2("二星"), pt_1("一星"), pt_dx("大小单双"), tx_5("五星通选"), hz_3(
            "三星和值"), hz_2("二星和值"), zs_2("二星组选"), zs_hz_2("二星组选和值"), q2("前二"), h2(
            "后二"), q1("前一"), h1("后一"), SPF("胜平负"), JQS("进球数"), CBF("猜比分"), BQC("半全场"),
            SF("胜负"), RFSF("让分胜负"), SFC("胜分差"), DXF("大小分"), q1_zhix("前一直选"), rx_2("任选二"),
            rx_3("任选三"), rx_4("任选四"), rx_5("任选五"), rx_6("任选六"), rx_7("任选七"), rx_8("任选八"),
            q2_zhix("前二直选"), q2_zux("前二组选"), q3_zhix("前三直选"), q3_zux("前三组选"),RQSPF("让球胜平负"),
            th("同花"),hs("同花顺"),sz("顺子"),bz("豹子"),dz("对子"),bx("快乐扑克3包选"),hz("和值"),sth_ds("三同号单式"),
            sth_tx("三同号通选"),eth_ds("二同号单式"),eth_fs("二同号复式"),sbt_ds("三不同号"),ebt_ds("二不同号"),slh_tx("三连号通选"),
            JZ_HH("竞足混合过关"), JL_HH("竞篮混合过关")
    ;

    private String nick_ne;

    private PlayType(String nick)
    {
        nick_ne = nick;
    }

    public String getNick_ne()
    {
        return nick_ne;
    }

    public void setNick_ne(String nick_ne)
    {
        this.nick_ne = nick_ne;
    }

    static public PlayType neToType(String ne)
    {
        for (PlayType type : PlayType.values()) {
            if (type.getNick_ne().equals(ne)) {
                return type;
            }
        }
        return null;
    }
}
