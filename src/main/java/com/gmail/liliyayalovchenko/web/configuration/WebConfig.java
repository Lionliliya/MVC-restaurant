package com.gmail.liliyayalovchenko.web.configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gmail.liliyayalovchenko.dao.*;
import com.gmail.liliyayalovchenko.dao.hibernate.*;
import com.gmail.liliyayalovchenko.service.DishService;
import com.gmail.liliyayalovchenko.service.EmployeeService;
import com.gmail.liliyayalovchenko.service.MenuService;
import com.gmail.liliyayalovchenko.service.OrderService;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.beans.PropertyVetoException;
import java.util.List;
import java.util.Properties;

@Configuration
@ComponentScan("com.gmail.liliyayalovchenko")
@EnableWebMvc
@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }

    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public MenuService menuService() {
        return new MenuService();
    }

    @Bean
    public DishService dishService() {
        return new DishService();
    }

    @Bean
    public EmployeeDAO employeeDAO() {
        return new EmployeeDAOImpl();
    }

    @Bean
    public DishDAO dishDAO() {
        return new DishDAOImpl();
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAOImpl();
    }

    @Bean
    public MenuDAO menuDAO() {
        return new MenuDAOImpl();
    }

    @Bean
    public IngredientDAO ingredientDAO() {
        return  new IngredientDAOImpl();
    }


    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(List<HttpMessageConverter<?>> converters){
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);
        converters.add(messageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        adapter.setMessageConverters(converters);
        return adapter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        messageConverter.setObjectMapper(objectMapper());
        return messageConverter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        return objectMapper;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("/WEB-INF/views/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean
    public ComboPooledDataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/RestaurantCRM?useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword("123_lion_123");
            dataSource.setMinPoolSize(1);
            dataSource.setMaxPoolSize(10);
            dataSource.setAcquireIncrement(1);

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.gmail.liliyayalovchenko.domain");
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        sessionFactory.setHibernateProperties(properties);

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager tx() {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(sessionFactory().getObject());
        return tx;
    }

}
