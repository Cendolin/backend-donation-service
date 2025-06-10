package id.hanifu.cendolin_donation_service.rabbitmq;

import id.hanifu.cendolin_donation_service.objects.UserCreatedObject;
import id.hanifu.cendolin_donation_service.rabbitmq.subscribers.UserCreatedQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitUserCreated {

    @Bean("user_created_queue")
    public Queue userCreatedQueue() {
        return new Queue(RabbitExchangesQueues.userCreatedQueueName);
    }

    @Bean("user_exchange")
    public DirectExchange userExchange() {
        return new DirectExchange(RabbitExchangesQueues.userExchangeName);
    }

    @Bean
    public Binding userCreatedBinding(Queue userCreatedQueue, DirectExchange userExchange) {
        return BindingBuilder
                .bind(userCreatedQueue)
                .to(userExchange)
                .with(RabbitExchangesQueues.userCreatedRouteKey);
    }

    @Bean
    public SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter,
            Queue userCreatedQueue
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(userCreatedQueue);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(UserCreatedQueue userCreatedQueue) {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(userCreatedQueue, "receiveUserCreatedMessage");
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        DefaultClassMapper defaultClassMapper = new DefaultClassMapper();

        defaultClassMapper.setDefaultType(UserCreatedObject.class);
        jsonMessageConverter.setClassMapper(defaultClassMapper);

        listenerAdapter.setMessageConverter(jsonMessageConverter);
        return listenerAdapter;
    }
}
