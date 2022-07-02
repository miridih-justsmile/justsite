package comm.justsmile.justsite.springboot.web.api.hello.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloDto {
    private final String name;
    private final String value;
}
