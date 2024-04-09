package com.xs.assistant.encryption.Service;

import org.springframework.stereotype.Service;

@Service
public interface EncryptionAlgorithm {
    String encryption(String pass);
    Boolean encodedEquals(String pass,String encodedPass);
}
