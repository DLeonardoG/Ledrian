package com.campus.ledrian;

import java.util.Collections;

import com.campus.ledrian.typeinteration.domain.TypeInterationDTO;
import com.campus.ledrian.typeinteration.service.TypeInterationServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LedrianApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LedrianApplication.class, args);

        TypeInterationServiceImpl typeInterationService = context.getBean(TypeInterationServiceImpl.class);

        TypeInterationDTO typeInterationDTO1 = new TypeInterationDTO();
        typeInterationDTO1.setType("Like");        TypeInterationDTO typeInterationDTO2 = new TypeInterationDTO();
        typeInterationDTO2.setType("Comment");

        typeInterationService.save(typeInterationDTO1);
        typeInterationService.save(typeInterationDTO2);
    }

}
