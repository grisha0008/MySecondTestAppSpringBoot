package ru.arkhipov.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Request {
    @NotBlank(message = "поле не должно быть пустым")
    @Size(max = 32 , message = "поле uid не больше 32 символа")
    private String uid;
    @NotBlank(message = "поле не должно быть пустым")
    @Size(max = 32 , message = "поле operationUID не больше 32 символа")
    private String operationUid;
    private String systemName;
    @NotBlank(message = "поле не должно быть пустым")
    private String systemTime;
    private String source;
    @Min(value = 1, message = "поле communication не может быть меньше 1")
    @Max(value = 100000, message = "поле не может быть больше 100000")
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;

}
