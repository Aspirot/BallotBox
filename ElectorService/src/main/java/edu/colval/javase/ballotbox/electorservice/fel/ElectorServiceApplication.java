package edu.colval.javase.ballotbox.electorservice.fel;

import edu.colval.javase.ballotbox.electorservice.api.ElectorRestService;
import edu.colval.javase.ballotbox.electorservice.bll.model.Elector;
import edu.colval.javase.ballotbox.electorservice.dal.Alwaysdata_SQL_Connector;
import edu.colval.javase.ballotbox.electorservice.dal.ElectorDAO;
import edu.colval.javase.ballotbox.electorservice.dal.IElectorDAO;
import edu.colval.javase.ballotbox.electorservice.dal.I_SQL_Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackageClasses = ElectorRestService.class)
public class ElectorServiceApplication {

    @Bean
    public WebClient createWebClient(){return WebClient.create();}

    public static void main(String[] args) {
        SpringApplication.run(ElectorServiceApplication.class, args);
        //run_usingOnlineRepository();
    }

    private static void run_usingOnlineRepository() {
        I_SQL_Connector sql_connector = new Alwaysdata_SQL_Connector();
        IElectorDAO electorDAO = new ElectorDAO(sql_connector);
        List<Elector> electors = electorDAO.getAllElectors();
        electors.stream().forEach(elector -> System.out.println(elector.toString()));
        System.out.println(electorDAO.fetchElectorById(3).toString());
    }

}
