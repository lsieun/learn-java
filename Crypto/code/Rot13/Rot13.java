public class Rot13 {
    public static void main(String[] args) {
        String str = "Why did the chicken cross the road? Gb trg gb gur bgure fvqr!";
        String value = rot13(str);
        System.out.println(value);
    }

    public static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if (c >= 'a' && c <= 'm') c += 13;
            else if (c >= 'A' && c <= 'M') c += 13;
            else if (c >= 'n' && c <= 'z') c -= 13;
            else if (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }
}
