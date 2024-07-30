package ru.yakovlev05.test.webmessenger.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.yakovlev05.test.webmessenger.filter.JwtAuthChannelInterceptor;

import java.util.List;

// Хороший проект для ознакомления - https://github.com/spring-guides/gs-messaging-stomp-websocket
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtAuthChannelInterceptor jwtAuthChannelInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // Префикс для сообщений от сервера к клиенту
        // Включает простой брокер сообщений с префиксом "/topic"
        // Именно брокер сообщений обрабатывает подписки пользователей
        // Все сообщения, которые направляются на топики, начинающиеся с /topic, будут обрабатываться простым брокером и доставляться подписчикам.
        registry.enableSimpleBroker("/topic");

        // Префикс для сообщений от клиента к серверу (обрабатывается @MessageMapping)
        // Префикс указывает, что все сообщения по адресу /app/* будут обрабатываться определёнными методами (@MessageMapping)
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Регистрация точки подключения для клиентов
        // То есть подключение будет идти к адресу ws://localhost:8080

        registry
                .addEndpoint("/ws")
                .withSockJS(); // Соединения через SockJS

        registry.addEndpoint("/ws"); // Соединения через обычный вебсокет

        // withSocketJS() - добавляет поддержку SockJS, однако если оставить только её, то через обычный вебсокет не подключишься
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        // Определяет MIME тип по умолчанию для сообщений (JSON)
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);

        // Устанавливает ObjectMapper для конвертера, который используется для сериализации и десериализации JSON.
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());

        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Регистрируем интерсептор для обработки входящих сообщений
        // Он необходим для авторизации в вебсокетах
        // Его логика аналогична фильтру для хттп запросов
        registration.interceptors(jwtAuthChannelInterceptor);
    }
}
