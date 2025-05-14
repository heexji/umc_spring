package umc.study.repository.MemberMissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.QMemberMissionDto;
import umc.study.web.dto.MemberMissionDto;
import umc.study.domain.mapping.QMemberMission;
import umc.study.domain.QMission;
import umc.study.domain.QStore;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MemberMissionDto> findMissionsWithCursor(
            Long memberId,
            MissionStatus status,
            LocalDateTime updatedAt,
            Long missionId,
            int pageSize
    ) {
        QMemberMission mm = QMemberMission.memberMission;
        QMission m = QMission.mission;
        QStore s = QStore.store;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mm.member.id.eq(memberId));

        BooleanBuilder cursorCondition = new BooleanBuilder();
        cursorCondition.or(mm.status.gt(status));
        cursorCondition.or(mm.status.eq(status).and(mm.updatedAt.lt(updatedAt)));
        cursorCondition.or(mm.status.eq(status)
                .and(mm.updatedAt.eq(updatedAt))
                .and(mm.mission.id.gt(missionId)));

        builder.and(cursorCondition);

        return queryFactory
                .select(new QMemberMissionDto(
                        mm.id,
                        mm.status,
                        mm.updatedAt,
                        m.id,
                        m.store.id,
                        m.reward,
                        m.deadline,
                        s.name,
                        Expressions.stringTemplate(
                                "concat({0}, '_', DATE_FORMAT({1}, '%Y%m%d%H%i%s'), '_', LPAD({2}, 10, '0'))",
                                mm.status.stringValue(),
                                mm.updatedAt,
                                m.id
                        )
                ))
                .from(mm)
                .join(m).on(mm.mission.id.eq(m.id))
                .join(s).on(m.store.id.eq(s.id))
                .where(builder)
                .orderBy(
                        Expressions.cases()
                                .when(mm.status.eq(MissionStatus.CHALLENGING)).then(0)
                                .when(mm.status.eq(MissionStatus.COMPLETE)).then(1)
                                .otherwise(2).asc(),
                        mm.updatedAt.desc(),
                        m.id.asc()
                )
                .limit(pageSize)
                .fetch();
    }
}