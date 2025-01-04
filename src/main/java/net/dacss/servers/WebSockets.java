package net.dacss.servers;

import net.dacss.interfaces.SocketsIface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * WebSockets server.
 * used for all web communications.
 */
public class WebSockets implements SocketsIface {

    SocketAddress webSocketAddress;
    Socket webSocket;

    public WebSockets(SocketAddress webSocketAddress, Socket webSocket) {
        this.webSocketAddress = webSocketAddress;
        this.webSocket = webSocket;
    }

    @Override
    public void startServer() {}

    /**
     * Connects this socket to the server.
     *
     * <p> This method is {@linkplain Thread#interrupt() interruptible} in the
     * following circumstances:
     * <ol>
     *   <li> The socket is {@linkplain SocketChannel#socket() associated} with
     *        a {@link SocketChannel SocketChannel}.
     *        In that case, interrupting a thread establishing a connection will
     *        close the underlying channel and cause this method to throw
     *        {@link ClosedByInterruptException} with the interrupt status set.
     *   <li> The socket uses the system-default socket implementation and a
     *        {@linkplain Thread#isVirtual() virtual thread} is establishing a
     *        connection. In that case, interrupting the virtual thread will
     *        cause it to wakeup and close the socket. This method will then throw
     *        {@code SocketException} with the interrupt status set.
     * </ol>
     *
     * @param endpoint the {@code SocketAddress}
     * @throws IOException                                    if an error occurs during the connection
     * @throws IllegalBlockingModeException if this socket has an associated channel,
     *                                                        and the channel is in non-blocking mode
     * @throws IllegalArgumentException                       if endpoint is null or is a
     *                                                        SocketAddress subclass not supported by this socket
     * @since 1.4
     */
    public void connect(SocketAddress endpoint) throws IOException {
        webSocket.connect(endpoint);
    }

    /**
     * Enable/disable the {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     * socket option.
     * <p>
     * When a TCP connection is closed the connection may remain
     * in a timeout state for a period of time after the connection
     * is closed (typically known as the {@code TIME_WAIT} state
     * or {@code 2MSL} wait state).
     * For applications using a well known socket address or port
     * it may not be possible to bind a socket to the required
     * {@code SocketAddress} if there is a connection in the
     * timeout state involving the socket address or port.
     * <p>
     * Enabling {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     * prior to binding the socket using {@link #bind(SocketAddress)} allows
     * the socket to be bound even though a previous connection is in a timeout
     * state.
     * <p>
     * When a {@code Socket} is created the initial setting
     * of {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is disabled.
     * <p>
     * The behaviour when {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is
     * enabled or disabled after a socket is bound (See {@link #isBound()})
     * is not defined.
     *
     * @param on whether to enable or disable the socket option
     * @throws SocketException if an error occurs enabling or
     *                         disabling the {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR}
     *                         socket option, or the socket is closed.
     * @see #getReuseAddress()
     * @see #bind(SocketAddress)
     * @see #isClosed()
     * @see #isBound()
     * @since 1.4
     */
    public void setReuseAddress(boolean on) throws SocketException {
        webSocket.setReuseAddress(on);
    }

    /**
     * Gets traffic class or type-of-services in the IP header
     * for packets sent from this Socket
     * <p>
     * As the underlying network implementation may ignore the
     * traffic class or type-of-services set using {@link #setTrafficClass(int)}
     * this method may return a different value than was previously
     * set using the {@link #setTrafficClass(int)} method on this Socket.
     *
     * @return the traffic class or type-of-services already set
     * @throws SocketException if there is an error obtaining the
     *                         traffic class or type-of-services value.
     * @see #setTrafficClass(int)
     * @see SocketOptions#IP_TOS
     * @since 1.4
     */
    public int getTrafficClass() throws SocketException {
        return webSocket.getTrafficClass();
    }

