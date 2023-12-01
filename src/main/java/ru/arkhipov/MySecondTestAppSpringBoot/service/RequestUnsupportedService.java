package ru.arkhipov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.Request;

@Service
public class RequestUnsupportedService implements UnsupportedService {
    @Override
    public void unsupported(Request request) throws UnsupportedCodeException {
        if (request.getUid().equals("123")) {
            throw new UnsupportedCodeException();
        }
    }
}
