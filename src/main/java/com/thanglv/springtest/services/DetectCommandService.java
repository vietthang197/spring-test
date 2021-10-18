package com.thanglv.springtest.services;

import com.thanglv.springtest.obj.GhtkCommand;

public class DetectCommandService {
    public static GhtkCommand detect(String command) {
        if (command != null) {
            String[] steps = command.split("\n");
            if (steps.length == 7) {
                GhtkCommand ghtkCommand = new GhtkCommand();

                // set size
                String step0 = steps[0];
                String[] arr = step0.replace("SIZE ", "").split(",");
                float heightPaper = Float.parseFloat(arr[0].replace(" mm", "").trim());  // height dài hơn width
                float widthPaper = Float.parseFloat(arr[1].replace(" mm", "").trim());
                ghtkCommand.setWidthPaper(widthPaper);
                ghtkCommand.setHeightPaper(heightPaper);

                // get gap (gap là khoảng cách giữa các đơn)
                String step1 = steps[1];
                arr = step1.replace("GAP ", "").split(",");
                double gap = Double.parseDouble(arr[0].replace(" mm", "").trim()); // chi lay 1 thong so cua gap
                ghtkCommand.setGap(gap);

                String step2 = steps[2];
                double direction = Double.parseDouble(step2.replace("DIRECTION ", "").trim());
                ghtkCommand.setDirection(direction);

                String step3 = steps[3];
                double speed = Double.parseDouble(step3.replace("SPEED ", "").trim());
                ghtkCommand.setSpeed(speed);
                return ghtkCommand;
            }
        }
        return null;
    }
}
