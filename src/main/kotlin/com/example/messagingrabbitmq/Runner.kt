package com.example.messagingrabbitmq

import org.springframework.amqp.rabbit.core.*
import org.springframework.boot.*
import org.springframework.stereotype.*
import java.util.concurrent.*

@Component
class Runner(val rabbitTemplate: RabbitTemplate, val receiver: Receiver): CommandLineRunner {
    override fun run(vararg args: String?) {
        println("Sending message...")
        rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName,
            "foo.bar.baz",
            "Hello from RabbitMQ!")
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS)
    }
}