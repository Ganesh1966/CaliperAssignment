package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.FarmCost;
import com.example.AcmeFresh.models.ProductCost;
import com.example.demo.db.jooqs.tables.Farm;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerFarmCost extends ActionHandler<Map> {

    @Override
    public Action handlingFor(){
        return Action.FARM_COST;
    }

    @Override
    public Map executeAction(Map operateOn){
        if(operateOn.isEmpty())
            throw new DataValidationException("User cannot be empty");

        FarmCost client=mapper.convertValue(operateOn.get("user"), FarmCost.class);

        return Map.of("productOrder",persistenceStore.farmCost(client));
    }
}
