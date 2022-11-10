package com.example.AcmeFresh.actions;

import com.example.AcmeFresh.actions.builder.Action;
import com.example.AcmeFresh.actions.builder.ActionHandler;
import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.Customer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerLogin extends ActionHandler<Map> {

@Override
public Action handlingFor() {
        return Action.LOGIN;
        }

@Override
public Map executeAction(Map operateOn) {
        if (operateOn.isEmpty())
        throw new DataValidationException("Login credentials missing for login  ");

        Customer credentials = mapper.convertValue(operateOn.get("credentials"), Customer.class);

        return Map.of("loggedInDetails", persistenceStore.login(credentials));
        }
}
