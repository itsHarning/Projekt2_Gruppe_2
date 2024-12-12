public class AsciiPrinter {
    static final String RESET = "\u001B[0m";
    static final String YELLOW = "\033[0;33m";  // YELLOW
    static final String CYAN = "\033[0;36m";    // CYAN

    public static void asciiPrint(){

        System.out.println(YELLOW+
                        "                                                          ...                             \n" +
                        "                                           .:..         ..=.                              \n" +
                        "                             ....          .=+..  .:.  .:+-   ....                        \n" +
                        "                              ..=...  ......++++..-++..=++=...=..                         \n" +
                        "                               ..+++-...++++++=-:.....:-=+++++=.......                    \n" +
                        "                                 .++++++=.....            ....=+++=:."+CYAN+"..:::::.......:=**+..\n" +
                        YELLOW+"                             . ...=++:.                   ....-"+CYAN+"*******************-...-*#.\n" +
                        YELLOW+"                     .....   ..+++=..                  "+CYAN+"...***********+.-****-...:######...\n" +
                        YELLOW+"                     ...=++++++++..                 "+CYAN+"..=****************+.. ++..######-..    \n" +
                        YELLOW+"                         ..:+++-.                "+CYAN+"..=****************... ..######:.        \n" +
                        YELLOW+"                         ....+:.     "+CYAN+"........::+#***************#..  ..######+..          \n" +
                        YELLOW+"                          .:+=.   "+CYAN+".:*************************#*.  ..*######..             \n" +
                        YELLOW+"                    .  ...-++..  "+CYAN+".==--=*********************#.. .=######+."+YELLOW+"+=.. .          \n" +
                        YELLOW+"                  ..-=+++++++..        "+CYAN+"..*****************#*..:########.."+YELLOW+".+++++++-..      \n" +
                        YELLOW+"                       ....++..      "+CYAN+"..=*****************#:.#######**#.."+YELLOW+"..++:...          \n" +
                        YELLOW+"                        ..-++.    "+CYAN+"...**********#********########****#.. "+YELLOW+".-+++=...         \n" +
                        CYAN+"*-...                 "+YELLOW+"..===+++.."+CYAN+"..-*********###+******#######:.****#.. "+YELLOW+"..+=:.....         \n" +
                        CYAN+".******+: .........-.     "+YELLOW+"..++"+CYAN+".:*********##=. .*****#####+.....***-.. "+YELLOW+"..+++..             \n" +
                        CYAN+" ..*********+-****#..   ....+********##:.. ..-****###+..     .**:.   "+YELLOW+"..+++++++:...        \n" +
                        CYAN+"   . .:*##*********++***********#+......+####**#*.. .         ..    "+YELLOW+".=++..... ......      \n" +
                        CYAN+"          ..  ...=##+:... .....:=##########-*:..                 "+YELLOW+"..=+++++..               \n" +
                        CYAN+ "                    .......::--:::...::...                    "+YELLOW+"..:+++.......               \n" +
                        "                               ..+=....+++:... .      ......-+++++++-.                    \n" +
                        "                              .:..     ++=-=+++++++++++++++++++....=+=.                   \n" +
                        "                                      ... ..++++...++....++......   ..+..                 \n" +
                        "                                          ..++:.  ....  ..+           ...                 \n" +
                        "                                          ..+..          ...                              \n" +
                        "                                          ..."+RESET
        );
    }

    public static void main(String[] args) {
        asciiPrint();
    }
}
