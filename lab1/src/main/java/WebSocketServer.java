import org.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/action")
public class WebSocketServer {
    private static Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<WebSocketSession>());

    @OnMessage
    public void onMessage(Session session, String msg) {
        try {
            System.out.println("received msg " + msg + " from " + session.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject(msg);
        String command = json.getString("command");

        if (command.equals("fire")) {
            // get starting x and y
            int x = json.getInt("x");
            int y = json.getInt("y");
            int z = json.getInt("z");
            double vx = json.getDouble("vx");
            double vy = json.getDouble("vy");
            double vz = json.getDouble("vz");
            double gravity = json.getDouble("gravity");
            WebSocketSession webSocketSession = findSession(session);
            if (webSocketSession != null && webSocketSession.fireRunner == null) {
                webSocketSession.fire(x, y, z, vx, vy, vz, gravity);
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        sessions.add(new WebSocketSession(session));
        System.out.println("New session opened: " + session.getId());
    }

    @OnError
    public void error(Session session, Throwable t) {
        WebSocketSession webSocketSession = findSession(session);
        if (webSocketSession != null) {
            sessions.remove(webSocketSession);
        }
        System.err.println("Error on session " + session.getId());
    }

    @OnClose
    public void closedConnection(Session session) {
        WebSocketSession webSocketSession = findSession(session);
        if (webSocketSession != null) {
            sessions.remove(webSocketSession);
        }
        System.out.println("Session closed: " + session.getId());
    }

    private WebSocketSession findSession(Session session) {
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.session == session) {
                return webSocketSession;
            }
        }
        return null;
    }
}
