package com.sportradar.mts.sdk.ws.entities.channel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a channel in the system.
 * Channels can be of various types, such as TV app, agent, mobile app, phone, call centre, SMS, mobile, terminal, retail, or internet.
 * This class provides static methods to create builders for each channel type.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TvAppChannel.class, name = "tv-app"),
        @JsonSubTypes.Type(value = AgentChannel.class, name = "agent"),
        @JsonSubTypes.Type(value = MobileAppChannel.class, name = "mobile-app"),
        @JsonSubTypes.Type(value = PhoneChannel.class, name = "phone"),
        @JsonSubTypes.Type(value = CallCentreChannel.class, name = "call-centre"),
        @JsonSubTypes.Type(value = SmsChannel.class, name = "sms"),
        @JsonSubTypes.Type(value = MobileChannel.class, name = "mobile"),
        @JsonSubTypes.Type(value = TerminalChannel.class, name = "terminal"),
        @JsonSubTypes.Type(value = RetailChannel.class, name = "retail"),
        @JsonSubTypes.Type(value = InternetChannel.class, name = "internet")
})
public class Channel {

    /**
     * Creates a new builder for a TV app channel.
     *
     * @return the builder for a TV app channel
     */
    public static TvAppChannel.Builder newTvAppChannelBuilder() {
        return TvAppChannel.newBuilder();
    }

    /**
     * Creates a new builder for an agent channel.
     *
     * @return the builder for an agent channel
     */
    public static AgentChannel.Builder newAgentChannelBuilder() {
        return AgentChannel.newBuilder();
    }

    /**
     * Creates a new builder for a mobile app channel.
     *
     * @return the builder for a mobile app channel
     */
    public static MobileAppChannel.Builder newMobileAppChannelBuilder() {
        return MobileAppChannel.newBuilder();
    }

    /**
     * Creates a new builder for a phone channel.
     *
     * @return the builder for a phone channel
     */
    public static PhoneChannel.Builder newPhoneChannelBuilder() {
        return PhoneChannel.newBuilder();
    }

    /**
     * Creates a new builder for a call centre channel.
     *
     * @return the builder for a call centre channel
     */
    public static CallCentreChannel.Builder newCallCentreChannelBuilder() {
        return CallCentreChannel.newBuilder();
    }

    /**
     * Creates a new builder for an SMS channel.
     *
     * @return the builder for an SMS channel
     */
    public static SmsChannel.Builder newSmsChannelBuilder() {
        return SmsChannel.newBuilder();
    }

    /**
     * Creates a new builder for a mobile channel.
     *
     * @return the builder for a mobile channel
     */
    public static MobileChannel.Builder newMobileChannelBuilder() {
        return MobileChannel.newBuilder();
    }

    /**
     * Creates a new builder for a terminal channel.
     *
     * @return the builder for a terminal channel
     */
    public static TerminalChannel.Builder newTerminalChannelBuilder() {
        return TerminalChannel.newBuilder();
    }

    /**
     * Creates a new builder for a retail channel.
     *
     * @return the builder for a retail channel
     */
    public static RetailChannel.Builder newRetailChannelBuilder() {
        return RetailChannel.newBuilder();
    }

    /**
     * Creates a new builder for an internet channel.
     *
     * @return the builder for an internet channel
     */
    public static InternetChannel.Builder newInternetChannelBuilder() {
        return InternetChannel.newBuilder();
    }

}
