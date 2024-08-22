package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.MinorLabTestRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MinorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MinorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.ResourceNotFoundException;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MinorLabTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.DBConstants.MINOR_LAB_TEST_ID;
import static com.tilakpathology.application.Tilak.Pathology.App.constants.DBConstants.MINOR_TEST_COLLECTION;
import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;


@Service
public class MinorLabTestServiceImpl implements MinorLabTestService {

    private final Logger log = LoggerFactory.getLogger(MinorLabTestServiceImpl.class);

    @Autowired
    private MinorLabTestRepository minorLabTestRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public MinorLabTest addMinorLabTest(MinorLabTestDto minorLabTestDto) {
        MinorLabTest minorLabTest = MinorLabTest.builder()
                .testId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .testName(minorLabTestDto.getTestName())
                .testPrice(minorLabTestDto.getTestPrice())
                .minorTestUnit(minorLabTestDto.getMinorTestUnit())
                .remarks(minorLabTestDto.getRemarks())
                .createdOn(LocalDateTime.now().toString())
                .build();
        minorLabTestRepository.save(minorLabTest);

        log.info("Minor Test Created Successfully with testId: {}", minorLabTest.getTestId() );

        return minorLabTest;
    }

    @Override
    public List<MinorLabTest> getAllMinorLabTests() {
        Sort sort = Sort.by(Sort.Order.desc("createdOn"));
        List<MinorLabTest> minorLabTest = minorLabTestRepository.findAll(sort);
        log.info("Fetched All List of Minor Lab Tests");
        return minorLabTest;
    }

    @Override
    public Optional<MinorLabTestResponseDto> getAllMinorLabTestByTestId(String testId) {
        MinorLabTest minorLabTest = minorLabTestRepository.getTestByTestId(testId);
        if (minorLabTest != null){
            return Optional.ofNullable(MinorLabTestResponseDto.builder()
                    .testId(minorLabTest.getTestId())
                    .testName(minorLabTest.getTestName())
                    .testPrice(minorLabTest.getTestPrice())
                    .remarks(minorLabTest.getRemarks())
                    .build());
        }
        log.info("Test Not Found for TestId: {}", testId);
        throw new ResourceNotFoundException("Test Not Found");
    }

    @Override
    public MinorLabTest updateMinorLabTest(MinorLabTestDto minorLabTestDto, String minorLabTestId) {
        Query query = new Query().addCriteria(Criteria.where("testId").is(minorLabTestId));
        FindAndModifyOptions options = new FindAndModifyOptions().remove(true).returnNew(true);
        Update updateDefinition = new Update();
        if (minorLabTestDto.getTestName() != null) {
            updateDefinition.set("testName", minorLabTestDto.getTestName());
        }
        if (minorLabTestDto.getTestPrice() != null) {
            updateDefinition.set("testPrice", minorLabTestDto.getTestPrice());
        }
        if(minorLabTestDto.getMinorTestUnit() != null){
            updateDefinition.set("minorLabTestUnit", minorLabTestDto.getMinorTestUnit());
        }
        if (minorLabTestDto.getRemarks() != null) {
            updateDefinition.set("remarks", minorLabTestDto.getRemarks());
        }
        mongoTemplate.findAndModify(query, updateDefinition, options, MinorLabTest.class);
        return null;
    }

    @Override
    public void deleteMinorLabTestById(String testId) {
        Query query = new Query(Criteria.where(MINOR_LAB_TEST_ID).is(testId));

        mongoTemplate.remove(query, MINOR_TEST_COLLECTION);
//        log.info("query",query);
    }
}
