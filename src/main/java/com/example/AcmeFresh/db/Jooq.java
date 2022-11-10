package com.example.AcmeFresh.db;

import com.example.AcmeFresh.exceptions.DataValidationException;
import com.example.AcmeFresh.models.*;
import com.example.demo.db.jooqs.Tables;
import com.example.demo.db.jooqs.tables.Farm;
import com.example.demo.db.jooqs.tables.OrderFarm;
import com.example.demo.db.jooqs.tables.records.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.impl.DSL;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.jooq.impl.DSL.sum;

@Component
public class Jooq implements PersistanceStore{

    private DSLContext jooqContext;

    @PostConstruct
    private void createJooqContext() {
        try {

            Dotenv dotenv = Dotenv
                    .configure()
                    .ignoreIfMissing()
                    .load();

            this.jooqContext = DSL.using(
                    dotenv.get("MYSQL_URL"),
                    dotenv.get("MYSQL_USERNAME"),
                    dotenv.get("MYSQL_PASSWORD")
            );
        } catch (Exception e) {

            throw e;
        }
    }

    private DSLContext liveContext(){
        this.jooqContext.connection((connection -> {
            if (connection.isClosed()){
                createJooqContext();
            }
        }));

        return this.jooqContext;
    }

    private String generateHashedPassword(String originalPassword) {
        return Hashing.sha256()
                .hashString(originalPassword + "wsedrftgyhjvhgc", StandardCharsets.UTF_8)
                .toString();
    }

    @Override
    public String addcustomer(Customer customer) {

        CustomerRecord customerRecord=liveContext().newRecord(Tables.CUSTOMER);
        customerRecord.setName(customer.getName());
        customerRecord.setAddress(customer.getAddress());
        customerRecord.setPassword(String.format("p:%s", generateHashedPassword(customer.getPassword())));
        customerRecord.setPhonenumber(customer.getPhoneNumber());
        customerRecord.setMailid(customer.getEmailId());
        customerRecord.setUpdatedAt(LocalDateTime.now());
        customerRecord.store();
        return "Customer added successfully";

    }

    @Override
    public String removeCustomer(Customer customer) {
        liveContext().deleteFrom(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.MAILID.eq(customer.getEmailId()))
                .execute();

        return "Customer removed successfully";

    }

    @Override
    public String updateCustomer(Customer customer) {

        liveContext().update(Tables.CUSTOMER)
                .set(Tables.CUSTOMER.NAME, customer.getName())
                .set(Tables.CUSTOMER.MAILID,customer.getEmailId())
                .set(Tables.CUSTOMER.PHONENUMBER,customer.getPhoneNumber())
                .set(Tables.CUSTOMER.UPDATED_AT, LocalDateTime.now())
                .where(Tables.CUSTOMER.ID.eq(customer.getId()))
                .execute();

        return "Customer details updated successfully";
    }

    @Override
    public CustomerView login(Customer credentials) {
        String hashedPassword = generateHashedPassword(credentials.getPassword());
        return liveContext().selectFrom(Tables.CUSTOMER)
                .where(Tables.CUSTOMER.MAILID.eq(credentials.getEmailId()))
                .and(Tables.CUSTOMER.PASSWORD.eq(String.format("p:%s", hashedPassword)))
                .stream()
                .map(userRecord -> {
                    CustomerView customerView = new CustomerView();
                    customerView.setAddress(userRecord.getAddress());
                    customerView.setEmailId(userRecord.getMailid());
                    customerView.setPhoneNumber(userRecord.getPhonenumber());
                    customerView.setName(userRecord.getName());
                    return customerView;
                })
                .findFirst()
                .orElseThrow(() -> new DataValidationException("Password is not correct"));
    }

    @Override
    public String addclient(Client customer) {
        ClientRecord customerRecord=liveContext().newRecord(Tables.CLIENT);
        customerRecord.setName(customer.getName());
        customerRecord.setAddress(customer.getAddress());
        customerRecord.setPassword(String.format("p:%s", generateHashedPassword(customer.getPassword())));
        customerRecord.setPhonenumber(customer.getPhoneNumber());
        customerRecord.setMailid(customer.getEmailId());
        customerRecord.setUpdatedAt(LocalDateTime.now());
        customerRecord.store();
        return "Client added successfully";
    }

    @Override
    public String removeclient(Client client) {
        liveContext().deleteFrom(Tables.CLIENT)
                .where(Tables.CLIENT.MAILID.eq(client.getEmailId()))
                .execute();

        return "Client removed successfully";
    }

    @Override
    public String updateclient(Client customer) {
        liveContext().update(Tables.CLIENT)
                .set(Tables.CLIENT.NAME, customer.getName())
                .set(Tables.CLIENT.MAILID,customer.getEmailId())
                .set(Tables.CLIENT.PHONENUMBER,customer.getPhoneNumber())
                .set(Tables.CLIENT.UPDATED_AT, LocalDateTime.now())
                .where(Tables.CLIENT.ID.eq(customer.getId()))
                .execute();

        return "Client details updated successfully";
    }

    @Override
    public String orderClient(order client) {

        OrderProductRecord orderProduct=liveContext().newRecord(Tables.ORDER_PRODUCT);
        for(UUID uuid:client.getFarmList()){
            orderProduct.setProductId(uuid);
            orderProduct.store();
        }

        return "client order completed successfully";
    }

    @Override
    public String orderCustomer(order customer) {

        OrderFarmRecord orderFarm= liveContext().newRecord(Tables.ORDER_FARM);

        for(UUID uuid:customer.getFarmList()){
            orderFarm.setFarmId(uuid);
            orderFarm.store();
        }


        return "client order completed successfully";
    }

    @Override
    public int productCost(ProductCost client) {

       Integer record1=(Integer) liveContext().select(sum(Tables.PRODUCT.COST)).
                from(Tables.CLIENT)
                .join(Tables.ORDER_CLIENT)
                .on(Tables.CLIENT.ID.eq(Tables.ORDER_CLIENT.CLIENT_ID))
                .join(Tables.ORDER_PRODUCT)
                .on(Tables.ORDER_PRODUCT.ORDER_ID.eq(Tables.ORDER_CLIENT.ORDER_ID))
                .join(Tables.PRODUCT)
                .on(Tables.ORDER_PRODUCT.PRODUCT_ID.eq(Tables.PRODUCT.ID))
                .execute();
        return (int)record1;
    }

    @Override
    public int farmCost(FarmCost client) {
        Integer record1=(Integer) liveContext().select(sum(Tables.FARM.COST)).
                from(Tables.CUSTOMER)
                .join(Tables.ORDER_CUSTOMER)
                .on(Tables.CUSTOMER.ID.eq(Tables.ORDER_CUSTOMER.CUSTOMER_ID))
                .join(Tables.ORDER_FARM)
                .on(Tables.ORDER_FARM.ORDER_ID.eq(Tables.ORDER_CUSTOMER.ORDER_ID))
                .join(Tables.FARM)
                .on(Tables.ORDER_FARM.FARM_ID.eq(Tables.FARM.ID))
                .execute();
        return (int)record1;
    }


}

