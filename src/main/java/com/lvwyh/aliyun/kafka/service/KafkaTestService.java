package com.lvwyh.aliyun.kafka.service;

import com.lvwyh.aliyun.kafka.vo.ConsumeMessageVO;
import com.lvwyh.aliyun.kafka.vo.CreateTopicVO;
import com.lvwyh.aliyun.kafka.vo.ProduceMessageVO;

public interface KafkaTestService {

    CreateTopicVO createTopic(String topic, Integer partitions, Short replicationFactor);

    ProduceMessageVO produce(String topic, String key, String message);

    ConsumeMessageVO consume(String topic, String groupId, Integer maxMessages, Integer timeoutMs, String autoOffsetReset);
}
