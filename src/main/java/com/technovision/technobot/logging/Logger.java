package com.technovision.technobot.logging;


import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import com.technovision.technobot.TechnoBot;
import net.dv8tion.jda.api.EmbedBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Logger {
    private static final WebhookClient client = new WebhookClientBuilder(TechnoBot.getInstance().getBotConfig().getJson().getString("logs-webhook")).build();

    private Object obj;

    public Logger(Object object) {


        if(!object.getClass().isAnnotationPresent(Loggable.class)) System.out.println("Could not register "+object.getClass().getName()+"'s logger because the object is not annotated with Loggable.");
        else obj = object;
    }

    public void log(LogLevel level, String message) {
        String date = new SimpleDateFormat("hh:mm:ss").format(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime());

        client.send("["+date+"]"+
                " ["+obj.getClass().getDeclaredAnnotation(Loggable.class).display()+"] "+
                "["+level+"] "+message);
        System.out.println("["+date+"]"+
                " ["+obj.getClass().getDeclaredAnnotation(Loggable.class).display()+"] "+
                "["+level+"] "+message);
    }

    public enum LogLevel {
        SEVERE,WARNING,INFO
    }

}
