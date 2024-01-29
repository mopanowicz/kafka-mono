/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.example;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class AvroMessage extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6755538634977558020L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroMessage\",\"namespace\":\"com.example\",\"fields\":[{\"name\":\"text\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}]},{\"name\":\"localDate\",\"type\":[\"null\",{\"type\":\"int\",\"logicalType\":\"date\"}]},{\"name\":\"amount\",\"type\":[\"null\",{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":4}],\"default\":null},{\"name\":\"localDateTime\",\"type\":[\"null\",{\"type\":\"long\",\"logicalType\":\"local-timestamp-millis\"}],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.DateConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.LocalTimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<AvroMessage> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<AvroMessage> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<AvroMessage> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<AvroMessage> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<AvroMessage> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this AvroMessage to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a AvroMessage from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a AvroMessage instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static AvroMessage fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.String text;
  private java.time.LocalDate localDate;
  private java.math.BigDecimal amount;
  private java.time.LocalDateTime localDateTime;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroMessage() {}

  /**
   * All-args constructor.
   * @param text The new value for text
   * @param localDate The new value for localDate
   * @param amount The new value for amount
   * @param localDateTime The new value for localDateTime
   */
  public AvroMessage(java.lang.String text, java.time.LocalDate localDate, java.math.BigDecimal amount, java.time.LocalDateTime localDateTime) {
    this.text = text;
    this.localDate = localDate;
    this.amount = amount;
    this.localDateTime = localDateTime;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return text;
    case 1: return localDate;
    case 2: return amount;
    case 3: return localDateTime;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: text = value$ != null ? value$.toString() : null; break;
    case 1: localDate = (java.time.LocalDate)value$; break;
    case 2: amount = (java.math.BigDecimal)value$; break;
    case 3: localDateTime = (java.time.LocalDateTime)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'text' field.
   * @return The value of the 'text' field.
   */
  public java.lang.String getText() {
    return text;
  }


  /**
   * Sets the value of the 'text' field.
   * @param value the value to set.
   */
  public void setText(java.lang.String value) {
    this.text = value;
  }

  /**
   * Gets the value of the 'localDate' field.
   * @return The value of the 'localDate' field.
   */
  public java.time.LocalDate getLocalDate() {
    return localDate;
  }


  /**
   * Sets the value of the 'localDate' field.
   * @param value the value to set.
   */
  public void setLocalDate(java.time.LocalDate value) {
    this.localDate = value;
  }

  /**
   * Gets the value of the 'amount' field.
   * @return The value of the 'amount' field.
   */
  public java.math.BigDecimal getAmount() {
    return amount;
  }


  /**
   * Sets the value of the 'amount' field.
   * @param value the value to set.
   */
  public void setAmount(java.math.BigDecimal value) {
    this.amount = value;
  }

  /**
   * Gets the value of the 'localDateTime' field.
   * @return The value of the 'localDateTime' field.
   */
  public java.time.LocalDateTime getLocalDateTime() {
    return localDateTime;
  }


  /**
   * Sets the value of the 'localDateTime' field.
   * @param value the value to set.
   */
  public void setLocalDateTime(java.time.LocalDateTime value) {
    this.localDateTime = value;
  }

  /**
   * Creates a new AvroMessage RecordBuilder.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.example.AvroMessage.Builder newBuilder() {
    return new com.example.AvroMessage.Builder();
  }

  /**
   * Creates a new AvroMessage RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.example.AvroMessage.Builder newBuilder(com.example.AvroMessage.Builder other) {
    if (other == null) {
      return new com.example.AvroMessage.Builder();
    } else {
      return new com.example.AvroMessage.Builder(other);
    }
  }

  /**
   * Creates a new AvroMessage RecordBuilder by copying an existing AvroMessage instance.
   * @param other The existing instance to copy.
   * @return A new AvroMessage RecordBuilder
   */
  public static com.example.AvroMessage.Builder newBuilder(com.example.AvroMessage other) {
    if (other == null) {
      return new com.example.AvroMessage.Builder();
    } else {
      return new com.example.AvroMessage.Builder(other);
    }
  }

  /**
   * RecordBuilder for AvroMessage instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroMessage>
    implements org.apache.avro.data.RecordBuilder<AvroMessage> {

    private java.lang.String text;
    private java.time.LocalDate localDate;
    private java.math.BigDecimal amount;
    private java.time.LocalDateTime localDateTime;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.example.AvroMessage.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.text)) {
        this.text = data().deepCopy(fields()[0].schema(), other.text);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.localDate)) {
        this.localDate = data().deepCopy(fields()[1].schema(), other.localDate);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.localDateTime)) {
        this.localDateTime = data().deepCopy(fields()[3].schema(), other.localDateTime);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing AvroMessage instance
     * @param other The existing instance to copy.
     */
    private Builder(com.example.AvroMessage other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.text)) {
        this.text = data().deepCopy(fields()[0].schema(), other.text);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.localDate)) {
        this.localDate = data().deepCopy(fields()[1].schema(), other.localDate);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.amount)) {
        this.amount = data().deepCopy(fields()[2].schema(), other.amount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.localDateTime)) {
        this.localDateTime = data().deepCopy(fields()[3].schema(), other.localDateTime);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'text' field.
      * @return The value.
      */
    public java.lang.String getText() {
      return text;
    }


    /**
      * Sets the value of the 'text' field.
      * @param value The value of 'text'.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder setText(java.lang.String value) {
      validate(fields()[0], value);
      this.text = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'text' field has been set.
      * @return True if the 'text' field has been set, false otherwise.
      */
    public boolean hasF1() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'text' field.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder clearF1() {
      text = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'localDate' field.
      * @return The value.
      */
    public java.time.LocalDate getLocalDate() {
      return localDate;
    }


    /**
      * Sets the value of the 'localDate' field.
      * @param value The value of 'localDate'.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder setLocalDate(java.time.LocalDate value) {
      validate(fields()[1], value);
      this.localDate = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'localDate' field has been set.
      * @return True if the 'localDate' field has been set, false otherwise.
      */
    public boolean hasLocalDate() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'localDate' field.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder clearLocalDate() {
      localDate = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'amount' field.
      * @return The value.
      */
    public java.math.BigDecimal getAmount() {
      return amount;
    }


    /**
      * Sets the value of the 'amount' field.
      * @param value The value of 'amount'.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder setAmount(java.math.BigDecimal value) {
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
    public com.example.AvroMessage.Builder clearAmount() {
      amount = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'localDateTime' field.
      * @return The value.
      */
    public java.time.LocalDateTime getLocalDateTime() {
      return localDateTime;
    }


    /**
      * Sets the value of the 'localDateTime' field.
      * @param value The value of 'localDateTime'.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder setLocalDateTime(java.time.LocalDateTime value) {
      validate(fields()[3], value);
      this.localDateTime = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'localDateTime' field has been set.
      * @return True if the 'localDateTime' field has been set, false otherwise.
      */
    public boolean hasLocalDateTime() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'localDateTime' field.
      * @return This builder.
      */
    public com.example.AvroMessage.Builder clearLocalDateTime() {
      localDateTime = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public AvroMessage build() {
      try {
        AvroMessage record = new AvroMessage();
        record.text = fieldSetFlags()[0] ? this.text : (java.lang.String) defaultValue(fields()[0]);
        record.localDate = fieldSetFlags()[1] ? this.localDate : (java.time.LocalDate) defaultValue(fields()[1]);
        record.amount = fieldSetFlags()[2] ? this.amount : (java.math.BigDecimal) defaultValue(fields()[2]);
        record.localDateTime = fieldSetFlags()[3] ? this.localDateTime : (java.time.LocalDateTime) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<AvroMessage>
    WRITER$ = (org.apache.avro.io.DatumWriter<AvroMessage>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<AvroMessage>
    READER$ = (org.apache.avro.io.DatumReader<AvroMessage>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}










