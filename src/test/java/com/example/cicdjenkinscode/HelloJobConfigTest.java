package com.example.cicdjenkinscode;

import com.example.cicdjenkinscode.entity.HelloEntity;
import com.example.cicdjenkinscode.repository.HelloEntityJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManagerFactory;

import static org.assertj.core.api.Assertions.*;

@SpringBatchTest
@SpringBootTest(classes = {TestBatchConfig.class, HelloJobConfig.class})
class HelloJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired
    private HelloEntityJpaRepository helloEntityJpaRepository;

    @BeforeEach
    void beforeEach(){
        jobRepositoryTestUtils.removeJobExecutions();
    }

    @AfterEach
    void afterEach(){
        helloEntityJpaRepository.deleteAll();
    }


    @Test
    void helloStartTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("value", "start")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        HelloEntity helloStart = helloEntityJpaRepository.findByHelloValue("hello start");
        assertThat(helloStart.getHelloValue()).isEqualTo("hello start");

    }

    @Test
    void hellLastTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("value", "last")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        HelloEntity helloStart = helloEntityJpaRepository.findByHelloValue("hello last");
        assertThat(helloStart.getHelloValue()).isEqualTo("hello last");

    }

    @Test
    void helloOtherTest() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("value", "hello yhnoh")
                .toJobParameters();
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        assertThat(jobExecution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);

        HelloEntity helloStart = helloEntityJpaRepository.findByHelloValue("hello yhnoh");
        assertThat(helloStart.getHelloValue()).isEqualTo("hello yhnoh");

    }

}
