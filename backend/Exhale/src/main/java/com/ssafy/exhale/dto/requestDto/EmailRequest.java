package com.ssafy.exhale.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailRequest {
    @JsonProperty("email_id")
    String emailId;
    @JsonProperty("email_domain")
    String emailDomain;
}
