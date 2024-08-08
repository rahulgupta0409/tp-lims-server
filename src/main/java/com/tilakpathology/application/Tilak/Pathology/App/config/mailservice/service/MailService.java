package com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.service;


import com.tilakpathology.application.Tilak.Pathology.App.config.mailservice.model.Mail;

public interface MailService
{
    public void sendEmail(Mail mail);
}