import java.util.ArrayList;
import java.util.List;

public class Question_6 {
    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        final int length = s.length();
        final int numEachBlock = 2 * numRows - 2;
        final int numBlock = length % numEachBlock == 0 ?
                length / numEachBlock :
                length / numEachBlock + 1;
        Character[][] charBlocks = new Character[numRows][(numRows - 1) * numBlock];
        all:
        for (int i = 0; i < numBlock; i++) {
            for (int j = 0; j < numEachBlock; j++) {
                char nowChar = s.charAt(i * numEachBlock + j);
                int nowBlockFirstCol = i * (numRows - 1);
                if (j + 1 > numRows) {
                    charBlocks[2 * numRows - j - 2][nowBlockFirstCol + j + 1 - numRows] = nowChar;
                } else {
                    charBlocks[j][nowBlockFirstCol] = nowChar;
                }
                if (i * numEachBlock + j + 1 == length) break all;
            }
        }
        StringBuilder returned = new StringBuilder();
        for (Character[] charBlock : charBlocks)
            for (Character character : charBlock)
                if (character != null)
                    returned.append(character);
        return returned.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3));
        Solution_6 k = new Solution_6();
    }
}

/**
 * 按行排序<p>
 * 思路<p>
 * 通过从左向右迭代字符串，我们可以轻松地确定字符位于Z字形图案中的哪一行。<p>
 * 算法<p>
 * 我们可以使用min(numRows,len(s))个列表来表示Z字形图案中的非空行。<p>
 * 从左到右迭代s，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。<p>
 * 只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
 */
class Solution_6 {
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}
