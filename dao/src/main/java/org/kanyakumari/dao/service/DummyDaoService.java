package org.kanyakumari.dao.service;

import org.kanyakumari.dao.entity.Test;
import org.kanyakumari.dao.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyDaoService {

    @Autowired
    private TestRepository repository;

    public void create() {
        Test test = Test.builder()
                .id(13L)
                .name("Hello")
                .build();

        repository.save(test);
    }
}
