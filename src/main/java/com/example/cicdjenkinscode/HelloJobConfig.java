package com.example.cicdjenkinscode;


import com.example.cicdjenkinscode.dto.HelloDTO;
import com.example.cicdjenkinscode.entity.HelloEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class HelloJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job helloJob(){
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                .start(this.hellStep())
                .build();
    }

    @Bean
    public Step hellStep(){
        return stepBuilderFactory.get("helloStep")
                .<HelloDTO, HelloEntity>chunk(1)
                .reader(this.helloItemReader(null))
                .processor(this.helloProcessor())
                .writer(this.helloItemWriter(null))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<HelloDTO> helloItemReader(@Value("#{jobParameters[value]}") String value){

        HelloDTO helloDto = new HelloDTO(value);
        return new ListItemReader<>(Arrays.asList(helloDto));
    }

    @Bean
    public ItemProcessor<HelloDTO, HelloEntity> helloProcessor(){
        return item -> HelloEntity.builder()
                .helloValue(item.getHelloValue())
                .build();
    }

    @Bean
    public ItemWriter<HelloEntity> helloItemWriter(EntityManagerFactory entityManagerFactory){
        return new JpaItemWriterBuilder<HelloEntity>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
    }

}
