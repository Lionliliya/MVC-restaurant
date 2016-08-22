package com.gmail.liliyayalovchenko.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Waiter extends Employee {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    //@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter {\n").append(" id = ").append(getId()).append("\n").
                append(" name = ").append(getFirstName()).append(" ").append(getSecondName()).append("\n").
                append(" orders = {\n");
        orderList.forEach(order -> sb.append("   ").append(order).append(",\n"));
        sb.append("}\n").append(" }");
        return sb.toString();
    }
}
