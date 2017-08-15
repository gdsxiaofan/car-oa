package com.xiaofan.car.schedule.batch;

import com.xiaofan.car.persistence.model.LedgerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;

@Configuration
@Slf4j
public class BatchConfiguration {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobCompletionNotificationListener jobCompletionNotificationListener;
    /**
     * 读取外部文件方法
     * @return
     * @throws IOException
     */
    @Bean
    @StepScope
    public ItemReader<LedgerInfo> reader(@Value("#{jobParameters[time]}") String time, DataSource dataSource) throws IOException {

        log.info("time:{}",time);
        JdbcCursorItemReader<LedgerInfo> reader = new JdbcCursorItemReader<LedgerInfo>();
        reader.setDataSource(dataSource);
        reader.setSql("select * from t_ledger_info ");

        reader.setRowMapper((ResultSet rs, int rowNum)->{
            LedgerInfo ledger = new LedgerInfo();
            ledger.setId(rs.getInt("id"));
            ledger.setDeviceNo(rs.getString("device_no"));
            return ledger;
        });
        reader.open(new ExecutionContext());
        return reader;
    }
    /**
     * 中间转换
     * @return
     * @throws IOException
     */
    @Bean
    public ItemProcessor<LedgerInfo,LedgerInfo> processor() throws IOException {
        return (final LedgerInfo ledgerInfo) -> {
            LedgerInfo ledgerInfo1=new LedgerInfo();
            ledgerInfo1.setDeviceNo(ledgerInfo.getDeviceNo());
            ledgerInfo1.setId(ledgerInfo.getId());
            ledgerInfo1.setCreateUser("batch转换");
            return ledgerInfo1;
        };
    }
    /**
     * 读取外部文件方法
     * @return
     * @throws IOException
     */
    @Bean
    @StepScope
    public ItemWriter<LedgerInfo> writer(@Value("#{jobParameters[id]}") String id) throws IOException {
        return list -> System.out.println(list.toString());
    }


    @Bean
    public Step step1(ItemReader<LedgerInfo> reader,
                      ItemProcessor<LedgerInfo,LedgerInfo> processor,
                      ItemWriter<LedgerInfo> writer,
                      PlatformTransactionManager transactionManage) {
        return stepBuilderFactory.get("step1")
                .<LedgerInfo,LedgerInfo>chunk(10)

                .reader(reader)

                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManage)
                .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .listener(jobCompletionNotificationListener) // 绑定监听器
                .build();
    }
}
