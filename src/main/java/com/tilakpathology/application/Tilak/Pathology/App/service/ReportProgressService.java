package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.dto.ReportProgressDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.ReportProgress;

import java.util.List;
import java.util.Map;

public interface ReportProgressService {

    ReportProgress createReportProgress(ReportProgressDto reportProgress);

    List<ReportProgress> getReportProgressByPatientIds(List<String> patientIds);

    ReportProgress getReportProgressByPatientId(String patientId);

    ReportProgress updateReportProgress(String patientId, Integer progress, Map<String, String> working);
}
