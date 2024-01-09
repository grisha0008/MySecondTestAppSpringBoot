package ru.arkhipov.MySecondTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpringBoot.model.*;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.UnsupportedService;
import ru.arkhipov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.arkhipov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedService unsupportedService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedService unsupportedService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.unsupportedService = unsupportedService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

            try {
                validationService.isValid(bindingResult);
                unsupportedService.unsupported(request);
            }
            catch (ValidationFailedException e) {
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
                response.setErrorMessage(ErrorMessages.VALIDATION);
                log.info("response {}", response);
                log.error("ValidationFailedException", e);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            } catch (UnsupportedCodeException e) {
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
                response.setErrorMessage(ErrorMessages.VALIDATION);
                log.info("response {}", response);
                log.error("UnsupportedCodeException", e);
                System.err.println(e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            } catch (Exception e) {
                response.setCode(Codes.FAILED);
                response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTIONS);
                response.setErrorMessage(ErrorMessages.UNKNOWN);
                log.info("response {}", response);
                log.error("Exception", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            modifyResponseService.modify(response);
        log.info("request {}", request);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);

    }
}