    /**
     * Returns the address of the endpoint this socket is connected to, or
     * {@code null} if it is unconnected.
     * <p>
     * If the socket was connected prior to being {@link #close closed},
     * then this method will continue to return the connected address
     * after the socket is closed.
     *
     * @return a {@code SocketAddress} representing the remote endpoint of this
     * socket, or {@code null} if it is not connected yet.
     * @see #getInetAddress()
     * @see #getPort()
     * @see #connect(SocketAddress, int)
     * @see #connect(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getRemoteSocketAddress() {
        return webSocket.getRemoteSocketAddress();
    }

    /**
     * Tests if {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE} is enabled.
     *
     * @return a {@code boolean} indicating whether or not
     * {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE} is enabled.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setOOBInline(boolean)
     * @since 1.4
     */
    public boolean getOOBInline() throws SocketException {
        return webSocket.getOOBInline();
    }

    /**
     * Returns the remote port number to which this socket is connected.
     * <p>
     * If the socket was connected prior to being {@link #close closed},
     * then this method will continue to return the connected port number
     * after the socket is closed.
     *
     * @return the remote port number to which this socket is connected, or
     * 0 if the socket is not connected yet.
     */
    public int getPort() {
        return webSocket.getPort();
    }

    /**
     * Sets traffic class or type-of-services octet in the IP
     * header for packets sent from this Socket.
     * As the underlying network implementation may ignore this
     * value applications should consider it a hint.
     *
     * <P> The tc <B>must</B> be in the range {@code 0 <= tc <=
     * 255} or an IllegalArgumentException will be thrown.
     * <p>Notes:
     * <p>For Internet Protocol v4 the value consists of an
     * {@code integer}, the least significant 8 bits of which
     * represent the value of the TOS octet in IP packets sent by
     * the socket.
     * RFC 1349 defines the TOS values as follows:
     *
     * <UL>
     * <LI><CODE>IPTOS_LOWCOST (0x02)</CODE></LI>
     * <LI><CODE>IPTOS_RELIABILITY (0x04)</CODE></LI>
     * <LI><CODE>IPTOS_THROUGHPUT (0x08)</CODE></LI>
     * <LI><CODE>IPTOS_LOWDELAY (0x10)</CODE></LI>
     * </UL>
     * The last low order bit is always ignored as this
     * corresponds to the MBZ (must be zero) bit.
     * <p>
     * Setting bits in the precedence field may result in a
     * SocketException indicating that the operation is not
     * permitted.
     * <p>
     * As RFC 1122 section 4.2.4.2 indicates, a compliant TCP
     * implementation should, but is not required to, let application
     * change the TOS field during the lifetime of a connection.
     * So whether the type-of-services field can be changed after the
     * TCP connection has been established depends on the implementation
     * in the underlying platform. Applications should not assume that
     * they can change the TOS field after the connection.
     * <p>
     * For Internet Protocol v6 {@code tc} is the value that
     * would be placed into the sin6_flowinfo field of the IP header.
     *
     * @param tc an {@code int} value for the bitset.
     * @throws SocketException if there is an error setting the
     *                         traffic class or type-of-services
     * @see #getTrafficClass
     * @see SocketOptions#IP_TOS
     * @since 1.4
     */
    public void setTrafficClass(int tc) throws SocketException {
        webSocket.setTrafficClass(tc);
    }

    /**
     * Returns the local port number to which this socket is bound.
     * <p>
     * If the socket was bound prior to being {@link #close closed},
     * then this method will continue to return the local port number
     * after the socket is closed.
     *
     * @return the local port number to which this socket is bound or -1
     * if the socket is not bound yet.
     */
    public int getLocalPort() {
        return webSocket.getLocalPort();
    }

    /**
     * Enable/disable {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE}
     * (receipt of TCP urgent data)
     * <p>
     * By default, this option is disabled and TCP urgent data received on a
     * socket is silently discarded. If the user wishes to receive urgent data, then
     * this option must be enabled. When enabled, urgent data is received
     * inline with normal data.
     * <p>
     * Note, only limited support is provided for handling incoming urgent
     * data. In particular, no notification of incoming urgent data is provided
     * and there is no capability to distinguish between normal data and urgent
     * data unless provided by a higher level protocol.
     *
     * @param on {@code true} to enable
     *           {@link SocketOptions#SO_OOBINLINE SO_OOBINLINE},
     *           {@code false} to disable.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #getOOBInline()
     * @since 1.4
     */
    public void setOOBInline(boolean on) throws SocketException {
        webSocket.setOOBInline(on);
    }

