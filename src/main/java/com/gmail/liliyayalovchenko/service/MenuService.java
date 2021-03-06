package com.gmail.liliyayalovchenko.service;

import com.gmail.liliyayalovchenko.dao.MenuDAO;
import com.gmail.liliyayalovchenko.domain.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MenuService {

    @Autowired
    private MenuDAO menuDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Transactional
    public List<Menu> getAllMenus() {
        LOGGER.info("Try to get all menus");
        List<Menu> menus = menuDAO.getAllMenu();
        LOGGER.info("All menus are got");
        return menus;
    }

    @Transactional
    public Menu getMenuByName(String menuName) {
        return menuDAO.getMenuByName(menuName);
    }

    @Transactional
    public Menu getMenuById(int id) {
        LOGGER.info("Try to get menu by id");
        Menu menu = menuDAO.getMenuById(id);
        LOGGER.info("Menu is got");
        return menu;
    }
}
