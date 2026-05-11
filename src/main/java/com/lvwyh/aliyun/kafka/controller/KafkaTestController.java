package com.lvwyh.aliyun.kafka.controller;

import com.lvwyh.aliyun.api.common.response.ApiResponse;
import com.lvwyh.aliyun.kafka.ao.ConsumeMessageAO;
import com.lvwyh.aliyun.kafka.ao.CreateTopicAO;
import com.lvwyh.aliyun.kafka.ao.ProduceMessageAO;
import com.lvwyh.aliyun.kafka.service.KafkaTestService;
import com.lvwyh.aliyun.kafka.vo.ConsumeMessageVO;
import com.lvwyh.aliyun.kafka.vo.CreateTopicVO;
import com.lvwyh.aliyun.kafka.vo.ProduceMessageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Kafka测试")
@Validated
@RestController
@RequestMapping("/api/kafka")
public class KafkaTestController {

    private static final Logger log = LogManager.getLogger(KafkaTestController.class);

    @Autowired
    private KafkaTestService kafkaTestService;

    @Operation(summary = "创建Topic", description = "创建Kafka Topic，已存在时返回existed=true")
    @PostMapping("/topic/create")
    public ApiResponse<CreateTopicVO> createTopic(@RequestBody @Valid CreateTopicAO ao) {

        log.info("Create Kafka topic request: topic={}, partitions={}, replicationFactor={}",
                ao.getTopic(), ao.getPartitions(), ao.getReplicationFactor());

        CreateTopicVO vo = kafkaTestService.createTopic(
                ao.getTopic(),
                ao.getPartitions(),
                ao.getReplicationFactor()
        );

        log.info("Create Kafka topic success: topic={}, partitions={}, existed={}",
                vo.getTopic(), vo.getPartitions(), vo.getExisted());

        return ApiResponse.success("创建成功", vo);
    }

    @Operation(summary = "发送消息", description = "向Kafka Topic发送字符串消息")
    @PostMapping("/message/produce")
    public ApiResponse<ProduceMessageVO> produce(@RequestBody @Valid ProduceMessageAO ao) {

        log.info("Produce Kafka message request: topic={}, key={}",
                ao.getTopic(), ao.getKey());

        ProduceMessageVO vo = kafkaTestService.produce(
                ao.getTopic(),
                ao.getKey(),
                ao.getMessage()
        );

        log.info("Produce Kafka message success: topic={}, partition={}, offset={}",
                vo.getTopic(), vo.getPartition(), vo.getOffset());

        return ApiResponse.success("发送成功", vo);
    }

    @Operation(summary = "消费消息", description = "从Kafka Topic消费字符串消息")
    @PostMapping("/message/consume")
    public ApiResponse<ConsumeMessageVO> consume(@RequestBody @Valid ConsumeMessageAO ao) {

        log.info("Consume Kafka message request: topic={}, groupId={}, maxMessages={}, timeoutMs={}, autoOffsetReset={}",
                ao.getTopic(),
                ao.getGroupId(),
                ao.getMaxMessages(),
                ao.getTimeoutMs(),
                ao.getAutoOffsetReset());

        ConsumeMessageVO vo = kafkaTestService.consume(
                ao.getTopic(),
                ao.getGroupId(),
                ao.getMaxMessages(),
                ao.getTimeoutMs(),
                ao.getAutoOffsetReset()
        );

        log.info("Consume Kafka message success: topic={}, groupId={}, consumedCount={}",
                ao.getTopic(), vo.getGroupId(), vo.getConsumedCount());

        return ApiResponse.success("消费成功", vo);
    }
}