    /**
     * Returns whether the write-half of the socket connection is closed.
     *
     * @return true if the output of the socket has been shutdown
     * @see #shutdownOutput
     * @since 1.4
     */
    public boolean isOutputShutdown() {
        return webSocket.isOutputShutdown();
    }

    /**
     * Returns setting for {@link SocketOptions#SO_LINGER SO_LINGER}.
     * -1 returns implies that the
     * option is disabled.
     * <p>
     * The setting only affects socket close.
     *
     * @return the setting for {@link SocketOptions#SO_LINGER SO_LINGER}.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setSoLinger(boolean, int)
     * @since 1.1
     */
    public int getSoLinger() throws SocketException {
        return webSocket.getSoLinger();
    }

    /**
     * Returns the address to which the socket is connected.
     * <p>
     * If the socket was connected prior to being {@link #close closed},
     * then this method will continue to return the connected address
     * after the socket is closed.
     *
     * @return the remote IP address to which this socket is connected,
     * or {@code null} if the socket is not connected.
     */
    public InetAddress getInetAddress() {
        return webSocket.getInetAddress();
    }

    /**
     * Returns whether the read-half of the socket connection is closed.
     *
     * @return true if the input of the socket has been shutdown
     * @see #shutdownInput
     * @since 1.4
     */
    public boolean isInputShutdown() {
        return webSocket.isInputShutdown();
    }

    /**
     * Gets the local address to which the socket is bound.
     * <p>
     * If there is a security manager set, its {@code checkConnect} method is
     * called with the local address and {@code -1} as its arguments to see
     * if the operation is allowed. If the operation is not allowed,
     * the {@link InetAddress#getLoopbackAddress loopback} address is returned.
     *
     * @return the local address to which the socket is bound,
     * the loopback address if denied by the security manager, or
     * the wildcard address if the socket is closed or not bound yet.
     * @since 1.1
     */
    public InetAddress getLocalAddress() {
        return webSocket.getLocalAddress();
    }

    /**
     * Returns the closed state of the socket.
     *
     * @return true if the socket has been closed
     * @see #close
     * @since 1.4
     */
    public boolean isClosed() {
        return webSocket.isClosed();
    }

    /**
     * Send one byte of urgent data on the socket. The byte to be sent is the lowest eight
     * bits of the data parameter. The urgent byte is
     * sent after any preceding writes to the socket OutputStream
     * and before any future writes to the OutputStream.
     *
     * @param data The byte of data to send
     * @throws IOException if there is an error
     *                     sending the data.
     * @since 1.4
     */
    public void sendUrgentData(int data) throws IOException {
        webSocket.sendUrgentData(data);
    }

    /**
     * Tests if {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE} is enabled.
     *
     * @return a {@code boolean} indicating whether or not
     * {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE} is enabled.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setKeepAlive(boolean)
     * @since 1.3
     */
    public boolean getKeepAlive() throws SocketException {
        return webSocket.getKeepAlive();
    }

    /**
     * Gets the value of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option
     * for this {@code Socket}, that is the buffer size used by the platform
     * for input on this {@code Socket}.
     *
     * @return the value of the {@link SocketOptions#SO_RCVBUF SO_RCVBUF}
     * option for this {@code Socket}.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setReceiveBufferSize(int)
     * @since 1.2
     */
    public int getReceiveBufferSize() throws SocketException {
        return webSocket.getReceiveBufferSize();
    }

    /**
     * Enable/disable {@link SocketOptions#SO_LINGER SO_LINGER} with the
     * specified linger time in seconds. The maximum timeout value is platform
     * specific.
     * <p>
     * The setting only affects socket close.
     *
     * @param on     whether or not to linger on.
     * @param linger how long to linger for, if on is true.
     * @throws SocketException          if there is an error
     *                                  in the underlying protocol, such as a TCP error.
     * @throws IllegalArgumentException if the linger value is negative.
     * @see #getSoLinger()
     * @since 1.1
     */
    public void setSoLinger(boolean on, int linger) throws SocketException {
        webSocket.setSoLinger(on, linger);
    }

