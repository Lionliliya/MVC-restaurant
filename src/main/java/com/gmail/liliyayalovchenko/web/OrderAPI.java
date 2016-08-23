package com.gmail.liliyayalovchenko.web;

import com.gmail.liliyayalovchenko.domain.Order;
import com.gmail.liliyayalovchenko.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderAPI {

    private OrderService orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderAPI.class);

    /**
     * Method returns a list of all the orders
     **/
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> allOrders() {
        LOGGER.info("Try to get all orders");
        List<Order> orders = orderService.getAllOrders();
        LOGGER.info("All orders are got");
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> orderById(@PathVariable int id) {
        LOGGER.info("Try to get order by id");
        Order order = orderService.getOrderById(id);
        LOGGER.info("Order by id is got");
        if (order == null) {
            System.out.println("Order with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/status/open", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> allOpenOrders() {
        LOGGER.info("Try to get all open orders");
        List<Order> orders = orderService.getAllOpenOrders();
        LOGGER.info("All open orders are got");
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/status/closed", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> allClosedOrders() {
        LOGGER.info("Try to get all closed orders");
        List<Order> orders = orderService.getAllClosedOrders();
        LOGGER.info("All closed orders are got");
        if(orders.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
