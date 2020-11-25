## Activemq - camel Performance tuning

### Message Broker
- Make sure to have good network & faster disk speed
- good broker topology - avoid a relational database based persistent store - it will perform around 50% of a file-based store
- using a network of brokers to scale horizontally will cost another 30% of performance

### Message client
- if performance is a priority
  - increase TCP socketBufferSize and ioBufferSize
  - tuning the OpenWire protocol parameters
  - using message compression, batch acknowledgements with optimizeAcknowledge, asynchronous send with useAsyncSend
  - and adjusting pre-fetch limit, etc.
  
### Database writes
- use batching whenever possible
- You can use an aggregator to collect a number of entries before performing a batch operation to interact with the database (for example with the SQL component)

### Working with templates
- try to use template components as part of the routinglike FreeMarker, Velocity, SpringTeplate, Mustache, Chunk

### Using Web Services
- Fine tune web container separately
- From Camel endpoint point of view, you can further optimise a little bit by skipping the unmarshalling if you don't need Java objects, and using asynchronous processing

### ConcurrentConsumers
- try to use parallel/concurrent consumers wherever possible; there are a number of components (Seda, VM, JMS, RabbitMQ, Disruptor, AWS-SQS, etc) that support parallel consumption. Before using an endpoint, check the component documentation for thread pool or batch processing capabilities

### Multithreading
- try to use the parallel processing & thread pools
- Use seda/async processing wherever possible
- Asynchronous Redelivery/Retry for exception handling

### Use streaming/stream cahcing, marshalling/unmarshalling wherever possible

### Disable JMX

### Async logging

### Disable message history
- camel maintains message history; try to disable it in prod (https://camel.apache.org/components/latest/eips/message-history.html)

### try to use 'lazyLoadTypeConverters' for a faster application startup, or configure the shutdownStrategy for a quicker shutdown when there are inflight messages, or a use a custom UuidGenerator that performs faster, etc

## Messaging performace tuning:

please refer to : https://activemq.apache.org/performance-tuning.html
