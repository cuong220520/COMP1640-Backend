package com.greenwich.comp1640.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "translation")
public class Translation {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "locale")
    private String locale;
    @Column(name = "key")
    private String key;
    @Column(name = "value")
    private String value;
}
