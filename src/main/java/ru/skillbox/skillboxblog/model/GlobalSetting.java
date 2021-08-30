package ru.skillbox.skillboxblog.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "global_settings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GlobalSetting {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String value;
}