    /**
     * Enable/disable {@link SocketOptions#SO_KEEPALIVE SO_KEEPALIVE}.
     *
     * @param on whether or not to have socket keep alive turned on.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #getKeepAlive()
     * @since 1.3
     */
    public void setKeepAlive(boolean on) throws SocketException {
        webSocket.setKeepAlive(on);
    }

    /**
     * Returns a set of the socket options supported by this socket.
     * <p>
     * This method will continue to return the set of options even after
     * the socket has been closed.
     *
     * @return A set of the socket options supported by this socket. This set
     * may be empty if the socket's SocketImpl cannot be created.
     * @since 9
     */
    public Set<SocketOption<?>> supportedOptions() {
        return webSocket.supportedOptions();
    }

    /**
     * Returns the binding state of the socket.
     * <p>
     * Note: Closing a socket doesn't clear its binding state, which means
     * this method will return {@code true} for a closed socket
     * (see {@link #isClosed()}) if it was successfully bound prior
     * to being closed.
     *
     * @return true if the socket was successfully bound to an address
     * @see #bind
     * @since 1.4
     */
    public boolean isBound() {
        return webSocket.isBound();
    }

    /**
     * Binds the socket to a local address.
     * <p>
     * If the address is {@code null}, then the system will pick up
     * an ephemeral port and a valid local address to bind the socket.
     *
     * @param bindpoint the {@code SocketAddress} to bind to
     * @throws IOException              if the bind operation fails, or if the socket
     *                                  is already bound.
     * @throws IllegalArgumentException if bindpoint is a
     *                                  SocketAddress subclass not supported by this socket
     * @throws SecurityException        if a security manager exists and its
     *                                  {@code checkListen} method doesn't allow the bind
     *                                  to the local port.
     * @see #isBound
     * @since 1.4
     */
    public void bind(SocketAddress bindpoint) throws IOException {
        webSocket.bind(bindpoint);
    }

    /**
     * Sets the {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option to the
     * specified value for this {@code Socket}. The
     * {@link SocketOptions#SO_RCVBUF SO_RCVBUF} option is
     * used by the platform's networking code as a hint for the size to set
     * the underlying network I/O buffers.
     *
     * <p>Increasing the receive buffer size can increase the performance of
     * network I/O for high-volume connection, while decreasing it can
     * help reduce the backlog of incoming data.
     *
     * <p>Because {@link SocketOptions#SO_RCVBUF SO_RCVBUF} is a hint,
     * applications that want to verify what size the buffers were set to
     * should call {@link #getReceiveBufferSize()}.
     *
     * <p>The value of {@link SocketOptions#SO_RCVBUF SO_RCVBUF} is also used
     * to set the TCP receive window that is advertised to the remote peer.
     * Generally, the window size can be modified at any time when a socket is
     * connected. However, if a receive window larger than 64K is required then
     * this must be requested <B>before</B> the socket is connected to the
     * remote peer. There are two cases to be aware of:
     * <ol>
     * <li>For sockets accepted from a ServerSocket, this must be done by calling
     * {@link ServerSocket#setReceiveBufferSize(int)} before the ServerSocket
     * is bound to a local address.</li>
     * <li>For client sockets, setReceiveBufferSize() must be called before
     * connecting the socket to its remote peer.</li></ol>
     *
     * @param size the size to which to set the receive buffer
     *             size. This value must be greater than 0.
     * @throws IllegalArgumentException if the value is 0 or is
     *                                  negative.
     * @throws SocketException          if there is an error
     *                                  in the underlying protocol, such as a TCP error.
     * @see #getReceiveBufferSize()
     * @see ServerSocket#setReceiveBufferSize(int)
     * @since 1.2
     */
    public void setReceiveBufferSize(int size) throws SocketException {
        webSocket.setReceiveBufferSize(size);
    }

    /**
     * Returns the connection state of the socket.
     * <p>
     * Note: Closing a socket doesn't clear its connection state, which means
     * this method will return {@code true} for a closed socket
     * (see {@link #isClosed()}) if it was successfully connected prior
     * to being closed.
     *
     * @return true if the socket was successfully connected to a server
     * @since 1.4
     */
    public boolean isConnected() {
        return webSocket.isConnected();
    }

