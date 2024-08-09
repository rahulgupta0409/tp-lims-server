package com.tilakpathology.application.Tilak.Pathology.App.service.impl;


import com.tilakpathology.application.Tilak.Pathology.App.dao.MajorLabTestRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.MajorLabTestDto;
import com.tilakpathology.application.Tilak.Pathology.App.dto.response.MajorLabTestResponseDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.MajorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.MajorLabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class MajorLabTestServiceImpl implements MajorLabTestService {

    @Autowired
    private MajorLabTestRepository majorLabTestRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public MajorLabTest addMajorLabTest(MajorLabTestDto majorLabTestDto) {
        Float allLabTestPrice = 0.0f;

        for(int i=0; i<majorLabTestDto.getMinorLabTestList().size(); i++){
            allLabTestPrice += majorLabTestDto.getMinorLabTestList().get(i).getTestPrice();
        }
        MajorLabTest majorLabTest = MajorLabTest.builder()
                .majorTestId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .majorTestName(majorLabTestDto.getMajorTestName())
                .majorTestPrice(allLabTestPrice)
                .minorLabTestList(majorLabTestDto.getMinorLabTestList())
                .majorTestRemarks(majorLabTestDto.getMajorTestRemarks()).build();
        majorLabTestRepository.save(majorLabTest);
        return majorLabTest;
    }

    @Override
    public List<MajorLabTest> getAllMajorLabTests() {
        List<MajorLabTest> majorLabTestList = majorLabTestRepository.findAll();
        return (majorLabTestList != null) ? majorLabTestList : null;
    }

    @Override
    public Optional<MajorLabTestResponseDto> getAllMajorLabTestByTestId(String majorLabTestId) {
        MajorLabTest majorLabTest = majorLabTestRepository.getMajorTestByTestId(majorLabTestId);
        return (majorLabTest != null) ? Optional.ofNullable(MajorLabTestResponseDto.builder().majorTestId(majorLabTestId)
                .majorTestName(majorLabTest.getMajorTestName())
                .majorTestPrice(majorLabTest.getMajorTestPrice())
                .majorTestRemarks(majorLabTest.getMajorTestRemarks()).build()) : Optional.empty();
    }

    @Override
    public MajorLabTest updateMajorLabTest(MajorLabTestDto majorLabTestDto, String majorLabTestId) {
        Query query = new Query().addCriteria(Criteria.where("majorTestId").is(majorLabTestId));
        FindAndModifyOptions options = new FindAndModifyOptions().remove(true).returnNew(true);
        Update updateDefinition = new Update();
        if (majorLabTestDto.getMajorTestName() != null) {
            updateDefinition.set("majorTestName", majorLabTestDto.getMajorTestName());
        }
        if (majorLabTestDto.getMajorTestPrice() != null) {
            updateDefinition.set("majorTestPrice", majorLabTestDto.getMajorTestPrice());
        }
        if (majorLabTestDto.getMajorTestRemarks() != null) {
            updateDefinition.set("majorTestRemarks", majorLabTestDto.getMajorTestRemarks());
        }
        mongoTemplate.findAndModify(query, updateDefinition, options, MinorLabTest.class);
        return null;
    }
}
