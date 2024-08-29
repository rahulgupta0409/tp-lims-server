package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.DefaultValueRangeRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dao.MinorLabTestRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.DefaultValueRangeDto;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.BadRequestException;
import com.tilakpathology.application.Tilak.Pathology.App.exceptions.type.ResourceNotFoundException;
import com.tilakpathology.application.Tilak.Pathology.App.model.DefaultValueRange;
import com.tilakpathology.application.Tilak.Pathology.App.model.MinorLabTest;
import com.tilakpathology.application.Tilak.Pathology.App.service.DefaultValueRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;


@Service
public class DefaultValueRangeServiceImpl implements DefaultValueRangeService {

    @Autowired
    private MinorLabTestRepository minorLabTestRepository;

    @Autowired
    private DefaultValueRangeRepository defaultValueRangeRepository;

    @Override
    public DefaultValueRange addDefaultValueRange(DefaultValueRangeDto defaultValueRange) {
        MinorLabTest minorLabTest = minorLabTestRepository.getTestByTestId(defaultValueRange.getTestId());
        if(minorLabTest != null){
            DefaultValueRange defaultValueRangeSetter = DefaultValueRange.builder()
                    .defaultValueRangeId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                    .testId(defaultValueRange.getTestId())
                    .minAge(defaultValueRange.getMinAge())
                    .maxAge(defaultValueRange.getMaxAge())
                    .defaultValue(defaultValueRange.getDefaultValue())
                    .defaultRemark(defaultValueRange.getDefaultRemark())
                    .build();
            defaultValueRangeRepository.save(defaultValueRangeSetter);
            return defaultValueRangeSetter;
        }else {
            throw new ResourceNotFoundException("The minor Test not found");
        }


    }
}
