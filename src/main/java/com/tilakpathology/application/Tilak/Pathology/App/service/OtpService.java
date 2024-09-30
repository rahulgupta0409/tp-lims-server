package com.tilakpathology.application.Tilak.Pathology.App.service;

import com.tilakpathology.application.Tilak.Pathology.App.model.Otp;

public interface OtpService {

    Otp generateOtp(String email);
}
