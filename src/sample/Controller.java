package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.ScaleTransition;
import javafx.animation.ScaleTransitionBuilder;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;


public class Controller {


    @FXML
    TextField calctf;

    @FXML
    TextField calctf3;
   @FXML TextField res;

   @FXML
   TextField operand;
   String operator;


   public void getres(ActionEvent e) throws ScriptException
   {
       Double first = Double.parseDouble(calctf.getText());
       Double second = Double.parseDouble(calctf3.getText());

       Double ans;
       switch (operator)
       {
           case "+":
               ans = first + second;
               if ((ans == Math.floor(ans)) && !Double.isInfinite(ans)) {
                   Integer i = ans.intValue();
                   res.setText(String.valueOf(i));
               }
               else
                    res.setText(String.valueOf(ans));
               break;
           case "-":
               ans = first - second;
               if ((ans == Math.floor(ans)) && !Double.isInfinite(ans)) {
                   Integer i = ans.intValue();
                   res.setText(String.valueOf(i));
               }
               else
                   res.setText(String.valueOf(ans));
               break;
           case "*":
               ans = first * second;
               if ((ans == Math.floor(ans)) && !Double.isInfinite(ans)) {
                   Integer i = ans.intValue();
                   res.setText(String.valueOf(i));
               }
               else
                   res.setText(String.valueOf(ans));
               break;
           case "/":
               ans = first / second;
               if ((ans == Math.floor(ans)) && !Double.isInfinite(ans)) {
                   Integer i = ans.intValue();
                   res.setText(String.valueOf(i));
               }
               else
                   res.setText(String.valueOf(ans));
               break;
       }

}

   public void Click(ActionEvent e)
   {
       Button b = (Button) e.getSource();
       operand.setText(b.getText());
       operator = (String)b.getText();
   }
}
