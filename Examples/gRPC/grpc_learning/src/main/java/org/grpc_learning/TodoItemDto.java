// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TodoService.proto

package org.grpc_learning;

/**
 * Protobuf type {@code org.grpc_learning.TodoItemDto}
 */
public  final class TodoItemDto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:org.grpc_learning.TodoItemDto)
    TodoItemDtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TodoItemDto.newBuilder() to construct.
  private TodoItemDto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TodoItemDto() {
    name_ = "";
    description_ = "";
    status_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TodoItemDto();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TodoItemDto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            id_ = input.readUInt64();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            description_ = s;
            break;
          }
          case 32: {
            int rawValue = input.readEnum();

            status_ = rawValue;
            break;
          }
          case 42: {
            com.google.protobuf.Timestamp.Builder subBuilder = null;
            if (performedAt_ != null) {
              subBuilder = performedAt_.toBuilder();
            }
            performedAt_ = input.readMessage(com.google.protobuf.Timestamp.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(performedAt_);
              performedAt_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.grpc_learning.TodoService.internal_static_org_grpc_learning_TodoItemDto_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.grpc_learning.TodoService.internal_static_org_grpc_learning_TodoItemDto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.grpc_learning.TodoItemDto.class, org.grpc_learning.TodoItemDto.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>uint64 id = 1;</code>
   * @return The id.
   */
  public long getId() {
    return id_;
  }

  public static final int NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DESCRIPTION_FIELD_NUMBER = 3;
  private volatile java.lang.Object description_;
  /**
   * <code>string description = 3;</code>
   * @return The description.
   */
  public java.lang.String getDescription() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      description_ = s;
      return s;
    }
  }
  /**
   * <code>string description = 3;</code>
   * @return The bytes for description.
   */
  public com.google.protobuf.ByteString
      getDescriptionBytes() {
    java.lang.Object ref = description_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      description_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int STATUS_FIELD_NUMBER = 4;
  private int status_;
  /**
   * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
   * @return The enum numeric value on the wire for status.
   */
  public int getStatusValue() {
    return status_;
  }
  /**
   * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
   * @return The status.
   */
  public org.grpc_learning.TodoItemStatus getStatus() {
    @SuppressWarnings("deprecation")
    org.grpc_learning.TodoItemStatus result = org.grpc_learning.TodoItemStatus.valueOf(status_);
    return result == null ? org.grpc_learning.TodoItemStatus.UNRECOGNIZED : result;
  }

  public static final int PERFORMEDAT_FIELD_NUMBER = 5;
  private com.google.protobuf.Timestamp performedAt_;
  /**
   * <code>.google.protobuf.Timestamp performedAt = 5;</code>
   * @return Whether the performedAt field is set.
   */
  public boolean hasPerformedAt() {
    return performedAt_ != null;
  }
  /**
   * <code>.google.protobuf.Timestamp performedAt = 5;</code>
   * @return The performedAt.
   */
  public com.google.protobuf.Timestamp getPerformedAt() {
    return performedAt_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : performedAt_;
  }
  /**
   * <code>.google.protobuf.Timestamp performedAt = 5;</code>
   */
  public com.google.protobuf.TimestampOrBuilder getPerformedAtOrBuilder() {
    return getPerformedAt();
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (id_ != 0L) {
      output.writeUInt64(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, name_);
    }
    if (!getDescriptionBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, description_);
    }
    if (status_ != org.grpc_learning.TodoItemStatus.Performing.getNumber()) {
      output.writeEnum(4, status_);
    }
    if (performedAt_ != null) {
      output.writeMessage(5, getPerformedAt());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, id_);
    }
    if (!getNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, name_);
    }
    if (!getDescriptionBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, description_);
    }
    if (status_ != org.grpc_learning.TodoItemStatus.Performing.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, status_);
    }
    if (performedAt_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getPerformedAt());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.grpc_learning.TodoItemDto)) {
      return super.equals(obj);
    }
    org.grpc_learning.TodoItemDto other = (org.grpc_learning.TodoItemDto) obj;

    if (getId()
        != other.getId()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (!getDescription()
        .equals(other.getDescription())) return false;
    if (status_ != other.status_) return false;
    if (hasPerformedAt() != other.hasPerformedAt()) return false;
    if (hasPerformedAt()) {
      if (!getPerformedAt()
          .equals(other.getPerformedAt())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + DESCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getDescription().hashCode();
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + status_;
    if (hasPerformedAt()) {
      hash = (37 * hash) + PERFORMEDAT_FIELD_NUMBER;
      hash = (53 * hash) + getPerformedAt().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.grpc_learning.TodoItemDto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.grpc_learning.TodoItemDto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.grpc_learning.TodoItemDto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.grpc_learning.TodoItemDto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.grpc_learning.TodoItemDto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code org.grpc_learning.TodoItemDto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:org.grpc_learning.TodoItemDto)
      org.grpc_learning.TodoItemDtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.grpc_learning.TodoService.internal_static_org_grpc_learning_TodoItemDto_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.grpc_learning.TodoService.internal_static_org_grpc_learning_TodoItemDto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.grpc_learning.TodoItemDto.class, org.grpc_learning.TodoItemDto.Builder.class);
    }

    // Construct using org.grpc_learning.TodoItemDto.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = 0L;

      name_ = "";

      description_ = "";

      status_ = 0;

      if (performedAtBuilder_ == null) {
        performedAt_ = null;
      } else {
        performedAt_ = null;
        performedAtBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.grpc_learning.TodoService.internal_static_org_grpc_learning_TodoItemDto_descriptor;
    }

    @java.lang.Override
    public org.grpc_learning.TodoItemDto getDefaultInstanceForType() {
      return org.grpc_learning.TodoItemDto.getDefaultInstance();
    }

    @java.lang.Override
    public org.grpc_learning.TodoItemDto build() {
      org.grpc_learning.TodoItemDto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public org.grpc_learning.TodoItemDto buildPartial() {
      org.grpc_learning.TodoItemDto result = new org.grpc_learning.TodoItemDto(this);
      result.id_ = id_;
      result.name_ = name_;
      result.description_ = description_;
      result.status_ = status_;
      if (performedAtBuilder_ == null) {
        result.performedAt_ = performedAt_;
      } else {
        result.performedAt_ = performedAtBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.grpc_learning.TodoItemDto) {
        return mergeFrom((org.grpc_learning.TodoItemDto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.grpc_learning.TodoItemDto other) {
      if (other == org.grpc_learning.TodoItemDto.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (!other.getDescription().isEmpty()) {
        description_ = other.description_;
        onChanged();
      }
      if (other.status_ != 0) {
        setStatusValue(other.getStatusValue());
      }
      if (other.hasPerformedAt()) {
        mergePerformedAt(other.getPerformedAt());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.grpc_learning.TodoItemDto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.grpc_learning.TodoItemDto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long id_ ;
    /**
     * <code>uint64 id = 1;</code>
     * @return The id.
     */
    public long getId() {
      return id_;
    }
    /**
     * <code>uint64 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(long value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 2;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 2;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 2;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object description_ = "";
    /**
     * <code>string description = 3;</code>
     * @return The description.
     */
    public java.lang.String getDescription() {
      java.lang.Object ref = description_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        description_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     * @return The bytes for description.
     */
    public com.google.protobuf.ByteString
        getDescriptionBytes() {
      java.lang.Object ref = description_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        description_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string description = 3;</code>
     * @param value The description to set.
     * @return This builder for chaining.
     */
    public Builder setDescription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      description_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string description = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDescription() {
      
      description_ = getDefaultInstance().getDescription();
      onChanged();
      return this;
    }
    /**
     * <code>string description = 3;</code>
     * @param value The bytes for description to set.
     * @return This builder for chaining.
     */
    public Builder setDescriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      description_ = value;
      onChanged();
      return this;
    }

    private int status_ = 0;
    /**
     * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
     * @return The enum numeric value on the wire for status.
     */
    public int getStatusValue() {
      return status_;
    }
    /**
     * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
     * @param value The enum numeric value on the wire for status to set.
     * @return This builder for chaining.
     */
    public Builder setStatusValue(int value) {
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
     * @return The status.
     */
    public org.grpc_learning.TodoItemStatus getStatus() {
      @SuppressWarnings("deprecation")
      org.grpc_learning.TodoItemStatus result = org.grpc_learning.TodoItemStatus.valueOf(status_);
      return result == null ? org.grpc_learning.TodoItemStatus.UNRECOGNIZED : result;
    }
    /**
     * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(org.grpc_learning.TodoItemStatus value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      status_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.org.grpc_learning.TodoItemStatus status = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Timestamp performedAt_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> performedAtBuilder_;
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     * @return Whether the performedAt field is set.
     */
    public boolean hasPerformedAt() {
      return performedAtBuilder_ != null || performedAt_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     * @return The performedAt.
     */
    public com.google.protobuf.Timestamp getPerformedAt() {
      if (performedAtBuilder_ == null) {
        return performedAt_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : performedAt_;
      } else {
        return performedAtBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public Builder setPerformedAt(com.google.protobuf.Timestamp value) {
      if (performedAtBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        performedAt_ = value;
        onChanged();
      } else {
        performedAtBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public Builder setPerformedAt(
        com.google.protobuf.Timestamp.Builder builderForValue) {
      if (performedAtBuilder_ == null) {
        performedAt_ = builderForValue.build();
        onChanged();
      } else {
        performedAtBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public Builder mergePerformedAt(com.google.protobuf.Timestamp value) {
      if (performedAtBuilder_ == null) {
        if (performedAt_ != null) {
          performedAt_ =
            com.google.protobuf.Timestamp.newBuilder(performedAt_).mergeFrom(value).buildPartial();
        } else {
          performedAt_ = value;
        }
        onChanged();
      } else {
        performedAtBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public Builder clearPerformedAt() {
      if (performedAtBuilder_ == null) {
        performedAt_ = null;
        onChanged();
      } else {
        performedAt_ = null;
        performedAtBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public com.google.protobuf.Timestamp.Builder getPerformedAtBuilder() {
      
      onChanged();
      return getPerformedAtFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    public com.google.protobuf.TimestampOrBuilder getPerformedAtOrBuilder() {
      if (performedAtBuilder_ != null) {
        return performedAtBuilder_.getMessageOrBuilder();
      } else {
        return performedAt_ == null ?
            com.google.protobuf.Timestamp.getDefaultInstance() : performedAt_;
      }
    }
    /**
     * <code>.google.protobuf.Timestamp performedAt = 5;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
        getPerformedAtFieldBuilder() {
      if (performedAtBuilder_ == null) {
        performedAtBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                getPerformedAt(),
                getParentForChildren(),
                isClean());
        performedAt_ = null;
      }
      return performedAtBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:org.grpc_learning.TodoItemDto)
  }

  // @@protoc_insertion_point(class_scope:org.grpc_learning.TodoItemDto)
  private static final org.grpc_learning.TodoItemDto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.grpc_learning.TodoItemDto();
  }

  public static org.grpc_learning.TodoItemDto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TodoItemDto>
      PARSER = new com.google.protobuf.AbstractParser<TodoItemDto>() {
    @java.lang.Override
    public TodoItemDto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TodoItemDto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TodoItemDto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TodoItemDto> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public org.grpc_learning.TodoItemDto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

