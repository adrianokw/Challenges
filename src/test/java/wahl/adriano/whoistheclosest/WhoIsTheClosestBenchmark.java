package wahl.adriano.whoistheclosest;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@Fork(value = 2)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class WhoIsTheClosestBenchmark {

    private static final List<WhoIsTheClosest> whoIsTheClosestImplementations = List.of(
            new WhoIsTheClosest0(),
            new WhoIsTheClosest1(),
            new WhoIsTheClosest2(),
            new WhoIsTheClosest3(),
            new WhoIsTheClosest4(),
            new WhoIsTheClosest5(),
            new WhoIsTheClosest6());

    private static final String tenThousandCharacterString;

    static {
        try {
            tenThousandCharacterString = readOneLineTxt("WhoIsTheClosest10000CharacterString.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Integer> tenThousandQueries;

    static {
        try {
            tenThousandQueries = readOneLineCsv("WhoIsTheClosest10000Queries.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Integer> tenThousandAnswers;

    static {
        try {
            tenThousandAnswers = readOneLineCsv("WhoIsTheClosest10000Answers.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readOneLineTxt(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(
                WhoIsTheClosestBenchmark.class.getClassLoader().getResource(fileName)
                        .getFile()).getAbsolutePath()))) {
            return bufferedReader.readLine();
        }
    }

    private static List<Integer> readOneLineCsv(String fileName) throws IOException {
        List<Integer> records = new ArrayList<>();
        try (InputStream inputStream = WhoIsTheClosestBenchmark.class.getClassLoader().getResourceAsStream(fileName);
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

    @Benchmark
    public void findClosest_0() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(0).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_1() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(1).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_2() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(2).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_3() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(3).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_4() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(4).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_5() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(5).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }

    @Benchmark
    public void findClosest_6() {
        List<Integer> answerList = whoIsTheClosestImplementations.get(6).findClosest(tenThousandCharacterString, tenThousandQueries);

        assertThat(answerList).containsExactlyElementsOf(tenThousandAnswers);
    }
}