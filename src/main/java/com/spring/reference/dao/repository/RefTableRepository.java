package com.spring.reference.dao.repository;

import com.spring.reference.model.RefTable;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefTableRepository extends JpaRepository<RefTable, Integer> {

    // Retrieve users by Id
    Optional<RefTable> findById(Integer id);

    // Retrieve entities by a list of IDs
    @Query("SELECT r FROM RefTable r WHERE r.refId IN :ids")
    Optional<List<RefTable>> findByIds(@Param("ids") List<Integer> ids);
}
