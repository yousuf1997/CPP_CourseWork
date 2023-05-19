package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers;

public class MusicPlayer implements Receiver {
    public void playSong(String song) {
        System.out.println("The system is currently playing song of " + song);
    }
}
