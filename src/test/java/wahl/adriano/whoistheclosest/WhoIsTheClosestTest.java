package wahl.adriano.whoistheclosest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class WhoIsTheClosestTest {

    private static final List<WhoIsTheClosest> whoIsTheClosestImplementations = List.of(
            new WhoIsTheClosest0(),
            new WhoIsTheClosest1(),
            new WhoIsTheClosest2(),
            new WhoIsTheClosest3(),
            new WhoIsTheClosest4(),
            new WhoIsTheClosest5(),
            new WhoIsTheClosest6());

    private static Stream<Arguments> whoIsTheClosestImplementationsMethodSource() {
        return whoIsTheClosestImplementations.stream().map(Arguments::of);
    }

    private static String tenThousandCharacterString;

    private static List<Integer> tenThousandQueries;

    private static List<Integer> tenThousandAnswers;

    @BeforeAll
    static void setUp() throws IOException {
        tenThousandCharacterString = readOneLineTxt("WhoIsTheClosest10000CharacterString.txt");
        tenThousandQueries = readOneLineCsv("WhoIsTheClosest10000Queries.csv");
        tenThousandAnswers = readOneLineCsv("WhoIsTheClosest10000Answers.csv");
    }

    private static String readOneLineTxt(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(
                WhoIsTheClosestTest.class.getClassLoader().getResource(fileName)
                        .getFile()).getAbsolutePath()))) {
            return bufferedReader.readLine();
        }
    }

    private static List<Integer> readOneLineCsv(String fileName) throws IOException {
        List<Integer> records = new ArrayList<>();
        try (InputStream inputStream = WhoIsTheClosestTest.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line = reader.readLine();
            String[] values = line.split(",");
            for (String value : values) {
                records.add(Integer.parseInt(value));
            }
        }
        return records;
    }

    @ParameterizedTest(name = "findClosest({0})")
    @MethodSource("whoIsTheClosestImplementationsMethodSource")
    void findClosestWith64CharactersAnd7Queries(WhoIsTheClosest whoIsTheClosest) {
        String s = "ydqfugdyhndjrrawcqepehkfvynoypdbstxxgsgjzguhbsvimimexglrcpevdrcb";
        List<Integer> queries = List.of(14, 15, 39, 35, 43, 42, 0);

        List<Integer> answerList = whoIsTheClosest.findClosest(s, queries);

        List<Integer> expectedAnswers = List.of(-1, -1, 11, 34, 21, 4, 7);
        assertThat(answerList).containsExactlyElementsOf(expectedAnswers);
    }

    @ParameterizedTest(name = "findClosest({0})")
    @MethodSource("whoIsTheClosestImplementationsMethodSource")
    void findClosestWith1000CharactersAnd500Queries(WhoIsTheClosest whoIsTheClosest) {
        String s = "ktwhctcvxnjheajqxvpvybzdtapxhfzhdvgwncjkmbrdkfjtymcpepcakzwxcgcwuftayryfbmuwykrzhjahuxcnwjviapbdzwzhjymdyufjhffmbgrxkzqhgqxhuimuwangwrgruqxceqpybjkxjcftwzzcyjktzkpbcvrtmicdwenztnrjrwxqdhuuwzvgvzzdnmhugjdvrgxpnnjqjrikhbtnukhhwrevakrpprvpkrtpietpgjyintnmprmqgzfemautzijhpippvvizgbhqcacbugzcmeadeeprfdjzudymmgxkfvehfwwvkxamknzbrnztqmhcvzjrxguzbhvhpzmggcvpvdaqgbqymnxdexmayhtbyrjyhwbeemynpjahxgtdywwtqdzccqzqkvxbaewynvmqnmgewnukkutjhnthtkcikrnnfqfjykkrcianwreukyzygutvreiankjbugivjtceqqwzgkycavmeeudycvbyzqifhhfijnfehymqmmnticwnfnnunwrttjatjnmeuvekrrbaqzvaacxqjwdxikcbnvwcbivmitwrqthbdjfxffkiqmmardhpvdujpckjfzctqxkrqemrwzpvmwgzbyittnkqggkphutnppghbuamvpnxwdndwpmrbfnqbebwhzqeapvpqptuwzhqehuyrraxfkfzzvqqrwmcuvnnpnzyrfqkraaeucjnmdrbcmarqrvtwgmitktwfhmuakycjufhuemjyygqeidtcvxezxzkrztkugghkxnwqyxuzdvgzixzbyixuvernjaaagwkmhkjjqadkqxmkzbrfjycuhzqqpxgaydavfycrtprvencnpkegziqqkihzpykzhpjfjqgetzecmzqgkhukjdiybaqtqughqncjrdmcjkyypzvxdzjfhbgmmxavnbrtjvktnqxbgugqzxqwxbjxpketnkycunpvpnubzjpmgvywawhvdnikjiitaga";
        List<Integer> queries = List.of(953, 183, 319, 371, 630, 260, 188, 566, 95, 422, 414, 881, 426, 274, 650, 288, 566, 914, 608, 69, 430, 134, 81, 818, 562, 139, 932, 564, 6, 749, 585, 725, 79, 240, 588, 613, 207, 898, 649, 706, 48, 319, 13, 634, 807, 242, 271, 39, 314, 302, 501, 120, 774, 940, 228, 965, 51, 677, 797, 123, 532, 328, 613, 873, 505, 608, 789, 614, 302, 872, 954, 6, 936, 648, 84, 174, 781, 248, 437, 718, 188, 621, 177, 874, 369, 306, 258, 587, 355, 44, 437, 570, 785, 275, 812, 412, 153, 8, 780, 913, 633, 641, 776, 37, 179, 461, 766, 364, 596, 13, 316, 529, 944, 580, 919, 665, 458, 99, 951, 531, 689, 726, 644, 57, 33, 456, 74, 564, 883, 567, 597, 613, 504, 349, 902, 6, 693, 672, 671, 614, 942, 355, 615, 717, 599, 909, 545, 272, 579, 777, 290, 109, 290, 667, 305, 775, 825, 287, 895, 963, 706, 131, 704, 194, 124, 966, 415, 925, 631, 254, 994, 977, 451, 481, 793, 409, 712, 112, 26, 889, 583, 611, 898, 432, 531, 724, 292, 398, 890, 908, 358, 454, 450, 634, 749, 622, 297, 865, 185, 321, 262, 744, 879, 504, 583, 33, 151, 926, 521, 395, 578, 656, 189, 46, 927, 163, 54, 878, 516, 930, 671, 626, 868, 600, 494, 247, 365, 671, 708, 174, 808, 647, 920, 306, 775, 671, 921, 243, 209, 773, 784, 39, 405, 440, 956, 290, 543, 156, 845, 969, 407, 873, 732, 12, 422, 893, 944, 972, 198, 283, 707, 362, 493, 807, 349, 641, 140, 165, 606, 149, 203, 806, 506, 238, 755, 726, 872, 168, 639, 179, 979, 161, 829, 499, 117, 221, 201, 791, 976, 15, 187, 318, 284, 30, 428, 624, 763, 427, 905, 799, 868, 271, 437, 213, 186, 172, 813, 730, 389, 585, 318, 642, 189, 504, 778, 281, 83, 227, 311, 737, 488, 921, 210, 217, 751, 713, 436, 900, 869, 158, 536, 618, 111, 874, 626, 918, 198, 283, 467, 761, 772, 380, 886, 253, 974, 458, 211, 736, 387, 607, 139, 966, 675, 427, 713, 76, 911, 94, 776, 880, 443, 4, 674, 12, 682, 803, 750, 0, 28, 911, 69, 820, 892, 334, 397, 841, 697, 366, 642, 138, 996, 644, 328, 26, 120, 633, 538, 741, 677, 41, 90, 966, 964, 513, 935, 164, 986, 831, 146, 336, 95, 328, 250, 35, 567, 266, 528, 617, 885, 726, 508, 662, 208, 497, 492, 970, 445, 135, 201, 536, 521, 783, 206, 915, 764, 370, 384, 494, 167, 217, 605, 862, 687, 838, 765, 924, 837, 842, 364, 646, 874, 542, 365, 281, 166, 222, 38, 92, 228, 968, 533, 761, 559, 586, 686, 474, 778, 845, 275, 472, 296, 542, 786, 973, 512, 282, 117, 43, 210, 596, 448, 482, 713, 218, 717, 337, 247, 647, 347, 839, 197, 329, 749, 940, 498, 918, 298, 876, 311, 603, 706, 820, 212, 532, 771, 654, 327, 502, 650, 59);

        List<Integer> answerList = whoIsTheClosest.findClosest(s, queries);

        List<Integer> expectedAnswers = List.of(977, 211, 329, 378, 634, 254, 181, 574, 103, 425, 417, 879, 430, 269, 654, 303, 574, 925, 612, 78, 432, 131, 89, 815, 567, 149, 933, 537, 4, 741, 584, 766, 96, 247, 576, 665, 231, 914, 641, 707, 68, 329, 25, 630, 831, 238, 270, 44, 313, 246, 515, 113, 772, 944, 261, 970, 53, 675, 802, 119, 531, 355, 665, 878, 504, 612, 801, 620, 246, 876, 957, 4, 942, 674, 74, 177, 782, 250, 447, 717, 181, 616, 174, 850, 376, 317, 296, 572, 358, 39, 447, 565, 790, 264, 811, 416, 154, 16, 791, 939, 632, 640, 807, 50, 157, 455, 793, 379, 619, 25, 320, 522, 940, 593, 920, 671, 483, 108, 949, 532, 688, 731, 660, 79, 19, 446, 64, 537, 892, 562, 593, 665, 505, 331, 935, 4, 715, 690, 665, 620, 936, 358, 611, 718, 603, 905, 544, 273, 568, 774, 281, 110, 281, 656, 285, 779, 821, 282, 906, 887, 707, 134, 720, 193, 127, 962, 403, 914, 612, 251, 995, 953, 439, 480, 766, 419, 716, 94, 18, 915, 559, 615, 914, 430, 532, 729, 293, 402, 886, 894, 355, 465, 467, 630, 741, 632, 301, 872, 198, 325, 284, 754, 881, 505, 559, 19, 159, 922, 496, 390, 594, 652, 193, 38, 917, 144, 50, 873, 517, 938, 665, 587, 867, 595, 558, 240, 362, 665, 677, 177, 786, 658, 919, 317, 779, 665, 961, 239, 208, 770, 779, 44, 413, 442, 984, 281, 561, 143, 850, 975, 378, 878, 714, 52, 425, 896, 940, 982, 185, 277, 706, 365, 472, 831, 331, 640, 173, 190, 601, 155, 192, 771, 503, 242, 745, 731, 876, 197, 629, 157, 973, 158, 838, 495, 98, 215, 210, 780, 958, 118, 186, 290, 300, 22, 431, 644, 788, 443, 903, 796, 867, 270, 447, 204, 187, 181, 795, 718, 418, 584, 290, 633, 193, 505, 767, 290, 80, 234, 330, 762, 467, 961, 212, 163, 768, 694, 433, 919, 875, 161, 533, 635, 102, 850, 587, 896, 185, 277, 450, 760, 774, 379, 890, 237, 970, 483, 183, 743, 376, 627, 149, 962, 673, 443, 694, 70, 916, 112, 807, 849, 427, 6, 697, 52, 685, 798, 760, 39, 31, 916, 78, 819, 883, 298, 391, 854, 705, 360, 633, 147, 964, 660, 355, 18, 113, 632, 517, 749, 675, 21, 33, 962, 944, 499, 902, 170, 984, 852, 158, 317, 103, 355, 248, 58, 562, 245, 526, 623, 904, 731, 533, 654, 209, 489, 491, 974, 446, 133, 210, 533, 496, 755, 182, 932, 757, 390, 351, 558, 159, 163, 617, 869, 711, 829, 739, 934, 817, 826, 379, 672, 850, 539, 362, 290, 178, 223, 46, 82, 261, 916, 536, 760, 554, 602, 679, 466, 767, 850, 264, 461, 308, 539, 808, 971, 505, 280, 98, 32, 212, 619, 434, 452, 694, 238, 718, 347, 240, 658, 348, 840, 168, 319, 741, 944, 471, 896, 266, 872, 330, 599, 707, 819, 210, 531, 764, 650, 370, 507, 654, 85);
        assertThat(answerList).containsExactlyElementsOf(expectedAnswers);
    }

    @ParameterizedTest(name = "findClosest({0})")
    @MethodSource("whoIsTheClosestImplementationsMethodSource")
    void findClosestWith10000CharactersAnd10000QueriesRunning10Times(WhoIsTheClosest whoIsTheClosest) {
        for (int i = 0; i < 10; i++) {
            List<Integer> answerList = whoIsTheClosest.findClosest(tenThousandCharacterString, tenThousandQueries);

            assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
        }
    }
}