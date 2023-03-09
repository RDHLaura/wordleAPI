package com.example.wordle.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Setter @Getter
@RequiredArgsConstructor @NoArgsConstructor
public class APIError {
    @NonNull
    private HttpStatusCode statusCode;

    @NonNull
    private String mensaje;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha = LocalDateTime.now();

}
