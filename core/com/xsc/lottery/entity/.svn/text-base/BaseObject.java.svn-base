package com.xsc.lottery.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class BaseObject implements Serializable
{
    public abstract Long getId();

    public boolean equals(Object obj)
    {
        if(this == obj) {
            return true;
        }

        if(obj == null) {
            return false;
        }

        if(this.getClass() != obj.getClass()) {
            return false;
        }

        final BaseObject other = (BaseObject) obj;
        if(other.getId() == null && getId() == null) {
            return true;
        }

        if(other.getId() != null && getId() == null) {
            return false;
        }

        if(other.getId() == null && getId() != null) {
            return false;
        }

        if(other.getId().equals(getId())) {
            return true;
        }
        
        return false;
    }
}
