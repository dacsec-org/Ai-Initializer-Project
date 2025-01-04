package net.dacss.servers;

import net.dacss.interfaces.SocketsIface;

import io.netty.channel.unix.DatagramSocketAddress;
import io.netty.channel.unix.DomainDatagramSocketAddress;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.channel.unix.Socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;

/**
 * Unix sockets server with delegate methods for local communications.
 */
public class UnixSockets implements SocketsIface {

    UnixDomainSocketAddress address;
    Socket unixSocket;

    public UnixSockets(UnixDomainSocketAddress address, Socket unixSocket) {
        this.address = address;
        this.unixSocket = unixSocket;
    }

    @Override
    public void startServer() {}


    public void shutdown() throws IOException {
        unixSocket.shutdown();
    }

    public DatagramSocketAddress recvFromAddress(long memoryAddress, int pos, int limit) throws IOException {
        return unixSocket.recvFromAddress(memoryAddress, pos, limit);
    }

    public void setIntOpt(int level, int optname, int optvalue) throws IOException {
        unixSocket.setIntOpt(level, optname, optvalue);
    }

    public DomainSocketAddress remoteDomainSocketAddress() {
        return unixSocket.remoteDomainSocketAddress();
    }

    public void setKeepAlive(boolean keepAlive) throws IOException {
        unixSocket.setKeepAlive(keepAlive);
    }

    public int sendTo(ByteBuffer buf, int pos, int limit, InetAddress addr, int port) throws IOException {
        return unixSocket.sendTo(buf, pos, limit, addr, port);
    }

    public int recvFd() throws IOException {
        return unixSocket.recvFd();
    }

    public DomainSocketAddress localDomainSocketAddress() {
        return unixSocket.localDomainSocketAddress();
    }

    public int write(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.write(buf, pos, limit);
    }

    /**
     * Close the file descriptor.
     */
    public void close() throws IOException {
        unixSocket.close();
    }

    public boolean isBroadcast() throws IOException {
        return unixSocket.isBroadcast();
    }

    public void setBroadcast(boolean broadcast) throws IOException {
        unixSocket.setBroadcast(broadcast);
    }

    public int send(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.send(buf, pos, limit);
    }

    public int readAddress(long address, int pos, int limit) throws IOException {
        return unixSocket.readAddress(address, pos, limit);
    }

    public int getSoError() throws IOException {
        return unixSocket.getSoError();
    }

    public int accept(byte[] addr) throws IOException {
        return unixSocket.accept(addr);
    }

    /**
     * Return the int value of the filedescriptor.
     */
    public int intValue() {
        return unixSocket.intValue();
    }

    public int sendToAddressDomainSocket(long memoryAddress, int pos, int limit, byte[] path) throws IOException {
        return unixSocket.sendToAddressDomainSocket(memoryAddress, pos, limit, path);
    }

    public boolean isTcpNoDelay() throws IOException {
        return unixSocket.isTcpNoDelay();
    }

    public void setSoLinger(int soLinger) throws IOException {
        unixSocket.setSoLinger(soLinger);
    }

    public int sendToAddresses(long memoryAddress, int length, InetAddress addr, int port, boolean fastOpen) throws IOException {
        return unixSocket.sendToAddresses(memoryAddress, length, addr, port, fastOpen);
    }

    public void disconnect() throws IOException {
        unixSocket.disconnect();
    }

    public int sendToDomainSocket(ByteBuffer buf, int pos, int limit, byte[] path) throws IOException {
        return unixSocket.sendToDomainSocket(buf, pos, limit, path);
    }

    public void setReusePort(boolean reusePort) throws IOException {
        unixSocket.setReusePort(reusePort);
    }

    public int recvAddress(long address, int pos, int limit) throws IOException {
        return unixSocket.recvAddress(address, pos, limit);
    }

    public boolean isReusePort() throws IOException {
        return unixSocket.isReusePort();
    }

    public void setSendBufferSize(int sendBufferSize) throws IOException {
        unixSocket.setSendBufferSize(sendBufferSize);
    }

    public boolean isOutputShutdown() {
        return unixSocket.isOutputShutdown();
    }

    public DomainDatagramSocketAddress recvFromAddressDomainSocket(long memoryAddress, int pos, int limit) throws IOException {
        return unixSocket.recvFromAddressDomainSocket(memoryAddress, pos, limit);
    }

    public long writev(ByteBuffer[] buffers, int offset, int length, long maxBytesToWrite) throws IOException {
        return unixSocket.writev(buffers, offset, length, maxBytesToWrite);
    }

