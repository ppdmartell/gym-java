/*
This example deals with Buffered streams and is based on [1], although the real explanation
is in the accepted answer in [2](stackoverflow). Basically Buffered streams allow you to make
expensive and several calls to the OS resources (every time you use read() method is expensive
and only reads 1 byte), and instead the purpose is to make a few and fill a buffer with every
call. So this way the amount of calls to the OS are less.
A way better explanation is in [2].

To demonstrate the example, there will be used a file named "text" with 3791413 bytes. We should
remember every character is a byte. This is one output for the optimized and not optimized
executions:

File was read with optimized[true] in 107 milliseconds.
File was read with optimized[false] in 4666 milliseconds.


Resources:
[1] https://www.youtube.com/watch?v=baHz_RmMt5I
[2] https://stackoverflow.com/a/18600383
[3] https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html
[4] https://docs.oracle.com/javase/8/docs/api/java/io/File.html
*/

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.time.LocalDateTime; //.now() [this is not used, ignore]

class App {

    private static final String filename = "text";
    private static final File file;

    static {
        file = new File(filename);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> read(false));
        executor.submit(() -> read(true));
        executor.shutdown();
    }

    /*
    If the param variable optimized is true, a BufferedInputStream would be used, otherwise it won't.
    */
    public static void read(boolean optimized) {
        long start = System.currentTimeMillis();
        InputStream is = null;
        try {
            if(optimized) is = new BufferedInputStream(new FileInputStream(file));
            else is = new FileInputStream(file);
            int[] fileArr = new int[(int) file.length()];
            for(int i = 0, temp = 0; (temp = is.read()) != -1; i++) {
                fileArr[i] = temp;
            }
        long end = System.currentTimeMillis();
            System.out.printf("File was read with optimized[%b] in %d milliseconds.%n", optimized, end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