    /**
     * Tests if {@link SocketOptions#TCP_NODELAY TCP_NODELAY} is enabled.
     *
     * @return a {@code boolean} indicating whether or not
     * {@link SocketOptions#TCP_NODELAY TCP_NODELAY} is enabled.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setTcpNoDelay(boolean)
     * @since 1.1
     */
    public boolean getTcpNoDelay() throws SocketException {
        return webSocket.getTcpNoDelay();
    }

    /**
     * Returns the value of a socket option.
     *
     * @param name The socket option
     * @return The value of the socket option.
     * @throws UnsupportedOperationException if the socket does not support
     *                                       the option.
     * @throws IOException                   if an I/O error occurs, or if the socket is closed.
     * @throws NullPointerException          if name is {@code null}
     * @throws SecurityException             if a security manager is set and if the socket
     *                                       option requires a security permission and if the caller does
     *                                       not have the required permission.
     *                                       {@link StandardSocketOptions StandardSocketOptions}
     *                                       do not require any security permission.
     * @since 9
     */
    public <T> T getOption(SocketOption<T> name) throws IOException {
        return webSocket.getOption(name);
    }

    /**
     * Disables the output stream for this socket.
     * For a TCP socket, any previously written data will be sent
     * followed by TCP's normal connection termination sequence.
     * <p>
     * If you write to a socket output stream after invoking
     * shutdownOutput() on the socket, the stream will throw
     * an IOException.
     *
     * @throws IOException if an I/O error occurs when shutting down this
     *                     socket.
     * @see Socket#shutdownInput()
     * @see Socket#close()
     * @see Socket#setSoLinger(boolean, int)
     * @see #isOutputShutdown
     * @since 1.3
     */
    public void shutdownOutput() throws IOException {
        webSocket.shutdownOutput();
    }

    /**
     * Returns an output stream for this socket.
     *
     * <p> If this socket has an associated channel then the resulting output
     * stream delegates all of its operations to the channel.  If the channel
     * is in non-blocking mode then the output stream's {@code write}
     * operations will throw an {@link
     * IllegalBlockingModeException}.
     *
     * <p> Writing to the output stream is {@linkplain Thread#interrupt()
     * interruptible} in the following circumstances:
     * <ol>
     *   <li> The socket is {@linkplain SocketChannel#socket() associated} with
     *        a {@link SocketChannel SocketChannel}.
     *        In that case, interrupting a thread writing to the output stream
     *        will close the underlying channel and cause the write method to
     *        throw {@link ClosedByInterruptException} with the interrupt status
     *        set.
     *   <li> The socket uses the system-default socket implementation and a
     *        {@linkplain Thread#isVirtual() virtual thread} is writing to the
     *        output stream. In that case, interrupting the virtual thread will
     *        cause it to wakeup and close the socket. The write method will then
     *        throw {@code SocketException} with the interrupt status set.
     * </ol>
     *
     * <p> Closing the returned {@link OutputStream OutputStream}
     * will close the associated socket.
     *
     * @return an output stream for writing bytes to this socket.
     * @throws IOException if an I/O error occurs when creating the
     *                     output stream or if the socket is not connected.
     * @revised 1.4
     */
    public OutputStream getOutputStream() throws IOException {
        return webSocket.getOutputStream();
    }

    /**
     * Get value of the {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option
     * for this {@code Socket}, that is the buffer size used by the platform
     * for output on this {@code Socket}.
     *
     * @return the value of the {@link SocketOptions#SO_SNDBUF SO_SNDBUF}
     * option for this {@code Socket}.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setSendBufferSize(int)
     * @since 1.2
     */
    public int getSendBufferSize() throws SocketException {
        return webSocket.getSendBufferSize();
    }

    /**
     * Enable/disable {@link SocketOptions#TCP_NODELAY TCP_NODELAY}
     * (disable/enable Nagle's algorithm).
     *
     * @param on {@code true} to enable TCP_NODELAY,
     *           {@code false} to disable.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #getTcpNoDelay()
     * @since 1.1
     */
    public void setTcpNoDelay(boolean on) throws SocketException {
        webSocket.setTcpNoDelay(on);
    }

