package me.alextodea.testioapplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.Position;
import com.github.javaparser.ast.Node;
import me.alextodea.testioapplication.model.AppUser;
import me.alextodea.testioapplication.model.Exercise;
import me.alextodea.testioapplication.model.Submission;
import me.alextodea.testioapplication.plagiarism.*;
import me.alextodea.testioapplication.repository.AppUserRepository;
import me.alextodea.testioapplication.repository.ExerciseRepository;
import me.alextodea.testioapplication.repository.SubmissionRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubmissionService {

    private SubmissionRepository submissionRepository;
    private AppUserRepository appUserRepository;
    private ExerciseRepository exerciseRepository;
    private static final int MIN_TREE_MASS = 4;
    private static final double SIMILARITY_THRESHOLD = 0.91;

    public SubmissionService(SubmissionRepository submissionRepository, AppUserRepository appUserRepository, ExerciseRepository exerciseRepository) {
        this.submissionRepository = submissionRepository;
        this.appUserRepository = appUserRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Submission> getSubmissions(OidcUser oidcUser) {

        String sub = oidcUser.getAttribute("sub");
        Optional<AppUser> appUserOptional = appUserRepository.findByAuthProviderId(sub);

        if (appUserOptional.isPresent()) {
            AppUser submitter = appUserOptional.get();
            return submissionRepository.findBySubmitter(submitter);
        } else {
            AppUser appUser = new AppUser(sub);
            appUserRepository.save(appUser);
            return new ArrayList<>();
        }

    }

    public List<PlagiarismReport> getPlagiarismReports(OidcUser oidcUser) {
        AppUser appUser = new AppUser(oidcUser.getAttribute("sub"));
        Exercise exercise = new Exercise(appUser,
                "publicclassSearchUtils{publicstaticintbinarySearch(int[]arr,intx){intstart=0;intend=arr.length-1;while(start<=end){intmiddle=(start+end)/2;if(arr[middle]==x){returnmiddle;}elseif(x<arr[middle]){end=middle-1;}else{start=middle+1;}}return-1;}}",
                "Implement the binary search algorithm.",
                "classSearchUtilsTest{@Test@DisplayName(\\\"-1isreturnedifsearchedarrayisempty\\\")voidminusOneIsReturnedIfSearchedArrayIsEmpty(){int[]numbers={};assertThat(SearchUtils.binarySearch(numbers,99)).isEqualTo(-1);}@Test@DisplayName(\\\"0isreturnedifthesearchednumberistheonlynumberinthearray\\\")voidzeroIsReturnedIfTheSearchedNumberIsTheOnlyNumberInTheArray(){int[]numbers=newint[]{1};assertThat(SearchUtils.binarySearch(numbers,1)).isEqualTo(0);}@Test@DisplayName(\\\"-1isreturnedifsearchednumbercannotbefoundinthearray\\\")voidminusOneIsReturnedIfSearchedNumberCannotBeFoundInTheArray(){int[]numbers={1,2,3};assertThat(SearchUtils.binarySearch(numbers,5)).isEqualTo(-1);}@Test@DisplayName(\\\"Zeroisreturnedifsearchednumberisonthefirstpositioninaneven-lengtharray\\\")voidzeroIsReturnedIfSearchedNumberIsOnTheFirstPositionInTheArray(){int[]numbers={1,2,3};assertThat(SearchUtils.binarySearch(numbers,1)).isEqualTo(0);}@Test@DisplayName(\\\"Zeroisreturnedifsearchednumberisonthefirstpositioninanodd-lengtharray\\\")voidzeroIsReturnedIfSearchedNumberIsOnTheFirstPositionInAnOddLengthArray(){int[]numbers={1,2,3,4};assertThat(SearchUtils.binarySearch(numbers,1)).isEqualTo(0);}@Test@DisplayName(\\\"Thelengthofthearrayminusoneisreturnedifthesearchednumberisonthelastpositioninaneven-lengtharray\\\")voidtheLengthOfTheArrayMinusOneIsReturnedIfTheSearchedNumberIsOnTheLastPositionInAnEvenLengthArray(){int[]numbers={1,2,3};assertThat(SearchUtils.binarySearch(numbers,3)).isEqualTo(numbers.length-1);}@Test@DisplayName(\\\"Thelengthofthearrayminusoneisreturnedifthesearchednumberisonthelastpositioninanodd-lengtharray\\\")voidtheLengthOfTheArrayMinusOneIsReturnedIfTheSearchedNumberIsOnTheLastPositionInAnOddLengthArray(){int[]numbers={1,2,3,4};assertThat(SearchUtils.binarySearch(numbers,4)).isEqualTo(numbers.length-1);}@Test@DisplayName(\\\"Correctindexisreturnedifthesearchednumberisinthemiddleofaneven-lengtharray\\\")voidcorrectIndexIsReturnedIfTheSearchedNumberIsInTheMiddleOfAnEvenLengthArray(){int[]numbers={1,2,3,4,5,6};assertThat(SearchUtils.binarySearch(numbers,3)).isEqualTo(2);}@Test@DisplayName(\\\"Correctindexisreturnedifsearchednumberisinthemiddleofanodd-lengtharray\\\")voidcorrectIndexIsReturnedIfSearchedNumberIsInTheMiddleOfAnOddLengthArray(){int[]numbers={1,2,3,4,5,6,7};assertThat(SearchUtils.binarySearch(numbers,4)).isEqualTo(3);}}",
                "publicclassSearchUtils{publicstaticintbinarySearch(int[]arr,intx){//writeyoursolutionhere}}");
        List<Submission> submissions = new ArrayList<>();

        appUserRepository.save(appUser);
        exerciseRepository.save(exercise);

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class Main {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S1a {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S1b {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "        float sum = 0.0f; //C1'\n" +
                                                          "        float prod = 1.0f; //C'\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S1c {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}"));

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S2a {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float s = 0.0f; //C1\n" +
                                                          "        float p = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int j = 1; j <= n; j++) {\n" +
                                                          "            s = s + j;\n" +
                                                          "            p = p * j;\n" +
                                                          "            foo(s, p);\n" +
                                                          "        }\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S2b {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float s = 0.0f; //C1\n" +
                                                          "        float p = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int j = 1; j <= n; j++) {\n" +
                                                          "            s = s + j;\n" +
                                                          "            p = p * j;\n" +
                                                          "            foo(p, s);\n" +
                                                          "        }\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S2c {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        int sum = 0; //C1\n" +
                                                          "        int prod = 1;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S2d {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + (i * i);\n" +
                                                          "            prod = prod * (i * i);\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S3a {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod, n);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S3b {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n)\n" +
                                                          "    {\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S3c {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            if (n % 2 == 0) {\n" +
                                                          "                foo(sum, prod);\n" +
                                                          "            }\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S3d {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S3e {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            if (i % 2) {\n" +
                                                          "                sum += i;\n" +
                                                          "            }\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));

        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S4a {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S4b {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S4c {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        for (int i = 1; i <= n; i++) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "            prod = prod * i;\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));
        submissions.add(new Submission(exercise, appUser, "package me.alextodea;\n" +
                                                          "\n" +
                                                          "public class S4d {\n" +
                                                          "\n" +
                                                          "    public void sumProd(int n) {\n" +
                                                          "\n" +
                                                          "        float sum = 0.0f; //C1\n" +
                                                          "        float prod = 1.0f;\n" +
                                                          "\n" +
                                                          "        int i = 0;\n" +
                                                          "\n" +
                                                          "        while (i <= n) {\n" +
                                                          "            sum = sum + i;\n" +
                                                          "            prod = prod * i;\n" +
                                                          "            foo(sum, prod);\n" +
                                                          "            i++;\n" +
                                                          "        }\n" +
                                                          "\n" +
                                                          "    }\n" +
                                                          "\n" +
                                                          "}\n"));

        submissionRepository.saveAll(submissions);


        Map<String, List<DetectorEntry>> hashMap = groupSubmissionsIntoBuckets(submissions);
        hashMap.entrySet().removeIf(entry -> entry.getValue().size() == 1);
        Set<Map.Entry<String, List<DetectorEntry>>> entries = hashMap.entrySet();
        List<ClonePair> clonePairs = new ArrayList<>();


        entries.forEach(entry -> {
            List<DetectorEntry> comparisonBin = entry.getValue();

            for (int i = 0; i < comparisonBin.size(); i++) {
                for (int j = i + 1; j < comparisonBin.size(); j++) {

                    DetectorEntry firstDetectorEntry = comparisonBin.get(i);
                    DetectorEntry secondDetectorEntry = comparisonBin.get(j);

                    Node firstDetectorEntryNode = firstDetectorEntry.getNode();
                    Node secondDetectorEntryNode = secondDetectorEntry.getNode();

                    double similarity = Utils.computeSubtreeSimilarity(
                            firstDetectorEntryNode,
                            secondDetectorEntryNode);

                    if (similarity > SIMILARITY_THRESHOLD) {

                        //for each subtree s of i
                        firstDetectorEntryNode.stream().forEach(node -> {
                            //if isMember(Clones, s)
                            if (Utils.isMember(clonePairs, node)) {
                                //removeClonePair(Clones, s)
                                Utils.removeClonePair(clonePairs, node);
                            }
                        });

                        //for each subtree s of j
                        secondDetectorEntryNode.stream().forEach(node -> {
                            //if isMember(Clones, s)
                            if (Utils.isMember(clonePairs, node)) {
                                //removeClonePair(Clones, s)
                                Utils.removeClonePair(clonePairs, node);
                            }
                        });

                        clonePairs.add(new ClonePair(firstDetectorEntry, secondDetectorEntry));

                    }

                }

            }

        });


        Map<Long, Double> plagiarismPercentagePerSubmission = new HashMap<>();
        List<PlagiarismReport> plagiarismReports = new ArrayList<>();

        for (ClonePair clonePair : clonePairs) {

            Submission submission = clonePair.getLeftMapEntry().getSubmission();

            Optional<Position> submissionBegin = submission.getSolutionCompilationUnit().getBegin();
            Optional<Position> submissionEnd = submission.getSolutionCompilationUnit().getEnd();

            Optional<Position> cloneBegin = clonePair.getLeftMapEntry().getNode().getBegin();
            Optional<Position> cloneEnd = clonePair.getLeftMapEntry().getNode().getEnd();

            if (submissionBegin.isPresent() && submissionEnd.isPresent() && cloneBegin.isPresent() && cloneEnd.isPresent()) {
                int submissionSize = submissionEnd.get().line - submissionBegin.get().line;
                int cloneSize = cloneEnd.get().line - cloneBegin.get().line;

                double plagiarismPercentage = cloneSize * 100.0 / submissionSize;

                plagiarismPercentagePerSubmission.putIfAbsent(submission.getId(), plagiarismPercentage);

                if (plagiarismPercentage > plagiarismPercentagePerSubmission.get(submission.getId())) {
                    plagiarismPercentagePerSubmission.put(submission.getId(), plagiarismPercentage);
                }
            }


        }

        for (Long key : plagiarismPercentagePerSubmission.keySet()) {
            plagiarismReports.add(new PlagiarismReport(key, plagiarismPercentagePerSubmission.get(key)));
        }

        return plagiarismReports;

    }

    public static Map<String, List<DetectorEntry>> groupSubmissionsIntoBuckets(List<Submission> submissions) {
        Map<String, List<DetectorEntry>> hashMap = new TreeMap<>(new StringSizeComparator());
        HashCollector hashCollector = new HashCollector();

        for (Submission submission : submissions) {
            submission.getSolutionCompilationUnit().stream()
                    .forEach(node -> {
                        List<String> hashList = new ArrayList<>();
                        node.accept(hashCollector, hashList);
                        //if skeleton tree mass greater than or equal to minimum tree mass
                        if (hashList.size() >= MIN_TREE_MASS) {
                            DetectorEntry detectorEntry = new DetectorEntry(submission, node);
                            String key = String.join(";", hashList);
                            hashMap.putIfAbsent(key, new ArrayList<>());
                            hashMap.get(key).add(detectorEntry);
                        }
                    });
        }

        return hashMap;

    }
}
