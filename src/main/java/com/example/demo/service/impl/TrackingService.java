package com.example.demo.service.impl;


import com.example.demo.dto.UserInteractionDto;
import com.example.demo.model.UserInteractionDocument;
import com.example.demo.model.UserInteractionEntity;
import com.example.demo.repository.UserInteractionRepository;
import com.example.demo.repository.UserInteractionSearchRepository;
import com.example.demo.utils.UserInteractionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TrackingService {
    private final UserInteractionRepository jpaRepo;
    private final UserInteractionSearchRepository esRepo;
    private final Logger log = LoggerFactory.getLogger(TrackingService.class);

    public TrackingService(UserInteractionRepository jpaRepo, UserInteractionSearchRepository esRepo) {
        this.jpaRepo = jpaRepo;
        this.esRepo = esRepo;
    }

    /**
     * Save canonical copy to Postgres and index to Elasticsearch.
     * Transaction applies to JPA only — ES indexing is best-effort: failure won't roll back DB by default.
     */
    @Transactional
    public String track(UserInteractionDto dto) {
        UserInteractionEntity entity = UserInteractionMapper.dtoToEntity(dto);
        jpaRepo.save(entity);

        // index to ES (best effort) - consider async retry in production
        try {
            UserInteractionDocument doc = UserInteractionMapper.entityToDocument(entity);
            esRepo.save(doc);
        } catch (Exception e) {
            log.error("Failed to index to Elasticsearch, continuing. error={}", e.getMessage());
        }

        return entity.getId();
    }
}