    /**
     * Returns an input stream for this socket.
     *
     * <p> If this socket has an associated channel then the resulting input
     * stream delegates all of its operations to the channel.  If the channel
     * is in non-blocking mode then the input stream's {@code read} operations
     * will throw an {@link IllegalBlockingModeException}.
     *
     * <p> Reading from the input stream is {@linkplain Thread#interrupt()
     * interruptible} in the following circumstances:
     * <ol>
     *   <li> The socket is {@linkplain SocketChannel#socket() associated} with
     *        a {@link SocketChannel SocketChannel}.
     *        In that case, interrupting a thread reading from the input stream
     *        will close the underlying channel and cause the read method to
     *        throw {@link ClosedByInterruptException} with the interrupt
     *        status set.
     *   <li> The socket uses the system-default socket implementation and a
     *        {@linkplain Thread#isVirtual() virtual thread} is reading from the
     *        input stream. In that case, interrupting the virtual thread will
     *        cause it to wakeup and close the socket. The read method will then
     *        throw {@code SocketException} with the interrupt status set.
     * </ol>
     *
     * <p>Under abnormal conditions the underlying connection may be
     * broken by the remote host or the network software (for example
     * a connection reset in the case of TCP connections). When a
     * broken connection is detected by the network software the
     * following applies to the returned input stream :-
     *
     * <ul>
     *
     *   <li><p>The network software may discard bytes that are buffered
     *   by the socket. Bytes that aren't discarded by the network
     *   software can be read using {@link InputStream#read read}.
     *
     *   <li><p>If there are no bytes buffered on the socket, or all
     *   buffered bytes have been consumed by
     *   {@link InputStream#read read}, then all subsequent
     *   calls to {@link InputStream#read read} will throw an
     *   {@link IOException IOException}.
     *
     *   <li><p>If there are no bytes buffered on the socket, and the
     *   socket has not been closed using {@link #close close}, then
     *   {@link InputStream#available available} will
     *   return {@code 0}.*
     * </ul>
     *
     * <p> Closing the returned {@link InputStream InputStream}
     * will close the associated socket.
     *
     * @return an input stream for reading bytes from this socket.
     * @throws IOException if an I/O error occurs when creating the
     *                     input stream, the socket is closed, the socket is
     *                     not connected, or the socket input has been shutdown
     *                     using {@link #shutdownInput()}
     * @revised 1.4
     */
    public InputStream getInputStream() throws IOException {
        return webSocket.getInputStream();
    }

    /**
     * Returns setting for {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}.
     * 0 returns implies that the option is disabled (i.e., timeout of infinity).
     *
     * @return the setting for {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setSoTimeout(int)
     * @since 1.1
     */
    public int getSoTimeout() throws SocketException {
        return webSocket.getSoTimeout();
    }

    /**
     * Sets the value of a socket option.
     *
     * @param name  The socket option
     * @param value The value of the socket option. A value of {@code null}
     *              may be valid for some options.
     * @return this Socket
     * @throws UnsupportedOperationException if the socket does not support
     *                                       the option.
     * @throws IllegalArgumentException      if the value is not valid for
     *                                       the option.
     * @throws IOException                   if an I/O error occurs, or if the socket is closed.
     * @throws NullPointerException          if name is {@code null}
     * @throws SecurityException             if a security manager is set and if the socket
     *                                       option requires a security permission and if the caller does
     *                                       not have the required permission.
     *                                       {@link StandardSocketOptions StandardSocketOptions}
     *                                       do not require any security permission.
     * @since 9
     */
    public <T> Socket setOption(SocketOption<T> name, T value) throws IOException {
        return webSocket.setOption(name, value);
    }

    /**
     * Places the input stream for this socket at "end of stream".
     * Any data sent to the input stream side of the socket is acknowledged
     * and then silently discarded.
     * <p>
     * If you read from a socket input stream after invoking this method on the
     * socket, the stream's {@code available} method will return 0, and its
     * {@code read} methods will return {@code -1} (end of stream).
     *
     * @throws IOException if an I/O error occurs when shutting down this
     *                     socket.
     * @see Socket#shutdownOutput()
     * @see Socket#close()
     * @see Socket#setSoLinger(boolean, int)
     * @see #isInputShutdown
     * @since 1.3
     */
    public void shutdownInput() throws IOException {
        webSocket.shutdownInput();
    }

