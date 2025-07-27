package com.Satisfyre.app.notification;


public interface NotificationService {
    void sendVerificationEmail(String to, String name, String link);
    void sendSms();
    void sendWhatsapp();
}
