package com.example.stock.configuration;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.stock.pojo.LogStock;
import com.fasterxml.jackson.databind.ObjectMapper;

import manager.LogStockManager;

@Configuration
public class MqttConfig {
    @Value("${mqtt.broker.url}")
    private String brokerURL;

    @Value("${mqtt.client.id}")
    private String clientId;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Autowired
    private LogStockManager logStockManager;

    @Bean
    public MqttClient MqttClient() throws MqttException {
        // instanciation du client
        MqttClient mqttClient = new MqttClient(brokerURL, clientId, new MemoryPersistence());

        // argument supplémentaire
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        mqttClient.connect(options);

        // callback
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                System.out.println("Connexion perdue : " + throwable.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                System.out.println("Message reçu du topic : " + topic);
                String payload = new String(mqttMessage.getPayload());

                // sérialiser le message reçu en objet java
                ObjectMapper objectMapper = new ObjectMapper();
                LogStock logStock = objectMapper.readValue(payload, LogStock.class);
                logStockManager.processLog(logStock);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                System.out.println("Message livré : " + iMqttDeliveryToken.getMessageId());
            }
        });

        mqttClient.subscribe("produit");
        return mqttClient;
    }

}
