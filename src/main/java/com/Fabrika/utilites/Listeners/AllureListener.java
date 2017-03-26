package com.Fabrika.utilites.Listeners;

import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import com.automation.remarks.video.recorder.VideoRecorder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.Listeners;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;



public class AllureListener extends VideoListener {

    private static final Logger log = Logger.getLogger(AllureListener.class.getName());

    @Override
    public void onTestStart(ITestResult result) {
        if (shouldIntercept(result.getTestClass().getRealClass(), this.getClass())) {
            super.onTestStart(result);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (shouldIntercept(result.getTestClass().getRealClass(), this.getClass())) {
            super.onTestSuccess(result);
            Video video = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Video.class);
            if (VideoRecorder.conf().isVideoEnabled() && video != null){
                attachment();
            }

        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (shouldIntercept(result.getTestClass().getRealClass(), this.getClass())) {
            super.onTestFailure(result);
            Video video = result.getMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotation(Video.class);
            if (VideoRecorder.conf().isVideoEnabled() && video != null){
                attachment();
            }

        }
    }

    @Attachment(value = "video", type = "video/mp4")
    private byte[] attachment() {
        try {
            File video = VideoRecorder.getLastRecording();
            return Files.readAllBytes(Paths.get(video.getAbsolutePath()));
        } catch (IOException e) {
            log.error("Allure listener exception" + e.getMessage());
            return new byte[0];
        }
    }


    private void sendFile() throws IOException {
        File recording = VideoRecorder.getLastRecording();
        if (recording.exists()) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://localhost:8086/upload");

            MultipartEntity entity = new MultipartEntity();
            entity.addPart("data", new FileBody(recording));
            post.setEntity(entity);

            HttpResponse response = client.execute(post);
            System.out.println(response);
            recording.delete();
        }
    }

}
