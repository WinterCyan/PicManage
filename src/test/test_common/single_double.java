package test.test_common;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class single_double extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button bt1 = new Button("bt1");
        Button bt2 = new Button("bt2");

        bt1.setOnAction(new A());
        bt1.setId("0");

        bt2.setOnAction(new A());
        bt2.setId("0");

        Pane pane = new HBox();
        pane.getChildren().addAll(bt1, bt2);
        Scene scene = new Scene(pane, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    //主要代码
    class A implements EventHandler<ActionEvent>{
        private int delay = 300;    // 设置延时为300ms
        @Override
        public void handle(ActionEvent event) {
            // TODO Auto-generated method stub
            Button bt = (Button)(event.getSource());
            int count = Integer.parseInt(bt.getId())+1;
            bt.setId(count + "");
            //System.out.println(count);
            if(count%2 == 1) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        if(Integer.parseInt(bt.getId()) == count) {
                            mouseSingleClicked(event);
                            bt.setId("0");
                        }
                    }

                }, delay);
            }else {
                mouseDoubleClicked(event);
                bt.setId("0");
            }

        }

        public void mouseSingleClicked(ActionEvent e) {
            System.out.println("单击");
        }


        public void mouseDoubleClicked(ActionEvent e) {
            System.out.println("双击");
        }

    }
}
