package com.gmail.liliyayalovchenko.web;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.liliyayalovchenko.domain.Menu;
import com.gmail.liliyayalovchenko.service.MenuService;
import com.gmail.liliyayalovchenko.web.JSON_View.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuAPI {

    private MenuService menuService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MenuAPI.class);

    /**
     * Method returns a list of all the menu
     * (the menu names only, without the dishes in them)
     * **/
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public List<Menu> menu() {
        LOGGER.info("Try to get all menu names");
        List<Menu> menuNames = menuService.getAllMenus();
        LOGGER.info("All menu names are got");
        return menuNames;
    }

    /**
     * Method that returns a menu by id with
     * a list of dishes in it
     * **/
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    public Menu menu(@PathVariable int id) {
        LOGGER.info("Try to get menu by id with dishes in it");
        Menu menu = menuService.getMenuById(id);
        LOGGER.info("Menu by id is got");
        return menu;
    }


    /**
     * Method that returns a menu by name
     * full menu instance(with dish list, name and id)
     * **/
    @RequestMapping(value = "/menu/name/{name}", method = RequestMethod.GET)
    public Menu menu(@PathVariable String name) {
        LOGGER.info("Try to get menu by name " + name + " with dishes in it");
        Menu menu = menuService.getMenuByName(name);
        LOGGER.info("Menu by name " + name + " is got");
        return menu;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
