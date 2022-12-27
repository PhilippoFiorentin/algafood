package com.philippo.algafood.domain.repository;

import com.philippo.algafood.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
