package TP2;

import java.net.InetAddress;

public class Client {

    private final InetAddress addr;
    private final int port;

    public Client(InetAddress addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public InetAddress getAddr() {
        return addr;
    }

    public int getPort() {
        return port;
    }
}
