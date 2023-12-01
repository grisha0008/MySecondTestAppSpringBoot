package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface UnsupportedService {
    void unsupported(Request request) throws UnsupportedCodeException;
}
