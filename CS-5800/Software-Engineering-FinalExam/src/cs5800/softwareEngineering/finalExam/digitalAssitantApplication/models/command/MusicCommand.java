package cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.command;

import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.MusicPlayer;
import cs5800.softwareEngineering.finalExam.digitalAssitantApplication.models.receivers.Receiver;

public class MusicCommand implements Command {
    private final MusicPlayer musicPlayer;
    private final String song;

    public MusicCommand(Receiver musicPlayer, String song) {
        this.musicPlayer = (MusicPlayer) musicPlayer;
        this.song = song;
    }

    @Override
    public void execute() {
        musicPlayer.playSong(song);
    }
}
