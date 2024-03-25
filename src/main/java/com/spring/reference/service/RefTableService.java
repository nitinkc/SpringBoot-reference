package com.spring.reference.service;

import static com.spring.reference.utils.TimerUtility.*;

import com.spring.reference.dao.repository.RefTableRepository;
import com.spring.reference.dto.RefTableDTO;
import com.spring.reference.exception.business.BadInputException;
import com.spring.reference.model.RefTable;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RefTableService {

   private RefTableRepository refTableRepository;

    @Cacheable(value = "refTableCache - findById", key = "#id")
    public RefTableDTO findById(Integer id) {

        log.info("Fetching RefTable with id {} from the cache...", id);

        startTimer();
        Optional<RefTable> refData = refTableRepository.findById(id);
        stopTimer();

        RefTable refTable = refData.orElseThrow(
                () -> new BadInputException("Ref Data with id " + id + " is not found"));

        log.info("RefTable with id {} was not found in the cache, fetched from the database.", id);
        return RefTableDTO.builder()
                .id(refTable.getRefId())
                .name(refTable.getRefName())
                .build();
    }


    @Cacheable("RefTable-findByIds")
    public List<RefTableDTO> findByIds(List<Integer> refIds) {
        startTimer();
        Optional<List<RefTable>> refTableListOptional = refTableRepository.findByIds(refIds);
        stopTimer();
        List<RefTable> refTableList = refTableListOptional.orElseThrow(() ->
                new BadInputException("User with id is not found"));

        // Map RefTable entities to RefTableDTO objects
        List<RefTableDTO> refTableDTOList = refTableList.stream()
                .map(refTable -> RefTableDTO.builder()
                        .id(refTable.getRefId())
                        .name(refTable.getRefName())
                        .build())
                .toList();

        return refTableDTOList;
    }
}
