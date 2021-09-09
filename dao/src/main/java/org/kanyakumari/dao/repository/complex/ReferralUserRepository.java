package org.kanyakumari.dao.repository.complex;


import org.kanyakumari.dao.entity.complex.ReferralUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferralUserRepository extends CrudRepository<ReferralUser, Long> {
}