    public boolean connect(SocketAddress socketAddress) throws IOException {
        return unixSocket.connect(socketAddress);
    }

    public int getSendBufferSize() throws IOException {
        return unixSocket.getSendBufferSize();
    }

    public boolean isShutdown() {
        return unixSocket.isShutdown();
    }

    public int getIntOpt(int level, int optname) throws IOException {
        return unixSocket.getIntOpt(level, optname);
    }

    public int sendAddress(long address, int pos, int limit) throws IOException {
        return unixSocket.sendAddress(address, pos, limit);
    }

    public int getTrafficClass() throws IOException {
        return unixSocket.getTrafficClass();
    }

    public int writeAddress(long address, int pos, int limit) throws IOException {
        return unixSocket.writeAddress(address, pos, limit);
    }

    public DomainDatagramSocketAddress recvFromDomainSocket(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.recvFromDomainSocket(buf, pos, limit);
    }

    public InetSocketAddress localAddress() {
        return unixSocket.localAddress();
    }

    public void setRawOpt(int level, int optname, ByteBuffer optvalue) throws IOException {
        unixSocket.setRawOpt(level, optname, optvalue);
    }

    public int sendToAddress(long memoryAddress, int pos, int limit, InetAddress addr, int port, boolean fastOpen) throws IOException {
        return unixSocket.sendToAddress(memoryAddress, pos, limit, addr, port, fastOpen);
    }

    public void setReceiveBufferSize(int receiveBufferSize) throws IOException {
        unixSocket.setReceiveBufferSize(receiveBufferSize);
    }

    public int sendToAddresses(long memoryAddress, int length, InetAddress addr, int port) throws IOException {
        return unixSocket.sendToAddresses(memoryAddress, length, addr, port);
    }

    public void listen(int backlog) throws IOException {
        unixSocket.listen(backlog);
    }

    /**
     * Returns {@code true} if the file descriptor is open.
     */
    public boolean isOpen() {
        return unixSocket.isOpen();
    }

    public DatagramSocketAddress recvFrom(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.recvFrom(buf, pos, limit);
    }

    public int getSoLinger() throws IOException {
        return unixSocket.getSoLinger();
    }

    public void setTrafficClass(int trafficClass) throws IOException {
        unixSocket.setTrafficClass(trafficClass);
    }

    public int read(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.read(buf, pos, limit);
    }

    public int sendTo(ByteBuffer buf, int pos, int limit, InetAddress addr, int port, boolean fastOpen) throws IOException {
        return unixSocket.sendTo(buf, pos, limit, addr, port, fastOpen);
    }

    public InetSocketAddress remoteAddress() {
        return unixSocket.remoteAddress();
    }

    public int recv(ByteBuffer buf, int pos, int limit) throws IOException {
        return unixSocket.recv(buf, pos, limit);
    }

    public boolean finishConnect() throws IOException {
        return unixSocket.finishConnect();
    }

    public void shutdown(boolean read, boolean write) throws IOException {
        unixSocket.shutdown(read, write);
    }

    public boolean isReuseAddress() throws IOException {
        return unixSocket.isReuseAddress();
    }

    public void getRawOpt(int level, int optname, ByteBuffer out) throws IOException {
        unixSocket.getRawOpt(level, optname, out);
    }

    public void bind(SocketAddress socketAddress) throws IOException {
        unixSocket.bind(socketAddress);
    }

    public int sendToAddressesDomainSocket(long memoryAddress, int length, byte[] path) throws IOException {
        return unixSocket.sendToAddressesDomainSocket(memoryAddress, length, path);
    }

    public void setReuseAddress(boolean reuseAddress) throws IOException {
        unixSocket.setReuseAddress(reuseAddress);
    }

    public long writevAddresses(long memoryAddress, int length) throws IOException {
        return unixSocket.writevAddresses(memoryAddress, length);
    }

    public int getReceiveBufferSize() throws IOException {
        return unixSocket.getReceiveBufferSize();
    }

    public int sendFd(int fdToSend) throws IOException {
        return unixSocket.sendFd(fdToSend);
    }

    public boolean isInputShutdown() {
        return unixSocket.isInputShutdown();
    }

    public void setTcpNoDelay(boolean tcpNoDelay) throws IOException {
        unixSocket.setTcpNoDelay(tcpNoDelay);
    }

    public int sendToAddress(long memoryAddress, int pos, int limit, InetAddress addr, int port) throws IOException {
        return unixSocket.sendToAddress(memoryAddress, pos, limit, addr, port);
    }

    public boolean isKeepAlive() throws IOException {
        return unixSocket.isKeepAlive();
    }
}
