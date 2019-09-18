# mq-demos
The following Message Queues have done on branch zeluo
* activemq
* rabbitmq
* kafka

# notes

# ActiveMQ结合JmsTemplate
### 实现生产者
* 实现ActiveMQQueue对象来获取一个信道
* 自动装配JmsTemplate 调用函数convertAndSend进行发送<br>***可以设置定时任务***
### 实现消费者
* 设置JmsListener监听信道
* 完成相应操作
* 信息被封装在了String类型的参数中，在方法签名中

### 消息队列
* 配置文件中设置地址
```
spring.activemq.broker-url=tcp://127.0.0.1:61616
```
# RabbitMQ
1. 完成rabbitmq配置 config类
2. 自动装配AmqpTemplate 用法和JmsTemplate一致
3. 创建接收者


### rabbitmq四种模式
* direct模式，绑定时设置一个routing_key，发送的消息与routing_key完全匹配才会被发送到队列中
* topic模式，模糊匹配然后发送到队列中，routing_key一定是一串字符，用(.)分开，其中(*)表示一个单词，(#)表示多个单词
* fanout模式，发送到交换机上的消息会全部转发到与交换机绑定的队列上
* hearder模式，根据自定义规则进行匹配，绑定时会设定一组键值对规则，消息中包括一组键值对，当这些键值对有一对或者全部匹配时发送到队列

### rabbitmq 手动确认
* 通过调用`channel.basicAck(tag,false);`进行手动确认
* 如果tag值与预期不符合则无效ack
* 通过channel.basicQos可以设置这个信道每次能接受的最大消息数量
* 两个结合可以实现公平分配


# Kafka
### Kafka基于windows10搭建通信
* kafka运行依赖于zookeeper所以需要先下载运行zookeeper
    * 下载后直接进入目录运行`zkServer.cmd`
* 启动kafka-broker消息处理中心
cmd进入根目录 输入命令`.\bin\windows\kafka-server-start.bat .\config\server.properties`
* 注册kafka-topic
cmd进入根目录下的bin/windows 输入命令`kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testDemo`
* 启动kafka-producer 消息创建者
cmd进入根目录下的bin/windows 输入`kafka-console-producer.bat --broker-list localhost:9092 --topic testDemo`
* 启动akfka-consumer 消息消费者
cmd进入根目录下的bin/windows 输入`kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testDemo`注意需要和消费者同一端口否则收不到消息

### SpringBoot整合Kafka
* 消费者
    * 通过注解`@KafkaListener`进行监听 总体和ActiveMq类似
* 生产者
    * 自动装配`KafkaTemplate`
    * 通过`KafkaTemplate.send`进行信息发送
