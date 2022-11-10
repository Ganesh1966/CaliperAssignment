package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.ProductCost;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ClientProductCost extends ActionHandler<Map> {

@Override
public Action handlingFor(){
        return Action.PRODUCT_COST;
        }

@Override
public Map executeAction(Map operateOn){
        if(operateOn.isEmpty())
        throw new DataValidationException("User cannot be empty");

        ProductCost client=mapper.convertValue(operateOn.get("user"), ProductCost.class);

        return Map.of("productOrder",persistenceStore.productCost(client));

        }
}
