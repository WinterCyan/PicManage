//package utility;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.input.MouseEvent;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class ClickHandler implements EventHandler<MouseEvent> {
//    private int delay = 300;    // 设置延时为300ms
//    @Override
//    public void handle(MouseEvent event) {
//        // TODO Auto-generated method stub
//        Button bt = (Button)(event.getSource());
//        int count = Integer.parseInt(bt.getId())+1;
//        bt.setId(count + "");
//        //System.out.println(count);
//        if(count%2 == 1) {
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    if(Integer.parseInt(bt.getId()) == count) {
//                        singleEvent();
//                        bt.setId("0");
//                    }
//                }
//
//            }, delay);
//        }else {
//            doubleEvent();
//            bt.setId("0");
//        }
//
//    }
//
//    private void singleEvent(){
//
//    }
//}
