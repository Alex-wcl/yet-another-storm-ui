package com.deepnighttwo.asu.server.model;

/**
 * User: mzang
 * Date: 2014-10-09
 * Time: 18:21
 */
public class ExecutorStatus {
    String executorId;
    String uptime;
    long emitted;
    long ransferred;
    long capacity;
    long executeLatency;
    long executed;
    long processLatency;
    long acked;
    long failed;

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

    public long getRansferred() {
        return ransferred;
    }

    public void setRansferred(long ransferred) {
        this.ransferred = ransferred;
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

    public long getProcessLatency() {
        return processLatency;
    }

    public void setProcessLatency(long processLatency) {
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
}