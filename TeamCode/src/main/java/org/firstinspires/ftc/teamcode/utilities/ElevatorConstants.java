package org.firstinspires.ftc.teamcode.utilities;


import com.acmerobotics.dashboard.config.Config;
import com.bylazar.ftcontrol.panels.configurables.annotations.Configurable;

@Configurable
public class ElevatorConstants {
    public static double transferHeight = 0;
    public static double clearanceHeight = 400;
    public static double sampleScoreHigh = 3300;
    public static double sampleScoreLow = 1000;
    public static double specimenReady = 1100;
    public static double specimenScore = 2290;
    public static double p = 0.0197;
    public static double i = 0.0;
    public static double d = 0.00015;
    public static double f = 0.0;
}
