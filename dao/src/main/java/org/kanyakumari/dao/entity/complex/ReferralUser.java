package org.kanyakumari.dao.entity.complex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "referral_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferralUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinTable(
            name = "referral_maping",
            joinColumns = {@JoinColumn(name = "refree_id")},
            inverseJoinColumns = {@JoinColumn(name = "referral_id")}
    )
    private List<ReferralUser> referralUser;
}
