package org.kanyakumari.dao.service;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.entity.Test;
import org.kanyakumari.dao.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyDaoService {

    @Autowired
    private TestRepository repository;

    public void main() {

    }

    public void oneToOne() {

    }

    public void oneToOneBi() {

    }

    public void OneToMany() {

    }
}
