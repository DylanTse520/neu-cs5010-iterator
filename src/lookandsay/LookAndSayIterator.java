package lookandsay;

import java.math.BigInteger;

public class LookAndSayIterator implements RIterator<BigInteger> {

    static final String ONE_HUNDRED_NINES =
            "9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
    BigInteger current;
    BigInteger endValue;

    public LookAndSayIterator() {
        this.current = BigInteger.ONE;
        this.endValue = new BigInteger(ONE_HUNDRED_NINES);
    }

    public LookAndSayIterator(BigInteger startingSeed) {
        if (startingSeed.compareTo(BigInteger.ZERO) <= 0 || startingSeed.compareTo(new BigInteger(ONE_HUNDRED_NINES)) >= 0 || startingSeed.toString().contains("0")) {
            throw new IllegalArgumentException();
        }
        this.current = startingSeed;
        this.endValue = new BigInteger(ONE_HUNDRED_NINES);
    }

    public LookAndSayIterator(BigInteger startingSeed, BigInteger endValue) {
        if (startingSeed.compareTo(BigInteger.ZERO) <= 0 || startingSeed.compareTo(endValue) >= 0 || startingSeed.toString().contains("0")) {
            throw new IllegalArgumentException();
        }
        this.current = startingSeed;
        this.endValue = endValue;
    }

    @Override
    public BigInteger next() {
        BigInteger res = current;

        StringBuilder nextString = new StringBuilder();
        String currentString = current.toString();
        int startingIndex = 0;
        String currentDigit = currentString.substring(0, 1);
        for (int i = 1; i < currentString.length(); i++) {
            if (currentString.substring(i, i + 1).compareTo(currentDigit) != 0) {
                nextString.append(i - startingIndex);
                nextString.append(currentDigit);
                startingIndex = i;
                currentDigit = currentString.substring(i, i + 1);
            }
        }
        nextString.append(currentString.length() - startingIndex);
        nextString.append(currentDigit);
        current = new BigInteger(nextString.toString());

        return res;
    }

    @Override
    public BigInteger prev() {
        BigInteger res = current;

        StringBuilder prevString = new StringBuilder();
        String currentString = current.toString();
        for (int i = 0; i < currentString.length() - 1; i += 2) {
            int length = Integer.parseInt(currentString.substring(i, i + 1));
            String digit = currentString.substring(i + 1, i + 2);
            prevString.append(digit.repeat(Math.max(0, length)));
        }
        current = new BigInteger(prevString.toString());

        return res;
    }

    @Override
    public boolean hasNext() {
        return current.compareTo(endValue) <= 0;
    }

    @Override
    public boolean hasPrevious() {
        return current.toString().length() % 2 == 0;
    }

}
