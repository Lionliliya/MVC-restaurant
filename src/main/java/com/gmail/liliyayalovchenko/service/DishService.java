package com.gmail.liliyayalovchenko.service;

import com.gmail.liliyayalovchenko.dao.DishDAO;
import com.gmail.liliyayalovchenko.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DishService {

    private DishDAO dishDAO;

    @Transactional
    public Dish getDishByName(String dishName) {
        return dishDAO.getDishByName(dishName);
    }


    @Autowired
    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }
}
