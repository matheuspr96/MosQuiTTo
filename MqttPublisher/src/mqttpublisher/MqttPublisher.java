package mqttpublisher;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author XPimenta
 */
public class MqttPublisher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
               String topic        = "MQTT";
        String content      = "Message from MqttPublishSample";
        int qos             = 10;
        String broker       = "tcp://mqtt.eclipse.org:1883";
       
        String clientId     = "JavaSample";
         try {
             while(true){
        MemoryPersistence persistence = new MemoryPersistence();
                          
            int min = 15;
            int max = 45;
            int delay = 1000;        
          
        
              
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
                    
            
            sampleClient.connect(connOpts);
           
     
            Random random = new Random();
            int valorAleatorio = (random.nextInt(max - (min - 1)) + min);
            String valorAleatorioToStr = Integer.toString(valorAleatorio);
            System.out.println("Publishing message: "+ valorAleatorioToStr);
            MqttMessage message = new MqttMessage(valorAleatorioToStr.getBytes());
            // message.setQos(qos);
            sampleClient.publish(topic, message);   
            System.out.println("Message published");
              Thread.sleep(delay);
            }
          //  sampleClient.disconnect();
          //  System.out.println("Disconnected");
         //   System.exit(0);
        }
        catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }             
    }
}
