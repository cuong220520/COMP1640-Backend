package com.greenwich.comp1640.model;

import com.greenwich.comp1640.util.constant.ArticleStatusConst;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "message")
    private String message;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ArticleStatusConst status;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "document_url")
    private String documentUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "faculty_code", referencedColumnName = "code")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "campaign_code", referencedColumnName = "code")
    private Campaign campaign;

}
