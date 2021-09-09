package org.kanyakumari.dao.service;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.service.onetomany.OneToManyService;
import org.kanyakumari.dao.service.onetoone.OneToOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyDaoService {

    @Autowired
    private OneToOneService oneToOneService;

    @Autowired
    private OneToManyService oneToManyService;


    public void main() {
//        oneToOneService.main();
        oneToManyService.main();
    }

}
