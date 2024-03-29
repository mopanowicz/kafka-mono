/**
 * Autogenerated by Avro
 * <p>
 * DO NOT EDIT DIRECTLY
 */
package com.example.event;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class SimpleTwo extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
    private static final long serialVersionUID = -4765489389604416492L;


    public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SimpleTwo\",\"namespace\":\"com.example.event\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sent\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"amount\",\"type\":[\"null\",\"double\"],\"default\":null}]}");

    public static org.apache.avro.Schema getClassSchema() {
        return SCHEMA$;
    }

    private static final SpecificData MODEL$ = new SpecificData();

    private static final BinaryMessageEncoder<SimpleTwo> ENCODER =
            new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

    private static final BinaryMessageDecoder<SimpleTwo> DECODER =
            new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

    /**
     * Return the BinaryMessageEncoder instance used by this class.
     * @return the message encoder used by this class
     */
    public static BinaryMessageEncoder<SimpleTwo> getEncoder() {
        return ENCODER;
    }

    /**
     * Return the BinaryMessageDecoder instance used by this class.
     * @return the message decoder used by this class
     */
    public static BinaryMessageDecoder<SimpleTwo> getDecoder() {
        return DECODER;
    }

    /**
     * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
     * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
     * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
     */
    public static BinaryMessageDecoder<SimpleTwo> createDecoder(SchemaStore resolver) {
        return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
    }

    /**
     * Serializes this SimpleTwo to a ByteBuffer.
     * @return a buffer holding the serialized data for this instance
     * @throws java.io.IOException if this instance could not be serialized
     */
    public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
        return ENCODER.encode(this);
    }

    /**
     * Deserializes a SimpleTwo from a ByteBuffer.
     * @param b a byte buffer holding serialized data for an instance of this class
     * @return a SimpleTwo instance decoded from the given buffer
     * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
     */
    public static SimpleTwo fromByteBuffer(
            java.nio.ByteBuffer b) throws java.io.IOException {
        return DECODER.decode(b);
    }

    private java.lang.String id;
    private java.lang.Long sent;
    private java.lang.Double amount;

    /**
     * Default constructor.  Note that this does not initialize fields
     * to their default values from the schema.  If that is desired then
     * one should use <code>newBuilder()</code>.
     */
    public SimpleTwo() {
    }

    /**
     * All-args constructor.
     * @param id The new value for id
     * @param sent The new value for sent
     * @param amount The new value for amount
     */
    public SimpleTwo(java.lang.String id, java.lang.Long sent, java.lang.Double amount) {
        this.id = id;
        this.sent = sent;
        this.amount = amount;
    }

    @Override
    public org.apache.avro.specific.SpecificData getSpecificData() {
        return MODEL$;
    }

    @Override
    public org.apache.avro.Schema getSchema() {
        return SCHEMA$;
    }

    // Used by DatumWriter.  Applications should not call.
    @Override
    public java.lang.Object get(int field$) {
        switch (field$) {
            case 0:
                return id;
            case 1:
                return sent;
            case 2:
                return amount;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    // Used by DatumReader.  Applications should not call.
    @Override
    @SuppressWarnings(value = "unchecked")
    public void put(int field$, java.lang.Object value$) {
        switch (field$) {
            case 0:
                id = value$ != null ? value$.toString() : null;
                break;
            case 1:
                sent = (java.lang.Long) value$;
                break;
            case 2:
                amount = (java.lang.Double) value$;
                break;
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + field$);
        }
    }

    /**
     * Gets the value of the 'id' field.
     * @return The value of the 'id' field.
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the value of the 'id' field.
     * @param value the value to set.
     */
    public void setId(java.lang.String value) {
        this.id = value;
    }

    /**
     * Gets the value of the 'sent' field.
     * @return The value of the 'sent' field.
     */
    public java.lang.Long getSent() {
        return sent;
    }


    /**
     * Sets the value of the 'sent' field.
     * @param value the value to set.
     */
    public void setSent(java.lang.Long value) {
        this.sent = value;
    }

    /**
     * Gets the value of the 'amount' field.
     * @return The value of the 'amount' field.
     */
    public java.lang.Double getAmount() {
        return amount;
    }


    /**
     * Sets the value of the 'amount' field.
     * @param value the value to set.
     */
    public void setAmount(java.lang.Double value) {
        this.amount = value;
    }

    /**
     * Creates a new SimpleTwo RecordBuilder.
     * @return A new SimpleTwo RecordBuilder
     */
    public static com.example.event.SimpleTwo.Builder newBuilder() {
        return new com.example.event.SimpleTwo.Builder();
    }

    /**
     * Creates a new SimpleTwo RecordBuilder by copying an existing Builder.
     * @param other The existing builder to copy.
     * @return A new SimpleTwo RecordBuilder
     */
    public static com.example.event.SimpleTwo.Builder newBuilder(com.example.event.SimpleTwo.Builder other) {
        if (other == null) {
            return new com.example.event.SimpleTwo.Builder();
        } else {
            return new com.example.event.SimpleTwo.Builder(other);
        }
    }

    /**
     * Creates a new SimpleTwo RecordBuilder by copying an existing SimpleTwo instance.
     * @param other The existing instance to copy.
     * @return A new SimpleTwo RecordBuilder
     */
    public static com.example.event.SimpleTwo.Builder newBuilder(com.example.event.SimpleTwo other) {
        if (other == null) {
            return new com.example.event.SimpleTwo.Builder();
        } else {
            return new com.example.event.SimpleTwo.Builder(other);
        }
    }

    /**
     * RecordBuilder for SimpleTwo instances.
     */
    @org.apache.avro.specific.AvroGenerated
    public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SimpleTwo>
            implements org.apache.avro.data.RecordBuilder<SimpleTwo> {

        private java.lang.String id;
        private java.lang.Long sent;
        private java.lang.Double amount;

        /** Creates a new Builder */
        private Builder() {
            super(SCHEMA$, MODEL$);
        }

        /**
         * Creates a Builder by copying an existing Builder.
         * @param other The existing Builder to copy.
         */
        private Builder(com.example.event.SimpleTwo.Builder other) {
            super(other);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = other.fieldSetFlags()[0];
            }
            if (isValidValue(fields()[1], other.sent)) {
                this.sent = data().deepCopy(fields()[1].schema(), other.sent);
                fieldSetFlags()[1] = other.fieldSetFlags()[1];
            }
            if (isValidValue(fields()[2], other.amount)) {
                this.amount = data().deepCopy(fields()[2].schema(), other.amount);
                fieldSetFlags()[2] = other.fieldSetFlags()[2];
            }
        }

        /**
         * Creates a Builder by copying an existing SimpleTwo instance
         * @param other The existing instance to copy.
         */
        private Builder(com.example.event.SimpleTwo other) {
            super(SCHEMA$, MODEL$);
            if (isValidValue(fields()[0], other.id)) {
                this.id = data().deepCopy(fields()[0].schema(), other.id);
                fieldSetFlags()[0] = true;
            }
            if (isValidValue(fields()[1], other.sent)) {
                this.sent = data().deepCopy(fields()[1].schema(), other.sent);
                fieldSetFlags()[1] = true;
            }
            if (isValidValue(fields()[2], other.amount)) {
                this.amount = data().deepCopy(fields()[2].schema(), other.amount);
                fieldSetFlags()[2] = true;
            }
        }

        /**
         * Gets the value of the 'id' field.
         * @return The value.
         */
        public java.lang.String getId() {
            return id;
        }


        /**
         * Sets the value of the 'id' field.
         * @param value The value of 'id'.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder setId(java.lang.String value) {
            validate(fields()[0], value);
            this.id = value;
            fieldSetFlags()[0] = true;
            return this;
        }

        /**
         * Checks whether the 'id' field has been set.
         * @return True if the 'id' field has been set, false otherwise.
         */
        public boolean hasId() {
            return fieldSetFlags()[0];
        }


        /**
         * Clears the value of the 'id' field.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder clearId() {
            id = null;
            fieldSetFlags()[0] = false;
            return this;
        }

        /**
         * Gets the value of the 'sent' field.
         * @return The value.
         */
        public java.lang.Long getSent() {
            return sent;
        }


        /**
         * Sets the value of the 'sent' field.
         * @param value The value of 'sent'.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder setSent(java.lang.Long value) {
            validate(fields()[1], value);
            this.sent = value;
            fieldSetFlags()[1] = true;
            return this;
        }

        /**
         * Checks whether the 'sent' field has been set.
         * @return True if the 'sent' field has been set, false otherwise.
         */
        public boolean hasSent() {
            return fieldSetFlags()[1];
        }


        /**
         * Clears the value of the 'sent' field.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder clearSent() {
            sent = null;
            fieldSetFlags()[1] = false;
            return this;
        }

        /**
         * Gets the value of the 'amount' field.
         * @return The value.
         */
        public java.lang.Double getAmount() {
            return amount;
        }


        /**
         * Sets the value of the 'amount' field.
         * @param value The value of 'amount'.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder setAmount(java.lang.Double value) {
            validate(fields()[2], value);
            this.amount = value;
            fieldSetFlags()[2] = true;
            return this;
        }

        /**
         * Checks whether the 'amount' field has been set.
         * @return True if the 'amount' field has been set, false otherwise.
         */
        public boolean hasAmount() {
            return fieldSetFlags()[2];
        }


        /**
         * Clears the value of the 'amount' field.
         * @return This builder.
         */
        public com.example.event.SimpleTwo.Builder clearAmount() {
            amount = null;
            fieldSetFlags()[2] = false;
            return this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public SimpleTwo build() {
            try {
                SimpleTwo record = new SimpleTwo();
                record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
                record.sent = fieldSetFlags()[1] ? this.sent : (java.lang.Long) defaultValue(fields()[1]);
                record.amount = fieldSetFlags()[2] ? this.amount : (java.lang.Double) defaultValue(fields()[2]);
                return record;
            } catch (org.apache.avro.AvroMissingFieldException e) {
                throw e;
            } catch (java.lang.Exception e) {
                throw new org.apache.avro.AvroRuntimeException(e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumWriter<SimpleTwo>
            WRITER$ = (org.apache.avro.io.DatumWriter<SimpleTwo>) MODEL$.createDatumWriter(SCHEMA$);

    @Override
    public void writeExternal(java.io.ObjectOutput out)
            throws java.io.IOException {
        WRITER$.write(this, SpecificData.getEncoder(out));
    }

    @SuppressWarnings("unchecked")
    private static final org.apache.avro.io.DatumReader<SimpleTwo>
            READER$ = (org.apache.avro.io.DatumReader<SimpleTwo>) MODEL$.createDatumReader(SCHEMA$);

    @Override
    public void readExternal(java.io.ObjectInput in)
            throws java.io.IOException {
        READER$.read(this, SpecificData.getDecoder(in));
    }

    @Override
    protected boolean hasCustomCoders() {
        return true;
    }

    @Override
    public void customEncode(org.apache.avro.io.Encoder out)
            throws java.io.IOException {
        out.writeString(this.id);

        if (this.sent == null) {
            out.writeIndex(0);
            out.writeNull();
        } else {
            out.writeIndex(1);
            out.writeLong(this.sent);
        }

        if (this.amount == null) {
            out.writeIndex(0);
            out.writeNull();
        } else {
            out.writeIndex(1);
            out.writeDouble(this.amount);
        }

    }

    @Override
    public void customDecode(org.apache.avro.io.ResolvingDecoder in)
            throws java.io.IOException {
        org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
        if (fieldOrder == null) {
            this.id = in.readString();

            if (in.readIndex() != 1) {
                in.readNull();
                this.sent = null;
            } else {
                this.sent = in.readLong();
            }

            if (in.readIndex() != 1) {
                in.readNull();
                this.amount = null;
            } else {
                this.amount = in.readDouble();
            }

        } else {
            for (int i = 0; i < 3; i++) {
                switch (fieldOrder[i].pos()) {
                    case 0:
                        this.id = in.readString();
                        break;

                    case 1:
                        if (in.readIndex() != 1) {
                            in.readNull();
                            this.sent = null;
                        } else {
                            this.sent = in.readLong();
                        }
                        break;

                    case 2:
                        if (in.readIndex() != 1) {
                            in.readNull();
                            this.amount = null;
                        } else {
                            this.amount = in.readDouble();
                        }
                        break;

                    default:
                        throw new java.io.IOException("Corrupt ResolvingDecoder.");
                }
            }
        }
    }
}










