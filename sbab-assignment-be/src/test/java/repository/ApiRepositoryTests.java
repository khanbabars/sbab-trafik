package repository;



import com.sbab.dev.DevApplication;
import com.sbab.dev.domain.model.JourneyPatternModel;
import com.sbab.dev.domain.model.LinesModel;
import com.sbab.dev.domain.model.StopPointsModel;
import com.sbab.dev.repository.ApiRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = DevApplication.class)
@Disabled
@ActiveProfiles("test")
@Sql({"classpath:repo/data.sql"})
@Sql(scripts = "classpath:repo/drop-all-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ApiRepositoryTests {



    @Autowired
    private ApiRepository apiRepository;

    @Test
    void findLineByIdTest() throws InterruptedException {
        LinesModel result = apiRepository.findLineById("636");
        System.out.println(result);
        assertNotNull(result);
    }

    @Test
    void findStopByPointTest() throws InterruptedException {
        StopPointsModel result = apiRepository.findStopByPoint("10001");
        assertNotNull(result);
    }

    @Test
    void findAllJourneyPatternByLineTest() throws InterruptedException {
        List<JourneyPatternModel> result = apiRepository.findAllJourneyPattern();
        assertNotNull(result);

    }

}
