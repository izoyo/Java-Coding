import leetcode.towsun;

public class Main {

    public static void main(String[] args) {
        towsun answer = new towsun();
        int[] inp = {1, 2, 3, 4, 5};
        int[] ans = answer.twoSum_map(inp, 6);
        for(int element: ans)
        {
            System.out.println(element);
        }
        System.out.println("Hello World!");
    }
}
