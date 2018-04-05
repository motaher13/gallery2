package gallery2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Double.min;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLUE;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TilePane tile;
    @FXML
    private TextField txtfld;
    @FXML
    private TextField txt;
    
    ObservableList<Buttonn> mv = FXCollections.observableArrayList();
    ObservableList<Buttonn> vd = FXCollections.observableArrayList();
    ObservableList<Buttonn> ig = FXCollections.observableArrayList();
    ObservableList<Buttonn> ms = FXCollections.observableArrayList();
    ObservableList<Buttonn> all = FXCollections.observableArrayList();
    
    int b=0;
    
    //INITIALIZE
    public void initialize(URL url, ResourceBundle rb) {
        
        for(int i=70;i<=71;i++){
            char c=(char)i;
            String path=""+c+"://";
            String nn=""+c+"(dir)";
            File f=new File(path);
            if(f.exists())
                search(path,nn);
        }

    }
    
    @FXML
    private void sear(KeyEvent event)throws FileNotFoundException,IOException{
        if(event.getCode()==KeyCode.ENTER){
        tile.getChildren().clear();
        String s=txt.getText();
        //System.out.println(b);
        int ch=0;
        if(ch==0){
        for(Buttonn c : mv){
            //System.out.println(c.name);
            if(c.name.toLowerCase().contains(s.toLowerCase())){
                tile.getChildren().add(c);
                ch=1;
                
            }
        }}
        if(ch==0){
        for(Buttonn c : vd){
            //System.out.println(c.name);
            if(c.name.toLowerCase().contains(s.toLowerCase())){
                tile.getChildren().add(c);
                ch=1;
                
            }
        }}
        if(ch==0){
        for(Buttonn c : ig){
            //System.out.println(c.name);
            if(c.name.toLowerCase().contains(s.toLowerCase())){
                tile.getChildren().add(c);
                ch=1;
                
            }
        }}
        if(ch==0){
            
        for(Buttonn c : all){
            //System.out.println(c.name);
            if(c.name.toLowerCase().contains(s.toLowerCase())){
                tile.getChildren().add(c);
                
            }
        }}
        if(b==5){
        for(Buttonn c : ms){
            //System.out.println(c.name);
            if(c.name.toLowerCase().contains(s.toLowerCase())){
                tile.getChildren().add(c);
                ch=1;
                
            }
        }}
        
        
        //txt.setText("SEARCH");
    }
    }
    
    @FXML
    private void bck(ActionEvent event){
        txtfld.setText("URL");
        if(b==0){
            
        }
        if(b==1)
            mvi(new ActionEvent());
        
        if(b==2)
            vde(new ActionEvent());
        if(b==3)
            img(new ActionEvent());
        if(b==4)
            glr(new ActionEvent());
        if(b==5)
            msc(new ActionEvent());
        
        
    }
    @FXML
    private void mvi(ActionEvent event) {
        b=1;
        tile.getChildren().clear();
        for (Buttonn f : mv) {
            tile.getChildren().add(f);
        }
    }
    
    @FXML
    private void img(ActionEvent event) {
        tile.getChildren().clear();
        b=3;
        for (Buttonn f : ig) {
            tile.getChildren().add(f);
        }
    }

    @FXML
    private void vde(ActionEvent event) {
        b=2;
        tile.getChildren().clear();
        for (Buttonn f : vd) {
            tile.getChildren().add(f);
        }
    }

    @FXML
    private void glr(ActionEvent event) {
        b=4;
        tile.getChildren().clear();
        for (Buttonn f : all) {
            //System.out.println(f.v+" "+f.name);
            tile.getChildren().add(f);
        }
    }

    @FXML
    private void msc(ActionEvent event) {
        b=5;
        tile.getChildren().clear();
        for (Buttonn f : ms) {
            tile.getChildren().add(f);
        }
    }
    
    @FXML
    private void refresh(ActionEvent event){
        tile.getChildren().clear();
        StackPane ac=new StackPane();
        Scene sc=new Scene(ac,100,100);
        Stage stg = new Stage();
        stg.setWidth(200);
        stg.setHeight(200);
        stg.setScene(sc);
        stg.setTitle("REFRESHING.....");
        stg.show();
        for(int i=70;i<=71;i++){
            char c=(char)i;
            String path=""+c+"://";
            String nn=""+c+"(dir)";
            File f=new File(path);
            if(f.exists())
                search(path,nn);
        }
        stg.close();
    }

    //SEARCH EVERYTHING
    //
    void search(String path,String nn) {
        //System.out.println("1");
        File folder = new File(path);
        File[] listOfFiles1 = folder.listFiles();
        int i = 0;
        String nmf = folder.getName().substring(0, (int) min((double) folder.getName().length(), 15.0));
        for (final File f1 : listOfFiles1) {
            //System.out.println(f1.getName());
            //System.out.println("2");
            String nm = f1.getName().substring(0, (int) min((double) f1.getName().length(), 15.0));
            int b = 0;
            String fExtension = f1.getName().substring(f1.getName().indexOf(".") + 1, f1.getName().length());
            ImageView imageView = null;

            if (f1.length() >50000 && (fExtension.equals("jpg") || fExtension.equals("JPG") || fExtension.equals("png"))) {

                try {
                    imageView = new ImageView(new Image(new FileInputStream(new File(path + "\\" + f1.getName())), 100, 150, false, false));
                    //System.out.println("3");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Buttonn im = new Buttonn(path + "\\" + f1.getName(),f1.getName(), nm, imageView);
                im.setContentDisplay(ContentDisplay.TOP);
                im.setOnAction(e -> {
                    txtfld.setText(im.path);
                    viewimage(f1);
                });
                        
                ig.add(im);
                b = 1;
            } else if (fExtension.equals("mkv") || fExtension.equals("3gp") || fExtension.equals("mp4")) {

                try {
                    imageView = new ImageView(new Image(new FileInputStream(new File("src/vdi.jpg")), 100, 150, false, false));
                    //System.out.println("4");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Buttonn mm = new Buttonn(path + "\\" + f1.getName(),f1.getName(), nm, imageView);
                mm.setContentDisplay(ContentDisplay.TOP);
                mm.setOnAction(e ->{
                    txtfld.setText(mm.path);
                    start(mm.path);
                }
                );
                if (f1.length() > 200000000) {
                    mv.add(mm);
                } else {
                    vd.add(mm);
                }

                b = 1;
            }  else if (fExtension.equals("mp3")) {

                try {
                    imageView = new ImageView(new Image(new FileInputStream(new File("src/mu.jpg")), 100, 150, false, false));
                    //System.out.println("4");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Buttonn mm = new Buttonn(path + "\\" + f1.getName(),f1.getName(), nm, imageView);
                mm.setContentDisplay(ContentDisplay.TOP);
                mm.setOnAction(e ->{
                    txtfld.setText(mm.path);
                    start(mm.path);
                }
                );
                ms.add(mm);
            }

            if (b == 1 && i == 0) {
                //System.out.println("5");
                Buttonn bn = new Buttonn(path,nn, nmf, imageView);
                //System.out.println("6");
                bn.setContentDisplay(ContentDisplay.TOP);

                bn.setOnAction(e -> {
                    tile.getChildren().clear();
                    txtfld.setText(bn.path);
                    for (final File ff : listOfFiles1) {
                        //System.out.println("7");
                        String fileNam = ff.getName();
                        String fileExtensio = fileNam.substring(fileNam.indexOf(".") + 1, ff.getName().length());
                        if (ff.length()>50000&&(fileExtensio.equals("jpg") || fileExtensio.equals("JPG"))) {

                            ImageView imageVie = null;
                            try {
                                imageVie = new ImageView(new Image(new FileInputStream(new File(bn.path + "\\" + ff.getName())), 100, 150, false, false));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String ni = ff.getName().substring(0, (int) min((double) ff.getName().length(), 15.0));
                            Buttonn bnn = new Buttonn(bn.path + "\\" + ff.getName(),ff.getName(), ni, imageVie);
                            bnn.setContentDisplay(ContentDisplay.TOP);
                            bnn.setOnAction(efg -> {
                                viewimage(ff);
                            });
                            //bnn.setMinWidth(tile.getPrefWidth());
                            tile.getChildren().add(bnn);

                        }
                        else if (fileExtensio.equals("mkv") || fileExtensio.equals("mp4")|| fileExtensio.equals("3gp")) {

                            ImageView imageVie = null;
                            try {
                                imageVie = new ImageView(new Image(new FileInputStream(new File("src/vdi.jpg")), 100, 150, false, false));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String ni = ff.getName().substring(0, (int) min((double) ff.getName().length(), 15.0));
                            Buttonn bnn = new Buttonn(bn.path + "\\" + ff.getName(),ff.getName(), ni, imageVie);
                            bnn.setContentDisplay(ContentDisplay.TOP);
                            bnn.setOnAction(efg -> {
                                start(bnn.path);
                            });
                            //bnn.setMinWidth(tile.getPrefWidth());
                            tile.getChildren().add(bnn);

                        }
                    }
 
                });

                //bn.setMinWidth(tile.getPrefWidth());
                //tile.getChildren().add(bn);
                //gp.getItem(bn);
                all.add(bn);
                i = 1;
            }
            if (!f1.isFile() && !f1.getName().equals("$RECYCLE.BIN") && !f1.getName().equals("System Volume Information")) {
                //System.out.println(path+" "+f1.getName());
                search(path + "\\" + f1.getName(),f1.getName());
            }

        }
    }

    void viewimage(File ff) {
        try {
            BorderPane borderPane = new BorderPane();
            ImageView imageVi = new ImageView();
            Image image = new Image(new FileInputStream(ff));
            imageVi.setImage(image);
            imageVi.setStyle("-fx-background-color: BLACK");
            imageVi.setFitHeight(420);
            imageVi.setPreserveRatio(true);
            imageVi.setSmooth(true);
            imageVi.setCache(true);
            borderPane.setCenter(imageVi);
            borderPane.setStyle("-fx-background-color: BLACK");
            Stage newStage = new Stage();
            newStage.setWidth(900);
            newStage.setHeight(700);
            newStage.setTitle(ff.getName());
            Scene scene = new Scene(borderPane, Color.BLACK);
            newStage.setScene(scene);
            newStage.show();
        } catch (FileNotFoundException ef) {
            ef.printStackTrace();
        }
    }
    public static void start(String path) {
        Stage primaryStage=new Stage();
        //String path="F:\\videos\\b.mp4";
        File f=new File(path);
        primaryStage.setTitle("Embedded Media Player");
        
        Group root = new Group();
        Scene scene = new Scene(root, 600, 500);

        // create media player
        Media media = new Media (f.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e ->{
            mediaPlayer.stop();
            primaryStage.close();
        });
        primaryStage.show();
    }

}

class Buttonn extends Button {

    String path,name,v;

    Buttonn(String ph,String fname, String name, Node img) {
        super(name, img);
        v=name;
        this.name=fname;
        path = ph;
    }
}
class MediaControl extends BorderPane {

    private MediaPlayer mp;
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
    private HBox mediaBar;

    public MediaControl(final MediaPlayer mp) {
        this.mp = mp;
        setStyle("-fx-background-color: #bfc2c7;");
        mediaView = new MediaView(mp);
        Pane mvPane = new Pane() {
        };
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;");
        setCenter(mvPane);

        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);

        final Button playButton = new Button(">");

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MediaPlayer.Status status = mp.getStatus();

                if (status == MediaPlayer.Status.UNKNOWN || status == MediaPlayer.Status.HALTED) {
                    // don't do anything in these states
                    return;
                }

                if (status == MediaPlayer.Status.PAUSED
                        || status == MediaPlayer.Status.READY
                        || status == MediaPlayer.Status.STOPPED) {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                    }
                    mp.play();
                } else {
                    mp.pause();
                }
            }
        });
        mp.currentTimeProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });

        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });

        mp.setOnPaused(new Runnable() {
            public void run() {
                System.out.println("onPaused");
                playButton.setText(">");
            }
        });

        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });

        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });

        mediaBar.getChildren().add(playButton);
        // Add spacer
        Label spacer = new Label("   ");
        mediaBar.getChildren().add(spacer);

        // Add Time label
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        mediaBar.getChildren().add(volumeSlider);

        setBottom(mediaBar);
    }

    protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mp.getVolume()
                                * 100));
                    }
                }
            });
        }
    }

    private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
    }
    void stop(){
        mp.stop();
    }
}

