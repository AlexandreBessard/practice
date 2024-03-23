package designPatterns;

/*
The Facade Pattern provides a unified interface to a set
of interfaces in a subsystem. It defines a higher-level interface
 that makes the subsystem easier to use.

 Let's say we have a complex subsystem for a home
 theater system, including multiple components
 like the DVD player, projector, and sound system.
 We can create a facade to simplify the usage of these components.
 */
// Subsystem: DVD Player
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is on");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void off() {
        System.out.println("DVD Player is off");
    }
}

// Subsystem: Projector
class Projector {
    public void on() {
        System.out.println("Projector is on");
    }

    public void setInput(String input) {
        System.out.println("Setting input to: " + input);
    }

    public void off() {
        System.out.println("Projector is off");
    }
}

// Subsystem: Sound System
class SoundSystem {
    public void on() {
        System.out.println("Sound System is on");
    }

    public void setVolume(int volume) {
        System.out.println("Setting volume to: " + volume);
    }

    public void off() {
        System.out.println("Sound System is off");
    }
}

// Facade
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        dvdPlayer.on();
        projector.on();
        projector.setInput("DVD");
        soundSystem.on();
        soundSystem.setVolume(10);
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        dvdPlayer.off();
        projector.off();
        soundSystem.off();
    }
}
public class Facade {

    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();

        HomeTheaterFacade homeTheater =
                new HomeTheaterFacade(dvdPlayer, projector, soundSystem);

        // Watch a movie
        homeTheater.watchMovie("The Matrix");

        // End the movie
        homeTheater.endMovie();
    }
}
