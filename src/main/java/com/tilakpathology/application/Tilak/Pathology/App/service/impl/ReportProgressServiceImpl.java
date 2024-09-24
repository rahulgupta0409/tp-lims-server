package com.tilakpathology.application.Tilak.Pathology.App.service.impl;

import com.tilakpathology.application.Tilak.Pathology.App.dao.ReportProgressRepository;
import com.tilakpathology.application.Tilak.Pathology.App.dto.ReportProgressDto;
import com.tilakpathology.application.Tilak.Pathology.App.model.ReportProgress;
import com.tilakpathology.application.Tilak.Pathology.App.service.ReportProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.tilakpathology.application.Tilak.Pathology.App.constants.IntegerConstants.SEVEN;

@Service
public class ReportProgressServiceImpl implements ReportProgressService {

    @Autowired
    private ReportProgressRepository reportProgressRepository;

    @Override
    public ReportProgress createReportProgress(ReportProgressDto reportProgress) {
        ReportProgress progress = ReportProgress.builder()
                .progressId(UUID.randomUUID() + UUID.randomUUID().toString().substring(SEVEN))
                .progress(reportProgress.getProgress())
                .patientId(reportProgress.getPatientId())
                .working(reportProgress.getWorking())
                .createdDate(LocalDateTime.now().toString()).build();
        reportProgressRepository.save(progress);
        return progress;
    }

    @Override
    public List<ReportProgress> getReportProgressByPatientIds(List<String> patientIds) {
        List<ReportProgress> reportProgressList = reportProgressRepository.findReportProgressByPatientIds(patientIds);
        return reportProgressList;
    }

    @Override
    public ReportProgress getReportProgressByPatientId(String patientId) {
        ReportProgress reportProgress = reportProgressRepository.findByPatientId(patientId);
        return reportProgress;
    }

    @Override
    public ReportProgress updateReportProgress(String patientId, Integer progress, Map<String, String> working) {
        return null;
    }


}
