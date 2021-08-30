package ru.skillbox.skillboxblog.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "capcha_codes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CapchaCode {
    @Id
    @GeneratedValue
    private Integer id;
    @CreationTimestamp
    private LocalDateTime time;
    @Column(nullable = false)
    private String code;
    @Column(name = "secrete_code", nullable = false)
    private String secret_code;
}