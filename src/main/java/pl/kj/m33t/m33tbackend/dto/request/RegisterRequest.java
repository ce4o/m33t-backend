package pl.kj.m33t.m33tbackend.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nickname;
    private String email;
    private String password;
}