    /**
     * Sets the {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option to the
     * specified value for this {@code Socket}.
     * The {@link SocketOptions#SO_SNDBUF SO_SNDBUF} option is used by the
     * platform's networking code as a hint for the size to set the underlying
     * network I/O buffers.
     *
     * <p>Because {@link SocketOptions#SO_SNDBUF SO_SNDBUF} is a hint,
     * applications that want to verify what size the buffers were set to
     * should call {@link #getSendBufferSize()}.
     *
     * @param size the size to which to set the send buffer
     *             size. This value must be greater than 0.
     * @throws SocketException          if there is an error
     *                                  in the underlying protocol, such as a TCP error.
     * @throws IllegalArgumentException if the
     *                                  value is 0 or is negative.
     * @see #getSendBufferSize()
     * @since 1.2
     */
    public void setSendBufferSize(int size) throws SocketException {
        webSocket.setSendBufferSize(size);
    }

    /**
     * Connects this socket to the server with a specified timeout value.
     * A timeout of zero is interpreted as an infinite timeout. The connection
     * will then block until established or an error occurs.
     *
     * <p> This method is {@linkplain Thread#interrupt() interruptible} in the
     * following circumstances:
     * <ol>
     *   <li> The socket is {@linkplain SocketChannel#socket() associated} with
     *        a {@link SocketChannel SocketChannel}.
     *        In that case, interrupting a thread establishing a connection will
     *        close the underlying channel and cause this method to throw
     *        {@link ClosedByInterruptException} with the interrupt status set.
     *   <li> The socket uses the system-default socket implementation and a
     *        {@linkplain Thread#isVirtual() virtual thread} is establishing a
     *        connection. In that case, interrupting the virtual thread will
     *        cause it to wakeup and close the socket. This method will then throw
     *        {@code SocketException} with the interrupt status set.
     * </ol>
     *
     * @param endpoint the {@code SocketAddress}
     * @param timeout  the timeout value to be used in milliseconds.
     * @throws IOException                                    if an error occurs during the connection
     * @throws SocketTimeoutException                         if timeout expires before connecting
     * @throws IllegalBlockingModeException if this socket has an associated channel,
     *                                                        and the channel is in non-blocking mode
     * @throws IllegalArgumentException                       if endpoint is null or is a
     *                                                        SocketAddress subclass not supported by this socket, or
     *                                                        if {@code timeout} is negative
     * @since 1.4
     */
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        webSocket.connect(endpoint, timeout);
    }

    /**
     * Closes this socket.
     * <p>
     * Any thread currently blocked in an I/O operation upon this socket
     * will throw a {@link SocketException}.
     * <p>
     * Once a socket has been closed, it is not available for further networking
     * use (i.e. can't be reconnected or rebound). A new socket needs to be
     * created.
     *
     * <p> Closing this socket will also close the socket's
     * {@link InputStream InputStream} and
     * {@link OutputStream OutputStream}.
     *
     * <p> If this socket has an associated channel then the channel is closed
     * as well.
     *
     * @throws IOException if an I/O error occurs when closing this socket.
     * @revised 1.4
     * @see #isClosed
     */
    public void close() throws IOException {
        webSocket.close();
    }

    /**
     * Enable/disable {@link SocketOptions#SO_TIMEOUT SO_TIMEOUT}
     * with the specified timeout, in milliseconds. With this option set
     * to a positive timeout value, a read() call on the InputStream associated with
     * this Socket will block for only this amount of time.  If the timeout
     * expires, a <B>java.net.SocketTimeoutException</B> is raised, though the
     * Socket is still valid. A timeout of zero is interpreted as an infinite timeout.
     * The option <B>must</B> be enabled prior to entering the blocking operation
     * to have effect.
     *
     * @param timeout the specified timeout, in milliseconds.
     * @throws SocketException          if there is an error in the underlying protocol,
     *                                  such as a TCP error
     * @throws IllegalArgumentException if {@code timeout} is negative
     * @see #getSoTimeout()
     * @since 1.1
     */
    public void setSoTimeout(int timeout) throws SocketException {
        webSocket.setSoTimeout(timeout);
    }

    /**
     * Tests if {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is enabled.
     *
     * @return a {@code boolean} indicating whether or not
     * {@link SocketOptions#SO_REUSEADDR SO_REUSEADDR} is enabled.
     * @throws SocketException if there is an error
     *                         in the underlying protocol, such as a TCP error.
     * @see #setReuseAddress(boolean)
     * @since 1.4
     */
    public boolean getReuseAddress() throws SocketException {
        return webSocket.getReuseAddress();
    }

    /**
     * Sets performance preferences for this socket.
     *
     * <p> Sockets use the TCP/IP protocol by default.  Some implementations
     * may offer alternative protocols which have different performance
     * characteristics than TCP/IP.  This method allows the application to
     * express its own preferences as to how these tradeoffs should be made
     * when the implementation chooses from the available protocols.
     *
     * <p> Performance preferences are described by three integers
     * whose values indicate the relative importance of short connection time,
     * low latency, and high bandwidth.  The absolute values of the integers
     * are irrelevant; in order to choose a protocol the values are simply
     * compared, with larger values indicating stronger preferences. Negative
     * values represent a lower priority than positive values. If the
     * application prefers short connection time over both low latency and high
     * bandwidth, for example, then it could invoke this method with the values
     * {@code (1, 0, 0)}.  If the application prefers high bandwidth above low
     * latency, and low latency above short connection time, then it could
     * invoke this method with the values {@code (0, 1, 2)}.
     *
     * <p> Invoking this method after this socket has been connected
     * will have no effect.
     *
     * @param connectionTime An {@code int} expressing the relative importance of a short
     *                       connection time
     * @param latency        An {@code int} expressing the relative importance of low
     *                       latency
     * @param bandwidth      An {@code int} expressing the relative importance of high
     *                       bandwidth
     * @since 1.5
     */
    public void setPerformancePreferences(int connectionTime, int latency, int bandwidth) {
        webSocket.setPerformancePreferences(connectionTime, latency, bandwidth);
    }

    /**
     * Returns the address of the endpoint this socket is bound to.
     * <p>
     * If a socket bound to an endpoint represented by an
     * {@code InetSocketAddress } is {@link #close closed},
     * then this method will continue to return an {@code InetSocketAddress}
     * after the socket is closed. In that case the returned
     * {@code InetSocketAddress}'s address is the
     * {@link InetAddress#isAnyLocalAddress wildcard} address
     * and its port is the local port that it was bound to.
     * <p>
     * If there is a security manager set, its {@code checkConnect} method is
     * called with the local address and {@code -1} as its arguments to see
     * if the operation is allowed. If the operation is not allowed,
     * a {@code SocketAddress} representing the
     * {@link InetAddress#getLoopbackAddress loopback} address and the local
     * port to which this socket is bound is returned.
     *
     * @return a {@code SocketAddress} representing the local endpoint of
     * this socket, or a {@code SocketAddress} representing the
     * loopback address if denied by the security manager, or
     * {@code null} if the socket is not bound yet.
     * @see #getLocalAddress()
     * @see #getLocalPort()
     * @see #bind(SocketAddress)
     * @since 1.4
     */
    public SocketAddress getLocalSocketAddress() {
        return webSocket.getLocalSocketAddress();
    }

    /**
     * Returns the unique {@link SocketChannel SocketChannel}
     * object associated with this socket, if any.
     *
     * <p> A socket will have a channel if, and only if, the channel itself was
     * created via the {@link SocketChannel#open
     * SocketChannel.open} or {@link
     * ServerSocketChannel#accept ServerSocketChannel.accept}
     * methods.
     *
     * @return the socket channel associated with this socket,
     * or {@code null} if this socket was not created
     * for a channel
     * @since 1.4
     */
    public SocketChannel getChannel() {
        return webSocket.getChannel();
    }
}
