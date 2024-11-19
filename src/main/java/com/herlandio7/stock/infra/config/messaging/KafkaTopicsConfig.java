package com.herlandio7.stock.infra.config.messaging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class KafkaTopicsConfig {

    @Value("${kafka.topic.critical}")
    private String topicCritical;

    @Value("${kafka.topic.moderate}")
    private String topicModerate;

    @Value("${kafka.topic.low}")
    private String topicLow;

    @Value("${kafka.topic.dlq-suffix}")
    private String topicDlqSuffix;
}
