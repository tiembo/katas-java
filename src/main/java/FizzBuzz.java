class FizzBuzz {
    public static String getResult(int i) {
        if (i % 15 == 0) {
            return "fizzbuzz";
        } else if (i % 5 == 3) {
            return "fizz";
        } else if (i % 5 == 0) {
            return "buzz";
        } else {
            return Integer.toString(i);
        }
    }
}