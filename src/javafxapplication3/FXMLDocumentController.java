/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;


public class FXMLDocumentController implements Initializable {
    private MediaPlayer mediaplayer;
    
    @FXML
    private MediaView mediaview;
    
    private String filepath;
    @FXML
    private Slider sound;
    @FXML
    private Slider seekslider;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser filechooser=new FileChooser();
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select a file(*.mp4)(*.mp3)","*.mp4","*.mp3");
        filechooser.getExtensionFilters().add(filter);
        File file=filechooser.showOpenDialog(null);
        filepath=file.toURI().toString();
        
        if(filepath!=null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            DoubleProperty width =mediaview.fitWidthProperty();
            DoubleProperty hight =mediaview.fitHeightProperty();
            
            width.bind(Bindings.selectDouble(mediaview.sceneProperty(),"width"));
            hight.bind(Bindings.selectDouble(mediaview.sceneProperty(),"hight"));
            
            sound.setValue(mediaplayer.getVolume() * 100);
            sound.valueProperty().addListener((Observable observable) -> {
                mediaplayer.setVolume(sound.getValue()/100);
            });
            
            mediaplayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    seekslider.setValue(newValue.toSeconds());
                }           
            });
            
            seekslider.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    mediaplayer.seek(Duration.seconds(seekslider.getValue()));
                }                       
            });
          
                  
            mediaplayer.play();
                  
        }       
    }
    
    @FXML
    private void playvideo(ActionEvent event)
    {
        mediaplayer.play();
        mediaplayer.setRate(1);
        
    }
    
    @FXML
    private void pausevideo(ActionEvent event)
    {
        mediaplayer.pause();
    }
     @FXML
    private void stopvideo(ActionEvent event)
    {
        mediaplayer.stop();
    }
     @FXML
    private void fastervideo(ActionEvent event)
    {
        mediaplayer.setRate(2);
    }
     @FXML
    private void fastvideo(ActionEvent event)
    {
         mediaplayer.setRate(1.5);
    }
     @FXML
    private void slowervideo(ActionEvent event)
    {
         mediaplayer.setRate(0.5);
    } @FXML
    private void slowvideo(ActionEvent event)
    {
         mediaplayer.setRate(0.75);
    }
     @FXML
    private void exitvideo(ActionEvent event)
    {
         System.exit(0);
    }
    
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private static class ChangeListenerImpl implements ChangeListener<Duration> {

        public ChangeListenerImpl() {
        }

        @Override
        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private class ChangeListenerImpl1 implements ChangeListener<Duration> {

        public ChangeListenerImpl1() {
        }

        @Override
        public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
            seekslider.setValue(newValue.toMillis());
            
        }
    }
    
}
