public class NBody
{
	private static String background = "images/starfield.jpg";

public static double readRadius(String file)
{
	In in = new In(file);
	in.readInt();
	double radius = in.readDouble();
	return radius;
} 

public static Planet[] readPlanets(String file)
{
	In in = new In(file);
	int planetNumber = in.readInt();
	double radius = in.readDouble();
	Planet[] planets = new Planet[planetNumber];
	for(int i = 0; i < planetNumber; i++)
	{
		double xPos = in.readDouble();
		double yPos = in.readDouble();
		double xVel = in.readDouble();
		double yVel = in.readDouble();
		double m = in.readDouble();
		String iFN = in.readString();
		planets[i] = new Planet(xPos, yPos, xVel, yVel, m, iFN);
	}

	return planets;
} 

private static void drawBackground(double radius)
{
	StdDraw.setScale(-radius, radius);
	StdDraw.clear();
	StdDraw.picture(0, 0, background);
	StdDraw.show();
	//StdDraw.pause(2000);
}

private static double[] calcForceX(Planet[] planets)
{
	double[] forceX = new double[planets.length];
	for(int i = 0; i < planets.length; i++)
	{
		for(int j = 0; j < planets.length; j++)
		{
			forceX[i] = planets[i].calcNetForceExertedByX(planets);
		}
	}
	return forceX;

}

private static double[] calcForceY(Planet[] planets)
{
	double[] forceY = new double[planets.length];
	for(int i = 0; i < planets.length; i++)
	{
		for(int j = 0; j < planets.length; j++)
		{
			forceY[i] += planets[i].calcNetForceExertedByY(planets);
		}
	}
	return forceY;

}


public static void main(String[] args)
{
	double time = Double.parseDouble(args[0]);
	double step = Double.parseDouble(args[1]);
	String filename = args[2];
	double radius = NBody.readRadius(filename);
	Planet planets[] = NBody.readPlanets(filename);
	NBody.drawBackground(radius);
	for(int i = 0; i < planets.length; i++)
	{
		planets[i].draw();
	}
	StdDraw.enableDoubleBuffering();

	double t = 0;
	while(t < time)
	{
		/* Calculate all force */
		double[] forceX = NBody.calcForceX(planets);
		double[] forceY = NBody.calcForceY(planets);


		/* Update all */
		for(int i = 0; i < planets.length; i++)
		{
			planets[i].update(step, forceX[i], forceY[i]);
		}	

		/* Draw all */
		NBody.drawBackground(radius);
		for(int i = 0; i < planets.length; i++)
		{
			planets[i].draw();
		}

		StdDraw.show();
	    StdDraw.pause(1);
		t += step;
	}
StdOut.printf("%d\n", planets.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < planets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}


}



}
