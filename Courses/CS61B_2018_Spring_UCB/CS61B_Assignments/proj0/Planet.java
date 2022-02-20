public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;


    /* The constructor of Body */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* Copy constructor */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double distance;
        distance = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double force;
        double distance;
        distance = this.calcDistance(p);
        force = 6.67e-11 * this.mass * p.mass / distance / distance;
        return force;
    }


    public double calcForceExertedByX(Planet p) {
        double forceX;
        double distance;
        distance = this.calcDistance(p);
        forceX = -calcForceExertedBy(p) / distance * (this.xxPos - p.xxPos);
        return forceX;
    }

    public double calcNetForceExertedByX(Planet[] p) {
        double forceX = 0;
        for (int i = 0; i < p.length; i++){
            if(!this.equals(p[i])){
                forceX += this.calcForceExertedByX(p[i]);
            }
        }
        return forceX;
    }


    public double calcForceExertedByY(Planet p) {
        double forceY;
        double distance;
        distance = this.calcDistance(p);
        forceY = -calcForceExertedBy(p) / distance * (this.yyPos - p.yyPos);
        return forceY;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double forceY = 0;
        for (int i = 0; i < p.length; i++){
            if(!this.equals(p[i])){
                forceY += this.calcForceExertedByY(p[i]);
            }
        }
        return forceY;
    }

    public void update(double step, double forceX, double forceY) {
        double aXX = forceX / this.mass;
        double aYY = forceY / this.mass;
        this.xxVel += aXX * step;
        this.yyVel += aYY * step;
        this.xxPos += this.xxVel * step;
        this.yyPos += this.yyVel * step;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }

}
