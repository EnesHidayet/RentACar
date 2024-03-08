package org.enes.rabbitmq.producer;


import org.enes.rabbitmq.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserProducer {


    private String directExchange = "auth-rac-exchange";
    private String bindingKeyRegister = "auth-rac-register-binding-key";

    private final RabbitTemplate rabbitTemplate;

    public void sendNewUser(RegisterModel model){
        rabbitTemplate.convertAndSend(directExchange,bindingKeyRegister,model);
    }
}