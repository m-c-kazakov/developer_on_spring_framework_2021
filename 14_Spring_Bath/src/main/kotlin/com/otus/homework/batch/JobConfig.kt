package com.otus.homework.batch

import com.otus.homework.model.mongo.BookMongo
import com.otus.homework.repository.BookMongoRepository
import com.otus.homework.repository.BookPostgresRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemWriter
import org.springframework.batch.item.data.MongoItemReader
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.lang.NonNull


internal const val EXECUTE_SQL_SCHEMA_STEP_NAME = "execute-sql-schema-step"
internal const val MONGO_READ_STEP_NAME = "mongo-read-step"
internal const val MONGO_READER_NAME = "mongo-reader"
internal const val CHUNK_SIZE = 2
internal const val READER_PAGE_SIZE: Int = 2


internal const val FROM_MONGO_TO_POSTGRES_JOB = "fromMongoToPostgresJob"

@EnableBatchProcessing
@Configuration
open class JobConfig(
    private val stepBuilderFactory: StepBuilderFactory,
    private val jobBuilderFactory: JobBuilderFactory
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JobConfig::class.java)
    }


    @Bean
    open fun fromMongoToPostgresJob(readFormMongoWriteToPostgres: Step): Job = jobBuilderFactory
        .get(FROM_MONGO_TO_POSTGRES_JOB)
        .incrementer(RunIdIncrementer())
        .listener(object : JobExecutionListener {
            override fun beforeJob(@NonNull jobExecution: JobExecution) {
                log.info("Начало job")
            }

            override fun afterJob(@NonNull jobExecution: JobExecution) {
                log.info("Конец job")
            }
        })
        .flow(readFormMongoWriteToPostgres)
        .end()
        .build()

    @Bean
    open fun readFormMongoWriteToPostgres(
        mongoReader: MongoItemReader<BookMongo>,
        postgresWriter: ItemWriter<BookMongo>
    ): Step = stepBuilderFactory
        .get("readFormMongoWriteToPostgres")
        .chunk<BookMongo, BookMongo>(CHUNK_SIZE)
        .reader(mongoReader)
        .writer(postgresWriter)
        .build()

    @Bean
    open fun mongoReader(mongoTemplate: MongoTemplate): MongoItemReader<BookMongo> = MongoItemReaderBuilder<BookMongo>()
        .name(MONGO_READER_NAME)
        .template(mongoTemplate)
        .targetType(BookMongo::class.java)
        .jsonQuery("{}")
        .sorts(mapOf("_id" to Sort.Direction.ASC))
        .pageSize(READER_PAGE_SIZE)
        .build()

}
