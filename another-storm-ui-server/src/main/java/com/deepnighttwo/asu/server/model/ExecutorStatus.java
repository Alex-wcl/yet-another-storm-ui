package com.deepnighttwo.asu.server.model;

import com.google.common.base.Splitter;

import java.util.regex.Pattern;

/**
 * User: mzang
 * Date: 2014-10-09
 * Time: 18:21
 */
public class ExecutorStatus implements Comparable<ExecutorStatus> {
    int port;
    String host;

    String executorId;
    String uptime;

    String topoName;
    String topoId;
    String compId;
    String compType;

    long emitted;
    long transferred;
    long capacity;
    long executeLatency;
    long executed;
    String processLatency;
    long acked;
    long failed;

    private static final Splitter SPLITTER = Splitter.on(Pattern.compile("[\\[|\\-|\\]]")).omitEmptyStrings().trimResults();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExecutorStatus)) return false;

        ExecutorStatus that = (ExecutorStatus) o;

        if (executorId != null ? !executorId.equals(that.executorId) : that.executorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return executorId != null ? executorId.hashCode() : 0;
    }

    public String getExecutorId() {
        return executorId;
    }

    public void setExecutorId(String executorId) {
        this.executorId = executorId;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public long getEmitted() {
        return emitted;
    }

    public void setEmitted(long emitted) {
        this.emitted = emitted;
    }

    public long getTransferred() {
        return transferred;
    }

    public void setTransferred(long ransferred) {
        this.transferred = ransferred;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getExecuteLatency() {
        return executeLatency;
    }

    public void setExecuteLatency(long executeLatency) {
        this.executeLatency = executeLatency;
    }

    public long getExecuted() {
        return executed;
    }

    public void setExecuted(long executed) {
        this.executed = executed;
    }

    public String getProcessLatency() {
        return processLatency;
    }

    public void setProcessLatency(String processLatency) {
        this.processLatency = processLatency;
    }

    public long getAcked() {
        return acked;
    }

    public void setAcked(long acked) {
        this.acked = acked;
    }

    public long getFailed() {
        return failed;
    }

    public void setFailed(long failed) {
        this.failed = failed;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTopoName() {
        return topoName;
    }

    public void setTopoName(String topoName) {
        this.topoName = topoName;
    }

    public String getTopoId() {
        return topoId;
    }

    public void setTopoId(String topoId) {
        this.topoId = topoId;
    }

    public String getCompId() {
        return compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    @Override
    public int compareTo(ExecutorStatus o) {
        Iterable<String> t1s = SPLITTER.split(this.executorId);
        String t1 = "-1";
        for (String str : t1s) {
            t1 = str;
            break;
        }

        Iterable<String> t2s = SPLITTER.split(o.executorId);
        String t2 = "-1";
        for (String str : t2s) {
            t2 = str;
            break;
        }

        return Integer.parseInt(t1) - Integer.parseInt(t2);
    }
}