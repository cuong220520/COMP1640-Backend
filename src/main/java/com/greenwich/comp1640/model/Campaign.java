package com.greenwich.comp1640.model;

import com.greenwich.comp1640.util.constant.CampaignStatusConst;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "campaign")
public class Campaign {

    @Id
    private String code;

    @Column(name = "submit_deadline")
    private Date submitDeadline;

    @Column(name = "edit_deadline")
    private Date editDeadline;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private CampaignStatusConst status;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private User admin;

}
