import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.share.sampled.file.TAudioFileFormat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.net.MalformedURLException;
public class main {
	static int lengthOftobecut;
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		Scanner interval = new Scanner(System.in);
		int cutinterval = interval.nextInt();
		interval.close();
		
		String tobecut = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\lil-pump-gucci-gang-instrumentalfx.mp3";
		String tobecut2 = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\lil-pump-gucci-gang-instrumentalfx.wav";
		String dest = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\soundfiles\\something";
		String dest2 = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\soundfiles2\\everything";
		File tibecut = new File(tobecut);
		String tobesampled = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\Beethoven-MoonlightSonata.mp3";
		File tibesampled = new File(tobesampled);
		String tibesampled2 = "C:\\Users\\Chand\\OneDrive\\Documents\\GitHub\\SPLIXER\\soundfiles\\Beethoven-MoonlightSonata.wav";
		getDurationWithMp3Spi(tibecut);
		lengthOftobecut /= 1000;
		System.out.println(lengthOftobecut);
		for (int i = 0; lengthOftobecut > i; i++) {
			copyAudio(tobecut2, dest+i+".wav", i, cutinterval);
			System.out.println(i);
		}
		getDurationWithMp3Spi(tibesampled);
		lengthOftobecut /= 1000;
		for (int i = 0; lengthOftobecut > i; i++) {
			copyAudio(tibesampled2, dest2+i+".wav", i, cutinterval);
			System.out.println(i);
		}
		
		System.out.println(lengthOftobecut);
	}
	public static void copyAudio(String sourceFileName, String destinationFileName, int startSecond, int secondsToCopy) {
		AudioInputStream inputStream = null;
		AudioInputStream shortenedStream = null;
		try {
		  File file = new File(sourceFileName);
		  AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		  AudioFormat format = fileFormat.getFormat();
		  inputStream = AudioSystem.getAudioInputStream(file);
		  int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
		  inputStream.skip(startSecond * bytesPerSecond);
		  long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
		  shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
		  File destinationFile = new File(destinationFileName);
		  AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		} catch (Exception e) {
		  println(e);
		} finally {
		  if (inputStream != null) try { inputStream.close(); } catch (Exception e) { println(e); }
		  if (shortenedStream != null) try { shortenedStream.close(); } catch (Exception e) { println(e); }
		 }
	}
	private static void println(Exception e) {
		// TODO Auto-generated method stub
		
	}
	public static void getDurationWithMp3Spi(File file) throws UnsupportedAudioFileException, IOException {

	    AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
	    if (fileFormat instanceof TAudioFileFormat) {
	        Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
	        String key = "duration";
	        Long microseconds = (Long) properties.get(key);
	        int mili = (int) (microseconds / 1000);
	        lengthOftobecut = mili;
	    } else {
	        throw new UnsupportedAudioFileException();
	    }

	}
}
