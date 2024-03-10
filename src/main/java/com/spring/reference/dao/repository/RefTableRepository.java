package com.spring.reference.dao.repository;

import com.spring.reference.model.RefTable;
import com.spring.reference.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefTableRepository extends JpaRepository<RefTable, Integer> {

    // Retrieve users by Id
    public Optional<RefTable> findById(Integer id);

    // Retrieve entities by a list of IDs
    @Query("SELECT r FROM RefTable r WHERE r.refId IN :ids")
    Optional<List<RefTable>> findByIds(@Param("ids") List<Integer> ids);
}
