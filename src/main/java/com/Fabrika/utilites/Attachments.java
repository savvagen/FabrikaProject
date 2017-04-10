package com.Fabrika.utilites;


import ru.yandex.qatools.allure.annotations.Attachment;
import static com.google.common.io.Files.toByteArray;
import java.io.File;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Attachments {

    public static final String LOG_DIR = "src/main/logs";

    @Attachment//(value = "{0}", type = "video/mp4")
    public static byte[] attachLog(String name) {
        try {
            File mp4 = new File(LOG_DIR + "/" + name + ".log");
            return toByteArray(mp4);
        } catch (Exception ignored) {
            return new byte[0];
        }
    }

    /*@Attachment(value = "{0}", type = "text/html")
    public static byte[] attachHtml(final String name, final String templateName, final Map<String, Object> args) {
        final String outName = "target" + File.separator + "attachment" + COUNTER.incrementAndGet();
        try {
            execute(templateName, outName, args);
            return toByteArray(new File(outName));
        } catch (Exception ignored) {
            return new byte[0];
        }
    }*/


}
