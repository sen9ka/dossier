package ru.senya.dossier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.senya.dossier.entity.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
