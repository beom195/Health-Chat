package com.beom195.health_chat.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trainer implements Persistable<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "trainer_login_id")
    private String trainerLoginId;

    @Column(name = "trainer_name")
    private String trainerName;

    @Column(name = "trainer_email")
    private String trainerEmail;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "likes")
    private Integer likes;

    @Builder
    public Trainer(Long trainerId, String trainerLoginId, String trainerName, String trainerEmail, Role role, Integer likes) {
        this.trainerId = trainerId;
        this.trainerLoginId = trainerLoginId;
        this.trainerName = trainerName;
        this.trainerEmail = trainerEmail;
        this.role = role;
        this.likes = likes;
    }

    /*
    Review 작성시 insert 전 Trainer Entity 추가 select 막기
    SimpleJpaRepository save method 참고

    if (entityInformation.isNew(entity)) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}

    Entity에 Id 값이 있으면 db를 조회(select)후 insert함.
    Id 생성전략이 존재하지 않을 경우 merge 로 동작하고 생성 전략이 존재하는 경우 persist 로 동작(현재 @GeneratedValue 생성 전략 사용중)
    isNew() method에서 select query를 수행하여 새로운 데이터인지 확인
     */

    @Override
    public Long getId() {
        return this.trainerId;
    }


    //true -> select 하지 않음
    @Override
    public boolean isNew() {
        return true;
    }
}
