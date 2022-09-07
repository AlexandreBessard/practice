package topInterviewQuestion.amazon.arraysAndStrings;

//https://leetcode.com/explore/interview/card/amazon/76/array-and-strings/481/
public class IntegerToEnglishWords {

    public static void main(String[] args) {
        int n = 1234567890;
        //Output: "Twelve Thousand Three Hundred Forty Five"
        var obj = new IntegerToEnglishWords();
        //System.out.println(obj.numberToWords(n));
        System.out.println(obj.numberToWordsOtherSolution(1234));
    }

    //Other solution:
    //https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
    final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    final String[] THOUSANDS = {"Billion", "Million", "Thousand", ""};
    final int[] radix = {1000000000, 1000000, 1000, 1};

    public String numberToWordsOtherSolution(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < radix.length; i++) {
            if (num / radix[i] == 0)
                continue;
            sb.append(trans(num / radix[i])).append(THOUSANDS[i]).append(' ');
            num %= radix[i];
        }
        //trim() removes space character
        return sb.toString().trim();
    }

    private String trans(int num) {
        if (num == 0)
            return "";
        if (num < 20)
            return LESS_THAN_20[num] + " ";
        if (num < 100)
            return TENS[num / 10] + " " + trans(num % 10);

        return LESS_THAN_20[num / 100] + " Hundred " + trans(num % 100);
    }


    //Approach 1: Divide and Conquer:
    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";
        int billion = num / 1_000_000_000;
        int million = (num - billion * 1000000000) / 1000000;
        int thousand = (num - billion * 1000000000 - million * 1000000) / 1000;
        int rest = num - billion * 1000000000 - million * 1000000 - thousand * 1000;

        String result = "";
        if (billion != 0)
            result = three(billion) + " Billion";
        if (million != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(million) + " Million";
        }
        if (thousand != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(thousand) + " Thousand";
        }
        if (rest != 0) {
            if (!result.isEmpty())
                result += " ";
            result += three(rest);
        }
        return result;
    }

    public String three(int num) {
        int hundred = num / 100;
        int rest = num - hundred * 100;
        String res = "";
        if (hundred * rest != 0)
            res = one(hundred) + " Hundred " + two(rest);
        else if ((hundred == 0) && (rest != 0))
            res = two(rest);
        else if ((hundred != 0) && (rest == 0))
            res = one(hundred) + " Hundred";
        return res;
    }

    public String two(int num) {
        if (num == 0)
            return "";
        else if (num < 10)
            return one(num);
        else if (num < 20)
            return twoLessThan20(num);
        else {
            int tenner = num / 10;
            int rest = num - tenner * 10;
            if (rest != 0)
                return ten(tenner) + " " + one(rest);
            else
                return ten(tenner);
        }
    }

    public String one(int num) {
        switch (num) {
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        return "";
    }

    public String twoLessThan20(int num) {
        switch (num) {
            case 10:
                return "Ten";
            case 11:
                return "Eleven";
            case 12:
                return "Twelve";
            case 13:
                return "Thirteen";
            case 14:
                return "Fourteen";
            case 15:
                return "Fifteen";
            case 16:
                return "Sixteen";
            case 17:
                return "Seventeen";
            case 18:
                return "Eighteen";
            case 19:
                return "Nineteen";
        }
        return "";
    }

    public String ten(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";
        }
        return "";
    }

}
