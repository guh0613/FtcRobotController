package org.firstinspires.ftc.teamcode.tuning;

import com.acmerobotics.roadrunner.ftc.DriveView;
import com.acmerobotics.roadrunner.ftc.DriveViewFactory;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.List;


@TeleOp
public class MyForwardPushTest extends LinearOpMode {
    private final DriveViewFactory dvf;

    private double avgPos(List<RawEncoder> es) {
        double sum = 0.0;
        for (Encoder e : es) {
            double pos = e.getPositionAndVelocity().position;
            if (pos >= 0.0) sum += pos;
            else sum -= pos;
        }
        return sum / es.size();
    }
    public MyForwardPushTest(DriveViewFactory dvf){
        this.dvf = dvf;
    }
    @Override
    public void runOpMode() throws InterruptedException {
        DriveView view = dvf.make(hardwareMap);

        for (DcMotorEx m: view.getMotors()) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
        waitForStart();

        double initAvgPos = avgPos(view.getForwardEncs());
        while (opModeIsActive()){
            telemetry.addData("ticks traveled", avgPos(view.getForwardEncs()) - initAvgPos);
            telemetry.update();
        }
    }
}
