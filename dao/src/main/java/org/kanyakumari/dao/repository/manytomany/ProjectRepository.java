package org.kanyakumari.dao.repository.manytomany;

import org.kanyakumari.dao.entity.manytomany.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
