
public class CoreFly {
    // init info
    double vx, vy, vz;
    double startX;
    double startY;
    double startZ;
    double gravity;
    // calc info
    double x;
    double y;
    double z;


    CoreFly(double startX, double startY, double startZ, double vx, double vy, double vz, double gravity) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;

        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;
        x = startX;
        y = startY;
        z = startZ;
        this.gravity = gravity;
    }

    void calcNext(double t) {
        x = vx * t + x;
        z = vz * t + z;
        y = vy * t + y;
        vy = vy - gravity * t;
    }
}
