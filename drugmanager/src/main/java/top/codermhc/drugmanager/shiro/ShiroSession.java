package top.codermhc.drugmanager.shiro;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;

public class ShiroSession extends SimpleSession implements Serializable {

    private boolean changed = false;

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public ShiroSession() {
        super();
        setChanged(true);
    }

    public ShiroSession(String host) {
        super(host);
        setChanged(true);
    }

    @Override
    public void setId(Serializable id) {
        super.setId(id);
        setChanged(true);
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        super.setStartTimestamp(startTimestamp);
        setChanged(true);
    }

    @Override
    public void setStopTimestamp(Date stopTimestamp) {
        super.setStopTimestamp(stopTimestamp);
        setChanged(true);
    }

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        super.setLastAccessTime(lastAccessTime);
    }

    @Override
    public void setExpired(boolean expired) {
        super.setExpired(expired);
        setChanged(true);
    }

    @Override
    public void setTimeout(long timeout) {
        super.setTimeout(timeout);
        setChanged(true);
    }

    @Override
    public void setHost(String host) {
        super.setHost(host);
        setChanged(true);
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        super.setAttributes(attributes);
        setChanged(true);
    }

    @Override
    public void touch() {
        super.touch();
        setChanged(true);
    }

    @Override
    public void stop() {
        super.stop();
        setChanged(true);
    }

    @Override
    protected void expire() {
        super.expire();
        setChanged(true);
    }

    @Override
    public void validate() throws InvalidSessionException {
        super.validate();
    }

    @Override
    public void setAttribute(Object key, Object value) {
        super.setAttribute(key, value);
        setChanged(true);
    }

    @Override
    public Object removeAttribute(Object key) {
        setChanged(true);
        return super.removeAttribute(key);
    }
}
