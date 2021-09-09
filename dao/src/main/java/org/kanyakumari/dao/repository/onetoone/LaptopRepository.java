package org.kanyakumari.dao.repository.onetoone;

import org.kanyakumari.dao.entity.onetoone.Laptop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends CrudRepository<Laptop, Long> {

}
