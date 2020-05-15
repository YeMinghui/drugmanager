package top.codermhc.drugmanager.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.SerializationUtils;

@Slf4j
public class ShiroSessionDAO extends AbstractSessionDAO {

    private Cache<String, byte[]> cache;

    public ShiroSessionDAO(Cache<String, byte[]> cache) {
        this.cache = cache;
    }

    private void saveSession(Session session) {
        if (session != null && session.getId() != null) {
            log.debug("shiro session saved, sessionId={}, session={}", session.getId(), session);
            String key = session.getId().toString();
            byte[] value = SerializationUtils.serialize(session);
            cache.put(key, value);
        } else {
            log.warn("shiro session invalid.");
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        log.debug("shiro session create. session={}", session);
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session,sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            log.warn("shiro session read, no sessionId.");
            return null;
        }
        String key = sessionId.toString();
        byte[] value  = cache.get(key);
        Session session = (Session) SerializationUtils.deserialize(value);
        log.debug("shiro session read, sessionId={}, session={}", sessionId, session);
        return session;
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        try {
            if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
                return;
            }

            if (session instanceof ShiroSession) {
                ShiroSession shiroSession = (ShiroSession) session;
                if (!shiroSession.isChanged()) {
                    return;
                }
                shiroSession.setChanged(false);
            }

            log.debug("shiro session update. session={}", session);
            saveSession(session);
        } catch (Exception e) {
            log.error("shiro session update failed.", e);
        }
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            log.debug("shiro session delete, but session doesn't exist.");
            return;
        }
        String key = session.getId().toString();
        cache.remove(key);
        log.debug("shiro session deleted, sessionId={}", session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        log.debug("shiro session getAll");
        Set<String> keys = cache.keys();
        Set<Session> sessions = new HashSet<>();
        if (CollectionUtils.isEmpty(keys)) {
            log.debug("shiro session getAll, there is no session active.");
            return sessions;
        }
        for (String key : keys) {
            byte[] value = cache.get(key);
            Session session = (Session) SerializationUtils.deserialize(value);
            sessions.add(session);
        }
        log.debug("shiro session getAll, sessions={}", sessions);
        return sessions;
    }
}
