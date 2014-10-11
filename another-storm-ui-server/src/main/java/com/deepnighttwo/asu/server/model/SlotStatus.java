package com.deepnighttwo.asu.server.model;

import java.util.Comparator;
import java.util.List;

/**
 * User: mzang
 * Date: 2014-10-09
 * Time: 18:18
 */
public class SlotStatus implements Comparator<SlotStatus> {
    String host;
    String ip;
    int port;

    List<ExecutorStatus> stats;

    @Override
    public int compare(SlotStatus o1, SlotStatus o2) {
        return o1.getPort() - o2.getPort();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlotStatus)) return false;

        SlotStatus that = (SlotStatus) o;

        if (port != that.port) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<ExecutorStatus> getStats() {
        return stats;
    }

    public void setStats(List<ExecutorStatus> stats) {
        this.stats = stats;
    }
}
