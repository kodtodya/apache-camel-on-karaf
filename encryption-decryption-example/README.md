# encryption-decryption-example
## camel-blueprint-example
### We are covering below topics in this example:
- pgp keys
- marshalling & unmarshalling
- blueprint
- file producer, consumer
- encryption
- decryption
- exception handling
- recipientList
- Parallel processing

### If you are not aware about PGP encryption/decryption, please read it on: https://www.gnupg.org/gph/en/manual.html

### In this example, I have already provided readymade public key for encryption and private key for decryption
 
Please build and follow instructions:

** first change the file location in `application-bundle.properties` so that your file will be written to appropriate location.

** Build this application with `mvn clean install -DskipTests`

Please invoke blow commands in `Karaf` server sequentially to have smooth execution:

** PLEASE DON'T CHANGE THE SEQUENCE OF COMMANDS.
```
    feature:install scr aries-blueprint

    feature:repo-add camel 3.4.0

    feature:install camel-blueprint

    feature:install camel-crypto camel-crypto-cms

    bundle:install -s mvn:com.kodtodya.training.fuse/encryption-decryption-example/1.0-SNAPSHOT
```

** After starting bundle, please check the decrypted file location for file:
