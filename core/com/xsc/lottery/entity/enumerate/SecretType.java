package com.xsc.lottery.entity.enumerate;

public enum SecretType
{
    公开("gk"),
    开奖后公开("kj"),
    保密("bm"),
    只对("only");

    private String nikeName;

    private SecretType(String name)
    {
        this.nikeName = name;
    }

    public String getNikeName()
    {
        return nikeName;
    }

    public void setNikeName(String nikeName)
    {
        this.nikeName = nikeName;
    }

    static public SecretType getByNikeName(String nikeName)
    {
        for (SecretType type : SecretType.values()) {
            if (nikeName.equals(type.getNikeName())) {
                return type;
            }
        }
        return null;
    }
}
