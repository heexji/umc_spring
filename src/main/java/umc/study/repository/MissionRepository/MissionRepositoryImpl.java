package umc.study.repository.MissionRepository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.study.domain.enums.MissionStatus;
import umc.study.dto.MissionRegionDto;
import umc.study.dto.QMissionRegionDto;

import java.time.LocalDate;
import java.util.List;

import static umc.study.domain.QMission.mission;
import static umc.study.domain.QStore.store;
import static umc.study.domain.QRegion.region;
import static umc.study.domain.mapping.QMemberMission.memberMission;

@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MissionRegionDto> findMissionByRegionWithCursor(Long regionId, Long memberId, LocalDate deadline, Long missionId) {

        // 🔹 서브쿼리: 지역별 완료 수
        SubQueryExpression<Long> regionCompletedCount = queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE),
                        store.region.id.eq(region.id)
                );

        return queryFactory
                .select(new QMissionRegionDto(
                        mission.id,
                        store.id,
                        store.name,
                        region.name,
                        mission.reward,
                        mission.missionSpec,
                        mission.deadline,
                        ExpressionUtils.as(regionCompletedCount, "regionCompletedCount"),
                        Expressions.stringTemplate(
                                "concat(DATE_FORMAT({0}, '%Y%m%d%H%i%s'), LPAD({1}, 10, '0'))",
                                mission.deadline,
                                mission.id
                        )
                ))
                .from(mission)
                .join(mission.store, store)
                .join(store.region, region)
                .join(memberMission).on(memberMission.mission.eq(mission))
                .where(
                        region.id.eq(regionId),
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING),
                        mission.deadline.goe(LocalDate.now()),
                        mission.deadline.gt(deadline)
                                .or(mission.deadline.eq(deadline)
                                        .and(mission.id.gt(missionId)))
                )
                .orderBy(mission.deadline.asc(), mission.id.asc())
                .limit(10)
                .fetch();
    }
}
