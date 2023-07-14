/*
Same approach as the firewall example but in this case we are implementing a caching example with the Proxy Pattern.

Resources:
[1] https://www.youtube.com/watch?v=TS5i-uPXLs8
*/

import java.util.HashMap;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		VideoDownloader vd = new RegularVideoDownloader();
		//The download action will be executed 5 times, even when the video1 was already downloaded.
		vd.getVideo("video1");
		vd.getVideo("video2");
		vd.getVideo("video3");
		vd.getVideo("video1");
		vd.getVideo("video4");
		System.out.println("-----------------------------------------------");
		VideoDownloader proxy = new ProxyVideoDownloader();
		//The download action will be executed only 4 times, even when there are 10 requests. The rest 6 are served from cache (hash map).
		proxy.getVideo("video1");
		proxy.getVideo("video2");
		proxy.getVideo("video3");
		proxy.getVideo("video1");
		proxy.getVideo("video4");
		proxy.getVideo("video1");
		proxy.getVideo("video2");
		proxy.getVideo("video3");
		proxy.getVideo("video1");
		proxy.getVideo("video4");
	}
}

interface VideoDownloader {
	Video getVideo(String videoName);
}

class RegularVideoDownloader implements VideoDownloader {
	@Override
	public Video getVideo(String videoName) {
		System.out.println("Connecting to https://www.youtube.com/\n" +
                           "Downloading video: [" + videoName  + "]\n" +
                           "Retrieving video metadata\n");
		return new NormalVideo(videoName);
	}
}

class ProxyVideoDownloader implements VideoDownloader {
	private final VideoDownloader regular = new RegularVideoDownloader();  //Declaring them as private means that they can't be initialized to point another space in memory. But you can add elements or modify the object itself.
	private final Map<String, Video> cache = new HashMap<>();

	@Override
	public Video getVideo(String videoName) {
		if(!cache.containsKey(videoName)) cache.put(videoName, regular.getVideo(videoName));
		return cache.get(videoName);
	}
}

abstract class Video {
	private String videoName;

	public Video() {}
	public Video(String videoName) {
		this.videoName = videoName;
	}
}

class NormalVideo extends Video {

	public NormalVideo() {}
	public NormalVideo(String videoName) {
		super(videoName);
	}

}
