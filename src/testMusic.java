import java.io.File;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.swing.Timer;
public class testMusic {
	private static MediaPlayer mp; // Don't declare this locally as the garbage collector will delete it while playing ¯\_(ツ)_/¯
	
	public static void main(String[] args) {
		Timer timer;
		//
		new JFXPanel(); // Needed to initialize the Java FX Toolkit ¯\_(ツ)_/¯
		File songFile = new File("./BGM/bgm.mp3");
		javafx.scene.media.Media song = new Media(songFile.toURI().toString()); // Converts the location of the file to the correct format, note that this will work no matter the location
		mp = new MediaPlayer(song);
		while(true){
			mp.play();
		}
		
	}
	
}