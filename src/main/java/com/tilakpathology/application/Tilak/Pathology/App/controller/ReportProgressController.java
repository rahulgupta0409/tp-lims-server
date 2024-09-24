package com.tilakpathology.application.Tilak.Pathology.App.controller;

import com.tilakpathology.application.Tilak.Pathology.App.model.ReportProgress;
import com.tilakpathology.application.Tilak.Pathology.App.service.ReportProgressService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reportProgress")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportProgressController {

    @Autowired
    private ReportProgressService reportProgressService;

    @PostMapping(value = "/getProgressByPatientIds")
    @Operation(summary = "Fetching report progress of given patients.")
    public ResponseEntity<List<ReportProgress>> getAllProgressByPatientIds(@RequestBody List<String> patientIds) {
        List<ReportProgress> reportProgressList = reportProgressService.getReportProgressByPatientIds(patientIds);
        return new ResponseEntity<>(reportProgressList, HttpStatus.OK);
    }

    @GetMapping(value = "/getProgressByPatientId/{patientId}")
    @Operation(summary = "Fetching report progress of given patients.")
    public ResponseEntity<ReportProgress> getAllProgressByPatientId(@PathVariable String patientId) {
        ReportProgress reportProgress = reportProgressService.getReportProgressByPatientId(patientId);
        return new ResponseEntity<>(reportProgress, HttpStatus.OK);
    }
}
