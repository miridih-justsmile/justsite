package comm.justsmile.justsite.springboot.web.api.pay.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String payName;

    @Column(nullable = false)
    private Long price;
}