package com.example.AcmeFresh.db;

import com.example.AcmeFresh.models.*;

public interface PersistanceStore {
    String addcustomer(Customer customer);

    String removeCustomer(Customer customer);

    String updateCustomer(Customer customer);

    CustomerView login(Customer credentials);

    String addclient(Client client);

    String removeclient(Client client);

    String updateclient(Client client);

    String orderClient(order client);

    String orderCustomer(order customer);

    int productCost(ProductCost client);

    int farmCost(FarmCost client);
}
