package com.lvwyh.aliyun.kafka.service.impl;

import com.lvwyh.aliyun.api.common.exception.BusinessException;
import com.lvwyh.aliyun.kafka.config.KafkaProperties;
import com.lvwyh.aliyun.kafka.service.KafkaTestService;
import com.lvwyh.aliyun.kafka.vo.ConsumeMessageVO;
import com.lvwyh.aliyun.kafka.vo.CreateTopicVO;
import com.lvwyh.aliyun.kafka.vo.KafkaMessageVO;
import com.lvwyh.aliyun.kafka.vo.ProduceMessageVO;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.errors.TopicExistsException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaTestServiceImpl implements KafkaTestService {

    private static final Logger log = LogManager.getLogger(KafkaTestServiceImpl.class);

    private static final String DEFAULT_API_TIMEOUT_MS_CONFIG = "default.api.timeout.ms";

    private final KafkaProperties kafkaProperties;

    public KafkaTestServiceImpl(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Override
    public CreateTopicVO createTopic(String topic, Integer partitions, Short replicationFactor) {
        String bootstrap = getBootstrapServers();
        Integer operationTimeoutMs = getOperationTimeoutMs();
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(DEFAULT_API_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(AdminClientConfig.RETRIES_CONFIG, 0);
        applyDataHubCompatibleProperties(props);

        try (AdminClient adminClient = AdminClient.create(props)) {
            NewTopic newTopic = new NewTopic(topic, partitions, replicationFactor);
            CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));
            result.values().get(topic).get(operationTimeoutMs, TimeUnit.MILLISECONDS);
            TopicDescription description = adminClient.describeTopics(Collections.singleton(topic))
                    .values().get(topic).get(operationTimeoutMs, TimeUnit.MILLISECONDS);
            return new CreateTopicVO(topic, description.partitions().size(), false);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof TopicExistsException) {
                log.info("Kafka topic already exists: topic={}", topic);
                return new CreateTopicVO(topic, partitions, true);
            }
            log.error("Create Kafka topic failed: bootstrap={}, topic={}, partitions={}, replicationFactor={}",
                    bootstrap, topic, partitions, replicationFactor, e);
            throw new BusinessException("创建Kafka Topic失败", e);
        } catch (Exception e) {
            log.error("Create Kafka topic failed: bootstrap={}, topic={}, partitions={}, replicationFactor={}",
                    bootstrap, topic, partitions, replicationFactor, e);
            throw new BusinessException("创建Kafka Topic失败", e);
        }
    }

    @Override
    public ProduceMessageVO produce(String topic, String key, String message) {
        String bootstrap = getBootstrapServers();
        Integer operationTimeoutMs = getOperationTimeoutMs();
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, operationTimeoutMs);
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "false");
        if (StringUtils.hasText(kafkaProperties.getCompressionType())) {
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, kafkaProperties.getCompressionType());
        }
        applyDataHubCompatibleProperties(props);

        try (KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props)) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, message);
            RecordMetadata metadata = producer.send(record).get(operationTimeoutMs, TimeUnit.MILLISECONDS);
            return new ProduceMessageVO(metadata.topic(), metadata.partition(), metadata.offset());
        } catch (Exception e) {
            log.error("Produce Kafka message failed: bootstrap={}, topic={}, key={}",
                    bootstrap, topic, key, e);
            throw new BusinessException("发送Kafka消息失败", e);
        }
    }

    @Override
    public ConsumeMessageVO consume(String topic, String groupId, Integer maxMessages, Integer timeoutMs, String autoOffsetReset) {
        String bootstrap = getBootstrapServers();
        Integer operationTimeoutMs = getOperationTimeoutMs();
        String finalGroupId = StringUtils.hasText(groupId) ? groupId : "kafka-test-" + UUID.randomUUID();
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, finalGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(DEFAULT_API_TIMEOUT_MS_CONFIG, operationTimeoutMs);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000");
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "40000");
        applyDataHubCompatibleProperties(props);

        List<KafkaMessageVO> messages = new ArrayList<KafkaMessageVO>();
        long deadline = System.currentTimeMillis() + timeoutMs;

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props)) {
            consumer.subscribe(Collections.singleton(topic));
            while (messages.size() < maxMessages && System.currentTimeMillis() < deadline) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
                for (ConsumerRecord<String, String> record : records) {
                    messages.add(new KafkaMessageVO(
                            record.topic(),
                            record.partition(),
                            record.offset(),
                            record.key(),
                            record.value()
                    ));
                    if (messages.size() >= maxMessages) {
                        break;
                    }
                }
            }
            return new ConsumeMessageVO(finalGroupId, messages);
        } catch (Exception e) {
            log.error("Consume Kafka message failed: bootstrap={}, topic={}, groupId={}, maxMessages={}, timeoutMs={}",
                    bootstrap, topic, finalGroupId, maxMessages, timeoutMs, e);
            throw new BusinessException("消费Kafka消息失败", e);
        }
    }

    private String getBootstrapServers() {
        if (!StringUtils.hasText(kafkaProperties.getBootstrapServers())) {
            throw new BusinessException("Kafka bootstrap servers未配置");
        }
        return kafkaProperties.getBootstrapServers();
    }

    private Integer getOperationTimeoutMs() {
        if (kafkaProperties.getOperationTimeoutMs() == null || kafkaProperties.getOperationTimeoutMs() < 1000) {
            return 5000;
        }
        return kafkaProperties.getOperationTimeoutMs();
    }

    private void applyDataHubCompatibleProperties(Properties props) {
        if (!Boolean.TRUE.equals(kafkaProperties.getDatahubCompatibleEnabled())) {
            return;
        }
        props.put("security.protocol", "SASL_SSL");
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        applyJaasConfig(props);
        if (kafkaProperties.getSslEndpointIdentificationAlgorithm() != null) {
            props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
                    kafkaProperties.getSslEndpointIdentificationAlgorithm());
        }
        applyTruststoreProperties(props);
    }

    private void applyJaasConfig(Properties props) {
        if (StringUtils.hasText(kafkaProperties.getJaasConfigPath())) {
            validateReadableFile(kafkaProperties.getJaasConfigPath(), "DataHub Kafka JAAS配置文件不存在或不可读");
            System.setProperty("java.security.auth.login.config", kafkaProperties.getJaasConfigPath());
            return;
        }
        if (!StringUtils.hasText(kafkaProperties.getAccessKeyId())) {
            throw new BusinessException("DataHub Kafka accessKeyId未配置");
        }
        if (!StringUtils.hasText(kafkaProperties.getAccessKeySecret())) {
            throw new BusinessException("DataHub Kafka accessKeySecret未配置");
        }
        props.put(SaslConfigs.SASL_JAAS_CONFIG,
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\""
                        + escapeJaasValue(kafkaProperties.getAccessKeyId())
                        + "\" password=\""
                        + escapeJaasValue(kafkaProperties.getAccessKeySecret())
                        + "\";");
    }

    private void applyTruststoreProperties(Properties props) {
        if (!Boolean.TRUE.equals(kafkaProperties.getTruststoreEnabled())) {
            return;
        }
        if (!StringUtils.hasText(kafkaProperties.getTruststoreLocation())) {
            throw new BusinessException("DataHub Kafka truststoreLocation未配置");
        }
        if (!StringUtils.hasText(kafkaProperties.getTruststorePassword())) {
            throw new BusinessException("DataHub Kafka truststorePassword未配置");
        }
        validateReadableFile(kafkaProperties.getTruststoreLocation(), "DataHub Kafka truststore文件不存在或不可读");
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaProperties.getTruststoreLocation());
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaProperties.getTruststorePassword());
    }

    private void validateReadableFile(String path, String message) {
        File file = new File(path);
        if (!file.isFile() || !file.canRead()) {
            throw new BusinessException(message + ": " + path);
        }
    }

    private String escapeJaasValue(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
