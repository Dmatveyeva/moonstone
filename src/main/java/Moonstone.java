import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class Moonstone {

    public static void main(String args[]) throws Exception{


        JDA jda;
        jda = new JDABuilder(AccountType.BOT).setToken("Njk0MjM0MTcwNDkwMDkzNzEw.XoIueg.SUNhnYUtwueWGx1mUTMryx1L8Lg").build();
        jda.getPresence().setStatus(OnlineStatus.ONLINE);

        jda.addEventListener(new Block());
    }
}

