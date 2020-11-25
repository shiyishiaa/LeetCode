import java.util.Arrays;

public class Question_948 {
    public static int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1;
        int score = 0;
        while (left <= right)
            if (P < tokens[left]) {
                if (score == 0 || left + 1 >= right) return score;
                score--;
                P += tokens[right];
                right--;
            } else {
                score++;
                P -= tokens[left];
                left++;
            }
        return score;
    }

    public static void main(String[] args) {
        int[] tokens = new int[]{
                100, 200,300,400
        };
        System.out.println(bagOfTokensScore(tokens, 200));
    }
}
