package com.example.emailwriter.models.Requests;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
