package pl.kj.m33t.m33tbackend.service.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest { //TODO: use the records in all the places.

    private String nickname;
    private String email;
    private String password;
}