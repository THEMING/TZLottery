package com.xsc.lottery.task.ticket;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.xsc.lottery.entity.enumerate.SendTicketPlat;

/**
 * @description factory for interface of sending tickets
 */
@Component
public class TicketBusinessFactory
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<SendTicketPlat, TicketTreatmentWork> treatmentMap = 
        new HashMap<SendTicketPlat, TicketTreatmentWork>(2, 1);

    public void registerTreatmentTicketInMap(TicketTreatmentWork newttw)
    {
        logger.info(newttw.getTicketPlat().name() + " registered...");
        treatmentMap.put(newttw.getTicketPlat(), newttw);
    }

    public TicketTreatmentWork getTreatmentTicketByType(SendTicketPlat key)
    {
        return treatmentMap.get(key);
    }

    public Map<SendTicketPlat, TicketTreatmentWork> getTreatmentMap()
    {
        return treatmentMap;
    }
}
