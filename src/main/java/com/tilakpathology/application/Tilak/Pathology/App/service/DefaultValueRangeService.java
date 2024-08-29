package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.DefaultValueRangeDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.DefaultValueRange;

public interface DefaultValueRangeService {

    DefaultValueRange addDefaultValueRange(DefaultValueRangeDto defaultValueRange);
}
