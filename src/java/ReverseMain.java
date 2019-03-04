public class ReverseMain {

    /**
     * 递归版reverse
     * @param str
     * @return
     */
    public static String reverse(String str) {
        if(str !=null && str.length()>1){
            return reverse(str.substring(1)) + str.charAt(0);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(reverse("abcd"));
    }
}
