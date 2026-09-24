package org.openmuc.jeebus.ship.api;

import org.openmuc.jeebus.ship.node.ShipNodeParameters;

import java.net.InetSocketAddress;
import java.time.Instant;

/**
 * This class contains information about a SHIP connection. It is used to provide a
 * snapshot of the connection state at a specific point in time, therefore the data
 * can't change.
 */
public class ShipConnectionInfoSnapshot {
    private final ConnectionTypeEnum connectionType;
    private final String ski;
    private final InetSocketAddress socketAddress;
    private final Integer trustLevel;
    private final boolean isDataExchangeEstablished;
    private final Instant connectionStartDate;

    public ShipConnectionInfoSnapshot(
        ConnectionTypeEnum connectionType,
        String ski,
        InetSocketAddress socketAddress,
        Integer trustLevel,
        boolean isDataExchangeEstablished,
        Instant connectionStartDate
    ) {
        this.connectionType = connectionType;
        this.ski = ski;
        this.socketAddress = socketAddress;
        this.trustLevel = trustLevel;
        this.isDataExchangeEstablished = isDataExchangeEstablished;
        this.connectionStartDate = connectionStartDate;
    }

    /**
     * Returns who initiated the connection.
     *
     * @return the connection type, either CLIENT_CONNECTION_TO_PEER or
     * PEER_CONNECTED_TO_SERVER
     */
    public ConnectionTypeEnum getConnectionType() {
        return this.connectionType;
    }

    /**
     * Returns the SKI (Subject Key Identifier) of the remote partner. The SKI is a
     * unique identifier for the public key of the remote partner.
     * Can be null if the tls handshake is not done yet.
     *
     * @return SKI String, or null
     */
    public String getSki() {
        return this.ski;
    }

    /**
     * Returns the socket address (ip address + port) of the remote partner.
     *
     * @return the socket address of the remote partner
     */
    public InetSocketAddress getSocketAddress() {
        return this.socketAddress;
    }

    /**
     * Returns the trust level that indicates the level of trust in the connection. A
     * value of &ge; 32 means that the user verified the SKI. It is between
     * {@link ShipNodeParameters#MINIMAL_TRUST_LEVEL 8} and
     * {@link ShipNodeParameters#MAXIMAL_TRUST_LEVEL 96}.
     *
     * @return the trust level of the connection
     */
    public Integer getTrustLevel() {
        return this.trustLevel;
    }

    /**
     * Returns true when the connection to a device reached the state "Connection
     * Data Exchange". This is the State where non-SHIP-specific Messages (i.e.
     * SPINE) are exchanged.
     *
     * @return true if the connection reached the state "Connection Data Exchange",
     * false otherwise
     */
    public boolean isDataExchangeEstablished() {
        return this.isDataExchangeEstablished;
    }

    /**
     * Returns the date when the connection was started.
     *
     * @return the date when the connection was started as Instant
     */
    public Instant getConnectionStartDate() {
        return this.connectionStartDate;
    }

    public enum ConnectionTypeEnum {
        /**
         * The connection was initiated by this node to a remote peer.
         */
        CLIENT_CONNECTION_TO_PEER,
        /**
         * The connection was initiated by the remote peer.
         */
        PEER_CONNECTED_TO_SERVER
    }
}
