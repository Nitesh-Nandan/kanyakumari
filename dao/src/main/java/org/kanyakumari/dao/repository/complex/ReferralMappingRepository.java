package org.kanyakumari.dao.repository.complex;

import org.kanyakumari.dao.entity.complex.ReferralMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReferralMappingRepository extends CrudRepository<ReferralMapping, Long> {

    @Query(value = "SELECT * FROM referral_maping WHERE id = :id", nativeQuery = true)
    List<ReferralMapping> getByRefreeId(Long id);
}
