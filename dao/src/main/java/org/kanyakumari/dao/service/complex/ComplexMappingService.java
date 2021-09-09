package org.kanyakumari.dao.service.complex;

import lombok.extern.slf4j.Slf4j;
import org.kanyakumari.dao.entity.complex.ReferralMapping;
import org.kanyakumari.dao.entity.complex.ReferralUser;
import org.kanyakumari.dao.repository.complex.ReferralMappingRepository;
import org.kanyakumari.dao.repository.complex.ReferralUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ComplexMappingService {

    @Autowired
    private ReferralUserRepository referralUserRepository;

    @Autowired
    private ReferralMappingRepository referralMappingRepository;

    public void main() {
        create();
        query();
        query2();
        query3();
    }

    private void create() {
        log.info("Create started ............................");

        ReferralUser r1 = ReferralUser.builder().name("Nitesh").build();
        ReferralUser r2 = ReferralUser.builder().name("Raju").build();
        ReferralUser r3 = ReferralUser.builder().name("Rahul").build();

        referralUserRepository.save(r1);
        referralUserRepository.save(r2);
        referralUserRepository.save(r3);

        List<ReferralUser> referralUsers = new ArrayList<>();
        referralUsers.add(r2);
        referralUsers.add(r3);
        r1.setReferralUser(referralUsers);

        referralUserRepository.save(r1);

        log.info("Create Finished ............................");
    }

    private void query() {

        log.info("Query 1....................");

        Optional<ReferralUser> optionalUser = referralUserRepository.findById(1L);
        if (optionalUser.isPresent()) {
            ReferralUser referralUser = optionalUser.get();
            System.out.println("Id: " + referralUser.getId());
            System.out.println("Name: " + referralUser.getName());
            List<ReferralUser> referred = referralUser.getReferralUser();
            System.out.println("Referred user .................");
            referred.stream().forEach(ele -> System.out.println("User: " + ele.getName()));

        }

    }

    private void query2() {
        log.info("Query 2....................");

        Optional<ReferralMapping> mapping = referralMappingRepository.findById(2L);
        if (mapping.isPresent()) {
            System.out.println("Id: " + mapping.get().getId());
            System.out.println("Refree: " + mapping.get().getRefreeUser());
            System.out.println("Referral: " + mapping.get().getReferredUser());
            System.out.println("Active: " + mapping.get().getIsActive());
        }
    }

    private void query3() {
        log.info("Query 3....................");

        List<ReferralMapping> lists = referralMappingRepository.getByRefreeId(1L);
        lists.stream().forEach(et -> {
            System.out.println("Id: " + et.getId());
            System.out.println("Refree: " + et.getRefreeUser());
            System.out.println("Referral: " + et.getReferredUser());
            System.out.println("Active: " + et.getIsActive());
        });
    }

}
