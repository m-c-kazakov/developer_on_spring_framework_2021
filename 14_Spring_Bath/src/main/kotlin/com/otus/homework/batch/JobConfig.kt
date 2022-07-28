package com.otus.homework.batch

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Configuration


internal const val MONGO_JOB_NAME = "mongo-to-postgres"
internal const val EXECUTE_SQL_SCHEMA_STEP_NAME = "execute-sql-schema-step"
internal const val MONGO_READ_STEP_NAME = "mongo-read-step"
internal const val MONGO_READER_NAME = "mongo-reader"
internal const val CHUNK_SIZE = 2
internal const val READER_PAGE_SIZE: Int = 2


@EnableBatchProcessing
@Configuration
open class JobConfig(
    private val stepBuilderFactory: StepBuilderFactory,
    private val jobBuilderFactory: JobBuilderFactory,
    private val sqlExecutioner: SqlExecutioner,
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JobConfig::class.java)
    }

//    @Bean
//    open fun executeSqlTasklet(): Tasklet = MethodInvokingTaskletAdapter().apply {
//        setTargetObject(sqlExecutioner)
//        setTargetMethod("execute")
//        setArguments(arrayOf("postgres_schema.sql"))
//    }
//
//    @Bean
//    open fun executeSQLSchemaStep(): Step = stepBuilderFactory
//        .get(EXECUTE_SQL_SCHEMA_STEP_NAME)
//        .tasklet(executeSqlTasklet())
//        .build()
//
//
//    @Bean
//    open fun mongoToPostgresJob(mongoReadStep: Step): Job = jobBuilderFactory
//        .get(MONGO_JOB_NAME)
//        .incrementer(RunIdIncrementer())
//        .start(executeSQLSchemaStep())
//        .next(mongoReadStep)
//        .listener(object : JobExecutionListener {
//            override fun beforeJob(@NonNull jobExecution: JobExecution) {
//                log.info("Начало job")
//            }
//
//            override fun afterJob(@NonNull jobExecution: JobExecution) {
//                log.info("Конец job")
//            }
//        })
//        .build()
//
//
//
//
//
//    @Bean
//    open fun mongoReadStep(
//        reader: ItemReader<Book>,
//        enrichingBookProcessor: ItemProcessor<BookMongo, BookWithComments>,
//        postgresBookWriter: ItemWriter<BookWithComments>,
//    ): Step = stepBuilderFactory
//        .get(MONGO_READ_STEP_NAME)
//        .chunk<Book, BookWithComments>(CHUNK_SIZE)
//        .reader(reader)
//        .processor(enrichingBookProcessor)
//        .writer(postgresBookWriter)
//        .build()
//
//
//    @Bean
//    @StepScope
//    open fun reader(mongoTemplate: MongoTemplate): ItemReader<Book> = MongoItemReaderBuilder<Book>()
//        .template(mongoTemplate)
//        .name(MONGO_READER_NAME)
//        .targetType(Book::class.java)
//        .jsonQuery("{}")
//        .sorts(mapOf("_id" to Sort.Direction.ASC))
//        .pageSize(READER_PAGE_SIZE)
//        .build()
//
//    @Bean
//    open fun enrichingBookProcessor(commentRepo: CommentRepo): ItemProcessor<Book, BookWithComments> =
//        EnrichingBookProcessor(commentRepo)
//
//    @Bean
//    open fun printingWriter(): ItemWriter<BookWithComments> {
//        return ItemWriter { books -> println(books) }
//    }
//
//    internal open class EnrichingBookProcessor(
//        private val commentRepo: CommentRepo,
//    ) : ItemProcessor<Book, BookWithComments> {
//
//        @Transactional(readOnly = true)
//        override fun process(book: Book): BookWithComments {
//            val comments = commentRepo.findAllById(book.commentIds)
//            return BookWithComments(book, comments)
//        }
//    }
}
