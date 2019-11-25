package com.renderthat.kafkaconsumer.Consumer;
import io.confluent.kafka.serializers.AbstractKafkaAvroDeserializer;
import org.apache.avro.Schema;
import org.apache.kafka.common.errors.SerializationException;

public class CustomKafkaAvroDeserializer extends AbstractKafkaAvroDeserializer{
    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            return deserialize(bytes);
        } catch (SerializationException e)
        {
            return null;
        }
    }

    public Object deserialize(String s, byte[] bytes, Schema readerSchema) {
        try {
            return deserialize(bytes, readerSchema);
        } catch (SerializationException e) {
            return null;
        }
    }

    }
}