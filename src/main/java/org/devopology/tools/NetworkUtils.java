package org.devopology.tools;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by Doug on 10/28/2016.
 */
public class NetworkUtils {

    private Toolset toolset = null;

    public NetworkUtils(Toolset toolset) {
        this.toolset = toolset;
    }

    public boolean canConnect(String hostname, int port) throws IOException {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(hostname, port), 1000);
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }
}
