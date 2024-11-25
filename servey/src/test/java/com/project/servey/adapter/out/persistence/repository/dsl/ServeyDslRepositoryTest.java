package com.project.servey.adapter.out.persistence.repository.dsl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.adapter.out.persistence.repository.ServeyRepository;
import com.project.servey.config.QuerydslTestConfig;
import jakarta.transaction.Transactional;
@ActiveProfiles("test") // application-test.yml 활성화
@TestPropertySource(locations = "classpath:application-test.yml")
@Transactional
@DisplayName("[통합] 게시글 queryDsl 테스트")
@DataJpaTest//자동으로 JPAQueryFactory 빈을 생성하지 않기 때문에 수동으로 구성 (QuerydslTestConfig.java)
@Import({ServeyDslRepository.class, QuerydslTestConfig.class})//커스텀 DslRepository는 스캔되지 않을 수 있어서 직접 불러옴.
public class ServeyDslRepositoryTest {
    @Autowired
    private ServeyDslRepository dslRepository;

    @Autowired
    private ServeyRepository serveyRepository;

    @Test
    @DisplayName("[happy] 게시글 성공적으로 삭제처리(delete_yn = 'Y')")
    void testDeleteServey() {
        //given
        Long serveyId = 1L;
        //when
        dslRepository.deleteServey(serveyId);
        //then
        Optional<ServeyEntity> updatedEntity = serveyRepository.findByServeyId(serveyId);
        ServeyEntity entity = updatedEntity.orElseThrow(() -> new AssertionError("Servey entity not found"));
        assertThat(entity.getDeleteYn()).isEqualTo("Y");
    }

    @Test
    void testSelectServeyFilteredList() {

    }

    @Test
    void testUpdateServey() {

    }

}
