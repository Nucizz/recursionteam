package com.example.recursionteamapp;

import android.content.Context;

import com.example.recursionteamapp.database.ArticleDB;
import com.example.recursionteamapp.database.MatchDB;
import com.example.recursionteamapp.database.TransactionDB;
import com.example.recursionteamapp.objects.Article;
import com.example.recursionteamapp.objects.Match;
import com.example.recursionteamapp.objects.Transaction;

import java.util.ArrayList;

public class Debug {

    public static ArrayList<Article> articleDummy = new ArrayList<>();
    public static ArrayList<Match> matchDummy = new ArrayList<>();

    public static void _init(Context ctx) {
        articleDummy.add(new Article(0,
                "https://cdn.nba.com/manage/2023/04/fox-driving-1-1536x864.jpg",
                "Playoffs Film Study: Kings overwhelm Warriors in transition, force Game 7",
                "The Sacramento Kings and Golden State Warriors are going to Game 7.\n" +
                        "\n" +
                        "The Warriors had a chance to close out their first-round series after winning three consecutive games. But the Kings went to San Francisco on Friday and took it to the champs, extending this series one more game with a convincing 118-99 victory.\n" +
                        "\n" +
                        "The Kings made a lineup adjustment, removing Alex Len from the rotation and playing Davion Mitchell for just 11 minutes, 13 fewer than he played in Game 5. Basically, they went all in on offense, and it paid off. After a sloppy first quarter in which the two teams combined to score just 48 points on 56 possessions (0.86 per), the Kings scored 93 points on 72 possessions (1.29 per) before the Warriors subbed out their starters, down 19 with 3:45 left in the fourth quarter.\n" +
                        "\n" +
                        "The Kings’ offense started with a clear effort to push the ball in transition. They led the league in the percentage of their possessions that were in transition in the regular season, according to Synergy tracking, and their 34 transition points on Friday were the most for either team in any game in this series.")
        );

        articleDummy.add(new Article(0,
                "https://o-cdf.sirclocdn.com/unsafe/o-cdn-cas.sirclocdn.com/parenting/images/Hanni_New_Jeans.width-800.format-webp.webp",
                "HANNI NewJeans Debuted!",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc luctus metus sit amet sollicitudin varius. Cras imperdiet elementum pellentesque. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aenean nec ullamcorper nunc. Curabitur lectus nunc, varius et ex vitae, sollicitudin consequat tellus.")
        );

        articleDummy.add(new Article(0,
                "https://cdn.nba.com/manage/2023/04/grizzlies.jpg?w=784&h=441",
                "Young Grizzlies must grow up after early playoff exit",
                "The Memphis Grizzlies must prove the NBA’s second-youngest team can learn the league’s toughest lesson that the playoffs are nothing like the regular season.\n" +
                        "\n" +
                        "It’s also time these young Grizzlies grow up.\n" +
                        "\n" +
                        "The Grizzlies’ third straight postseason — their second consecutive as the Western Conference’s No. 2 seed — ended Friday night in six games in the worst playoff loss in franchise history in 13 appearances. Anthony Davis, LeBron James and the Lakers humbled them after having to win the No. 7 seed in the play-in tournament.\n" +
                        "\n" +
                        "“This is probably that moment in time that’s going to be the ultimate wake-up call,” Memphis coach Taylor Jenkins said.\n" +
                        "\n" +
                        "“Are we going to really understand it’s the preparation in the offseason and the preparation in the season? It’s what you do at work, off the court. Clearly, things that we’ve got to control and just embrace it together.”\n" +
                        "\n" +
                        "Two-time All-Star point guard Ja Morant, who signed a five-year supermax contract last summer, will have the biggest chance to prove how much he can mature. Morant was suspended by the NBA for eight games in March and had almost as many off-court distractions as endorsement deals.\n" +
                        "\n" +
                        "Morant, who set the franchise single-season record by averaging 26.2 points a game, said he understands where he can grow as a leader.\n" +
                        "\n" +
                        "“I just got to be better with my decision-making,” Morant said. “That’s pretty much it. My off-the-court issues affected us as an organization pretty much. Just more disciplined.”\n" +
                        "\n" +
                        "The Grizzlies set a franchise mark by going 35-6 at home this season, the NBA’s best home record since Golden State won 36 games in the 2016-17 season. Memphis needs to play better on the road after going 16-25, better than only Golden State among the teams reaching the first round.\n" +
                        "\n" +
                        "Memphis lost all three playoff games in Los Angeles, with only Game 4 reaching overtime.\n" +
                        "\n" +
                        "This was the fifth time Memphis’ playoff hopes were hampered by injuries or suspensions.\n" +
                        "\n" +
                        "Morant missed Game 2 against the Lakers after aggravating his bruised right hand, while Dillon Brooks was ejected in Game 3 for striking James in the groin. Luke Kennard, the NBA’s top 3-point shooter, missed Game 6 with an injured shoulder.\n" +
                        "\n" +
                        "The Grizzlies also didn’t have starting center Steven Adams, their oldest player, or key reserve Brandon Clarke because of season-ending injuries. Adams hurt his right knee in late January, and Clarke tore his left Achilles tendon in early March.\n" +
                        "\n" +
                        "The Lakers dominated the Grizzlies on the boards.\n" +
                        "\n" +
                        "“It would be dumb to say that (injuries) didn’t play a part in it,” third-year guard Desmond Bane said. ”(Adams) was a part of what we did all season along. Obviously (Clarke) was, too.”")
        );


        matchDummy.add(new Match(0,
                "Sunday, 30 April 2023",
                "19:00 WIB",
                "GBK Arena Senayan Jakarta",
                "L.A. Lakers",
                "https://ssl.gstatic.com/onebox/media/sports/logos/4ndR-n-gall7_h3f7NYcpQ_96x96.png",
                "Binus Recursion",
                "https://cdn.discordapp.com/attachments/836993835372380213/1101896293107900529/Logo_Team.png",
                389000.0)
        );

        matchDummy.add(new Match(0,
                "Monday, 1 May 2023",
                "15:00 WIB",
                "Tokyo Dome Arena",
                "Pheonix Suns",
                "https://ssl.gstatic.com/onebox/media/sports/logos/pRr87i24KHWH0UuAc5EamQ_96x96.png",
                "Binus Recursion",
                "https://cdn.discordapp.com/attachments/836993835372380213/1101896293107900529/Logo_Team.png",
                4200000.0)
        );

        ArticleDB articleDB = new ArticleDB(ctx);
        MatchDB matchDB = new MatchDB(ctx);

        for (Article article : articleDummy) {
            articleDB.addArticle(article);
        }

        for (Match match : matchDummy) {
            matchDB.addMatch(match);
        }

    }

}
