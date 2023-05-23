package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        String url = "https://www.emoticonstext.com/";
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select(".cate a[href]");
        String urlChild, urlPart;
        Document doc1;
        Elements emjGroup;
        Elements emjText;
        for(Element link: links){
            urlPart = link.attr("href");
            urlChild = url + urlPart;

            File file = new File(urlPart);
            FileWriter writer = new FileWriter(file);
            doc1 = Jsoup.connect(urlChild).get();
            emjGroup = doc1.select(".emoticons");
            for(Element eg: emjGroup){
                writer.write(eg.select(".titletext").toString());

                emjText = doc1.select(".emoticon");
                for(Element et: emjText){
                    writer.write(et.toString());
                    writer.write("\n");
                }
            }
            writer.close();
        }

//        String url = "https://www.emoticonstext.com/";
//        Document doc = Jsoup.connect(url).get();
//        String html = doc.toString();
//
//        File file = new File("list1.html");
//        FileWriter writer = new FileWriter(file);
//        writer.write(html);
//        writer.close();

    }
}