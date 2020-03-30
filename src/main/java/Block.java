import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;


public class Block extends ListenerAdapter {
    public static String prefix = "-";
    public EmbedBuilder eb = new EmbedBuilder();
    public GoogleSheets list = new GoogleSheets();
    List<List<Object>> log = null;

    public Block() throws IOException, GeneralSecurityException {

    }

    public void film(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(1).get(0).toString() + " " + log.get(1).get(1)).setThumbnail(log.get(1).get(4).toString());
        eb.setColor(Color.decode(log.get(1).get(2).toString())).setImage(log.get(1).get(5).toString());
        eb.setDescription(mess);
        eb.addField("**Время:**", log.get(1).get(6).toString(), true).addField("**Место:**", log.get(1).get(7).toString(), true)
                .addField("**Организаторы :**", log.get(1).get(8).toString(), true)
                .addField("**В ПРОКАТЕ НА ЭТОЙ НЕДЕЛЕ:**", log.get(1).get(9).toString() + "\n" + log.get(1).get(10).toString() + "\n" + log.get(1).get(11).toString(), true);
        eb.setFooter(log.get(1).get(12).toString());
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }


    public void messurl(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(2).get(0).toString() + " " + log.get(2).get(1));
        eb.setColor(Color.decode(log.get(2).get(2).toString())).setImage(log.get(2).get(5).toString());
        eb.setDescription(mess);
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }

    public void messurl2(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(2).get(0).toString() + " " + log.get(2).get(1)).setThumbnail(log.get(1).get(4).toString());
        eb.setColor(Color.decode(log.get(2).get(2).toString())).setImage(log.get(2).get(5).toString());
        eb.setDescription(mess);
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }

    public void mess(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(2).get(0).toString() + " " + log.get(2).get(1));
        eb.setColor(Color.decode(log.get(2).get(2).toString()));
        eb.setDescription(mess);
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }

    public void mess2(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(2).get(0).toString() + " " + log.get(2).get(1)).setThumbnail(log.get(1).get(4).toString());;
        eb.setColor(Color.decode(log.get(2).get(2).toString()));
        eb.setDescription(mess);
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }

    public void event(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(3).get(0).toString() + " " + log.get(3).get(1)).setThumbnail(log.get(3).get(4).toString());
        eb.setColor(Color.decode(log.get(3).get(2).toString())).setImage(log.get(3).get(5).toString());
        eb.setDescription(mess);
        eb.addField("**Время:**", log.get(3).get(6).toString(), true).addField("**Место:**", log.get(3).get(7).toString(), true)
                .addField("**Организаторы :**", log.get(3).get(8).toString(), true)
                .addField("**В ПРОКАТЕ НА ЭТОЙ НЕДЕЛЕ:**", log.get(3).get(9).toString() + "\n" + log.get(3).get(10).toString() + "\n" + log.get(3).get(11).toString(), true);
        eb.setFooter(log.get(3).get(12).toString());
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }

    public void overEvent(GuildMessageReceivedEvent event, String id, String mess) throws IOException, GeneralSecurityException {
        log = list.getGoogleSheets("log");
        eb.setTitle(log.get(4).get(0).toString() + " " + log.get(4).get(1)).setThumbnail(log.get(4).get(4).toString());
        eb.setColor(Color.decode(log.get(4).get(2).toString())).setImage(log.get(4).get(5).toString());
        eb.setDescription(mess);
        eb.addField("**Время:**", log.get(4).get(6).toString(), true).addField("**Место:**", log.get(4).get(7).toString(), true)
                .addField("**Организаторы :**", log.get(4).get(8).toString(), true)
                .addField("**В ПРОКАТЕ НА ЭТОЙ НЕДЕЛЕ:**", log.get(4).get(9).toString() + "\n" + log.get(4).get(10).toString() + "\n" + log.get(4).get(11).toString(), true);
        eb.setFooter(log.get(4).get(12).toString());
        TextChannel textChannel = event.getGuild().getTextChannelById(id);
        textChannel.sendMessage(eb.build()).queue();
        eb.clear();
    }


    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        try {
            String messageSent = event.getMessage().getContentRaw();

            if (messageSent.equalsIgnoreCase(prefix + "film " + messageSent.replace(prefix + "film ", ""))) {
                String[] st = messageSent.split(" ");

                film(event, st[1], messageSent.replace(prefix + "film " + st[1], ""));

            }

            if (messageSent.equalsIgnoreCase(prefix + "mess " + messageSent.replace(prefix + "mess ", ""))) {
                String[] st = messageSent.split(" ");

                mess(event, st[1], messageSent.replace(prefix + "mess " + st[1], ""));

            }

            if (messageSent.equalsIgnoreCase(prefix + "messurl " + messageSent.replace(prefix + "messurl ", ""))) {
                String[] st = messageSent.split(" ");

                messurl(event, st[1], messageSent.replace(prefix + "messurl " + st[1], ""));

            }
            if (messageSent.equalsIgnoreCase(prefix + "mess2 " + messageSent.replace(prefix + "mess2 ", ""))) {
                String[] st = messageSent.split(" ");

                mess2(event, st[1], messageSent.replace(prefix + "mess2 " + st[1], ""));

            }

            if (messageSent.equalsIgnoreCase(prefix + "messurl2 " + messageSent.replace(prefix + "messurl2 ", ""))) {
                String[] st = messageSent.split(" ");

                messurl2(event, st[1], messageSent.replace(prefix + "messurl2 " + st[1], ""));

            }

            if (messageSent.equalsIgnoreCase(prefix + "event " + messageSent.replace(prefix + "event ", ""))) {
                String[] st = messageSent.split(" ");

                event(event, st[1], messageSent.replace(prefix + "event " + st[1], ""));

            }
            if (messageSent.equalsIgnoreCase(prefix + "overevent " + messageSent.replace(prefix + "overevent ", ""))) {
                String[] st = messageSent.split(" ");

                overEvent(event, st[1], messageSent.replace(prefix + "overevent " + st[1], ""));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }


    }
}