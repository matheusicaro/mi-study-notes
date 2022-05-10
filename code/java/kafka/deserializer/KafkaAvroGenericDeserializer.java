package java.kafka.deserializer;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import java.io.ByteArrayOutputStream;
import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.errors.SerializationException;


public class KafkaAvroGenericDeserializer extends KafkaAvroDeserializer {

    @Override
    public KafkaEventPayloadClass deserialize(String s, byte[] payload) {
        return parse(payload);
    }

    private KafkaEventPayloadClass parse(byte[] payload) {
        try (var baos = new ByteArrayOutputStream()) {
            var record = deserializeToGenericDataRecord(payload);
            var source = record.getSchema();
            var target = KafkaEventPayloadClass.getClassSchema();
            read(source, record, baos);
            return parse(source, target, baos);
        } catch (Exception ex) {
            throw new SerializationException(ex);
        }
    }

    @SneakyThrows
    private void read(Schema source, GenericData.Record record, ByteArrayOutputStream output) {
        var encoder = getEncoder(output);
        var writer = new SpecificDatumWriter<>(source);
        writer.write(record, encoder);
        encoder.flush();
    }

    @SneakyThrows
    private KafkaEventPayloadClass parse(Schema source, Schema target, ByteArrayOutputStream data) {
        var decoder = getDecoder(data);
        var reader = new SpecificDatumReader<KafkaEventPayloadClass>(source, target);
        return reader.read(null, decoder);
    }

    private GenericData.Record deserializeToGenericDataRecord(byte[] payload) {
        return (GenericData.Record) super.deserialize(payload);
    }

    private BinaryEncoder getEncoder(ByteArrayOutputStream baos) {
        return EncoderFactory.get().binaryEncoder(baos, null);
    }

    private BinaryDecoder getDecoder(ByteArrayOutputStream baos) {
        return DecoderFactory.get().binaryDecoder(baos.toByteArray(), null);
    }

}

