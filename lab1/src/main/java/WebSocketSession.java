import org.json.JSONStringer;

import javax.websocket.Session;
import java.io.IOException;

// wrapper on Session object.
public class WebSocketSession {
    Session session;
    Thread fireRunner = null;

    WebSocketSession(Session session) {
        this.session = session;
    }

    double sleepingTime = 0.01;

    public void fire(int x, int y, int z, double vx, double vy, double vz, double gravity) {
        if (fireRunner != null) {
            return;
        }
        final CoreFly coreFly = new CoreFly(x, y, z, vx, vy, vz, gravity);
        fireRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    coreFly.calcNext(sleepingTime);
                    WebSocketSession.this.sendToClient(coreFly.x, coreFly.y, coreFly.z);

                    if (coreFly.y <= 0) {
                        System.out.println("end" + coreFly.y);
                        WebSocketSession.this.sendToClient("finish");
                        break;
                    }

                    try {
                        Thread.sleep((long) (sleepingTime * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                fireRunner = null;
            }
        }
        );

        fireRunner.setDaemon(true);
        fireRunner.start();
    }

    void sendToClient(double newX, double newY, double newZ) {
        String json = new JSONStringer()
                .object()
                .key("command")
                .value("new_position")
                .key("new_x")
                .value(newX)
                .key("new_y")
                .value(newY)
                .key("new_z")
                .value(newZ)
                .endObject()
                .toString();
        try {
            session.getBasicRemote().sendText(json);
        } catch (IOException ignored) {
        }
    }

    void sendToClient(String command) {
        String json = new JSONStringer()
                .object()
                .key("command")
                .value(command)
                .endObject()
                .toString();
        try {
            session.getBasicRemote().sendText(json);
        } catch (IOException ignored) {
        }
    }
}
